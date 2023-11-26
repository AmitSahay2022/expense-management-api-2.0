package com.google.expensemanagementapi.exception;

public class UserAllreadyExistException extends RuntimeException {
   public UserAllreadyExistException(String message) {
	   super(message);
   }
}
