package com.cg.vm.controllers;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.cg.vm.VmtoolapiApplication;
import com.cg.vm.bean.User;
import com.cg.vm.repository.UserRepository;
import com.cg.vm.serviceimpl.UserServiceImpl;



@SpringBootTest(classes = VmtoolapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UserServiceImpl userService;

	@SuppressWarnings("unused")
	@Autowired
	private UserRepository userRepository;

	@LocalServerPort
	private int port;
	private User user1, user2;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@BeforeEach
	public void setMockData() {
		user1 = new User("admin1", "adminpass", "admin");
		user2 = new User("cust1", "custpass", "customer");
	}

	@Test
	@DisplayName("User Controller Add User Test for Role Admin")
	public void testAddUser() {

		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/user/add",
				user1, User.class);
		assertEquals(user1.getClass(), postResponse.getBody().getClass());
		assertNotNull(postResponse);

	}

	@Test
	@DisplayName("User Controller Add User Test for Role Customer")
	public void testAddUserException() {

		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/user/add",
				user2, User.class);
		assertEquals("Cannot Save User for customer", postResponse.getBody().getUserId());
		assertEquals(400, postResponse.getStatusCodeValue());

	}

	@Test
	@DisplayName("Validate User")
	public void testValidateUser() {

		ResponseEntity<String> getResponse = restTemplate
				.getForEntity(getRootUrl() + "/api/vehiclemanagement/user/signin/admin1/adminpass", String.class);
		assertEquals("Valid User Credentials", getResponse.getBody());

	}

	@Test
	@DisplayName("Invalid User")
	public void testInvalidUser() {

		ResponseEntity<User> getResponse = restTemplate
				.getForEntity(getRootUrl() + "/api/vehiclemanagement/user/signin/admin7/adminpass", User.class);
		assertEquals("Invalid User Id or Password", getResponse.getBody().getUserId());

	}

	@Test
	@DisplayName("Delete User")
	public void testDeleteUser() {

		restTemplate.delete("/api/vehiclemanagement/user/signin/admin1/adminpass", String.class);
		userService.addorUpdateUser(user1);

	}

}