package com.gopomelo.projects.ananda.landsourcing.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gopomelo.projects.ananda.landsourcing.controllers.vo.UserRoleColumnViewVO;
import com.gopomelo.projects.ananda.landsourcing.controllers.vo.UserRoleVO;
import com.gopomelo.projects.ananda.landsourcing.models.ColumnView;
import com.gopomelo.projects.ananda.landsourcing.models.UserRole;
import com.gopomelo.projects.ananda.landsourcing.repository.RoleDao;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 */
@Controller
public class UserRoleController {

	private Logger LOG = Logger.getLogger(UserRoleController.class);

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	@Autowired
	private RoleDao userRoleDao;

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	@RequestMapping(method = RequestMethod.GET, value = "/userroles/{roleId}/columns")
	public List<UserRoleColumnViewVO> findRoleColumnView(@PathVariable long roleId) {
		List<UserRoleColumnViewVO> result = new ArrayList<>();

		try {
			UserRole ur = userRoleDao.findOne(roleId);
			List<ColumnView> columns = ur.getColumns();

			if (CollectionUtils.isNotEmpty(columns)) {
				for (ColumnView columnView : columns) {
					UserRoleColumnViewVO columnViewVo = new UserRoleColumnViewVO();
					columnViewVo.setField(columnView.getName());
					columnViewVo.setTitle(columnView.getTitle());
					columnViewVo.setVisible(true);
				}
			}

		} catch (Exception ex) {
			// TODO
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/userroles")
	public void createUserRoles(@RequestBody List<UserRoleVO> userRoles) {
		try {
			// TODO
			List<UserRole> userRolesEnt = new ArrayList<>();
			for (UserRoleVO userRoleVO : userRoles) {
				UserRole ur = new UserRole(userRoleVO.getName(), userRoleVO.getDescription());

				userRolesEnt.add(ur);
			}
			userRoleDao.save(userRolesEnt);

		} catch (Exception ex) {
			// TODO
			LOG.trace(ex.getMessage(), ex);
		}
	}

}
