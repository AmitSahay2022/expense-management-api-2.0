package com.google.expensemanagementapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.expensemanagementapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
   Optional<User> findByEmail(String email);
   boolean existsByEmail(String email);
   
}
