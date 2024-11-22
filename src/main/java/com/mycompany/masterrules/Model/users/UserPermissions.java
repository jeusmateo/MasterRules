package com.mycompany.masterrules.Model.users;

import jakarta.persistence.Embeddable;

import java.util.EnumSet;
import java.util.Objects;


@Embeddable
public class UserPermissions {

    //falta probar si funciona con la gran cantidad de permisos que puse
    //probablemente Permissions se ponga en un archivo separado de esta clase?

    private EnumSet<Permission> grantedPermissions;//lo cambie por GrantedPermissions


    public UserPermissions() {
        grantedPermissions = EnumSet.noneOf(Permission.class);
    }

    //The type of "grantedPermissions" should be an interface such as "Set" rather than the implementation "EnumSet".
    public UserPermissions(EnumSet<Permission> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }


    public void givePermission(Permission permission) {
        grantedPermissions.add(permission);
    }


    public void removePermission(Permission permission) {
        grantedPermissions.remove(permission);
    }

    public boolean isEnabled(Permission permission) {
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
