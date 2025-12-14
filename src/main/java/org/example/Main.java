package org.example;

import org.example.entity.Account;
import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidDateException;
import org.example.presentation.StatementPrinter;
import org.example.service.TransactionService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        TransactionService transactionService = new TransactionService();
        StatementPrinter printer = new StatementPrinter();

        try {
            transactionService.performTransaction(account, 1000, "30-12-2026", "DEPOSIT");
            transactionService.performTransaction(account, 2000, "30-12-2026", "DEPOSIT");

            transactionService.performTransaction(account, 500, "30-12-2026", "WITHDRAW");

        } catch (InvalidAmountException | InsufficientFundsException | InvalidDateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        printer.print(account);
    }
}