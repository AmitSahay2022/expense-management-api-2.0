package com.google.expensemanagementapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.expensemanagementapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
