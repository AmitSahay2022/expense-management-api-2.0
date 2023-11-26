package com.google.expensemanagementapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.expensemanagementapi.dto.UserDto;
import com.google.expensemanagementapi.dto.UserUpdateDto;
import com.google.expensemanagementapi.entity.Role;
import com.google.expensemanagementapi.entity.User;
import com.google.expensemanagementapi.exception.UserAllreadyExistException;
import com.google.expensemanagementapi.exception.UserNotFoundException;
import com.google.expensemanagementapi.repository.UserRepository;
import com.google.expensemanagementapi.service.UserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
	@Override
	public UserDto saveUser(UserDto userDto) {
		// TODO Auto-generated method stub
		if(userRepository.existsByEmail(userDto.getEmail())) {
			throw new UserAllreadyExistException("User Allready Exist With This Email");
		}
		User user = modelMapper.map(userDto, User.class);
		
			Role role=new Role();
			role.setRoleName("ROLE_USER");
			user.getRoles().add(role);
		
		String encodedPassword = passwordEncoder.encode(userDto.getPassword());
		user.setPassword(encodedPassword);
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser, UserDto.class);
	}

	@Override
	public UserDto updateUser(long userId, UserUpdateDto dto) {
		// TODO Auto-generated method stub
		UserDto userDto = getUser(userId);
		User user = modelMapper.map(userDto, User.class);
		user.setName(dto.getName());
		String encodedPassword = passwordEncoder.encode(dto.getPassword());
		user.setPassword(encodedPassword);
		User updatedUser = userRepository.save(user);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public String deleteUser(long userId) {
		// TODO Auto-generated method stub
		UserDto userDto = getUser(userId);
		User user = modelMapper.map(userDto, User.class);
		userRepository.delete(user);
		return "User Deleted Successfully";
	}

	@Override
	public UserDto getUser(long userId) {
		// TODO Auto-generated method stub
		User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User Not Found"));
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> allUsers = userRepository.findAll();
		List<UserDto> list = allUsers.stream().map(user->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public User getLoggedInUser() {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		return userRepository.findByEmail(email).get();
	}
}
