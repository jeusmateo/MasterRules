package com.mycompany.masterrules.Model;

import java.util.ArrayList;

/**
 * Clase que representa al conjunto de usuarios en MasterRules
 * @author alexs 
 */
public class UserManager {
    /**
     * Conjunto de usuarios en el sistema de punto de venta
     */
    private ArrayList<UserAccount> userAccounts;

    /**
     * Constructor de la clase
     */
    public UserManager() {
        userAccounts= new ArrayList<UserAccount>();
        userAccounts.add(new UserAccount("4", "Chepo", "Chepo", "Josue David Torres Tec","Chepo"));
        userAccounts.add(new UserAccount("2", "Chepo", "Chepo", "Chepito","Chepo"));
    }
    
    /**
     * Constructor de la clase
     * @param userAccounts Conjunto de usuarios en el sistema de punto de venta
     */
    public UserManager(ArrayList<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
    
    /**
     * Agrega a un usuario en el sistema de punto de venta
     * @param newUser Usuario a agregar
     * @throws java.lang.Exception Si el usuario ya esta en el sistema de punto de venta o si el nombre de usuario ya esta existe, se lanza un error
     */
    public void addUser(UserAccount newUser) throws Exception{//cambie el nombre del parametro
        if(!isUserRegistered(newUser)){
            if(!isUsernameTaken(newUser.getUserName())){//cambie el param por username
                userAccounts.add(newUser);
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
     * Remueve un usuario del sistema de punto de venta
     * @param userID Identificador del usuario a eliminar
     * @throws java.lang.Exception Si el usuario no existe en el sistema de punto de venta, se lanza un error
     */
    public void removeUser(String userID) throws Exception{
        for(int registeredUserCount=0;registeredUserCount<userAccounts.size();registeredUserCount++){//hay que ver si este nombre del indice esta bien
            if(userID.equals(userAccounts.get(registeredUserCount).getUserID())){
                userAccounts.remove(registeredUserCount);
                return;//se me olvido terminar la busqueda aqui
            }
        }
        throw new Exception("ERROR: El usuario no existe");
    }
    
    /**
     * Checa si el usuario ya esta registrado en el sistema de punto de venta
     * @param user Usuario a checar
     * @return Verdadero, si el usuario existe en el sistema de punto de venta. Falso, si el usuario no existe en el sistema
     */
    private boolean isUserRegistered(UserAccount user){
        for(UserAccount registeredUser : userAccounts){
            if(user.getUserID().equals(registeredUser.getUserID())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checa si el nombre de usuario ya esta tomado
     * @param username Nombre de usuario a checar
     * @return Verdadero, si el nombre de usuario ya esta tomado. Falso, si no fue tomado
     */
    private boolean isUsernameTaken(String username){//cambie el param por username
        for(UserAccount registeredUser : userAccounts){
            if(username.equals(registeredUser.getUserName())){
                return true;
            }
        }
        return false;
    }
    
    //movi los metodos FindUser y ValidateUser a la clase Login
    
    /**
     * Cambia los permisos de un usuario
     * @param userID Identificador del usuario al cual se le cambiara sus permisos
     * @param newPermissions Nuevos permisos del usuario
     * @throws java.lang.Exception Si el usuario no existe en el sistema de punto de venta, se lanza error
     */
    public void changeUserPermits(String userID, UserPermissions newPermissions) throws Exception{//cambie el nombre del parametro permissions  newPermission//antes se llamaba changeUserRole, tambien creo que se debe poner como parametro los permisos actualizados
        for(int registeredUserCount=0;registeredUserCount<userAccounts.size();registeredUserCount++){//hay que ver si el nombre del indice esta bien
            if(userAccounts.get(registeredUserCount).getUserID().equals(userID)){
                userAccounts.get(registeredUserCount).setPermissions(newPermissions);
                return;
            }
        }
        throw new Exception("ERROR: El usuario no existe");
    }

    /**
     * Obtiene el conjunto de usuarios en el sistema de punto de venta
     * @return Conjunto de usuarios en el sistema de punto de venta
     */
    public ArrayList<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    /**
     * Establece el conjunto de usuarios en el sistema de punto de venta
     * @param userAccounts Conjunto de usuarios en el sistema de punto de venta
     */
    public void setUserAccounts(ArrayList<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
}
