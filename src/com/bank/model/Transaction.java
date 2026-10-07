package com.bank.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Immutable record of a single transaction. */
public final class Transaction {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private final TransactionType type;
    private final double amount;
    private final double balanceAfter;
    private final LocalDateTime timestamp;

    public Transaction(TransactionType type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("%s | %-12s | %10.2f | Balance: %10.2f",
                timestamp.format(FMT), type, amount, balanceAfter);
    }
}
