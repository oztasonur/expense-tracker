package com.onuroztas.expensetracker.controller;

import com.onuroztas.expensetracker.dto.ExpenseDTO;
import com.onuroztas.expensetracker.model.Expense;
import com.onuroztas.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Expense Tracker API is working!");
    }

    @PostMapping("/create")
    public ResponseEntity<ExpenseDTO> createExpense(@RequestBody Expense expense, Authentication authentication) {
        ExpenseDTO createdExpense = expenseService.createExpense(expense, authentication.getName());
        return ResponseEntity.ok(createdExpense);
    }

    @DeleteMapping("/delete/{expenseId}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long expenseId, Authentication authentication) {
        boolean deleted = expenseService.deleteExpense(expenseId, authentication.getName());
        if (deleted) {
            return ResponseEntity.ok("Expense deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Unable to delete expense");
        }
    }
}
