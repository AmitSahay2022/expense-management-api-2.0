package com.google.expensemanagementapi;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.expensemanagementapi.dto.UserDto;
import com.google.expensemanagementapi.service.UserService;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Test
	public void saveUser() {
		UserDto userDto=new UserDto("Amit Kumar", "sahay.mtech2013@gmail.com", "test1234");
		UserDto saveUser = userService.saveUser(userDto);
		assertNotNull(saveUser);
	}
}
