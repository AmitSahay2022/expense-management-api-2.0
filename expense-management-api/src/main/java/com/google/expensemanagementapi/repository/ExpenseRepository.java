package com.google.expensemanagementapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.expensemanagementapi.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	List<Expense> findByUserId(long userId);

	Optional<Expense> findByUserIdAndId(long userId, long expenseId);
}
