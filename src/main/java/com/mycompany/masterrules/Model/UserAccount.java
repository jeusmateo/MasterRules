package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Model.UserPermissions.Permission;
import jakarta.persistence.*;

/**
 * Clase que representa al usuario en MasterRules
 * @author Alexandra Saavedra
 */
@Entity
@Table(name = "Usuarios")
public class UserAccount {
    /**
     * Identificador del usuario
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String userID;//agregue esto
    /**
     * Nombre del usuario
     */
    private String username;
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
     * Constructor vacío de la clase UserAccount para Hibernate
     */
    public UserAccount() {
    }

    /**
     * Constructor de la clase
     * @param userID Identificador del usuario
     * @param username Nombre del usuario
     * @param password Contraseña del usuario
     * @param fullEmployeeName Nombre completo del empleado
     */
    public UserAccount(String userID,String username, String password, String fullEmployeeName) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullEmployeeName = fullEmployeeName;
        this.permissions = new UserPermissions();
    }

    /**
     * Constructor de la clase
     * @param userID Identificador del usuario
     * @param username Nombre del usuario
     * @param password Contraseña del usuario
     * @param permissions Permisos concedidos al usuario
     * @param fullEmployeeName Nombre completo del empleado
     */
    public UserAccount(String userID,String username, String password, UserPermissions permissions, String fullEmployeeName) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.permissions = permissions;
        this.fullEmployeeName = fullEmployeeName;
    }
    
    /**
     * Checa si el usuario tiene permiso
     * @param permission Permiso a checar
     * @return Verdadero, si el usuario tiene el permiso. Falso, si el usuario no tiene el permiso
     */
    public boolean hasPermission(Permission permission){//¿creo me decian que este ya no iba a estar? habria que verlo
        return permissions.isEnabled(permission);
    }

    /**
     * Obtiene el identificador del usuario
     * @return Identificador del usuario
     */
    public String getUserID() {
        return userID;
    }
    
    /**
     * Obtiene el nombre del usuario
     * @return Nombre del usuario
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtiene la contraseña del usuario
     * @return Contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Obtiene los permisos concedidos al usuario
     * @return Permisos concedidos al usuario
     */
    public UserPermissions getPermissions() {
        return permissions;
    }
    
    /**
     * Obtiene el nombre completo del empleado
     * @return Nombre completo del empleado
     */
    public String getFullEmployeeName() {
        return fullEmployeeName;
    }

    /**
     * Establece el identificador del usuario
     * @param userID Identificador del usuario
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    /**
     * Establece el nombre del usuario
     * @param username Nombre del usuario
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Establece la contraseña del usuario
     * @param password Contraseña del usuario
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Establece los permisos concedidos al usuario
     * @param permissions Permisos concedidos al usuario
     */
    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }

    /**
     * Establece el nombre completo del empleado
     * @param fullEmployeeName Nombre completo del empleado
     */
    public void setFullEmployeeName(String fullEmployeeName) {
        this.fullEmployeeName = fullEmployeeName;
    }
    
}
