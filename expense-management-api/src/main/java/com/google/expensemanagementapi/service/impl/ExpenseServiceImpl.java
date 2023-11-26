package com.google.expensemanagementapi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.google.expensemanagementapi.entity.Expense;
import com.google.expensemanagementapi.entity.User;
import com.google.expensemanagementapi.repository.ExpenseRepository;
import com.google.expensemanagementapi.service.ExpenseService;
import com.google.expensemanagementapi.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ExpenseServiceImpl implements ExpenseService {
	private ExpenseRepository expenseRepository;
	private UserService userService;

	@Override
	public Expense saveExpense(Expense expense) {
		// TODO Auto-generated method stub
		User user = userService.getLoggedInUser();
		expense.setUser(user);
		return expenseRepository.save(expense);
	}

	@Override
	public Expense updateExpense(long expenseId, Expense expense) {
		// TODO Auto-generated method stub
		Expense expense2 = getExpense(expenseId);
		expense2.setAmount(expense.getAmount());
		expense2.setCategory(expense.getCategory());
		expense2.setDate(expense.getDate());
		expense2.setDescription(expense.getDescription());
		expense2.setName(expense.getName());
		return expenseRepository.save(expense2);
	}

	@Override
	public String deleteExpense(long expenseId) {
		// TODO Auto-generated method stub
		Expense expense = getExpense(expenseId);
		expenseRepository.delete(expense);
		return "Expense Deleted Successfully";
	}

	@Override
	public Expense getExpense(long id) {
		// TODO Auto-generated method stub
		User user = userService.getLoggedInUser();
		Expense expense = expenseRepository.findByUserIdAndId(user.getId(), id)
				.orElseThrow(() -> new RuntimeException("Expense Not Found"));
		return expense;
	}

	@Override
	public List<Expense> getAllExpense() {
		// TODO Auto-generated method stub
		User user = userService.getLoggedInUser();
		return expenseRepository.findByUserId(user.getId());
	}

}
