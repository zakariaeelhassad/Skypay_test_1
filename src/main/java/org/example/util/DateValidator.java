package org.example.util;

import org.example.exception.InvalidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    static {
        sdf.setLenient(false); // strict parsing
    }

    // Validate new transaction date compared to today and lastTransactionDate
    public static void validate(String date, String lastTransactionDate) throws InvalidDateException {
        if (date == null || date.isEmpty()) {
            throw new InvalidDateException("Date cannot be empty.");
        }
        try {
            Date transactionDate = sdf.parse(date);
            Date today = sdf.parse(sdf.format(new Date())); // today without time

            // 1. cannot be in the past compared to today
            if (transactionDate.before(today)) {
                throw new InvalidDateException("Cannot make a transaction for a past date.");
            }

            // 2. cannot be before last transaction date
            if (lastTransactionDate != null) {
                Date lastDate = sdf.parse(lastTransactionDate);
                if (transactionDate.before(lastDate)) {
                    throw new InvalidDateException("Transaction date cannot be before last transaction date.");
                }
            }

        } catch (ParseException e) {
            throw new InvalidDateException("Invalid date format. Use dd-MM-yyyy.");
        }
    }
}
