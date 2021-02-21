package com.cg.vm.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.vm.bean.User;
import com.cg.vm.serviceimpl.MapValidationErrorService;
import com.cg.vm.serviceimpl.UserServiceImpl;


@RestController
@RequestMapping("/api/vehiclemanagement/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@PostMapping("/add")
	public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if (errorMap != null)
			return errorMap;
		User addedUser = userService.addorUpdateUser(user);
		return new ResponseEntity<User>(addedUser, HttpStatus.OK);

	}

	@GetMapping("/signin/{userid}/{password}")
	public ResponseEntity<String> validateUser(@PathVariable String userid, @PathVariable String password) {
		User user = new User(userid,password);
		userService.ValidateUser(user);
		return new ResponseEntity<String>("Valid User Credentials", HttpStatus.OK);
	}

	@DeleteMapping("/signin/{userid}/{password}")
	public ResponseEntity<String> removeUser(@PathVariable String userid, @PathVariable String password) {
		User user = new User(userid,password);
		 String result = userService.RemoveUser(user);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}

