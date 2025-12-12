package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidDateException;
import org.example.util.DateValidator;

public class TransactionService {

    public void performTransaction(Account account, int amount, String date, String type)
            throws InvalidAmountException, InsufficientFundsException, InvalidDateException {

        if (amount <= 0) {
            throw new InvalidAmountException(type + " amount must be positive.");
        }

        String lastTransactionDate = null;
        if (!account.getTransactions().isEmpty()) {
            lastTransactionDate = account.getTransactions()
                    .get(account.getTransactions().size() - 1)
                    .getDate();
        }

        DateValidator.validate(date, lastTransactionDate);

        if (type.equalsIgnoreCase("WITHDRAW") && amount > account.getBalance()) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }

        if (type.equalsIgnoreCase("DEPOSIT")) {
            account.updateBalance(amount);
        } else if (type.equalsIgnoreCase("WITHDRAW")) {
            account.updateBalance(-amount);
        } else {
            throw new IllegalArgumentException("Invalid transaction type: " + type);
        }

        account.addTransaction(new Transaction(amount, date, type.toUpperCase()));
    }
}
