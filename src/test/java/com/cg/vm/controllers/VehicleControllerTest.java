package com.cg.vm.controllers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.cg.vm.VmtoolapiApplication;
import com.cg.vm.bean.Driver;
import com.cg.vm.bean.Vehicle;

@SpringBootTest(classes = VmtoolapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VehicleControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testsaveOrUpdate() {
		Driver driver1 = new Driver(2, "Ramu", "sharma", "goa", "8237776771", "ramu@gmail", "GA45799");
		Vehicle vehicle2 = new Vehicle((long) 3, "MH129997", "CAR", "AC", "Innova", "pune", 5, 12, 2000, driver1);
		ResponseEntity<Vehicle> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/api/vehiclemanagement/vehicle/vehicle/create", vehicle2, Vehicle.class);
		assertNotNull(postResponse);
	}

	@Test
	public void testfindByVehicleId() {
//		Driver driver1 = new Driver(2, "Ramu", "sharma", "goa", "8237776771", "ramu@gmail", "GA45799");
//		Vehicle vehicle2 = new Vehicle((long) 3, "MH129997", "CAR", "AC", "Innova", "pune", 5, 12, 2000, driver1);
		ResponseEntity<Vehicle> postResponse = restTemplate
				.getForEntity(getRootUrl() + "/api/vehiclemanagement/vehicle/2", Vehicle.class);
		assertNotNull(postResponse);

	}

	@Test
	public void testdeleteVehicleById() {
		Driver driver1 = new Driver(2, "Ramu", "sharma", "goa", "8237776771", "ramu@gmail", "GA45799");
		Vehicle vehicle2 = new Vehicle((long) 3, "MH129997", "CAR", "AC", "Innova", "pune", 5, 12, 2000, driver1);
		restTemplate.delete(getRootUrl() + "/api/vehiclemanagement/vehicle/2", vehicle2, Vehicle.class);

	}
}
