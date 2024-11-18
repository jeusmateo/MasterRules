/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.model.users;

import java.util.ArrayList;
import java.util.List;

// todo: renombrar esta clase
public class LoginValidator {
    private final List<UserAccount> userAccounts;

    public LoginValidator() {
        userAccounts = new ArrayList<UserAccount>();
    }

    public LoginValidator(List<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    private boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin") || username.isEmpty() && password.isEmpty();
    }

    public boolean validateUser(String username, String password) throws Exception {//agregue este nuevo metodo para validar si existe el usuario y de serlo, la vista debe abrir la pagina que le corresponde
        // hardcodeado el admin y el password
        if (isAdmin(username, password)) {
            return true;
        }

        //aqui probablemente se involucre la BD
        if (!isUserRegistered(username)) {
            return false;
        }

        //se encuentra al usuario por nombre
        UserAccount foundUser = findUser(username);

        return password.equals(foundUser.getPassword());

    }

    public UserAccount findUser(String username) throws Exception {
        //aqui encuentra en la BD el usuario, de manera que la vista puede ver los permisos que tiene que operar
        for (UserAccount registeredUser : userAccounts) {
            if (username.equals(registeredUser.getUserName())) {
                return registeredUser;
            }
        }
        throw new UserNotFoundException(username + " no encontrado");
    }

    public boolean isUserRegistered(String username) {
        for (UserAccount registeredUser : userAccounts) {
            if (username.equals(registeredUser.getUserName())) {
                return true;
            }
        }
        return false;
    }
}
