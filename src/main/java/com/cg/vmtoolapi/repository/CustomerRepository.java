package com.cg.vmtoolapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.vmtoolapi.domain.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>{

}
