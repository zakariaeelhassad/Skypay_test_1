package org.example.service;

import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidDateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {

    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
    }

    @Test
    void deposit_shouldIncreaseBalance() {
        accountService.deposit(1000, "10-12-2025");

        int balance = accountService.getBalance();

        assertEquals(1000, balance);
    }

    @Test
    void multipleDeposits_shouldAccumulateBalance() {
        accountService.deposit(500, "01-12-2025");
        accountService.deposit(300, "02-12-2025");

        assertEquals(800, accountService.getBalance());
    }

    @Test
    void withdraw_shouldDecreaseBalance() {
        accountService.deposit(1000, "01-12-2025");
        accountService.withdraw(400, "02-12-2025");

        assertEquals(600, accountService.getBalance());
    }

    @Test
    void withdraw_moreThanBalance_shouldThrowException() {
        accountService.deposit(300, "01-12-2025");

        assertThrows(
                InsufficientFundsException.class,
                () -> accountService.withdraw(500, "02-12-2025")
        );
    }

    @Test
    void deposit_negativeAmount_shouldThrowException() {
        assertThrows(
                InvalidAmountException.class,
                () -> accountService.deposit(-100, "01-12-2025")
        );
    }

    @Test
    void withdraw_zeroAmount_shouldThrowException() {
        assertThrows(
                InvalidAmountException.class,
                () -> accountService.withdraw(0, "01-12-2025")
        );
    }

    @Test
    void invalidDateFormat_shouldThrowException() {
        assertThrows(
                InvalidDateException.class,
                () -> accountService.deposit(100, "2025/12/01")
        );
    }

    @Test
    void nullDate_shouldThrowException() {
        assertThrows(
                InvalidDateException.class,
                () -> accountService.deposit(100, null)
        );
    }

    @Test
    void printStatement_shouldNotThrowException() {
        accountService.deposit(200, "01-12-2025");
        accountService.withdraw(50, "02-12-2025");

        assertDoesNotThrow(() -> accountService.printStatement());
    }
}
