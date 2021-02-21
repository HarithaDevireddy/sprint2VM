package com.cg.vm.controllers;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.vm.bean.Driver;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DriverControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testAddDriver() {
		Driver driver = new Driver(1, "Arun", "cibi", "pollachi", "9080664252", "arun@gmail.com", "L123");
		ResponseEntity<Driver> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/driver",
				driver, Driver.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testCancelDriverById() {
		Driver driver = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/driver/delete1/8",
				Driver.class);
		restTemplate.delete(getRootUrl() + "/api/vehiclemanagement/driver/delete1/8");
		Driver driver1 = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/driver/delete1/8",
				Driver.class);
		restTemplate.delete(getRootUrl() + "/api/vehiclemanagement/driver/delete1/8");
		System.out.println(driver);
		assertEquals(driver, driver1);
	}

	@Test
	public void testCancelDriverByLicenseNo() {
		Driver driver = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/driver/delete/L123",
				Driver.class);
		restTemplate.delete(getRootUrl() + "/api/vehiclemanagement/driver/delete/L123");
		Driver driver1 = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/driver/delete/L123",
				Driver.class);
		System.out.println(driver);
		assertEquals(driver, driver1);
		// Assert.assertNotEquals(null, "L123");
	}

	@Test
	public void testRemoveById() {
		Driver driver1 = new Driver(7, "Arun", "cibi", "pollachi", "9080664252", "arun@gmail.com", "L123");
		ResponseEntity<Driver> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/driver",
				driver1, Driver.class);
		Driver driver2 = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/driver/delete1/7",
				Driver.class);
		restTemplate.delete(getRootUrl() + "/api/vehiclemanagement/driver/delete1/7");
		assertNotEquals(driver2, postResponse);

	}

}