package com.onuroztas.expensetracker.controller;

import com.onuroztas.expensetracker.dto.ExpenseDTO;
import com.onuroztas.expensetracker.model.Expense;
import com.onuroztas.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/api/expenses/create")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody Expense expense, Authentication authentication) {
        ExpenseDTO createdExpense = expenseService.createExpense(expense, authentication.getName());
        return ResponseEntity.ok(createdExpense);
    }
}