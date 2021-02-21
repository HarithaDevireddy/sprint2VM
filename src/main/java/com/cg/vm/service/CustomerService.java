package com.cg.vm.service;

import java.util.List;

import com.cg.vm.bean.Customer;

public interface CustomerService {
	
	public Customer saveorUpdateCustomer(Customer customer) ;
	public void removeCustomer(Customer customer);
	public void removeCustomerById(Long id) ;
	public Customer viewCustomer(Long id);
	public List<Customer> viewCustomerByLocation(String location);
	public List<Customer> viewAllCustomers();

}
