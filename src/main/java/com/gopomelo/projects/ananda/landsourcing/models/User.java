package com.gopomelo.projects.ananda.landsourcing.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private long id;

	@NotNull
	@Getter
	@Setter
	private String email;

	@NotNull
	@Getter
	@Setter
	private String name;

	@NotNull
	@Getter
	@Setter
	private String password = "abcd"; // default password

	@ManyToMany(mappedBy = "users")
	@Getter
	@Setter
	private List<UserRole> roles;

	public User() {
	}

	public User(long id) {
		this.id = id;
	}

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}

}
