package com.gopomelo.projects.ananda.landsourcing.cfg;

import java.io.InputStream;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseCredentials;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FirebaseConfig {

	// value will be overridden from application.properties file
	@Value("${ananda.firebase.database.url:https://gcp-labs-nk.firebaseio.com}")
	private String fbDBUrl;

	// https://ananda-cloud.firebaseio.com/rest/saving-data/user_role/roles
	@Value("${ananda.firebase.user.roles.path:/rest/saving-data/user_role/roles}")
	private String fbUserRolesPath;

	// value will be overridden from application.properties file
	@Value("${ananda.serviceaccount.json:dev-serviceaccount.json}")
	private String serviceAccountJson;

	@Bean
	public String firebaseDBUrl() {
		return fbDBUrl;
	}

	@Bean
	public String firebaseUserRolesPath() {
		return fbUserRolesPath;
	}

	@Bean
	public DatabaseReference firebaseDatabse() {
		DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
		return firebase;
	}

	@Bean
	public GoogleCredential googelCredentialScope() {
		GoogleCredential scoped = null;
		try {
			InputStream serviceAccount = FirebaseConfig.class.getClassLoader().getResourceAsStream(serviceAccountJson);
			GoogleCredential googleCred = GoogleCredential.fromStream(serviceAccount);
			scoped = googleCred.createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.database",
					"https://www.googleapis.com/auth/userinfo.email"));
		} catch (Exception e) {

			log.error(e.getMessage(), e);
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
		InputStream serviceAccount = FirebaseConfig.class.getClassLoader().getResourceAsStream(serviceAccountJson);
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredential(FirebaseCredentials.fromCertificate(serviceAccount)).setDatabaseUrl(fbDBUrl).build();
		FirebaseApp.initializeApp(options);

	}
}
