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
public class Login {
    private ArrayList<UserAccount> userAccounts;

    public Login() {
        this.userAccounts = new ArrayList<UserAccount>();
    }
    
    public Login(ArrayList<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
    
    /**
     * Valida al usuario cuando este ingrese al sistema de punto de venta
     * @param username Nombre del usuario ingresado
     * @param password Constraseña del usuario ingresado
     * @return Verdadero, si el nombre y constraseña del usuario son correctos. Falso, si el nombre o contraseña del usuario son incorrectos
     * @throws java.lang.Exception Si no se encuentra al usuario en el sistema de punto de venta, se lanza un error
     */
    public boolean validateUser(String username,String password) throws Exception{//agregue este nuevo metodo para validar si existe el usuario y de serlo, la vista debe abrir la pagina que le corresponde
        //aqui probablemente se involucre la BD
        if(isUserRegistered(username)){
            //se encuentra al usuario por nombre
            UserAccount foundUser=findUser(username);

            if(password.equals(foundUser.getPassword())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Busca a un usuario
     * @param username Nombre de usuario a encontrar
     * @return Usuario encontrado
     * @throws java.lang.Exception Si el usuario no existe en el sistema de punto de venta, se lanza un error
     */
    public UserAccount findUser(String username) throws Exception{
        //aqui encuentra en la BD el usuario, de manera que la vista puede ver los permisos que tiene que operar
        for(UserAccount registeredUser : userAccounts){
            if(username.equals(registeredUser.getUserName())){
                return registeredUser;
            }
        }
        throw new Exception("ERROR: El usuario no existe");//quite return null por una exception
    }
    
    public boolean isUserRegistered(String username){
        for(UserAccount registeredUser : userAccounts){
            if(username.equals(registeredUser.getUserName())){
                return true;
            }
        }
        return false;
    }
}
