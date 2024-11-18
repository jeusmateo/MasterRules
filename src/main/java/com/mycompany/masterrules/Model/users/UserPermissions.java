package com.mycompany.masterrules.Model.users;

import jakarta.persistence.Embeddable;

import java.util.EnumSet;
import java.util.Objects;


@Embeddable
public class UserPermissions {

    //falta probar si funciona con la gran cantidad de permisos que puse
    //probablemente Permissions se ponga en un archivo separado de esta clase?

    public enum Permission{
        //Sales
        MAKE_SALE,
        CANCEL_SALE,
        LOOK_SALES_HISTORY,

        //Products
        CREATE_PRODUCT,
        EDIT_PRODUCT,
        DELETE_PRODUCT,

        //Combos
        CREATE_COMBO,
        EDIT_COMBO,
        DELETE_COMBO,

        //Clients
        CREATE_CUSTOMER,
        EDIT_CUSTOMER,
        DELETE_CUSTOMER,
        EDIT_CREDITS,

        //Inventory
        EDIT_STOCK,
        EDIT_MAX_MIN,

        //Reports
        LOOK_SALE_REPORT,
        // REVIEW_INVENTORY_REPORT, // Pendiente para implementar
        RECORD_CASHIN,
        RECORD_CASHOUT,
        RECORD_CASH_AUDIT_REPORT,
        

        //Users
        CREATE_USER,
        EDIT_USER,
        DELETE_USER,
    }

    public UserPermissions() {
        grantedPermissions= EnumSet.noneOf(Permission.class);
    }


    private EnumSet<Permission> grantedPermissions;//lo cambie por GrantedPermissions


    public UserPermissions(EnumSet<Permission> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }


    public void givePermission(Permission permission){
        grantedPermissions.add(permission);
    }


    public void removePermission(Permission permission){
        grantedPermissions.remove(permission);
    }

    public boolean isEnabled(Permission permission){
        return (grantedPermissions.contains(permission));
    }


    public EnumSet<Permission> getGrantedPermissions() {
        return grantedPermissions;
    }

    public void setGrantedPermissions(EnumSet<Permission> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }

    @Override
    public String toString() {
        return "UserPermissions{" +
                "grantedPermissions=" + grantedPermissions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPermissions that = (UserPermissions) o;
        return Objects.equals(grantedPermissions, that.grantedPermissions);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(grantedPermissions);
    }
}
