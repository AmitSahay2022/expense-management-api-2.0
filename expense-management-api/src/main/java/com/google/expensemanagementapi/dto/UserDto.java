package com.google.expensemanagementapi.dto;

import java.sql.Timestamp;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private long id;
	@NotBlank(message = "Name Should not blank")
	private String name;
	@NotBlank(message = "email Should not blank")
	@Email
	private String email;
	@NotBlank(message = "password Should not blank")
	private String password;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;

	public UserDto(@NotBlank(message = "Name Should not blank") String name,
			@NotBlank(message = "email Should not blank") String email,
			@NotBlank(message = "password Should not blank") String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	
}
