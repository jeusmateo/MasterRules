package com.mycompany.masterrules;

import com.mycompany.masterrules.Model.users.UserAccount;

import com.mycompany.masterrules.Database.UserDB;
import com.mycompany.masterrules.Model.users.UserPermissions;
import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * Clase para pruebas de la base de datos de usuarios
 */
public class UserDBTest {
    @Test
    void general() {
        UserAccount user = new UserAccount("alex", "1234", "Alex S");

        // Asignar permisos
        UserPermissions permissions = new UserPermissions();
        permissions.givePermission(UserPermissions.Permission.CREATE_USER);
        user.setPermissions(permissions);

        // Insertar usuario
        UserDB userDBManager = new UserDB();
        assert userDBManager.save(user);

        // Leer usuario
        UserAccount userFound = userDBManager.findById("1");
        assert userFound != null;
        assert userFound.equals(user); // Verificar que sea correcta la lectura

        // Actualizar usuario
        user.setPassword("4321");
        assert userDBManager.update(user);

        // Leer todos los usuarios
        assert !Objects.requireNonNull(userDBManager.readAll()).isEmpty();

        for(UserAccount userAccount : Objects.requireNonNull(userDBManager.readAll())){
            System.out.println(userAccount);
        }

        // Eliminar usuario
        assert userDBManager.delete(user);

    }
}
