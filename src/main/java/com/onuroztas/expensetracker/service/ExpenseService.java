package com.onuroztas.expensetracker.service;

import com.onuroztas.expensetracker.dto.ExpenseDTO;
import com.onuroztas.expensetracker.model.Expense;
import com.onuroztas.expensetracker.model.User;
import com.onuroztas.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}