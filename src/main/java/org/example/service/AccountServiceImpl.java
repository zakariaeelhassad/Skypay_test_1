package org.example.service;

import org.example.entity.Account;
import org.example.entity.Transaction;
import org.example.util.DateUtil;
import org.example.util.ValidationUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class AccountServiceImpl implements AccountService {

    private final Account account;

    public AccountServiceImpl() {
        this.account = new Account();
    }

    @Override
    public void deposit(int amount, String date) {
        ValidationUtil.validateAmount(amount, "Deposit");
        LocalDate transactionDate = DateUtil.parseDate(date);

        Transaction transaction = new Transaction(
                transactionDate,
                amount,
                Transaction.TransactionType.DEPOSIT
        );

        account.addTransaction(transaction);
    }

    @Override
    public void withdraw(int amount, String date) {
        ValidationUtil.validateAmount(amount, "Withdrawal");
        ValidationUtil.validateSufficientFunds(account.getBalance(), amount);
        LocalDate transactionDate = DateUtil.parseDate(date);

        Transaction transaction = new Transaction(
                transactionDate,
                amount,
                Transaction.TransactionType.WITHDRAWAL
        );

        account.addTransaction(transaction);
    }

    @Override
    public void printStatement() {
        System.out.println("Date       || Amount || Balance");

        ArrayList<Transaction> transactions = account.getTransactions();

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        transactions.sort(Comparator.comparing(Transaction::getDate).reversed());

        int runningBalance = 0;
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            if (t.getType() == Transaction.TransactionType.DEPOSIT) {
                runningBalance += t.getAmount();
            } else {
                runningBalance -= t.getAmount();
            }

            String formattedDate = DateUtil.formatDate(t.getDate());
            String amountStr = (t.getType() == Transaction.TransactionType.DEPOSIT ? "+" : "-")
                    + t.getAmount();

            System.out.printf("%s || %7s || %d%n", formattedDate, amountStr, runningBalance);
        }
    }

    public int getBalance() {
        return account.getBalance();
    }
}
