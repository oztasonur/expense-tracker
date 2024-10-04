package com.onuroztas.expensetracker.service;

import com.onuroztas.expensetracker.dto.ExpenseDTO;
import com.onuroztas.expensetracker.model.Expense;
import com.onuroztas.expensetracker.model.User;
import com.onuroztas.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    public ExpenseDTO createExpense(Expense expense, String username) {
        User user = userService.getUserByUsername(username);
        expense.setUser(user);
        Expense savedExpense = expenseRepository.save(expense);
        return convertToDTO(savedExpense);
    }

    public boolean deleteExpense(Long expenseId, String username) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isPresent()) {
            Expense expense = expenseOptional.get();
            if (expense.getUser().getUsername().equals(username)) {
                expenseRepository.delete(expense);
                return true;
            }
        }
        return false;
    }

    private ExpenseDTO convertToDTO(Expense expense) {
        return new ExpenseDTO(
                expense.getId(),
                expense.getUser().getId(),
                expense.getExpenseName(),
                expense.getDescription(),
                expense.getAmount(),
                expense.getCurrency(),
                expense.isExpense()
        );
    }

    public List<ExpenseDTO> getAllExpensesForUser(String username) {
        User user = userService.getUserByUsername(username);
        List<Expense> expenses = expenseRepository.findByUser(user);
        return expenses.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ExpenseDTO updateExpense(Long expenseId, Expense updatedExpense, String username) {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);
        if (expenseOptional.isPresent()) {
            Expense existingExpense = expenseOptional.get();
            if (existingExpense.getUser().getUsername().equals(username)) {
                existingExpense.setExpenseName(updatedExpense.getExpenseName());
                existingExpense.setDescription(updatedExpense.getDescription());
                existingExpense.setAmount(updatedExpense.getAmount());
                existingExpense.setCurrency(updatedExpense.getCurrency());
                existingExpense.setExpense(updatedExpense.isExpense());
                
                Expense savedExpense = expenseRepository.save(existingExpense);
                return convertToDTO(savedExpense);  
            }
        }
        return null;
    }
}