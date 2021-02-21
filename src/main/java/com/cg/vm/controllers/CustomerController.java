package com.cg.vm.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vm.bean.Customer;
import com.cg.vm.serviceimpl.CustomerServiceImpl;
import com.cg.vm.serviceimpl.MapValidationErrorService;

@RestController
@RequestMapping("/api/vehiclemanagement/customer")

public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("/add")
	public ResponseEntity<?> createNewCustomer(@Valid @RequestBody Customer customer, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		Customer customer1 = customerService.saveorUpdateCustomer(customer);
		return new ResponseEntity<Customer>(customer1, HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) {
		customerService.removeCustomer(customer);
		return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.OK);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		customerService.removeCustomerById(id);
		return new ResponseEntity<String>("Deleted Sucessfully based on customer Id", HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> viewCustomerById(@PathVariable Long id) {
		Customer customer = customerService.viewCustomer(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);

	}

	@GetMapping("/all/{location}")
	public ResponseEntity<List<Customer>> viewCustomersByLocation(@PathVariable String location) {
		List<Customer> customers = customerService.viewCustomerByLocation(location);
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<List<Customer>> viewAllCustomers() {
		List<Customer> customers = customerService.viewAllCustomers();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

	}

}
