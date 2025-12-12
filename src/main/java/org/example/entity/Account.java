package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int amount) {
        balance += amount;
    }

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}