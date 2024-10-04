package com.onuroztas.expensetracker.repository;

import com.onuroztas.expensetracker.model.Expense;
import com.onuroztas.expensetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
}