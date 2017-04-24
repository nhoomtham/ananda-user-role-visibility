package com.gopomelo.projects.ananda.landsourcing.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.gopomelo.projects.ananda.landsourcing.controllers.vo.UserRoleVO;

/**
 * A class to interactions with the FIREBASE database
 *
 */
@Controller
public class UserRoleColumnViewFBController {

	private static final Logger LOG = Logger.getLogger(UserRoleColumnViewFBController.class);

	@Autowired
	private DatabaseReference firebaseDatabse;

	@Autowired
	private GoogleCredential googelCredentialScope;

	@RequestMapping(method = RequestMethod.GET, value = "/userrolesfb/k1")
	public ResponseEntity<String> findRoleColumnViewRTMP() {
		ResponseEntity<String> result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		try {

			LOG.info("start=====UserRoleColumnViewFBController.findRoleColumnViewRTMP()");

			final String uri = "https://gcp-labs-nk.firebaseio.com/k1.json";
			RestTemplate restTemplate = new RestTemplate();

			// InputStream serviceAccount =
			// UserRoleColumnViewFBController.class.getClassLoader()
			// .getResourceAsStream("service-accnt-gcp-labs-nk.json");
			// GoogleCredential googleCred =
			// GoogleCredential.fromStream(serviceAccount);
			// GoogleCredential scoped = googleCred
			// .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.database",
			// "https://www.googleapis.com/auth/userinfo.email"));
			googelCredentialScope.refreshToken();
			String accessToken = googelCredentialScope.getAccessToken();
			LOG.info("====accessToken[" + accessToken + "]");

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer " + accessToken);

			LOG.info("====headers[" + headers + "]");

			HttpEntity<String> entity = new HttpEntity<String>(headers);
			result = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);

			LOG.info("====restTemplate.getForObject.result[" + result.getBody() + "]");

		} catch (Exception ex) {
			// TODO
			LOG.info(ex.getMessage(), ex);
			result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/userrolesfb/{roleId}/columns")
	public String findRoleColumnView(@PathVariable long roleId) {
		// List<UserRoleColumnViewVO> result = new ArrayList<>();

		try {

			LOG.info(firebaseDatabse.getPath().toString());

			firebaseDatabse.child("k1").addListenerForSingleValueEvent(new ValueEventListener() {

				@Override
				public void onDataChange(DataSnapshot dataSnapshot) {
					// TODO Auto-generated method stub
					LOG.info(
							"UserRoleColumnViewFBController.findRoleColumnView(...).new ValueEventListener() {...}.onDataChange()");
					LOG.info("+++++++++childCount[" + dataSnapshot.getChildrenCount() + "]+++++++++");
					LOG.info("+++++++++getKey[" + dataSnapshot.getKey() + "]+++++++++");

				}

				@Override
				public void onCancelled(DatabaseError dataSnapshot) {
					// TODO Auto-generated method stub
					LOG.info(
							"UserRoleColumnViewFBController.findRoleColumnView(...).new ValueEventListener() {...}.onCancelled()");
					LOG.info("+++++++++childCount[" + dataSnapshot.getDetails() + "]+++++++++");
				}
			});

			// firebaseDatabse.child("k1").addChildEventListener(new
			// ChildEventListener() {
			//
			// @Override
			// public void onCancelled(DatabaseError databaseError) {
			// // ...
			// }
			//
			// @Override
			// public void onChildAdded(DataSnapshot dataSnapshot, String arg1)
			// {
			// // TODO Auto-generated method stub
			// LOG.info(
			// "UserRoleColumnViewFBController.findRoleColumnView(...).new
			// ChildEventListener() {...}.onChildAdded()");
			// LOG.info("+++++++++childCount[" + dataSnapshot.getChildrenCount()
			// + "]+++++++++[" + arg1);
			//
			// Iterable<DataSnapshot> children = dataSnapshot.getChildren();
			// LOG.info("+++++++++dataSnapshot.getChildren[" + children +
			// "]+++++++++[" + arg1);
			// for (DataSnapshot child : children) {
			// LOG.info("--- child[" + child.getValue() + "]---");
			// }
			// }
			//
			// @Override
			// public void onChildChanged(DataSnapshot dataSnapshot, String
			// arg1) {
			// // TODO Auto-generated method stub
			// LOG.info(
			// "UserRoleColumnViewFBController.findRoleColumnView(...).new
			// ChildEventListener() {...}.onChildChanged()");
			// LOG.info("+++++++++childCount[" + dataSnapshot.getChildrenCount()
			// + "]+++++++++[" + arg1);
			//
			// Iterable<DataSnapshot> children = dataSnapshot.getChildren();
			// LOG.info("+++++++++dataSnapshot.getChildren[" + children +
			// "]+++++++++[" + arg1);
			// for (DataSnapshot child : children) {
			// LOG.info("--- child[" + child.getValue() + "]---");
			// }
			//
			// }
			//
			// @Override
			// public void onChildMoved(DataSnapshot dataSnapshot, String arg1)
			// {
			// // TODO Auto-generated method stub
			// LOG.info(
			// "UserRoleColumnViewFBController.findRoleColumnView(...).new
			// ChildEventListener() {...}.onChildMoved()");
			// Iterable<DataSnapshot> children = dataSnapshot.getChildren();
			//
			// LOG.info("+++++++++dataSnapshot.getChildren[" + children +
			// "]+++++++++[" + arg1);
			// for (DataSnapshot child : children) {
			// LOG.info("--- child[" + child.getValue() + "]---");
			// }
			//
			// }
			//
			// @Override
			// public void onChildRemoved(DataSnapshot dataSnapshot) {
			// // TODO Auto-generated method stub
			// LOG.info(
			// "UserRoleColumnViewFBController.findRoleColumnView(...).new
			// ChildEventListener() {...}.onChildRemoved()");
			//
			// Iterable<DataSnapshot> children = dataSnapshot.getChildren();
			// LOG.info("+++++++++dataSnapshot.getChildren[" + children +
			// "]+++++++++");
			// for (DataSnapshot child : children) {
			// LOG.info("--- child[" + child.getValue() + "]---");
			// }
			//
			// }
			// });

		} catch (Exception ex) {
			// TODO
			LOG.trace(ex.getMessage(), ex);
		}
		return "";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/userrolesfb/")
	public void createUserRoles(@RequestBody List<UserRoleVO> userRoles) {
		try {
			// TODO
			// upload json configuration file for column view changes.

		} catch (Exception ex) {
			// TODO
			LOG.trace(ex.getMessage(), ex);
		}
	}

}
