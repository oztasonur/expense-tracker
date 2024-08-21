package com.onuroztas.expensetracker.dto;

import java.math.BigDecimal;

public class ExpenseDTO {
    private Long id;
    private Long userId;
    private String expenseName;
    private String description;
    private BigDecimal amount;
    private String currency;
    private boolean isExpense;

    // Constructors
    public ExpenseDTO() {}

    public ExpenseDTO(Long id, Long userId, String expenseName, String description, BigDecimal amount, String currency, boolean isExpense) {
        this.id = id;
        this.userId = userId;
        this.expenseName = expenseName;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.isExpense = isExpense;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}