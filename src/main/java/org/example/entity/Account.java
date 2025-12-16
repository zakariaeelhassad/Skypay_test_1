package org.example.entity;

import java.util.ArrayList;

public class Account {
    private final ArrayList<Transaction> transactions;
    private int balance;

    public Account() {
        this.transactions = new ArrayList<>();
        this.balance = 0;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);

        if (transaction.getType() == Transaction.TransactionType.DEPOSIT) {
            balance += transaction.getAmount();
        } else {
            balance -= transaction.getAmount();
        }
    }

    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public int getBalance() {
        return balance;
    }

    public void deductBalance(int amount) {
        this.balance -= amount;
    }
}