package com.gopomelo.projects.ananda.landsourcing.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.google.firebase.database.DatabaseReference;
import com.gopomelo.projects.ananda.landsourcing.controllers.vo.UserRoleVO;

/**
 * A class to interactions with the FIREBASE database
 *
 */
@Controller
public class UserRoleColumnViewFBController {

	private Logger LOG = Logger.getLogger(UserRoleColumnViewFBController.class);

	@Autowired
	private DatabaseReference firebaseDatabse;

	@RequestMapping(method = RequestMethod.GET, value = "/userrolesfb/{roleId}/columns")
	public String findRoleColumnView(@PathVariable long roleId) {
		// List<UserRoleColumnViewVO> result = new ArrayList<>();

		try {

			final String uri = "http://localhost:8080/springrestexample/employees.json";

			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(uri, String.class);

			System.out.println(result);

		} catch (Exception ex) {
			// TODO
		}
		return result;
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
