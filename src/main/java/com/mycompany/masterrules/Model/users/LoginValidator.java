package com.mycompany.masterrules.Model.users;

import com.mycompany.masterrules.Database.UserDatabase;

public class LoginValidator {
    private final UserDatabase userDatabaseManager;

    public LoginValidator() {
        this.userDatabaseManager = new UserDatabase();
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
        UserAccount foundUser = userDatabaseManager.findByUserName(username);
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


    public UserAccount findUser(String username) throws UserNotFoundException {
        // Obtiene al usuario directamente desde la base de datos
        UserAccount foundUser = userDatabaseManager.findById(username);
        if (foundUser == null) {
            throw new UserNotFoundException(username + " no encontrado");
        }
        return foundUser;
    }

    public boolean isUserRegistered(String username) {
        // Verifica si el usuario existe en la base de datos
        return userDatabaseManager.findById(username) != null;
    }
}
