package com.cg.vm.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "credentials") // in pgadmin user table name gives problem
public class User {
	@Id
	@Column(name = "user_id", updatable = false)
	@NotBlank(message = "User Id required")
	private String userId;

	@NotBlank(message = "Password Cannot Be Null")
	private String password;
	private String role;
	
	
	public User() {

		super();
	}

	public User(@NotBlank(message = "User Id required") String userId,
			@NotBlank(message = "Password Cannot Be Null") String password) {
		super();
		this.userId = userId;
		this.password = password;
	}
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
