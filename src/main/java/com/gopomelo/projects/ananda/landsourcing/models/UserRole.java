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

@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name; // LD, LS1, LS2, ...

	@NotNull
	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role_column_view", joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "column_view_id", referencedColumnName = "id"))
	private List<ColumnView> columns;

	@ManyToMany
	@JoinTable(name = "user_role_user", joinColumns = @JoinColumn(name = "user_role_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private List<User> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ColumnView> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnView> columns) {
		this.columns = columns;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
