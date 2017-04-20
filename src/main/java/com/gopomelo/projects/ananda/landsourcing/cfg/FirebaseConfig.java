package com.gopomelo.projects.ananda.landsourcing.cfg;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@Configuration
public class FirebaseConfig {

	@Bean
	public DatabaseReference firebaseDatabse() {
		DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
		return firebase;
	}

	@Value("${ananda.firebase.database.url:https://gcp-labs-nk.firebaseio.com}")
	private String databaseUrl;

	@Value("${ananda.config.path:service-accnt-gcp-labs-nk.json}")
	private String configPath;

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
