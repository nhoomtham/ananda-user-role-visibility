package com.gopomelo.projects.ananda.landsourcing.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private long id;

	@NotNull
	@Getter
	@Setter
	private String name; // LD, LS1, LS2, ...

	@NotNull
	@Getter
	@Setter
	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role_column_view", joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "column_view_id", referencedColumnName = "id"))
	@Getter
	@Setter
	private List<ColumnView> columns;

	@ManyToMany
	@JoinTable(name = "user_role_user", joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	@Getter
	@Setter
	private List<User> users;

	public UserRole(String name, String description) {
		this.name = name;
		this.description = description;
	}

}
