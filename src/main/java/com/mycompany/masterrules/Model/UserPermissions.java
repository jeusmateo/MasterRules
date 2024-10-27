/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.util.EnumSet;

/**
 *
 * @author alexs
 */
public class UserPermissions {//falta probar si funciona con la gran cantidad de permisos que puse
    public enum Permission{//probablemente este se ponga en un archivo separado de esta clase?
        //Sales
        MAKE_SALE,
        CANCEL_SALE,
        RECORD_CASHIN,
        RECORD_CASHOUT,
        REVIEW_SALES_HISTORY,
        REVIEW_CASH_REGISTER_HISTORY,
        
        //Products
        CREATE_PRODUCT,
        EDIT_PRODUCT,
        DELETE_PRODUCT,
        
        //Combos
        CREATE_COMBO,
        EDIT_COMBO,
        DELETE_COMBO,
        
        //Clients
        CREATE_CLIENT,
        EDIT_CLIENT,
        DELETE_CLIENT,
        EDIT_CREDITS,
        REVIEW_CLIENT_ACCOUNTS,
        
        //Inventory
        EDIT_STOCK,
        EDIT_MAX_MIN,
        
        //Reports
        REVIEW_CLIENT_REPORT,
        REVIEW_SALE_REPORT,
        REVIEW_INVENTORY_REPORT,
        
        //Users
        CREATE_USER,
        EDIT_USER,
        DELETE_USER,
    };
    private EnumSet<Permission> permissionsStatus;

    public UserPermissions() {
        permissionsStatus= EnumSet.noneOf(Permission.class);
    }

    public UserPermissions(EnumSet<Permission> permissionsStatus) {
        this.permissionsStatus = permissionsStatus;
    }
    
    public void givePermission(Permission permission){
        permissionsStatus.add(permission);
    }
    
    public void removePermission(Permission permission){
        permissionsStatus.remove(permission);
    }
    
    public boolean isEnabled(Permission permission){
        return (permissionsStatus.contains(permission));
    }

    public EnumSet<Permission> getPermissionsStatus() {
        return permissionsStatus;
    }

    public void setPermissionsStatus(EnumSet<Permission> permissionsStatus) {
        this.permissionsStatus = permissionsStatus;
    }
}
