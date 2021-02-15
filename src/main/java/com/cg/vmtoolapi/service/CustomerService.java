package com.cg.vmtoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vmtoolapi.domain.Customer;
import com.cg.vmtoolapi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveorUpdateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

}
