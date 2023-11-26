package com.google.expensemanagementapi.service;

import java.util.List;

import com.google.expensemanagementapi.dto.UserDto;
import com.google.expensemanagementapi.dto.UserUpdateDto;

public interface UserService {
	UserDto saveUser(UserDto userDto);

	UserDto updateUser(long userId, UserUpdateDto userUpdateDto);

	String deleteUser(long userId);

	UserDto getUser(long userId);

	List<UserDto> getAllUser();
}
