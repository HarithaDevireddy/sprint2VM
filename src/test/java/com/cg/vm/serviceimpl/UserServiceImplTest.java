package com.cg.vm.serviceimpl;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cg.vm.bean.User;
import com.cg.vm.exceptions.UserIdException;

import com.cg.vm.repository.UserRepository;

import com.cg.vm.service.UserService;

public class UserServiceImplTest {

	private static final Class<UserIdException> expected = null;

	UserService userService;

	@Mock
	UserRepository userRepository;


	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		userService = new UserServiceImpl(userRepository);

	}

	@Test(expected = NullPointerException.class)
	public void testAddUser() throws NullPointerException {
		User user = new User("haritha", "haritha123@", "admin");
		User expected = userService.addorUpdateUser(user);
		assertEquals(expected, user);

	}

	@Test(expected = UserIdException.class)
	public void testAddUserFailed() throws UserIdException {
		User user = new User("haritha", "haritha123@", "customer");
		User added = userService.addorUpdateUser(user);
		System.out.println(added);

	}

	@Test(expected = NullPointerException.class)
	public void testValidateUser() {
		User user = new User("haritha", "haritha123@", "admin");
		when(userRepository.findByUserIdAndPassword("haritha", "haritha123@")).thenReturn(user);
		User expected = userService.ValidateUser(user);
		assertEquals(expected, user);

	}

	@Test(expected = NullPointerException.class)
	public void testRemoveUser() throws UserIdException {
		User user = new User("haritha", "haritha123@");
		String expected = userService.RemoveUser(user);
		assertEquals("User Deleted Sucessfully", expected);

	}

}