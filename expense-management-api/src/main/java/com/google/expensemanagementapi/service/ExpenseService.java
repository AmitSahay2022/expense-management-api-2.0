package com.google.expensemanagementapi.service;

import java.util.List;

import com.google.expensemanagementapi.entity.Expense;

public interface ExpenseService {
	Expense saveExpense(Expense expense);

	Expense updateExpense(long expenseId, Expense expense);

	String deleteExpense(long expenseId);

	Expense getExpense(long id);

	List<Expense> getAllExpense();
}
