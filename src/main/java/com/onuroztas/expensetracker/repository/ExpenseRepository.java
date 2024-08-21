package com.onuroztas.expensetracker.repository;

import com.onuroztas.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    // You can add custom query methods here if needed
}