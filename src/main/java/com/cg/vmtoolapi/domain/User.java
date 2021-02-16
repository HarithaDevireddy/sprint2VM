package com.cg.vmtoolapi.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "credentials")//in pgadmin user table name gives problem
public class User {
	@Id
	@Column(name = "user_id")
    @NotBlank(message="User Id required")
	private String userId;

	@NotBlank(message = "Password Cannot Be Null")
	private String password;
	private String role ;
	
	@OneToOne(fetch = FetchType.LAZY,
            mappedBy = "user")
	@JsonIgnore
    private Customer customer;


	public User() {

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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
