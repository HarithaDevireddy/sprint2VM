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
		
		if(customer.getCustomerId()!=null)
		{
			Customer savedCustomer = customerRepository.findByCustomerId(customer.getCustomerId());
			savedCustomer = customer;
			return customerRepository.save(customer);
			
		}
		
		return customerRepository.save(customer);
	}
	
	public void removeCustomer(Customer customer)
	{
		customerRepository.delete(customer);
		
	}
	
	public void removeCustomerById(Long cust_id)
	{
	
		customerRepository.deleteById(cust_id);
		
	}

}
