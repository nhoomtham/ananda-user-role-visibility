package com.gopomelo.projects.ananda.landsourcing.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gopomelo.projects.ananda.landsourcing.controllers.vo.RoleColumnView;
import com.gopomelo.projects.ananda.landsourcing.repository.RoleDao;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 */
@Controller
public class RoleController {

	private Logger LOG = Logger.getLogger(RoleController.class);

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private RoleDao roleDao;

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	@RequestMapping("/{roleId}/columns")
	@ResponseBody
	public RoleColumnView findRoleColumnView(long roleId) {
		RoleColumnView result = null;

		try {
			// TODO
		} catch (Exception ex) {
			// TODO
		}
		return result;
	}

}
