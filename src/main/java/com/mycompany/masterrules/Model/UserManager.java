package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Database.UserDBManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa al conjunto de usuarios en MasterRules
 * @author alexs
 */
public class UserManager {
    /**
     * Conjunto de usuarios en el sistema de punto de venta
     */
    private final UserDBManager userDBManager = new UserDBManager();

    // TODO: dos constructores, uno sin parámetros y otro con un ArrayList de UserAccount?
    /**
     * Constructor de la clase
     */
    public UserManager() {
        // todo: usuarios temporales
        userDBManager.save(new UserAccount("4", "Chepo", "Chepo", "Josue David Torres Tec","Chepo"));
        userDBManager.save(new UserAccount("2", "Chepo", "Chepo", "Chepito","Chepo"));
    }

    /**
     * Constructor de la clase
     * @param userAccounts Conjunto de usuarios en el sistema de punto de venta
     */
    public UserManager(List<UserAccount> userAccounts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Agrega a un usuario en el sistema de punto de venta
     * @param newUser Usuario a agregar
     */
    public boolean registerNewUser(UserAccount newUser) {//cambie el nombre del parametro
        if (isValid(newUser)) {
            return false;
        }
        return userDBManager.save(newUser);
    }

    private boolean isValid(UserAccount newUser) {
        return !isUserRegistered(newUser) && isUsernameValid(newUser.getUserName()) && isPasswordValid(newUser.getPassword());
    }

    /**
     * Remueve un usuario del sistema de punto de venta
     * @param userID Identificador del usuario a eliminar
     * @throws java.lang.Exception Si el usuario no existe en el sistema de punto de venta, se lanza un error
     */
    public void removeUser(String userID) throws Exception{
        var findUser = userDBManager.findById(userID);
        if(findUser==null){
            throw new UserNotFoundException(userID + " no encontrado");
        }
        userDBManager.delete(findUser);
    }

    /**
     * Checa si el usuario ya esta registrado en el sistema de punto de venta
     * @param user Usuario a checar
     * @return Verdadero, si el usuario existe en el sistema de punto de venta. Falso, si el usuario no existe en el sistema
     */
    private boolean isUserRegistered(UserAccount user){
        return userDBManager.findById(user.getUserID()) != null;
    }

    private boolean isUsernameValid(String username){//se agrego este nuevo mateodo para validar el username bajo ciertos estandares
        return !username.contains(" ") && username.length() >= 6 && username.length() <= 20 && !isUsernameTaken(username);
    }

    /**
     * Checa si el nombre de usuario ya esta tomado
     * @param username Nombre de usuario a checar
     * @return Verdadero, si el nombre de usuario ya esta tomado. Falso, si no fue tomado
     */
    private boolean isUsernameTaken(String username){//cambie el param por username
        for(UserAccount registeredUser : getUserAccounts()){
            if(username.equals(registeredUser.getUserName())){
                return true;
            }
        }
        return false;
    }

    private boolean isPasswordValid(String password){
        return !password.contains(" ") && password.length() >= 8 && password.length() <= 20 && !hasMoreThanTwoConsecutiveChars(password);
    }

    private boolean hasMoreThanTwoConsecutiveChars(String input){
        String threeRepeatedConsecutiveChars=".*(.)\\1\\1.*";
        return input.matches(threeRepeatedConsecutiveChars);
    }

    //movi los metodos FindUser y ValidateUser a la clase LoginValidator

    /**
     * Cambia los permisos de un usuario
     * @param userID Identificador del usuario al cual se le cambiara sus permisos
     * @param newPermissions Nuevos permisos del usuario
     * @throws java.lang.Exception Si el usuario no existe en el sistema de punto de venta, se lanza error
     */
    public void changeUserPermissons(String userID, UserPermissions newPermissions) throws Exception{//cambie el nombre del parametro permissions  newPermission//antes se llamaba changeUserRole, tambien creo que se debe poner como parametro los permisos actualizados
        for (UserAccount userAccount : getUserAccounts()) {
            if (userAccount.getUserID().equals(userID)) {
                userAccount.setPermissions(newPermissions);
                return;
            }
        }
        throw new UserNotFoundException(userID + " no encontrado");
    }

    /**
     * Obtiene el conjunto de usuarios en el sistema de punto de venta
     * @return Conjunto de usuarios en el sistema de punto de venta
     */
    public List<UserAccount> getUserAccounts() {
        return userDBManager.readAll();
    }
}
