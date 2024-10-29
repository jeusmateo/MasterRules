/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.util.ArrayList;

/**
 * Class for instances of UserManager
 * @author alexs 
 */
public class UserManager {
    private ArrayList<UserAccount> userAccounts;

    /**
     * Constructor of class UserManager
     * @param userAccounts User accounts
     */
    public UserManager(ArrayList<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
    
    /**
     * Adds a user in the Point of Sale
     * @param user User to add
     */
    public void addUser(UserAccount user){
        userAccounts.add(user);
    }
    
    /**
     * Removes a user from the Point of Sale
     * @param user User to remove
     */
    public void removeUser(UserAccount user){
        //luego habria que ver como remover la cuenta, tal vez la cuenta de usuario requiere de un id?
    }
    
    /**
     * Validates the user
     * @param username Username
     * @param password Password
     * @return True, if the user and password is correct. False, if the user or password is incorrect
     */
    public boolean validateUser(String username,String password){//agregue este nuevo metodo para validar si existe el usuario y de serlo, la vista debe abrir la pagina que le corresponde
        //aqui probablemente se involucre la BD
        return false;
    }
    
    /**
     * Searches for a user
     * @param username Username
     * @param password Password
     * @return Found user
     */
    public UserAccount findUser(String username,String password){//no se si sdeben tomar en cuenta otros parámetros?. Habria que asegurar que el nombre de usuario sea distinto para todos no?
        //aqui encuentra en la BD el usuario, de manera que la vista puede ver los permisos que tiene que operar
        return null;
    }
    
    /**
     * Changes permits of a user
     * @param user User to modify permits
     */
    public void changeUserPermits(UserAccount user){//antes se llamaba changeUserRole, tambien creo que se debe poner como parametro los permisos actualizados
        //habria que seguir viendo lo de los permisos
    }
}
