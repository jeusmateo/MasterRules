/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    public UserAccount(String username, String password,UserPermissions permissions) {
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }

    public void login(){
        //no se si este debe estar en esta clase? la verdad no se como este funcionara, habria que ver la vista
    }
    
    public void logout(){
        //no se si este debe estar en esta clase? la verdad no se como este funcionara, habria que ver la vista
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
}
