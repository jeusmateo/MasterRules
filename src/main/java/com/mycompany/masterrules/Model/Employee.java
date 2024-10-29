package com.mycompany.masterrules.Model;

/**
 * Class for instances of Employee
 * @author alexs
 */
public class Employee {
    private String fullName;
    private UserAccount account;

    /**
     * Contructor of class Product
     * @param fullName Employee's full name
     * @param account Employee's user account
     */
    public Employee(String fullName, UserAccount account) {
        this.fullName = fullName;
        this.account = account;
    }

    /**
     * Getter of Employee's full name
     * @return Employee's full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Getter of Employee's user account
     * @return Employee's user account
     */
    public UserAccount getAccount() {
        return account;
    }
    
    /**
     * Setter of Employee's full name
     * @param fullName Employee's full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Getter of Employee's user account
     * @param account Employee's user account
     */
    public void setAccount(UserAccount account) {
        this.account = account;
    }
    
}
