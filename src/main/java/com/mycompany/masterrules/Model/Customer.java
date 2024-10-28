/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.util.Random;

/**
 *
 * @author chepo
 */
public class Customer {

    private long ID;
    private String customerName;
    private String customerPhoneNumber;
    private CustomerAccount customerAccount;

    public Customer(String customerName, String customerPhoneNumber) {
        //Falta ver como generarlothis.ID = Random.nextLong(1000)+1;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccount = new CustomerAccount();
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
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

}
