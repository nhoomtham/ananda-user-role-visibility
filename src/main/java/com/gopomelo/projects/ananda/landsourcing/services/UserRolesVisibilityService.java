package com.gopomelo.projects.ananda.landsourcing.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import lombok.extern.slf4j.Slf4j;

/**
 * A class to interactions with the FIREBASE database
 *
 */
@Slf4j
@Component
@CacheConfig(cacheNames = "objects")
public class UserRolesVisibilityService {

	@Autowired
	private GoogleCredential googelCredentialScope;

	@Autowired
	private String firebaseDBUrl;

	@Autowired
	private String firebaseUserRolesPath;

	private static final Set<String> ROLES = new HashSet<>(Arrays.asList("LD", "LS", "BD", "MI", "DD", "RSK"));
	private static final Set<String> OBJECTS = new HashSet<>(Arrays.asList("actions", "columns", "formFields"));

	/**
	 * @param roleName:
	 * @param objectName:
	 * @throws IOException
	 */
	@Cacheable
	public ResponseEntity<String> getObjects(String roleName, String objectName) throws IOException {

		ResponseEntity<String> result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (!ROLES.contains(roleName.toUpperCase()))
			return result;
		if (!OBJECTS.contains(objectName))
			return result;

		log.debug("start=====UserRolesVisibilityService.getObjects()");

		final String uri = firebaseDBUrl + firebaseUserRolesPath + "/" + roleName.toUpperCase() + "/" + objectName
				+ ".json";

		log.debug("=====uri[" + uri + "]");

		RestTemplate restTemplate = new RestTemplate();

		googelCredentialScope.refreshToken();
		String accessToken = googelCredentialScope.getAccessToken();
		log.debug("====accessToken[" + accessToken + "]");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Bearer " + accessToken);

		log.debug("====headers[" + headers + "]");

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

		log.debug("====restTemplate.getForObject.result[" + result.getBody() + "]");

		return result;
	}

}
