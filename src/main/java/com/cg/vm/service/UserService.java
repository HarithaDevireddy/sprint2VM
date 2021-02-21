package com.cg.vm.service;

import com.cg.vm.bean.User;

public interface UserService {

	public User ValidateUser(User user);
	public User addorUpdateUser(User user);
	public String RemoveUser(User user) ;
}
