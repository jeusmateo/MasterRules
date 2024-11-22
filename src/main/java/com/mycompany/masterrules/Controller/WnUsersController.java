package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.users.UserAccount;
import com.mycompany.masterrules.Model.users.UserManager;
import com.mycompany.masterrules.Model.users.UserNotFoundException;
import com.mycompany.masterrules.Model.users.UserPermissions;
import com.mycompany.masterrules.Model.users.UserPermissions.Permission;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * Controlador de la ventana de Usuarios
 */
public class WnUsersController implements Initializable {

    // --------------------------------------------------------------------------------------------

    // Atributos

    private UserManager userManager = new UserManager();
    private UserAccount userEdit;

    private Map<CheckBox, Permission> checkBoxPermissionMap = new HashMap<>();
    private Map<CheckBox, Permission> checkBoxPermissionMap2 = new HashMap<>();

    // --------------------------------------------------------------------------------------------

    // Componentes de la vista

    @FXML
    private Button btnEditUser;
    @FXML
    private Button btnCreateUserAccount;
    @FXML
    private Button btnDeleteUserAccount;

    @FXML
    private TextField txtFieldSearchUser;
    @FXML
    private TextField textEditUserName;
    @FXML
    private TextField txtEditUserCompleteName;
    @FXML
    private TextField txtFieldCreateUserCompleteName;
    @FXML
    private TextField txtFieldCreateUserName;

    @FXML
    private AnchorPane verCliente;
    @FXML
    private AnchorPane verCliente1;

    @FXML
    private TableView<UserAccount> tblUserAccount;
    @FXML
    private TableColumn<UserAccount, String> colUserID;
    @FXML
    private TableColumn<UserAccount, String> colUserName;

    @FXML
    private Tab tabSalePerm;
    @FXML
    private Tab tabProductsPerm;
    @FXML
    private Tab tabInventoryPerm;
    @FXML
    private Tab tabCustomerPerm;
    @FXML
    private Tab tabReportPerm;
    @FXML
    private Tab tabUserPerm;
    @FXML
    private Tab tabSalePerm1;
    @FXML
    private Tab tabProductsPerm1;
    @FXML
    private Tab tabInventoryPerm1;
    @FXML
    private Tab tabCustomerPerm1;
    @FXML
    private Tab tabReportPerm1;
    @FXML
    private Tab tabUserPerm1;

    @FXML
    private CheckBox chkMakeSalePerm;
    @FXML
    private CheckBox chkCancelSalePerm;
    @FXML
    private CheckBox chkReviewSaleHistoryPerm;
    @FXML
    private CheckBox chkCreateProductPerrm;
    @FXML
    private CheckBox chkEditProductPerm;
    @FXML
    private CheckBox chkDeleteProductPerm;
    @FXML
    private CheckBox chkCreateComboPerm;
    @FXML
    private CheckBox chkEditComboPerm;
    @FXML
    private CheckBox chkDeleteComboPerm;
    @FXML
    private CheckBox chkEditStockPerm;
    @FXML
    private CheckBox chkEditStockMinMaxPerm;
    @FXML
    private CheckBox chkCreateNewCustomerPerm;
    @FXML
    private CheckBox chkEditCustomerPerm;
    @FXML
    private CheckBox chkDeleteCustomerPerm;
    @FXML
    private CheckBox chkEditStoreCreditPerm;
    @FXML
    private CheckBox chkReviewCashRegisterAduitReportPerm;
    @FXML
    private CheckBox chkCreateCashInReportPerm;
    @FXML
    private CheckBox chkCreateCashOutReportPerm;
    @FXML
    private CheckBox chkCreateUserPerm;
    @FXML
    private CheckBox chkEditUserPerm;
    @FXML
    private CheckBox chkDeleteUserPerm;
    @FXML
    private CheckBox chkMakeSalePerm2;
    @FXML
    private CheckBox chkCancelSalePerm2;
    @FXML
    private CheckBox chkReviewSaleHistoryPerm2;
    @FXML
    private CheckBox chkCreateProductPerrm2;
    @FXML
    private CheckBox chkEditProductPerm2;
    @FXML
    private CheckBox chkDeleteProductPerm2;
    @FXML
    private CheckBox chkCreateComboPerm2;
    @FXML
    private CheckBox chkEditComboPerm2;
    @FXML
    private CheckBox chkDeleteComboPerm2;
    @FXML
    private CheckBox chkCreateNewCustomerPerm2;
    @FXML
    private CheckBox chkEditCustomerPerm2;
    @FXML
    private CheckBox chkDeleteCustomerPerm2;
    @FXML
    private CheckBox chkEditStoreCreditPerm2;
    @FXML
    private CheckBox chkReviewCashRegisterAduitReportPerm2;
    @FXML
    private CheckBox chkCreateCashInReportPerm2;
    @FXML
    private CheckBox chkCreateCashOutReportPerm2;
    @FXML
    private CheckBox chkCreateUserPerm2;
    @FXML
    private CheckBox chkEditUserPerm2;
    @FXML
    private CheckBox chkDeleteUserPerm2;
    @FXML
    private CheckBox chkEditStockPerm2;
    @FXML
    private CheckBox chkEditStockMinMaxPerm2;

