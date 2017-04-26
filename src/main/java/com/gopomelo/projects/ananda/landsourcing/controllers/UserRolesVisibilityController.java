package com.gopomelo.projects.ananda.landsourcing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gopomelo.projects.ananda.landsourcing.services.UserRolesVisibilityService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserRolesVisibilityController {

	@Autowired
	private UserRolesVisibilityService userRolesVisibilityService;

	/**
	 * @param roleName:
	 * @param objectName:
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/userroles/{roleName}/{objectName}")
	public ResponseEntity<String> getObjects(@PathVariable String roleName, @PathVariable String objectName) {

		ResponseEntity<String> result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		try {
			result = userRolesVisibilityService.getObjects(roleName, objectName);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			result = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

}
