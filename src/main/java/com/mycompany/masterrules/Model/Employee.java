/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

/**
 *
 * @author alexs
 */
public class Employee {
    private String fullName;
    private UserAccount account;

    public Employee(String fullName, UserAccount account) {
        this.fullName = fullName;
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public UserAccount getAccount() {
        return account;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAccount(UserAccount account) {
        this.account = account;
    }
    
}
