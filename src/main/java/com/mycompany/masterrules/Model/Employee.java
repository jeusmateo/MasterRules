package com.mycompany.masterrules.Model;


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
