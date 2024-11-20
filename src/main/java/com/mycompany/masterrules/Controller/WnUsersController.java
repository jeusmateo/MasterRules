package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.users.UserAccount;
import com.mycompany.masterrules.Model.users.UserManager;
import com.mycompany.masterrules.Model.users.UserNotFoundException;
import com.mycompany.masterrules.Model.users.UserPermissions;
import com.mycompany.masterrules.Model.users.UserPermissions.Permission;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

/**
 * Controlador de la ventana de Usuarios
 */
public class WnUsersController implements Initializable {

    private UserManager userManager = new UserManager();
    private UserAccount userEdit;

    private Map<CheckBox, Permission> checkBoxPermissionMap = new HashMap<>();
    private Map<CheckBox, Permission> checkBoxPermissionMap2 = new HashMap<>();

    @FXML
    private Button btnEditUser;
    @FXML
    private Button btnCreateUserAccount;
    @FXML
    private Button btnDeleteUserAccount;

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

    private void setUserEdit(UserAccount userAccount) {
        this.userEdit = userAccount;
    }

    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        try {
            if (evt.equals(btnEditUser)) {
                String editUserName = txtEditUserCompleteName.getText();
                String editUserPassword = pswdFieldEditUserAccount.getText();
                String confirmUserPassword = pswdFieldEditUserPasswordConfirm.getText();
                String confirUserAccountUserEdit = textEditUserName.getText();
                if (editUserPassword.equals(confirmUserPassword)) {
                    this.userEdit.setFullEmployeeName(editUserName);
                    this.userEdit.setPassword(editUserPassword);
                    this.userEdit.setUserName(confirUserAccountUserEdit);
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
                    this.userEdit.getPermissions().setGrantedPermissions(selectedPermissions);
                    userManager.updateUserInformation(this.userEdit);
                    ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getAllUsers());
                    tblUserAccount.setItems(userAccounts);
                    tblUserAccount.refresh();
                    clearFields(null, new PasswordField[]{pswdFieldEditUserPasswordConfirm});
                } else {
                    //throw Exception chepo = new Exception("Chepo");
                }

            }
            if (evt.equals(btnCreateUserAccount)) {
                String createUserCompleteName = txtFieldCreateUserCompleteName.getText();
                String createUserName = txtFieldCreateUserName.getText();
                String createUserPassword = pswdFieldCreateUserAccount.getText();
                String createUserPasswordConfirm = pswdFieldConfirmCreateUserAccount.getText();
                if (createUserPassword.equals(createUserPasswordConfirm)) {

                    EnumSet<Permission> selectedPermissions = EnumSet.noneOf(Permission.class);
                    checkBoxPermissionMap.forEach((checkBox, permission) -> {
                        if (checkBox.isSelected()) {
                            selectedPermissions.add(permission);
                        }
                    });
                    UserPermissions permissions = new UserPermissions(selectedPermissions);
                    UserAccount newUser = new UserAccount(createUserName, createUserPassword, permissions, createUserCompleteName);
                    userManager.registerNewUser(newUser);
                    clearFields(
                            new TextField[]{txtFieldCreateUserCompleteName, txtFieldCreateUserName},
                            new PasswordField[]{pswdFieldCreateUserAccount, pswdFieldConfirmCreateUserAccount},
                            checkBoxPermissionMap
                    );
                    ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getAllUsers());
                    tblUserAccount.setItems(userAccounts);
                    tblUserAccount.refresh();
                } else {
                    //throw Exception chepo = new Exception("Chepo");
                }
            }
            if (evt.equals(btnDeleteUserAccount)) {
                try {
                    if (this.userEdit != null) {
                        String userIdToDelete = this.userEdit.getUserID();
                        userManager.removeUser(userIdToDelete);
                        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getUserAccounts());
                        tblUserAccount.setItems(userAccounts);
                        tblUserAccount.refresh();
                        clearFields(null, new PasswordField[]{pswdFieldEditUserPasswordConfirm});
                    } else {
                        System.out.println("No se seleccionó ningún usuario para eliminar.");
                    }
                } catch (UserNotFoundException e) {
                    System.out.println("Error al eliminar usuario: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Chepo " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Chepo " + e.getMessage());
        }
    }

    private void configCheckBox() {
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

    private void configCheckBox2() {
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

    private void configColumns() {
        colUserID.setReorderable(false);
        colUserID.setResizable(false);
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        colUserName.setReorderable(false);
        colUserName.setReorderable(false);
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configCheckBox();
        configCheckBox2();
        configColumns();
        configTextFields();

        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getAllUsers());
        tblUserAccount.setItems(userAccounts);
    }

    private void showUserAccountInfoForEditButtonHolaJajajChepo(UserAccount userAccount) {
        this.userEdit = userAccount;
        if (userAccount == null) {
            System.out.println("Chepo23");
        }
        try {
            txtEditUserCompleteName.setText(userAccount.getFullEmployeeName());
            textEditUserName.setText(userAccount.getUserName());
            pswdFieldEditUserAccount.setText(userAccount.getPassword());
            clearFields(null, new PasswordField[]{pswdFieldEditUserPasswordConfirm});
            syncCheckBoxesWithPermissions(userAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void syncCheckBoxesWithPermissions(UserAccount userAccount) {
        if (userAccount == null) return;

        checkBoxPermissionMap2.forEach((checkBox, permission) -> checkBox.setSelected(userAccount.getPermissions().isEnabled(permission)));

        checkBoxPermissionMap.forEach((checkBox, permission) -> System.out.println("CheckBox: " + checkBox.getId() + ", Permission: " + permission + ", Selected: " + userAccount.getPermissions().isEnabled(permission)));
    }

    @FXML
    private void displaySelected(javafx.scene.input.MouseEvent event) {
        UserAccount userAccount = tblUserAccount.getSelectionModel().getSelectedItem();
        showUserAccountInfoForEditButtonHolaJajajChepo(userAccount);
    }

    private void clearFields(TextField[] textFields, PasswordField[] passwordFields, Map<CheckBox, Permission>... checkBoxMaps) {
        if (textFields != null) {
            for (TextField textField : textFields) {
                textField.clear();
            }
        }

        if (passwordFields != null) {
            for (PasswordField passwordField : passwordFields) {
                passwordField.clear();
            }
        }

        if (checkBoxMaps != null) {
            for (Map<CheckBox, Permission> checkBoxMap : checkBoxMaps) {
                checkBoxMap.keySet().forEach(checkBox -> checkBox.setSelected(false));
            }
        }
    }

    private void configTextFields(){
        txtEditUserCompleteName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtFieldCreateUserCompleteName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtFieldCreateUserCompleteName)) {
            String character = event.getCharacter();
            if (!character.matches("[\\p{L} ]") && !character.equals("\b")) { // Permite letras y espacios
                event.consume();
            } else if (character.equals("\r")) { // Si es Enter
                txtFieldCreateUserName.requestFocus();
                event.consume();
            }
        } else if (evt.equals(txtEditUserCompleteName)) {
            String character = event.getCharacter();
            if (!character.matches("[\\p{L} ]") && !character.equals("\b")) { // Permite letras y espacios
                event.consume();
            } else if (character.equals("\r")) { // Si es Enter
                textEditUserName.requestFocus();
                event.consume();
            }
        }
    }
}