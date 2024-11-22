package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.UserDatabase;
import com.mycompany.masterrules.Model.users.Permission;
import com.mycompany.masterrules.Model.users.UserAccount;

import com.mycompany.masterrules.Model.users.UserPermissions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * Clase para pruebas de la base de datos de usuarios
 */
public class UserDatabaseTest {
    @Test
    void general() {
        UserAccount user = new UserAccount("alex", "1234", "Alex S");

        // Asignar permisos
        UserPermissions permissions = new UserPermissions();
        permissions.givePermission(Permission.CREATE_USER);
        user.setPermissions(permissions);

        // Insertar usuario
        UserDatabase userDatabaseManager = new UserDatabase();
        assert userDatabaseManager.save(user);

        // Leer usuario
        UserAccount userFound = userDatabaseManager.findById("1");
        assert userFound != null;
        assert userFound.equals(user); // Verificar que sea correcta la lectura

        // Actualizar usuario
        user.setPassword("4321");
        assert userDatabaseManager.update(user);

        // Leer todos los usuarios
        assert !Objects.requireNonNull(userDatabaseManager.readAll()).isEmpty();

        for(UserAccount userAccount : Objects.requireNonNull(userDatabaseManager.readAll())){
            System.out.println(userAccount);
        }

        // Eliminar usuario
        assert userDatabaseManager.delete(user);

    }
}
