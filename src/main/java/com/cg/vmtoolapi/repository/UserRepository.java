package com.cg.vmtoolapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.cg.vmtoolapi.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	//User findByEmailId(String emailId)

}
