package com.mycompany.masterrules.Model.users;

import com.mycompany.masterrules.Database.UserDB;

public class LoginValidator {
    private final UserDB userDBManager;

    public LoginValidator() {
        this.userDBManager = new UserDB();
    }

    private boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin") || username.isEmpty() && password.isEmpty();
    }

    public boolean validateUser(String username, String password) {
        System.out.println("Validando usuario: " + username);

        // Validación hardcodeada para administrador
        if (isAdmin(username, password)) {
            System.out.println("Usuario administrador validado.");
            return true;
        }

        // Verifica si el usuario existe usando findByUserName
        UserAccount foundUser = userDBManager.findByUserName(username);
        if (foundUser == null) {
            System.out.println("Usuario no encontrado en la base de datos.");
            return false;
        }

        System.out.println("Usuario encontrado: " + foundUser.getUserName());
        System.out.println("Comparando contraseñas...");

        // Compara las contraseñas
        if (password.equals(foundUser.getPassword())) {
            System.out.println("Contraseña válida.");
            return true;
        } else {
            System.out.println("Contraseña incorrecta.");
            return false;
        }
    }


    public UserAccount findUser(String username) throws Throwable {
        // Obtiene al usuario directamente desde la base de datos
        UserAccount foundUser = userDBManager.findById(username);
        if (foundUser == null) {
            throw new UserNotFoundException(username + " no encontrado");
        }
        return foundUser;
    }

    public boolean isUserRegistered(String username) {
        // Verifica si el usuario existe en la base de datos
        return userDBManager.findById(username) != null;
    }
}
