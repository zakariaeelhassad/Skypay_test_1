package org.example.service;

import org.example.entity.Account;
import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactinServiceTest {
    private TransactionService transactionService;
    private Account account;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionService();
        account = new Account();
    }

    @Test
    void shouldPerformDepositSuccessfully() throws Exception {
        transactionService.performTransaction(account, 1000, "30-12-2026", "DEPOSIT");

        assertEquals(1000, account.getBalance());
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    void shouldPerformWithdrawSuccessfully() throws Exception {
        transactionService.performTransaction(account, 2000, "30-12-2026", "DEPOSIT");
        transactionService.performTransaction(account, 500, "30-12-2026", "WITHDRAW");

        assertEquals(1500, account.getBalance());
        assertEquals(2, account.getTransactions().size());
    }

    @Test
    void shouldThrowInvalidAmountExceptionWhenAmountIsNegative() {
        assertThrows(InvalidAmountException.class, () ->
                transactionService.performTransaction(account, -100, "30-12-2026", "DEPOSIT")
        );
    }

    @Test
    void shouldThrowInsufficientFundsExceptionWhenWithdrawTooMuch() throws Exception {
        transactionService.performTransaction(account, 500, "30-12-2026", "DEPOSIT");

        assertThrows(InsufficientFundsException.class, () ->
                transactionService.performTransaction(account, 1000, "30-12-2026", "WITHDRAW")
        );
    }

    @Test
    void shouldThrowInvalidDateExceptionWhenDateIsBeforeLastTransaction() throws Exception {
        transactionService.performTransaction(account, 1000, "30-12-2026", "DEPOSIT");

        assertThrows(InvalidDateException.class, () ->
                transactionService.performTransaction(account, 500, "29-12-2026", "DEPOSIT")
        );
    }

    @Test
    void shouldThrowIllegalArgumentExceptionForUnknownTransactionType() {
        assertThrows(IllegalArgumentException.class, () ->
                transactionService.performTransaction(account, 100, "30-12-2026", "TRANSFER")
        );
    }
}
