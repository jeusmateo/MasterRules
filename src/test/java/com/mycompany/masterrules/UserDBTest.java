package com.mycompany.masterrules;

import com.mycompany.masterrules.Model.UserAccount;

import com.mycompany.masterrules.Database.UserDBManager;
import com.mycompany.masterrules.Model.UserPermissions;

import java.util.Objects;

/**
 * Clase para pruebas de la base de datos de usuarios
 */
public class UserDBTest {
    public static void main(String[] args) {
        UserAccount user = new UserAccount("1", "alex", "1234", "Alex S");

        // Asignar permisos
        UserPermissions permissions = new UserPermissions();
        permissions.givePermission(UserPermissions.Permission.CREATE_USER);
        user.setPermissions(permissions);

        // Insertar usuario
        UserDBManager userDBManager = new UserDBManager();
        assert userDBManager.save(user);

        // Leer usuario
        UserAccount userFound = userDBManager.findById("1");
        assert userFound != null;

        // Actualizar usuario
        user.setPassword("4321");
        assert userDBManager.update(user);

        // Leer todos los usuarios
        assert !Objects.requireNonNull(userDBManager.readAll()).isEmpty();

        // Eliminar usuario
        assert userDBManager.delete(user);

    }
}
