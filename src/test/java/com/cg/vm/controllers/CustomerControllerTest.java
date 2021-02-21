package com.cg.vm.controllers;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.cg.vm.bean.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

class CustomerControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testAddCustomer() {
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		ResponseEntity<Customer> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/api/vehiclemanagement/Customer/add", customer, Customer.class);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testDeleteCustomerById() {
		Customer customer = new Customer((long) 2, "swas", "shetti", "swastikashet1@gmail.com", "2345678976", "goa",
				null);
		restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/Customer/add", customer, Customer.class);
		restTemplate.delete(getRootUrl() + "/api/vehiclemanagement/Customer/delete/1", Customer.class);
		Customer postResponse1 = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/Customer/1",
				Customer.class);
		assertNull(postResponse1.getCustomerId());
	}

	@Test
	public void testGetCustomerById() {
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/Customer/add", customer, Customer.class);
		ResponseEntity<Customer> postResponse1 = restTemplate
				.getForEntity(getRootUrl() + "/api/vehiclemanagement/Customer/1", Customer.class);
		assertNotNull(postResponse1);
	}

	@Test
	public void testGetCustomerByLocation() {
		Customer customer1 = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		Customer customer2 = new Customer((long) 2, "swas", "shetti", "swas@gmail.com", "2345678976", "goa", null);
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customer1);
		customers.add(customer2);
		restTemplate.postForEntity(getRootUrl() + "/api/vehiclemanagement/Customer/add", customers, Customer.class);
		ResponseEntity<Customer> postResponse2 = restTemplate
				.getForEntity(getRootUrl() + "/api/vehiclemanagement/Customer/all/goa", Customer.class);
		assertNotNull(postResponse2);
	}

}