package com.gopomelo.projects.ananda.landsourcing.cfg;

import java.io.InputStream;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Configuration
public class FirebaseConfig {

	private static final Logger LOG = LoggerFactory.getLogger(FirebaseConfig.class);

	@Value("${ananda.firebase.database.url:https://gcp-labs-nk.firebaseio.com}")
	private String databaseUrl;

	@Value("${ananda.config.path:service-accnt-gcp-labs-nk.json}")
	private String configPath;

	@Bean
	public DatabaseReference firebaseDatabse() {
		DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
		return firebase;
	}

	@Bean
	public GoogleCredential googelCredentialScope() {
		GoogleCredential scoped = null;
		try {
			InputStream serviceAccount = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);
			GoogleCredential googleCred = GoogleCredential.fromStream(serviceAccount);
			scoped = googleCred.createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.database",
					"https://www.googleapis.com/auth/userinfo.email"));
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return scoped;
	}

	@PostConstruct
	public void init() {

		/**
		 * https://firebase.google.com/docs/server/setup
		 * 
		 * Create service account , download json
		 */
		InputStream serviceAccount = FirebaseConfig.class.getClassLoader().getResourceAsStream(configPath);
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(serviceAccount)).setDatabaseUrl(databaseUrl).build();
		FirebaseApp.initializeApp(options);

	}
}
