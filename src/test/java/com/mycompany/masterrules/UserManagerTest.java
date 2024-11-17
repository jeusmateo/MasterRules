package com.mycompany.masterrules;

import com.mycompany.masterrules.Model.UserAccount;
import com.mycompany.masterrules.Model.UserManager;
import com.mycompany.masterrules.Model.UserPermissions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @Test
    void registerNewUser() {
        UserManager userManager = new UserManager();
        UserAccount newUser = new UserAccount("5", "Chepo", "Chepo", "Josue David Torres Tec");
        assertTrue(userManager.registerNewUser(newUser));
    }

    @Test
    void removeUser() {
        UserManager userManager = new UserManager();
        UserAccount newUser = new UserAccount("5", "Chepo", "Chepo", "Josue David Torres Tec");
        assertDoesNotThrow(() -> userManager.removeUser("5"));
    }

    @Test
    void changeUserPermissons() {
        UserManager userManager = new UserManager();
        UserAccount newUser = new UserAccount("5", "Chepo", "Chepo", "Josue David Torres Tec");
        assertTrue(userManager.registerNewUser(newUser));
        UserPermissions permissions = new UserPermissions();
        permissions.givePermission(UserPermissions.Permission.CREATE_USER);
        assertDoesNotThrow(() -> userManager.changeUserPermissons("5", permissions));
    }

    @Test
    void getUserAccounts() {
        UserManager userManager = new UserManager();
        userManager.registerNewUser(new UserAccount("5", "Chepo", "Chepo", "Josue David Torres Tec"));
        userManager.registerNewUser(new UserAccount("6", "Chepo", "Chepo", "Josue David Torres Tec"));

        assertNotNull(userManager.getUserAccounts());
    }
}