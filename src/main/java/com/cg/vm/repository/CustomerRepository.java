package com.cg.vm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.vm.bean.Customer;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByCustomerId(Long customerId);
	Customer findByEmailId(String emailId);
	List<Customer> findByAddressContaining(String location);

}