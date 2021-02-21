package com.cg.vm.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNull;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.vm.VmtoolapiApplication;
import com.cg.vm.bean.Booking;
import com.cg.vm.bean.Customer;
import com.cg.vm.bean.Driver;
import com.cg.vm.bean.Payment;
import com.cg.vm.bean.Vehicle;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VmtoolapiApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	public void testAddPayment() {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 1, "online", date1, "accepted", booking);
		ResponseEntity<Payment> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/api/vehiclemanagement/payment/add", payment, Payment.class);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testDeletePaymentById() {
		Driver driver = new Driver(2, "Ramu", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 3, "MH124999", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 2, "swas", "shetti", "swastikashet1@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(10, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 2, "online", date1, "accepted", booking);
		@SuppressWarnings("unused")
		ResponseEntity<Payment> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/api/vehiclemanagement/payment/add", payment, Payment.class);
		restTemplate.delete(getRootUrl() + "/api/vehiclemanagement/payment/delete/2", Payment.class);
		Payment getResponse = restTemplate.getForObject(getRootUrl() + "/api/vehiclemanagement/payment/find/2",
				Payment.class);
		assertNull(getResponse.getPaymentId());
	}

	@Test
	public void testFindPaymentByBookingId() {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 1, "online", date1, "accepted", booking);
		@SuppressWarnings("unused")
		ResponseEntity<Payment> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/api/vehiclemanagement/payment/add", payment, Payment.class);
		ResponseEntity<Payment> postResponse1 = restTemplate
				.getForEntity(getRootUrl() + "/api/vehiclemanagement/payment/booking/9", Payment.class);
		assertNotNull(postResponse1);
	}

	@Test
	public void testFindPaymentByVehicleId() {
		Driver driver = new Driver(1, "Ram", "sharma", "mumbai", "8237776772", "ram@gmail", "GA45789");
		Vehicle vehicle = new Vehicle((long) 2, "MH124997", "CAR", "AC", "Maruti", "pune", 5, 12, 2000, driver);
		Customer customer = new Customer((long) 1, "swastika", "shetti", "swastikashet@gmail.com", "2345678976", "goa",
				null);
		LocalDate date = LocalDate.of(2020, 02, 1);
		LocalDate date1 = LocalDate.of(2020, 02, 2);
		Booking booking = new Booking(9, customer, vehicle, date, date1, "driver", 100, 1000);

		Payment payment = new Payment((long) 1, "online", date1, "accepted", booking);
		@SuppressWarnings("unused")
		ResponseEntity<Payment> postResponse = restTemplate
				.postForEntity(getRootUrl() + "/api/vehiclemanagement/payment/add", payment, Payment.class);
		ResponseEntity<Payment> postResponse2 = restTemplate
				.getForEntity(getRootUrl() + "/api/vehiclemanagement/payment//vehicle/2", Payment.class);
		assertNotNull(postResponse2);
	}

}
