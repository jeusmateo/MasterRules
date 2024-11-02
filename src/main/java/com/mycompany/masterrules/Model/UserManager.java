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
    public void addUser(UserAccount user) throws Exception{
        if(!isUserRegistered(user)){
            if(!isUsernameTaken(user)){
                userAccounts.add(user);
            }
            else{
                throw new Exception("ERROR: El nombre usuario ya esta tomado");
            }
        }
        else{
            throw new Exception("ERROR: EL usuario ya existe");
        }
    }
    
    /**
     * Removes a user from the Point of Sale
     * @param userID User identificator
     */
    public void removeUser(String userID) throws Exception{
        for(int registeredUserCount=0;registeredUserCount<userAccounts.size();registeredUserCount++){//hay que ver si este nombre del indice esta bien
            if(userID.equals(userAccounts.get(registeredUserCount).getUserID())){
                userAccounts.remove(registeredUserCount);
            }
        }
        throw new Exception("ERROR: El usuario no existe");
    }
    
    private boolean isUserRegistered(UserAccount user){
        for(UserAccount registeredUser : userAccounts){
            if(user.getUserID().equals(registeredUser.getUserID())){
                return true;
            }
        }
        return false;
    }
    
    private boolean isUsernameTaken(UserAccount user){
        for(UserAccount registeredUser : userAccounts){
            if(user.getUsername().equals(registeredUser.getUsername())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Validates the user
     * @param username Username
     * @param password Password
     * @return True, if the user and password is correct. False, if the user or password is incorrect
     */
    public boolean validateUser(String username,String password){//agregue este nuevo metodo para validar si existe el usuario y de serlo, la vista debe abrir la pagina que le corresponde
        //aqui probablemente se involucre la BD
        
        //se encuentra al usuario por nombre
        UserAccount foundUser=findUser(username);
        
        if(foundUser!=null && foundUser.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Searches for a user
     * @param username Username
     * @return Found user
     */
    public UserAccount findUser(String username){
        //aqui encuentra en la BD el usuario, de manera que la vista puede ver los permisos que tiene que operar
        for(UserAccount registeredUser : userAccounts){
            if(registeredUser.getUsername().equals(username)){
                return registeredUser;
            }
        }
        return null;
    }
    
    /**
     * Changes permits of a user
     * @param userID User identification
     * @param permissions New user permissions
     */
    public void changeUserPermits(String userID, UserPermissions permissions) throws Exception{//antes se llamaba changeUserRole, tambien creo que se debe poner como parametro los permisos actualizados
        for(int registeredUserCount=0;registeredUserCount<userAccounts.size();registeredUserCount++){//hay que ver si el nombre del indice esta bien
            if(userAccounts.get(registeredUserCount).getUserID().equals(userID)){
                userAccounts.get(registeredUserCount).setPermissions(permissions);
            }
        }
        throw new Exception("ERROR: El usuario no existe");
    }
}