    @FXML
    private PasswordField pswdFieldCreateUserAccount;
    @FXML
    private PasswordField pswdFieldConfirmCreateUserAccount;
    @FXML
    private PasswordField pswdFieldEditUserPasswordConfirm;
    @FXML
    private PasswordField pswdFieldEditUserAccount;

    // --------------------------------------------------------------------------------------------


    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        try {
            if (evt.equals(btnEditUser)) {
                handleEditUser();
            } else if (evt.equals(btnCreateUserAccount)) {
                handleCreateUserAccount();
            } else if (evt.equals(btnDeleteUserAccount)) {
                handleDeleteUserAccount();
            }
        } catch (Exception e) {
            Logger.getLogger(WnUsersController.class.getName()).log(Level.SEVERE, "Error: " + e.getMessage(), e);
            showAlert("Error", "Ocurrió un error: " + e.getMessage());
        }
    }

    private void handleEditUser() {
    String editUserName = txtEditUserCompleteName.getText();
    String editUserPassword = pswdFieldEditUserAccount.getText();
    String confirmUserPassword = pswdFieldEditUserPasswordConfirm.getText();
    String confirUserAccountUserEdit = textEditUserName.getText();

    if (!editUserPassword.equals(confirmUserPassword)) {
        throw new IllegalArgumentException("Error: Las contraseñas no coinciden.");
    }

    updateUser(editUserName, editUserPassword, confirUserAccountUserEdit);
    refreshUserTable();
    clearPasswordFields(new PasswordField[]{pswdFieldEditUserPasswordConfirm});
}



    private void handleCreateUserAccount() throws IllegalArgumentException {
        String createUserCompleteName = txtFieldCreateUserCompleteName.getText();
        String createUserName = txtFieldCreateUserName.getText();
        String createUserPassword = pswdFieldCreateUserAccount.getText();
        String createUserPasswordConfirm = pswdFieldConfirmCreateUserAccount.getText();

        if (!createUserPassword.equals(createUserPasswordConfirm)) {
            throw new IllegalArgumentException("Error: Las contraseñas no coinciden.");
        }

        createNewUser(createUserCompleteName, createUserName, createUserPassword);
        refreshUserTable();
        clearTextFields(new TextField[]{txtFieldCreateUserCompleteName, txtFieldCreateUserName});
        clearPasswordFields(new PasswordField[]{pswdFieldCreateUserAccount, pswdFieldConfirmCreateUserAccount});
        clearCheckBoxes(checkBoxPermissionMap);
    }


    private void createNewUser(String fullName, String userName, String password) {
        EnumSet<Permission> selectedPermissions = getSelectedPermissions();
        UserPermissions permissions = new UserPermissions(selectedPermissions);
        UserAccount newUser = new UserAccount(userName, password, permissions, fullName);
        userManager.registerNewUser(newUser);
    }

    private void updateUser(String name, String password, String userName) {
        this.userEdit.setFullEmployeeName(name);
        this.userEdit.setPassword(password);
        this.userEdit.setUserName(userName);

        EnumSet<Permission> selectedPermissions = getSelectedPermissions();
        this.userEdit.getPermissions().setGrantedPermissions(selectedPermissions);
        userManager.updateUserInformation(this.userEdit);
    }

