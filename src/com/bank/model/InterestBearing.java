package com.bank.model;

/** Interface: any account that can earn interest implements this contract. */
public interface InterestBearing {
    double getInterestRate();     // annual rate in percent
    void applyInterest();
}
