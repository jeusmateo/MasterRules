package com.mycompany.masterrules.Model.users;

import com.mycompany.masterrules.Database.UserDatabase;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

public class UserManager {

    private final List<UserAccount> allUsers = new ArrayList<>();
    private final UserDatabase userDatabaseManager = new UserDatabase();

    public UserManager() {
        readFromDatabase();
    }

    private void readFromDatabase() {
        allUsers.addAll(userDatabaseManager.readAll());
    }

    public void registerNewUser(UserAccount newUser) {//cambie el nombre del parametro
        if (!isValidForRegister(newUser)) {
            throw new IllegalArgumentException("Usuario no valido");
        }
        allUsers.add(newUser);
        userDatabaseManager.save(newUser);
    }

    public Optional<UserAccount> getUser(String username, String password) throws Exception {

        // hardcoding the admin user
        if (isAdmin(username, password)) {
            return Optional.of(new UserAccount("admin", "admin", new UserPermissions(EnumSet.allOf(Permission.class)), "admin"));
        }

        if (!isUserRegistered(username)) {
            throw new Exception("Usuario no registrado");
        }
        var findUser = allUsers.stream().findFirst().filter(user -> user.getUserName().equals(username) && user.getPassword().equals(password));
        if (findUser.isEmpty()) {
            throw new UserNotFoundException("Usuario no encontrado");
        }
        return findUser;
    }

    private boolean isAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin") || username.isEmpty() && password.isEmpty();
    }

    public void updateUserInformation(UserAccount editedUserAccount) {
        if (isUserRegistered(editedUserAccount.getUserName())) {
            allUsers.set(allUsers.indexOf(editedUserAccount), editedUserAccount);
            userDatabaseManager.update(editedUserAccount);
        } else {
            throw new IllegalArgumentException("Usuario no valido");
        }
    }

    private boolean isValidForRegister(UserAccount newUser) {
        return !isUserRegistered(newUser.getUserName()) && isPasswordValid(newUser.getPassword());
    }

    public void removeUser(String userID) throws UserNotFoundException {
        var findUser = allUsers.stream().findFirst().filter(user -> user.getUserName().equals(userID));

        if (findUser.isEmpty()) {
            throw new UserNotFoundException(userID + " no encontrado");
        }
        allUsers.remove(findUser.get());
        userDatabaseManager.delete(findUser.get());
    }

    private boolean isUserRegistered(String user) {
        return allUsers.stream().anyMatch(userAccount -> userAccount.getUserName().equals(user));
    }


    private boolean isUsernameTaken(String username) {//cambie el param por username
        return allUsers.stream().anyMatch(user -> user.getUserName().equals(username));
    }

    private boolean isPasswordValid(String password) {
        return !password.contains(" ") && password.length() >= 8 && password.length() <= 20 && !hasMoreThanTwoConsecutiveChars(password);
    }

    private boolean hasMoreThanTwoConsecutiveChars(String input) {
        String threeRepeatedConsecutiveChars = ".*(.)\\1\\1.*";
        return input.matches(threeRepeatedConsecutiveChars);
    }

    public void changeUserPermissons(String username, UserPermissions newPermissions) throws UserNotFoundException {
        var findUser = allUsers.stream().findFirst().filter(user -> user.getUserName().equals(username));

        if (findUser.isEmpty()) {
            throw new UserNotFoundException(username + " no encontrado");
        }
        findUser.get().setPermissions(newPermissions);
        userDatabaseManager.update(findUser.get());
    }

    public List<UserAccount> getAllUsers() {
        return allUsers;
    }
}
