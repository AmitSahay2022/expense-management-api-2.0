package com.google.expensemanagementapi.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String, Object>> userNotFound(UserNotFoundException e) {
		Map<String, Object> error = new HashMap<>();
		error.put("message", e.getLocalizedMessage());
		error.put("status code", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String, Object>>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> invalidData(MethodArgumentNotValidException e) {
		Map<String, Object> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(err -> {
			errors.put(err.getField(), err.getDefaultMessage());
		});
		return new ResponseEntity<Map<String, Object>>(errors, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserAllreadyExistException.class)
	public ResponseEntity<Map<String,Object>> userAllReadyExist(UserAllreadyExistException e){
		Map<String, Object> map=new HashMap<>();
		map.put("message", e.getLocalizedMessage());
		map.put("status", HttpStatus.CONFLICT);
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.CONFLICT);
	}
}
