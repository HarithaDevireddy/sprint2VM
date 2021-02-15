package com.cg.vmtoolapi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vmtoolapi.domain.Customer;
import com.cg.vmtoolapi.service.CustomerService;

@RestController
@RequestMapping("/api/vehiclemanagement")

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("")
	public ResponseEntity<Customer>createNewCustomer(@RequestBody Customer customer) {
		Customer customer1=customerService.saveorUpdateCustomer(customer);
		return new ResponseEntity<Customer>(customer1,HttpStatus.OK);
		
	}
	
	
	

}
