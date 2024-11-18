package com.mycompany.masterrules.Model.customers;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ID;
    private String customerName;
    private String customerPhoneNumber;
    @Embedded
    private CustomerAccount customerAccount;


    public Customer(String customerName, String customerPhoneNumber, int loyaltyPoints, boolean vipStatus) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccount = new CustomerAccount(loyaltyPoints, vipStatus);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(ID, customer.ID) && Objects.equals(customerName, customer.customerName) && Objects.equals(customerPhoneNumber, customer.customerPhoneNumber) && Objects.equals(customerAccount, customer.customerAccount);
    }



    protected Customer() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, customerName, customerPhoneNumber, customerAccount);
    }


    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", customerAccount=" + customerAccount +
                '}';
    }
}
