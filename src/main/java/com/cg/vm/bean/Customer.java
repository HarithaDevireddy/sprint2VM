package com.cg.vm.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="cust_id")
	private Long customerId;

	@NotBlank(message = "First name required")
	private String firstname;

	@NotBlank(message = "Last name required")
	private String lastname;

	@NotBlank(message = "Email-Id required")
	@Column(unique = true, updatable = false)
	private String emailId;

	@NotBlank(message = "Mobile number required")
	@Column(nullable = false)
	@Size(min = 10, max = 10, message = "Mobile number Should Be 10 digits")
	private String mobileNumber;

	@NotBlank(message = "Address required")
	private String address;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "user_id", nullable = false, updatable = true)
	@Valid
	private User user;

	public Customer()
	{
		super();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", emailId=" + emailId + ", mobileNumber=" + mobileNumber + ", address=" + address + ", user=" + user
				+ "]";
	}
	public Customer(Long customerId, @NotBlank(message = "First name required") String firstname,
			@NotBlank(message = "Last name required") String lastname,
			@NotBlank(message = "Email-Id required") String emailId,
			@NotBlank(message = "Mobile number required") @Size(min = 10, max = 10, message = "Mobile number Should Be 10 digits") String mobileNumber,
			@NotBlank(message = "Address required") String address, @Valid User user) {
		super();
		this.customerId = customerId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.user = user;
	}

}