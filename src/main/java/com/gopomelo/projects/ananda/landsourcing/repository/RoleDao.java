package com.gopomelo.projects.ananda.landsourcing.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.gopomelo.projects.ananda.landsourcing.models.UserRole;

@Transactional
public interface RoleDao extends CrudRepository<UserRole, Long> {


}
