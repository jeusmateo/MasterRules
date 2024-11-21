package com.mycompany.masterrules.Model.customers;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(length = 16)
    private String customerPhoneNumber;
    private String customerName;
    @Embedded
    private CustomerAccount customerAccount;

    public Customer(String customerName, String customerPhoneNumber, int loyaltyPoints, boolean vipStatus) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccount = new CustomerAccount(loyaltyPoints, vipStatus);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    protected Customer() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(customerPhoneNumber, customer.customerPhoneNumber) &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(customerAccount, customer.customerAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerPhoneNumber, customerName, customerAccount);
    }
}
