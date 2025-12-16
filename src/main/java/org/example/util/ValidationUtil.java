package org.example.util;

import org.example.exception.InsufficientFundsException;
import org.example.exception.InvalidAmountException;
import org.example.exception.InvalidDateException;

public class ValidationUtil {

    public static void validateAmount(int amount, String operationType) {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    operationType + " amount must be positive. Provided: " + amount
            );
        }

        if (amount > Integer.MAX_VALUE - 1000) {
            throw new InvalidAmountException(
                    operationType + " amount is too large. Maximum allowed: " + (Integer.MAX_VALUE - 1000)
            );
        }
    }

    public static void validateSufficientFunds(int currentBalance, int withdrawalAmount) {
        if (currentBalance < withdrawalAmount) {
            throw new InsufficientFundsException(currentBalance, withdrawalAmount);
        }
    }

    public static void validateDateString(String dateStr) {
        if (dateStr == null) {
            throw new InvalidDateException("Date cannot be null");
        }

        if (dateStr.trim().isEmpty()) {
            throw new InvalidDateException("Date cannot be empty");
        }
    }
}
