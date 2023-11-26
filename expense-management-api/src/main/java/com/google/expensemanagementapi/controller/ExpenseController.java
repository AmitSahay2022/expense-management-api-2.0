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

import com.google.expensemanagementapi.entity.Expense;
import com.google.expensemanagementapi.service.ExpenseService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/expenses")
@AllArgsConstructor
public class ExpenseController {
   private ExpenseService expenseService;
   @PostMapping
   public ResponseEntity<Expense> saveExpense(@RequestBody Expense expense){
	   return new ResponseEntity<Expense>(expenseService.saveExpense(expense),HttpStatus.CREATED);
   }
   @PutMapping("{expenseId}")
   public ResponseEntity<Expense> updateExpense(@PathVariable long expenseId,@RequestBody Expense expense){
	   return new ResponseEntity<Expense>(expenseService.updateExpense(expenseId, expense),HttpStatus.ACCEPTED);
   }
   @DeleteMapping("{expenseId}")
   public ResponseEntity<String> deleteExpense(@PathVariable long expenseId){
	   return new ResponseEntity<String>(expenseService.deleteExpense(expenseId),HttpStatus.OK);
   }
   @GetMapping("{id}")
   public ResponseEntity<Expense> getExpense(@PathVariable long id){
	   return new ResponseEntity<Expense>(expenseService.getExpense(id),HttpStatus.OK);
   }
   @GetMapping
   public ResponseEntity<List<Expense>> getAllExpense(){
	   return new ResponseEntity<List<Expense>>(expenseService.getAllExpense(),HttpStatus.OK);
   }
}
