package org.example.entity;

import java.time.LocalDate;

public class Transaction {
    private final LocalDate date;
    private final int amount;
    private final TransactionType type;

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }

    public Transaction(LocalDate date, int amount, TransactionType type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Transaction{date=" + date + ", amount=" + amount + ", type=" + type + "}";
    }
}
