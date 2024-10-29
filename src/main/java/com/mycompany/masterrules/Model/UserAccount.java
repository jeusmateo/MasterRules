package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Model.UserPermissions.Permission;

/**
 *
 * @author alexs
 */
public class UserAccount {
    private String username;
    private String password;
    private UserPermissions permissions;
    private String employeeName;

    public UserAccount(String username, String password,UserPermissions permissions) {
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.permissions = new UserPermissions();
    }
    
    public boolean hasPermission(Permission permission){
        return permissions.isEnabled(permission);
    }
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserPermissions getPermissions() {
        return permissions;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
    
}
