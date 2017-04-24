package com.gopomelo.projects.ananda.landsourcing.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
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

	@RequestMapping(method = RequestMethod.GET, value = "/userrolesfb/{roleId}/columns")
	public String findRoleColumnView(@PathVariable long roleId) {
		// List<UserRoleColumnViewVO> result = new ArrayList<>();

		try {

			// final String uri = "https://gcp-labs-nk.firebaseio.com/";
			//
			// RestTemplate restTemplate = new RestTemplate();
			// String result = restTemplate.getForObject(uri, String.class);

			// LOG.info(result);

			firebaseDatabse.child("k1").addChildEventListener(new ChildEventListener() {

				@Override
				public void onCancelled(DatabaseError databaseError) {
					// ...
				}

				@Override
				public void onChildAdded(DataSnapshot dataSnapshot, String arg1) {
					// TODO Auto-generated method stub
					LOG.info(
							"UserRoleColumnViewFBController.findRoleColumnView(...).new ChildEventListener() {...}.onChildAdded()");
					LOG.info("+++++++++childCount[" + dataSnapshot.getChildrenCount() + "]+++++++++[" + arg1);

					Iterable<DataSnapshot> children = dataSnapshot.getChildren();
					LOG.info("+++++++++dataSnapshot.getChildren[" + children + "]+++++++++[" + arg1);
					for (DataSnapshot child : children) {
						LOG.info("--- child[" + child.getValue() + "]---");
					}
				}

				@Override
				public void onChildChanged(DataSnapshot dataSnapshot, String arg1) {
					// TODO Auto-generated method stub
					LOG.info(
							"UserRoleColumnViewFBController.findRoleColumnView(...).new ChildEventListener() {...}.onChildChanged()");
					LOG.info("+++++++++childCount[" + dataSnapshot.getChildrenCount() + "]+++++++++[" + arg1);

					Iterable<DataSnapshot> children = dataSnapshot.getChildren();
					LOG.info("+++++++++dataSnapshot.getChildren[" + children + "]+++++++++[" + arg1);
					for (DataSnapshot child : children) {
						LOG.info("--- child[" + child.getValue() + "]---");
					}

				}

				@Override
				public void onChildMoved(DataSnapshot dataSnapshot, String arg1) {
					// TODO Auto-generated method stub
					LOG.info(
							"UserRoleColumnViewFBController.findRoleColumnView(...).new ChildEventListener() {...}.onChildMoved()");
					Iterable<DataSnapshot> children = dataSnapshot.getChildren();

					LOG.info("+++++++++dataSnapshot.getChildren[" + children + "]+++++++++[" + arg1);
					for (DataSnapshot child : children) {
						LOG.info("--- child[" + child.getValue() + "]---");
					}

				}

				@Override
				public void onChildRemoved(DataSnapshot dataSnapshot) {
					// TODO Auto-generated method stub
					LOG.info(
							"UserRoleColumnViewFBController.findRoleColumnView(...).new ChildEventListener() {...}.onChildRemoved()");

					Iterable<DataSnapshot> children = dataSnapshot.getChildren();
					LOG.info("+++++++++dataSnapshot.getChildren[" + children + "]+++++++++");
					for (DataSnapshot child : children) {
						LOG.info("--- child[" + child.getValue() + "]---");
					}

				}
			});

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
