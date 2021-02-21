package com.cg.vm.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.vm.bean.Customer;
import com.cg.vm.bean.User;
import com.cg.vm.exceptions.CustomerIdException;
import com.cg.vm.exceptions.EmailIdException;
import com.cg.vm.exceptions.UserIdException;
import com.cg.vm.repository.CustomerRepository;
import com.cg.vm.repository.UserRepository;
import com.cg.vm.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private UserRepository userRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository2) {
		
	}

	public Customer saveorUpdateCustomer(Customer customer) 
	{
		if(customer.getCustomerId()==0)
			{
			User user = userRepository.findByUserId(customer.getUser().getUserId());
			if(user != null) 
				throw new UserIdException("User Id "+customer.getUser().getUserId() +" Already In USE");
			
			Customer findCustomerByEmail = customerRepository.findByEmailId(customer.getEmailId());
		if (findCustomerByEmail != null)
			throw new EmailIdException("Email Id ALready Exists In database for different User");
		
		customer.setAddress(customer.getAddress().toLowerCase());
		customer.getUser().setRole("Customer");
		return customerRepository.save(customer);
			}
		return customerRepository.save(customer);

	}

	public void removeCustomer(Customer customer) {
		Customer findCustomer = customerRepository.findByCustomerId(customer.getCustomerId());
		if (findCustomer == null)
			throw new CustomerIdException("Customer Id " + customer.getCustomerId() + " Not In Database");
		customerRepository.delete(customer);

	}

	public void removeCustomerById(Long id) {
		Customer findCustomer = customerRepository.findByCustomerId(id);
		if (findCustomer == null)
			throw new CustomerIdException("Customer Id " + id + " Not In Database");
		customerRepository.deleteById(id);

	}

	public Customer viewCustomer(Long id) {
		Customer customer = customerRepository.findByCustomerId(id);
		if (customer == null)
			throw new CustomerIdException("Customer Id " + id + " Not IN Database");
		return customer;
	}

	public List<Customer> viewCustomerByLocation(String location) {
		List<Customer> customers = customerRepository.findByAddressContaining(location);
		if (customers.size() == 0)
			throw new CustomerIdException("Customers from " + location + " Not Found");
		return customers;
	}

	public List<Customer> viewAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		if (customers.size() == 0)
			throw new CustomerIdException("No Customers Found In Customer Table");
		return customers;
	}
}
