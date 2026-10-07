package com.bank.model;

/** Inheritance + polymorphism: allows an overdraft, unlike SavingsAccount. */
public class CurrentAccount extends Account {
    private static final double OVERDRAFT_LIMIT = 10000.0;

    public CurrentAccount(String accountNumber, Customer owner, double openingBalance) {
        super(accountNumber, owner, openingBalance);
    }

    @Override
    public String getAccountType() { return "CURRENT"; }

    @Override
    protected boolean canWithdraw(double amount) {
        return balance - amount >= -OVERDRAFT_LIMIT;
    }
}
