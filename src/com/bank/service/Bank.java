package com.bank.service;

import com.bank.exception.InsufficientFundsException;
import com.bank.model.*;
import java.util.LinkedHashMap;
import java.util.Map;

/** Manages customers and accounts (composition: a Bank HAS accounts and customers). */
public class Bank {
    private final String name;
    private final Map<String, Customer> customers = new LinkedHashMap<>();
    private final Map<String, Account> accounts = new LinkedHashMap<>();
    private int customerCounter = 1000;
    private int accountCounter = 5000;

    public Bank(String name) {
        this.name = name;
    }

    public Customer addCustomer(String customerName, String phone) {
        String id = "C" + (++customerCounter);
        Customer c = new Customer(id, customerName, phone);
        customers.put(id, c);
        return c;
    }

    public Account openAccount(String customerId, String type, double openingBalance) {
        Customer owner = customers.get(customerId);
        if (owner == null) {
            throw new IllegalArgumentException("No customer with ID " + customerId);
        }
        String accNo = "AC" + (++accountCounter);
        Account account;
        switch (type.toUpperCase()) {
            case "SAVINGS": account = new SavingsAccount(accNo, owner, openingBalance); break;
            case "CURRENT": account = new CurrentAccount(accNo, owner, openingBalance); break;
            default: throw new IllegalArgumentException("Unknown account type: " + type);
        }
        accounts.put(accNo, account);
        return account;
    }

    public Account findAccount(String accountNumber) {
        Account a = accounts.get(accountNumber);
        if (a == null) {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }
        return a;
    }

    public void transfer(String fromAcc, String toAcc, double amount) throws InsufficientFundsException {
        Account from = findAccount(fromAcc);
        Account to = findAccount(toAcc);
        from.transferOut(amount);
        to.transferIn(amount);
    }

    public void applyInterestToAll() {
        for (Account a : accounts.values()) {
            if (a instanceof InterestBearing) {
                ((InterestBearing) a).applyInterest();
            }
        }
    }

    public void listCustomers() {
        System.out.println("---- Customers of " + name + " ----");
        customers.values().forEach(System.out::println);
    }

    public void listAccounts() {
        System.out.println("---- Accounts of " + name + " ----");
        accounts.values().forEach(System.out::println);
    }

    public String getName() { return name; }
}
