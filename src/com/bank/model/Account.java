package com.bank.model;

import com.bank.exception.InsufficientFundsException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstraction: Account defines what every account must do,
 * while subclasses decide the withdrawal rule and account type.
 */
public abstract class Account {
    private final String accountNumber;
    private final Customer owner;
    protected double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    protected Account(String accountNumber, Customer owner, double openingBalance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = openingBalance;
    }

    // ---- Abstract methods: each subclass provides its own behaviour (polymorphism) ----
    public abstract String getAccountType();
    protected abstract boolean canWithdraw(double amount);

    // ---- Common behaviour shared by all accounts ----
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        record(TransactionType.DEPOSIT, amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (!canWithdraw(amount)) {
            throw new InsufficientFundsException(
                    "Cannot withdraw " + amount + " from " + accountNumber + ". Balance: " + balance);
        }
        balance -= amount;
        record(TransactionType.WITHDRAW, amount);
    }

    /** Used by Bank during transfers so the history shows TRANSFER_IN / TRANSFER_OUT. */
    public void transferOut(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive.");
        }
        if (!canWithdraw(amount)) {
            throw new InsufficientFundsException("Insufficient funds for transfer from " + accountNumber);
        }
        balance -= amount;
        record(TransactionType.TRANSFER_OUT, amount);
    }

    public void transferIn(double amount) {
        balance += amount;
        record(TransactionType.TRANSFER_IN, amount);
    }

    protected void record(TransactionType type, double amount) {
        transactions.add(new Transaction(type, amount, balance));
    }

    public void printStatement() {
        System.out.println("---- Statement: " + accountNumber + " (" + getAccountType() + ") ----");
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        }
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.printf("Current balance: %.2f%n", balance);
    }

    // ---- Getters (encapsulation) ----
    public String getAccountNumber() { return accountNumber; }
    public Customer getOwner() { return owner; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return Collections.unmodifiableList(transactions); }

    @Override
    public String toString() {
        return String.format("%s | %-8s | %-15s | Balance: %10.2f",
                accountNumber, getAccountType(), owner.getName(), balance);
    }
}
