package com.mycompany.masterrules.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.EnumSet;

/**
 * Clase que representa a los permisos que tiene un usuario en MasterRules
 * @author Alexandra Saavedra
 */
@Embeddable
public class UserPermissions {
    //falta probar si funciona con la gran cantidad de permisos que puse
    //probablemente Permissions se ponga en un archivo separado de esta clase?
    /**
     * Permisos que puede tener el usuario
     */
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
    /**
     * Permisos concedidos al usuario
     */
    private EnumSet<Permission> grantedPermissions;//lo cambie por GrantedPermissions

    /**
     * Constructor de la clase
     */
    public UserPermissions() {
        grantedPermissions= EnumSet.noneOf(Permission.class);
    }

    /**
     * Constructor de la clase
     * @param grantedPermissions Permisos concedidos al usuario
     */
    public UserPermissions(EnumSet<Permission> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }
    
    /**
     * Concede un permiso a un usuario
     * @param permission Permiso a concedir
     */
    public void givePermission(Permission permission){
        grantedPermissions.add(permission);
    }
    
    /**
     * Remueve permiso a un usuario
     * @param permission Permiso a remover
     */
    public void removePermission(Permission permission){
        grantedPermissions.remove(permission);
    }
    
    /**
     * Checa si un usuario tiene el permiso
     * @param permission Permiso a revisar
     * @return Verdadero, si el usuario tiene el permiso. Falso, si el usuario no tiene el permiso
     */
    public boolean isEnabled(Permission permission){
        return (grantedPermissions.contains(permission));
    }

    /**
     * Obtiene los permisos concedidos al usuario
     * @return Permisos concedidos al usuario
     */
    public EnumSet<Permission> getGrantedPermissions() {
        return grantedPermissions;
    }

    /**
     * Establece los permisos concedidos al usuario
     * @param grantedPermissions Permisos concedidos al usuario
     */
    public void setGrantedPermissions(EnumSet<Permission> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }
}
