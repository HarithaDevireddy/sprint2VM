package com.cg.vmtoolapi.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vmtoolapi.domain.Customer;
import com.cg.vmtoolapi.service.CustomerService;

@RestController
@RequestMapping("/api/vehiclemanagement/customer")

public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add")
	public ResponseEntity<Customer>createNewCustomer(@RequestBody Customer customer) {
		customer.getUser().setRole("Customer");
		Customer customer1=customerService.saveorUpdateCustomer(customer);
		return new ResponseEntity<Customer>(customer1,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?>deleteCustomer(@RequestBody Customer customer) {
		customerService.removeCustomer(customer);
		return new ResponseEntity<String>("Deleted Sucessfully",HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteCustomer(@PathVariable Long id) {
		customerService.removeCustomerById(id);
		return new ResponseEntity<String>("Deleted Sucessfully based on customer Id",HttpStatus.OK);
		
	}
	
	
	

}
