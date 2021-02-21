package com.cg.vm.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cg.vm.bean.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUserId(String userId);
	User findByUserIdAndPassword(String userId, String password);

}
