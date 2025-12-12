package org.example.presentation;

import org.example.entity.Account;
import org.example.entity.Transaction;

public class StatementPrinter {
    public void print(Account account) {
        System.out.println("DATE       | AMOUNT | TYPE  | BALANCE");
        int runningBalance = 0;
        for (Transaction t : account.getTransactions()) {
            if (t.getType().equals("DEPOSIT")) runningBalance += t.getAmount();
            else runningBalance -= t.getAmount();
            System.out.printf("%s | %6d | %6s | %7d%n", t.getDate(), t.getAmount(), t.getType(), runningBalance);
        }
    }
}