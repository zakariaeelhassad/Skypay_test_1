package org.example.exception;

public class InsufficientFundsException extends RuntimeException {
    private final int currentBalance;
    private final int requestedAmount;

    public InsufficientFundsException(int currentBalance, int requestedAmount) {
        super(String.format(
                "Insufficient funds. Current balance: %d, Requested amount: %d",
                currentBalance, requestedAmount
        ));
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }
}
