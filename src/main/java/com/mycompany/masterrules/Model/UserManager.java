/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.util.ArrayList;

/**
 *
 * @author alexs
 */
public class UserManager {
    private ArrayList<UserAccount> userAccounts;

    public UserManager(ArrayList<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
    
    public void addUser(UserAccount user){
        userAccounts.add(user);
    }
    
    public void removeUser(UserAccount user){
        //luego habria que ver como remover la cuenta, tal vez la cuenta de usuario requiere de un id?
    }
    
    public boolean validateUser(String username,String password){//agregue este nuevo metodo para validar si existe el usuario y de serlo, la vista debe abrir la pagina que le corresponde
        //aqui probablemente se involucre la BD
        return false;
    }
    
    public UserAccount findUser(String username,String password){//no se si sdeben tomar en cuenta otros parámetros?. Habria que asegurar que el nombre de usuario sea distinto para todos no?
        //aqui encuentra en la BD el usuario, de manera que la vista puede ver los permisos que tiene que operar
        return null;
    }
    
    public void changeUserPermits(UserAccount user){//antes se llamaba changeUserRole, tambien creo que se debe poner como parametro los permisos actualizados
        //habria que seguir viendo lo de los permisos
    }
}
