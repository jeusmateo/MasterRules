package com.mycompany.masterrules.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.EnumSet;

/**
 * Class for instances of UserPermissions
 * @author alexs
 */
@Embeddable
public class UserPermissions {
    //falta probar si funciona con la gran cantidad de permisos que puse
    //probablemente Permissions se ponga en un archivo separado de esta clase?
    public enum Permission{
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
    private EnumSet<Permission> grantedPermissions;//lo cambie por GrantedPermissions

    /**
     * Constructor of class UserPermissions
     */
    public UserPermissions() {
        grantedPermissions= EnumSet.noneOf(Permission.class);
    }

    /**
     * Constructor of class UserPermissions
     * @param grantedPermissions Granted user's permissions
     */
    public UserPermissions(EnumSet<Permission> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }
    
    /**
     * Gives permission to a user
     * @param permission Permission to give
     */
    public void givePermission(Permission permission){
        grantedPermissions.add(permission);
    }
    
    /**
     * Denies permission to a user
     * @param permission Permission to deny
     */
    public void removePermission(Permission permission){
        grantedPermissions.remove(permission);
    }
    
    /**
     * Checks if the user has the permission
     * @param permission
     * @return 
     */
    public boolean isEnabled(Permission permission){
        return (grantedPermissions.contains(permission));
    }

    /**
     * Getter of granted user's permissions
     * @return Granted user's permissions
     */
    public EnumSet<Permission> getGrantedPermissions() {
        return grantedPermissions;
    }

    /**
     * Setter of granted user's permissions
     * @param grantedPermissions Granted user's permissions
     */
    public void setGrantedPermissions(EnumSet<Permission> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }
}
