package com.cg.vmtoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vmtoolapi.domain.Customer;
import com.cg.vmtoolapi.domain.User;
import com.cg.vmtoolapi.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveorUpdateCustomer(Customer customer) {
		
		if(customer.getCustomerId()==null) {
			User user=new User();
		    customer.setUser(user);
		    user.setCustomer(customer);
		    user.setEmailId(customer.getEmailId());
		    user.setPassword(customer.getPassword());
			
		}
		return customerRepository.save(customer);
	}

}
