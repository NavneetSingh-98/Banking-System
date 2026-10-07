package com.bank.model;

/** Encapsulation: fields are private and exposed through getters. */
public class Customer {
    private final String customerId;
    private String name;
    private String phone;

    public Customer(String customerId, String name, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.phone = phone;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return name + " (ID: " + customerId + ", Phone: " + phone + ")";
    }
}
