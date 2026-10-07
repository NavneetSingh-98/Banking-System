package com.bank.exception;

/** Custom checked exception thrown when a withdrawal/transfer exceeds the allowed balance. */
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
