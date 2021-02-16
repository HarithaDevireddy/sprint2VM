package com.cg.vmtoolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cg.vmtoolapi.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);

}
