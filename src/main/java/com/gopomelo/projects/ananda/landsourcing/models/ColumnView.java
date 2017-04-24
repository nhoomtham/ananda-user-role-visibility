package com.gopomelo.projects.ananda.landsourcing.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "column_view")
public class ColumnView {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	private String name;

	@NotNull
	private String title;

	@ManyToMany(mappedBy = "columns")
	private List<UserRole> roles;

}
