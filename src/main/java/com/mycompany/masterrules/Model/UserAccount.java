package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Model.UserPermissions.Permission;

/**
 * Class for instances of UserAccount 
 * @author alexs
 */
public class UserAccount {
    private String username;
    private String password;
    private UserPermissions permissions;
    private String employeeName;

    /**
     * Constructor of class UserAccount
     * @param username Username
     * @param password Password
     * @param employeeName Employee's full name
     */
    public UserAccount(String username, String password, String employeeName) {
        this.username = username;
        this.password = password;
        this.employeeName = employeeName;
        this.permissions = new UserPermissions();
    }

    /**
     * Constructor of class UserAccount
     * @param username Username
     * @param password Password
     * @param permissions User's permissions
     * @param employeeName Employee's full name
     */
    public UserAccount(String username, String password, UserPermissions permissions, String employeeName) {
        this.username = username;
        this.password = password;
        this.permissions = permissions;
        this.employeeName = employeeName;
    }
    
    /**
     * Checks if the user has permission
     * @param permission Permission of the user
     * @return True, if the user has the permission. False, if the user doesn't have the permission
     */
    public boolean hasPermission(Permission permission){
        return permissions.isEnabled(permission);
    }
    
    /**
     * Getter of username
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter of password
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter of the user's permissions
     * @return User's permissions
     */
    public UserPermissions getPermissions() {
        return permissions;
    }
    
    /**
     * Getter of Employee's full name
     * @return Employee's full name
     */
    public String getEmployeeName() {
            return employeeName;
        }
    
    /**
     * Setter of the username
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Setter of password
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter of the user's permissions
     * @param permissions 
     */
    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }

    /**
     * Setter of the Employee's full name
     * @param employeeName Employee's full name
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
}
