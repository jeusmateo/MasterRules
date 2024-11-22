package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.users.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controlador de la ventana de Usuarios
 */
public class WnUsersController implements Initializable {

    // --------------------------------------------------------------------------------------------

    // Atributos

    private static final String ERROR_TITLE = "Error:";
    private final UserManager userManager = new UserManager();
    private UserAccount userEdit;
    private final Map<CheckBox, Permission> checkBoxPermissionMap = new HashMap<>();
    private final Map<CheckBox, Permission> checkBoxPermissionMapToEdit = new HashMap<>();

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
    private TableView<UserAccount> tblUserAccount;
    @FXML
    private TableColumn<UserAccount, String> colUserID;
    @FXML
    private TableColumn<UserAccount, String> colUserName;

    @FXML
    private CheckBox chkMakeSalePerm;
    @FXML
    private CheckBox chkCreateProductPerm;
    @FXML
    private CheckBox chkEditProductPerm;
    @FXML
    private CheckBox chkDeleteProductPerm;
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
    private CheckBox chkMakeSaleEditPerm;
    @FXML
    private CheckBox chkCreateProductEditPerm;
    @FXML
    private CheckBox chkEditProductEditPerm;
    @FXML
    private CheckBox chkDeleteProductEditPerm;
    @FXML
    private CheckBox chkCreateNewCustomerEditPerm;
    @FXML
    private CheckBox chkEditCustomerEditPerm;
    @FXML
    private CheckBox chkDeleteCustomerEditPerm;
    @FXML
    private CheckBox chkEditStoreCreditEditPerm;
    @FXML
    private CheckBox chkReviewCashRegisterAduitReportEditPerm;
    @FXML
    private CheckBox chkCreateCashInReportEditPerm;
    @FXML
    private CheckBox chkCreateCashOutReportEditPerm;
    @FXML
    private CheckBox chkCreateUserEditPerm;
    @FXML
    private CheckBox chkEditUserEditPerm;
    @FXML
    private CheckBox chkDeleteUserEditPerm;
    @FXML
    private CheckBox chkEditStockEditPerm;
    @FXML
    private CheckBox chkEditStockMinMaxEditPerm;

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
            showAlert(ERROR_TITLE, "Ocurrió un error: " + e.getMessage());
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
            showAlert(ERROR_TITLE, "No se seleccionó ningún usuario para eliminar.");
            return;
        }

        try {
            String userIdToDelete = this.userEdit.getUserName();
            userManager.removeUser(userIdToDelete);
            refreshUserTable();
            clearPasswordFields(new PasswordField[]{pswdFieldEditUserPasswordConfirm});
            showAlert("Éxito", "Usuario eliminado correctamente.");
        } catch (UserNotFoundException e) {
            showAlert(ERROR_TITLE, "Error al eliminar usuario: " + e.getMessage());
        } catch (Exception e) {
            showAlert(ERROR_TITLE, "Error" + e.getMessage());
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
        checkBoxPermissionMapToEdit.forEach((checkBox, permission) -> {
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
    public void initialize(URL location, ResourceBundle resources) {
        configCheckBoxToCreate();
        configCheckBoxToEdit();
        configColumnsOnUserTable();
        configTextFields();
        configSearchField();

        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getAllUsers());
        tblUserAccount.setItems(userAccounts);
    }

    private void displayUserAccountInfoForEdit(UserAccount userAccount) {
        this.userEdit = userAccount;

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


    private void syncCheckBoxesWithPermissions(UserAccount userAccount) {
        if (userAccount == null) return;

        checkBoxPermissionMapToEdit.forEach((checkBox, permission) -> checkBox.setSelected(userAccount.getPermissions().isEnabled(permission)));

        checkBoxPermissionMap.forEach((checkBox, permission) -> System.out.println("CheckBox: " + checkBox.getId() + ", Permission: " + permission + ", Selected: " + userAccount.getPermissions().isEnabled(permission)));
    }

    @FXML
    private void displaySelected(javafx.scene.input.MouseEvent event) {
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


    private void configTextFields() {
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
        checkBoxPermissionMap.put(chkCreateProductPerm, Permission.CREATE_PRODUCT);
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

    private void configCheckBoxToEdit() {
        checkBoxPermissionMapToEdit.put(chkMakeSaleEditPerm, Permission.MAKE_SALE);
        checkBoxPermissionMapToEdit.put(chkCreateProductEditPerm, Permission.CREATE_PRODUCT);
        checkBoxPermissionMapToEdit.put(chkEditProductEditPerm, Permission.EDIT_PRODUCT);
        checkBoxPermissionMapToEdit.put(chkDeleteProductEditPerm, Permission.DELETE_PRODUCT);
        checkBoxPermissionMapToEdit.put(chkEditStockEditPerm, Permission.EDIT_STOCK);
        checkBoxPermissionMapToEdit.put(chkEditStockMinMaxEditPerm, Permission.EDIT_MAX_MIN);
        checkBoxPermissionMapToEdit.put(chkCreateNewCustomerEditPerm, Permission.CREATE_CUSTOMER);
        checkBoxPermissionMapToEdit.put(chkEditCustomerEditPerm, Permission.EDIT_CUSTOMER);
        checkBoxPermissionMapToEdit.put(chkDeleteCustomerEditPerm, Permission.DELETE_CUSTOMER);
        checkBoxPermissionMapToEdit.put(chkEditStoreCreditEditPerm, Permission.EDIT_CREDITS);
        checkBoxPermissionMapToEdit.put(chkReviewCashRegisterAduitReportEditPerm, Permission.RECORD_CASH_AUDIT_REPORT);
        checkBoxPermissionMapToEdit.put(chkCreateCashInReportEditPerm, Permission.RECORD_CASHIN);
        checkBoxPermissionMapToEdit.put(chkCreateCashOutReportEditPerm, Permission.RECORD_CASHOUT);
        checkBoxPermissionMapToEdit.put(chkCreateUserEditPerm, Permission.CREATE_USER);
        checkBoxPermissionMapToEdit.put(chkEditUserEditPerm, Permission.EDIT_USER);
        checkBoxPermissionMapToEdit.put(chkDeleteUserEditPerm, Permission.DELETE_USER);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
