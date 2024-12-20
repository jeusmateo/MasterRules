package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Model.users.Permission;
import com.mycompany.masterrules.Model.users.UserAccount;
import com.mycompany.masterrules.Model.users.UserManager;
import com.mycompany.masterrules.Model.users.UserPermissions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @Test
    void registerNewUser() {
        UserManager userManager = new UserManager();
        UserAccount newUser = new UserAccount("Chepo", "Chepo", "Josue David Torres Tec");
//        assertTrue(userManager.registerNewUser(newUser));
    }

    @Test
    void removeUser() {
        UserManager userManager = new UserManager();
        UserAccount newUser = new UserAccount("Chepo", "Chepo", "Josue David Torres Tec");
        assertDoesNotThrow(() -> userManager.removeUser("5"));
    }

    @Test
    void changeUserPermissons() {
        UserManager userManager = new UserManager();
        UserAccount newUser = new UserAccount("Chepo", "Chepo", "Josue David Torres Tec");
//        assertTrue(userManager.registerNewUser(newUser));
        UserPermissions permissions = new UserPermissions();
        permissions.givePermission(Permission.CREATE_USER);
        assertDoesNotThrow(() -> userManager.changeUserPermissons("5", permissions));
    }

    @Test
    void getUserAccounts() {
        UserManager userManager = new UserManager();
        userManager.registerNewUser(new UserAccount("Chepo", "Chepo", "Josue David Torres Tec"));
        userManager.registerNewUser(new UserAccount("Chepo", "Chepo", "Josue David Torres Tec"));

        assertNotNull(userManager.getAllUsers());
    }

}