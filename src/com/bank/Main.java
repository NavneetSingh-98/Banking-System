package com.bank;

import com.bank.exception.InsufficientFundsException;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.service.Bank;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Bank bank = new Bank("Navneet National Bank");

    public static void main(String[] args) {
        System.out.println("=== Welcome to " + bank.getName() + " ===");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1: addCustomer(); break;
                    case 2: openAccount(); break;
                    case 3: deposit(); break;
                    case 4: withdraw(); break;
                    case 5: transfer(); break;
                    case 6: statement(); break;
                    case 7: bank.listCustomers(); break;
                    case 8: bank.listAccounts(); break;
                    case 9: bank.applyInterestToAll(); System.out.println("Monthly interest applied to savings accounts."); break;
                    case 0: running = false; System.out.println("Thank you. Goodbye!"); break;
                    default: System.out.println("Invalid option.");
                }
            } catch (InsufficientFundsException e) {
                System.out.println("Transaction failed: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMenu() {
        System.out.println("1. Add customer");
        System.out.println("2. Open account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transfer");
        System.out.println("6. Account statement");
        System.out.println("7. List customers");
        System.out.println("8. List accounts");
        System.out.println("9. Apply monthly interest");
        System.out.println("0. Exit");
    }

    private static void addCustomer() {
        String name = readLine("Name: ");
        String phone = readLine("Phone: ");
        Customer c = bank.addCustomer(name, phone);
        System.out.println("Customer created: " + c);
    }

    private static void openAccount() {
        String cid = readLine("Customer ID: ");
        String type = readLine("Type (SAVINGS/CURRENT): ");
        double amt = readDouble("Opening balance: ");
        Account a = bank.openAccount(cid, type, amt);
        System.out.println("Account opened: " + a);
    }

    private static void deposit() {
        Account a = bank.findAccount(readLine("Account number: "));
        a.deposit(readDouble("Amount: "));
        System.out.printf("Deposited. New balance: %.2f%n", a.getBalance());
    }

    private static void withdraw() throws InsufficientFundsException {
        Account a = bank.findAccount(readLine("Account number: "));
        a.withdraw(readDouble("Amount: "));
        System.out.printf("Withdrawn. New balance: %.2f%n", a.getBalance());
    }

    private static void transfer() throws InsufficientFundsException {
        String from = readLine("From account: ");
        String to = readLine("To account: ");
        bank.transfer(from, to, readDouble("Amount: "));
        System.out.println("Transfer successful.");
    }

    private static void statement() {
        bank.findAccount(readLine("Account number: ")).printStatement();
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readLine(prompt)); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try { return Double.parseDouble(readLine(prompt)); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid amount."); }
        }
    }
}
