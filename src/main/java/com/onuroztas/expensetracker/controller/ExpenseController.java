package com.onuroztas.expensetracker.controller;

import com.onuroztas.expensetracker.dto.ExpenseDTO;
import com.onuroztas.expensetracker.model.Expense;
import com.onuroztas.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@CrossOrigin(origins = "http://localhost:5173")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

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

    @GetMapping("/all")
    public ResponseEntity<List<ExpenseDTO>> getAllExpenses(Authentication authentication) {
        List<ExpenseDTO> expenses = expenseService.getAllExpensesForUser(authentication.getName());
        return ResponseEntity.ok(expenses);
    }

    @PutMapping("/update/{expenseId}")
    public ResponseEntity<?> updateExpense(@PathVariable Long expenseId, @RequestBody Expense expense, Authentication authentication) {
        ExpenseDTO updatedExpense = expenseService.updateExpense(expenseId, expense, authentication.getName());
        if (updatedExpense != null) {
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.badRequest().body("Unable to update expense. Either the expense doesn't exist or you don't have permission to update it.");
        }
    }
}
