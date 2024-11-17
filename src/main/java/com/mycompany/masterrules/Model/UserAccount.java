package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Model.UserPermissions.Permission;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

/**
 * Clase que representa al usuario en MasterRules
 *
 * @author Alexandra Saavedra
 */
@Entity
public class UserAccount {

    /**
     * Identificador del usuario
     */
    @Id
    private String userID;//agregue esto
    /**
     * Nombre del usuario
     */
    private String userName;
    /**
     * Contraseña del usuario
     */
    private String password;
    /**
     * Permisos concedidos al usuario
     */
    @Embedded
    private UserPermissions permissions;
    /**
     * Nombre completo del empleado que es dueño de la cuenta
     */
    private String fullEmployeeName;//le cambie el nombre para que indique que es el nombre completo



    /**
     * Constructor de la clase
     *
     * @param userID Identificador del usuario
     * @param username Nombre del usuario
     * @param password Contraseña del usuario
     * @param fullEmployeeName Nombre completo del empleado
     */
    public UserAccount(String userID, String username, String password, String fullEmployeeName) {
        this.userID = userID;
        this.userName = username;
        this.password = password;
        this.fullEmployeeName = fullEmployeeName;
        this.permissions = new UserPermissions();
    }

    public UserAccount(String userID, String username, String password, String fullEmployeeName, String Chepo) {
        this.userID = userID;
        this.userName = username;
        this.password = password;
        this.fullEmployeeName = fullEmployeeName;
        this.permissions = new UserPermissions("Chepo");
    }

    /**
     * Constructor de la clase
     *
     * @param userID Identificador del usuario
     * @param username Nombre del usuario
     * @param password Contraseña del usuario
     * @param permissions Permisos concedidos al usuario
     * @param fullEmployeeName Nombre completo del empleado
     */
    public UserAccount(String userID, String username, String password, UserPermissions permissions, String fullEmployeeName) {
        this.userID = userID;
        this.userName = username;
        this.password = password;
        this.permissions = permissions;
        this.fullEmployeeName = fullEmployeeName;
    }

    /**
     * Checa si el usuario tiene permiso
     *
     * @param permission Permiso a checar
     * @return Verdadero, si el usuario tiene el permiso. Falso, si el usuario
     * no tiene el permiso
     */
    public boolean hasPermission(Permission permission) {//¿creo me decian que este ya no iba a estar? habria que verlo
        return permissions.isEnabled(permission);
    }

    /**
     * Obtiene el identificador del usuario
     *
     * @return Identificador del usuario
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Establece el identificador del usuario
     *
     * @param userID Identificador del usuario
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * Obtiene el nombre del usuario
     *
     * @return Nombre del usuario
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Establece el nombre del usuario
     *
     * @param username Nombre del usuario
     */
    public void setUserName(String username) {
        this.userName = username;
    }

    /**
     * Obtiene la contraseña del usuario
     *
     * @return Contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario
     *
     * @param password Contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene los permisos concedidos al usuario
     *
     * @return Permisos concedidos al usuario
     */
    public UserPermissions getPermissions() {
        return permissions;
    }

    /**
     * Establece los permisos concedidos al usuario
     *
     * @param permissions Permisos concedidos al usuario
     */
    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }

    /**
     * Obtiene el nombre completo del empleado
     *
     * @return Nombre completo del empleado
     */
    public String getFullEmployeeName() {
        return fullEmployeeName;
    }

    /**
     * Establece el nombre completo del empleado
     *
     * @param fullEmployeeName Nombre completo del empleado
     */
    public void setFullEmployeeName(String fullEmployeeName) {
        this.fullEmployeeName = fullEmployeeName;
    }


    // Necesario para Hibernate
    protected UserAccount() {
    }

    @Override
    public String toString() {
        return "UserAccount{"
                + "userID='" + userID + '\''
                + ", username='" + userName + '\''
                + ", password='" + password + '\''
                + ", permissions=" + permissions
                + ", fullEmployeeName='" + fullEmployeeName + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAccount that = (UserAccount) o;
        return Objects.equals(userID, that.userID)
                && Objects.equals(userName, that.userName)
                && Objects.equals(password, that.password)
                && Objects.equals(permissions, that.permissions)
                && Objects.equals(fullEmployeeName, that.fullEmployeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userName, password, permissions, fullEmployeeName);
    }
}
