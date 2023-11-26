package com.google.expensemanagementapi.dto;

import lombok.Data;

@Data
public class UserUpdateDto {
  private String name;
  private String password;
}
