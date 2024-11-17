package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Database.UserDBManager;

import java.util.ArrayList;
import java.util.List;


public class UserManager {

    private final UserDBManager userDBManager = new UserDBManager();

    // TODO: dos constructores, uno sin parámetros y otro con un ArrayList de UserAccount?

    public UserManager() {
        // todo: usuarios temporales
        userDBManager.save(new UserAccount("4", "Chepo", "Chepo", "Josue David Torres Tec","Chepo"));
        userDBManager.save(new UserAccount("2", "Chepo", "Chepo", "Chepito","Chepo"));
    }


    public UserManager(List<UserAccount> userAccounts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    public void registerNewUser(UserAccount newUser) {//cambie el nombre del parametro
        userDBManager.save(newUser);

    }

    private boolean isValid(UserAccount newUser) {
        return !isUserRegistered(newUser) && isUsernameValid(newUser.getUserName()) && isPasswordValid(newUser.getPassword());
    }


    public void removeUser(String userID) throws Exception{
        var findUser = userDBManager.findById(userID);
        if(findUser==null){
            throw new UserNotFoundException(userID + " no encontrado");
        }
        userDBManager.delete(findUser);
    }


    private boolean isUserRegistered(UserAccount user){
        return userDBManager.findById(user.getUserID()) != null;
    }

    private boolean isUsernameValid(String username){//se agrego este nuevo mateodo para validar el username bajo ciertos estandares
        return !username.contains(" ") && username.length() >= 6 && username.length() <= 20 && !isUsernameTaken(username);
    }


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


    public void changeUserPermissons(String userID, UserPermissions newPermissions) throws Exception{//cambie el nombre del parametro permissions  newPermission//antes se llamaba changeUserRole, tambien creo que se debe poner como parametro los permisos actualizados
        for (UserAccount userAccount : getUserAccounts()) {
            if (userAccount.getUserID().equals(userID)) {
                userAccount.setPermissions(newPermissions);
                return;
            }
        }
        throw new UserNotFoundException(userID + " no encontrado");
    }

    public List<UserAccount> getUserAccounts() {
        return userDBManager.readAll();
    }
}