private void handleDeleteUserAccount() {
    if (this.userEdit == null) {
        Logger.getLogger(WnUsersController.class.getName()).log(Level.INFO, "No se seleccionó ningún usuario para eliminar.");
        showAlert("Error", "No se seleccionó ningún usuario para eliminar.");
        return;
    }

    try {
        String userIdToDelete = this.userEdit.getUserName();
        userManager.removeUser(userIdToDelete);
        refreshUserTable();
        clearPasswordFields(new PasswordField[]{pswdFieldEditUserPasswordConfirm});
        showAlert("Éxito", "Usuario eliminado correctamente.");
    } catch (UserNotFoundException e) {
        showAlert("Error", "Error al eliminar usuario: " + e.getMessage());
    } catch (Exception e) {
        showAlert("Error", "Error: " + e.getMessage());
    }
}

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtFieldCreateUserCompleteName)) {
            handleTextFieldKeyEvent(event, txtFieldCreateUserName);
        } else if (evt.equals(txtEditUserCompleteName)) {
            handleTextFieldKeyEvent(event, textEditUserName);
        }
    }

    private void handleTextFieldKeyEvent(KeyEvent event, TextField nextField) {
        String character = event.getCharacter();
        if (isInvalidCharacter(character)) {
            event.consume();
        } else if (character.equals("\r")) { // Maneja la tecla Enter
            nextField.requestFocus();
            event.consume();
        }
    }

    private boolean isInvalidCharacter(String character) {
        // Permite letras, espacios y retroceso (borrar)
        return !character.matches("[\\p{L} ]") && !character.equals("\b");
    }


    private EnumSet<Permission> getSelectedPermissions() {
        EnumSet<Permission> selectedPermissions = EnumSet.noneOf(Permission.class);
        checkBoxPermissionMap.forEach((checkBox, permission) -> {
            if (checkBox.isSelected()) {
                selectedPermissions.add(permission);
            }
        });
        checkBoxPermissionMap2.forEach((checkBox, permission) -> {
            if (checkBox.isSelected()) {
                selectedPermissions.add(permission);
            }
        });
        return selectedPermissions;
    }

    private void refreshUserTable() {
        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getAllUsers());
        tblUserAccount.setItems(userAccounts);
        tblUserAccount.refresh();
    }


        @Override
        public void initialize (URL location, ResourceBundle resources){
            configCheckBoxToCreate();
            configCheckBoxToUpdate();
            configColumnsOnUserTable();
            configTextFields();
            configSearchField();

            ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getAllUsers());
            tblUserAccount.setItems(userAccounts);
        }

    private void displayUserAccountInfoForEdit(UserAccount userAccount) {
        this.userEdit = userAccount;

        if (userAccount == null) {
            System.out.println("Chepo23");
            return;
        }

        try {
            txtEditUserCompleteName.setText(userAccount.getFullEmployeeName());
            textEditUserName.setText(userAccount.getUserName());
            pswdFieldEditUserAccount.setText(userAccount.getPassword());
            clearPasswordFields(new PasswordField[]{pswdFieldEditUserPasswordConfirm}); // Reemplazo aquí
            syncCheckBoxesWithPermissions(userAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private void syncCheckBoxesWithPermissions (UserAccount userAccount){
            if (userAccount == null) return;

            checkBoxPermissionMap2.forEach((checkBox, permission) -> checkBox.setSelected(userAccount.getPermissions().isEnabled(permission)));

            checkBoxPermissionMap.forEach((checkBox, permission) -> System.out.println("CheckBox: " + checkBox.getId() + ", Permission: " + permission + ", Selected: " + userAccount.getPermissions().isEnabled(permission)));
        }

        @FXML
        private void displaySelected(javafx.scene.input.MouseEvent event){
            UserAccount userAccount = tblUserAccount.getSelectionModel().getSelectedItem();
            displayUserAccountInfoForEdit(userAccount);
        }

    private void configSearchField() {
        txtFieldSearchUser.textProperty().addListener((observable, oldValue, newValue) -> {
            filterUserTable(newValue);
        });
    }

    private void filterUserTable(String searchText) {
        ObservableList<UserAccount> allUsers = FXCollections.observableArrayList(userManager.getAllUsers());
        if (searchText == null || searchText.isEmpty()) {
            tblUserAccount.setItems(allUsers);
        } else {
            String lowerCaseSearchText = searchText.toLowerCase();
            ObservableList<UserAccount> filteredUsers = allUsers.filtered(user ->
                    user.getUserName().toLowerCase().contains(lowerCaseSearchText) ||
                            user.getFullEmployeeName().toLowerCase().contains(lowerCaseSearchText)
            );
            tblUserAccount.setItems(filteredUsers);
        }
    }

    private void clearTextFields(TextField[] textFields) {
        if (textFields != null) {
            for (TextField textField : textFields) {
                textField.clear();
            }
        }
    }

    private void clearPasswordFields(PasswordField[] passwordFields) {
        if (passwordFields != null) {
            for (PasswordField passwordField : passwordFields) {
                passwordField.clear();
            }
        }
    }

    private void clearCheckBoxes(Map<CheckBox, Permission> checkBoxMap) {
        if (checkBoxMap != null) {
            checkBoxMap.keySet().forEach(checkBox -> checkBox.setSelected(false));
        }
    }


    private void configTextFields () {
        txtEditUserCompleteName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtFieldCreateUserCompleteName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }

    private void configColumnsOnUserTable() {
        colUserID.setReorderable(false);
        colUserID.setResizable(false);
        colUserID.setCellValueFactory(new PropertyValueFactory<>("id"));

        colUserName.setReorderable(false);
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    private void configCheckBoxToCreate() {
        checkBoxPermissionMap.put(chkMakeSalePerm, Permission.MAKE_SALE);
        checkBoxPermissionMap.put(chkCancelSalePerm, Permission.CANCEL_SALE);
        checkBoxPermissionMap.put(chkReviewSaleHistoryPerm, Permission.LOOK_SALES_HISTORY);
        checkBoxPermissionMap.put(chkCreateProductPerrm, Permission.CREATE_PRODUCT);
        checkBoxPermissionMap.put(chkEditProductPerm, Permission.EDIT_PRODUCT);
        checkBoxPermissionMap.put(chkDeleteProductPerm, Permission.DELETE_PRODUCT);
        checkBoxPermissionMap.put(chkEditStockPerm, Permission.EDIT_STOCK);
        checkBoxPermissionMap.put(chkEditStockMinMaxPerm, Permission.EDIT_MAX_MIN);
        checkBoxPermissionMap.put(chkCreateNewCustomerPerm, Permission.CREATE_CUSTOMER);
        checkBoxPermissionMap.put(chkEditCustomerPerm, Permission.EDIT_CUSTOMER);
        checkBoxPermissionMap.put(chkDeleteCustomerPerm, Permission.DELETE_CUSTOMER);
        checkBoxPermissionMap.put(chkEditStoreCreditPerm, Permission.EDIT_CREDITS);
        checkBoxPermissionMap.put(chkReviewCashRegisterAduitReportPerm, Permission.RECORD_CASH_AUDIT_REPORT);
        checkBoxPermissionMap.put(chkCreateCashInReportPerm, Permission.RECORD_CASHIN);
        checkBoxPermissionMap.put(chkCreateCashOutReportPerm, Permission.RECORD_CASHOUT);
        checkBoxPermissionMap.put(chkCreateUserPerm, Permission.CREATE_USER);
        checkBoxPermissionMap.put(chkEditUserPerm, Permission.EDIT_USER);
        checkBoxPermissionMap.put(chkDeleteUserPerm, Permission.DELETE_USER);
    }

    private void configCheckBoxToUpdate() {
        checkBoxPermissionMap2.put(chkMakeSalePerm2, Permission.MAKE_SALE);
        checkBoxPermissionMap2.put(chkCancelSalePerm2, Permission.CANCEL_SALE);
        checkBoxPermissionMap2.put(chkReviewSaleHistoryPerm2, Permission.LOOK_SALES_HISTORY);
        checkBoxPermissionMap2.put(chkCreateProductPerrm2, Permission.CREATE_PRODUCT);
        checkBoxPermissionMap2.put(chkEditProductPerm2, Permission.EDIT_PRODUCT);
        checkBoxPermissionMap2.put(chkDeleteProductPerm2, Permission.DELETE_PRODUCT);
        checkBoxPermissionMap2.put(chkEditStockPerm2, Permission.EDIT_STOCK);
        checkBoxPermissionMap2.put(chkEditStockMinMaxPerm2, Permission.EDIT_MAX_MIN);
        checkBoxPermissionMap2.put(chkCreateNewCustomerPerm2, Permission.CREATE_CUSTOMER);
        checkBoxPermissionMap2.put(chkEditCustomerPerm2, Permission.EDIT_CUSTOMER);
        checkBoxPermissionMap2.put(chkDeleteCustomerPerm2, Permission.DELETE_CUSTOMER);
        checkBoxPermissionMap2.put(chkEditStoreCreditPerm2, Permission.EDIT_CREDITS);
        checkBoxPermissionMap2.put(chkReviewCashRegisterAduitReportPerm2, Permission.RECORD_CASH_AUDIT_REPORT);
        checkBoxPermissionMap2.put(chkCreateCashInReportPerm2, Permission.RECORD_CASHIN);
        checkBoxPermissionMap2.put(chkCreateCashOutReportPerm2, Permission.RECORD_CASHOUT);
        checkBoxPermissionMap2.put(chkCreateUserPerm2, Permission.CREATE_USER);
        checkBoxPermissionMap2.put(chkEditUserPerm2, Permission.EDIT_USER);
        checkBoxPermissionMap2.put(chkDeleteUserPerm2, Permission.DELETE_USER);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
