package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Model.UserPermissions.Permission;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userID;//agregue esto
    private String userName;
    private String password;
    @Embedded
    private UserPermissions permissions;
    private String fullEmployeeName;//le cambie el nombre para que indique que es el nombre completo

    public UserAccount(String username, String password, String fullEmployeeName) {
        this.userID = userID;
        this.userName = username;
        this.password = password;
        this.fullEmployeeName = fullEmployeeName;
        this.permissions = new UserPermissions();
    }

    public UserAccount(String username, String password, UserPermissions permissions, String fullEmployeeName) {
        this.userID = userID;
        this.userName = username;
        this.password = password;
        this.permissions = permissions;
        this.fullEmployeeName = fullEmployeeName;
    }

    public boolean hasPermission(Permission permission) {//¿creo me decian que este ya no iba a estar? habria que verlo
        return permissions.isEnabled(permission);
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPermissions getPermissions() {
        return permissions;
    }

    public void setPermissions(UserPermissions permissions) {
        this.permissions = permissions;
    }

    public String getFullEmployeeName() {
        return fullEmployeeName;
    }

    public void setFullEmployeeName(String fullEmployeeName) {
        this.fullEmployeeName = fullEmployeeName;
    }



    protected UserAccount() {
    }

    @Override
    public String toString() {
        return "UserAccount{"
                + "userID='" + userID + '\''
                + ", username='" + userName + '\''
                + ", password='" + password + '\''
                + ", permissions=" + permissions
                + ", fullEmployeeName='" + fullEmployeeName + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserAccount that = (UserAccount) o;
        return Objects.equals(userID, that.userID)
                && Objects.equals(userName, that.userName)
                && Objects.equals(password, that.password)
                && Objects.equals(permissions, that.permissions)
                && Objects.equals(fullEmployeeName, that.fullEmployeeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, userName, password, permissions, fullEmployeeName);
    }
}
