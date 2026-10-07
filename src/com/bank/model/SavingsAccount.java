package com.bank.model;

/** Inheritance: SavingsAccount IS-A Account. Cannot go below a minimum balance. */
public class SavingsAccount extends Account implements InterestBearing {
    private static final double MIN_BALANCE = 500.0;
    private static final double INTEREST_RATE = 4.0;

    public SavingsAccount(String accountNumber, Customer owner, double openingBalance) {
        super(accountNumber, owner, openingBalance);
    }

    @Override
    public String getAccountType() { return "SAVINGS"; }

    @Override
    protected boolean canWithdraw(double amount) {
        return balance - amount >= MIN_BALANCE;
    }

    @Override
    public double getInterestRate() { return INTEREST_RATE; }

    @Override
    public void applyInterest() {
        double interest = balance * INTEREST_RATE / 100.0 / 12.0; // one month
        balance += interest;
        record(TransactionType.INTEREST, interest);
    }
}
