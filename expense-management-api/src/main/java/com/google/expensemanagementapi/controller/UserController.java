package com.google.expensemanagementapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.expensemanagementapi.dto.UserDto;
import com.google.expensemanagementapi.dto.UserUpdateDto;
import com.google.expensemanagementapi.service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
	private UserService userService;
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody  UserDto userDto) {
		return new ResponseEntity<>(userService.saveUser(userDto),HttpStatus.CREATED);
	}
	@PutMapping("{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateDto dto,@PathVariable long id){
		return new ResponseEntity<UserDto>(userService.updateUser(id, dto),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable long userId){
		return new ResponseEntity<String>(userService.deleteUser(userId),HttpStatus.OK);
	}
	@GetMapping("{userId}")
	public ResponseEntity<UserDto> getUserInfo(@PathVariable long userId){
		return new ResponseEntity<UserDto>(userService.getUser(userId),HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUserInfo(){
		return new ResponseEntity<List<UserDto>>(userService.getAllUser(),HttpStatus.OK);
	}

}
