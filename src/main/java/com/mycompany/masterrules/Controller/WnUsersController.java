package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.UserAccount;
import com.mycompany.masterrules.Model.UserManager;
import com.mycompany.masterrules.Model.UserPermissions.Permission;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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

/**
 * Controlador de la ventana de Usuarios
 *
 * @author Jimena Garcia
 */
public class WnUsersController implements Initializable {

    private UserManager userManager = new UserManager();
    private UserAccount userEdit;


    private void setUserEdit(UserAccount userAccount) {
        this.userEdit = userAccount;
    }

    private Map<CheckBox, Permission> checkBoxPermissionMap = new HashMap();

    @FXML
    private TextField textEditUserName;

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
    private CheckBox chkMakeSalePerm; //Ya estufas
    @FXML
    private CheckBox chkCancelSalePerm; //Ya estufas 2
    @FXML
    private CheckBox chkReviewSaleHistoryPerm; //Ya estufas 3
    @FXML
    private Tab tabProductsPerm;
    @FXML
    private CheckBox chkCreateProductPerrm; //Ya estufas 4
    @FXML
    private CheckBox chkEditProductPerm;//Ya estufas 5
    @FXML
    private CheckBox chkDeleteProductPerm;//Ya estufas 6
    @FXML
    private CheckBox chkCreateComboPerm;//Ya estufas7
    @FXML
    private CheckBox chkEditComboPerm;
    @FXML
    private CheckBox chkDeleteComboPerm;
    @FXML
    private Tab tabInventoryPerm;
    @FXML
    private CheckBox chkEditStockPerm;//Ya estufas8
    @FXML
    private CheckBox chkEditStockMinMaxPerm;//Ya estufas9
    @FXML
    private Tab tabCustomerPerm;
    @FXML
    private CheckBox chkCreateNewCustomerPerm;//Ya estufas10
    @FXML
    private CheckBox chkEditCustomerPerm;//Ya estufas11
    @FXML
    private CheckBox chkDeleteCustomerPerm; //Ya estufas12
    @FXML
    private CheckBox chkEditStoreCreditPerm;//Ya estufas13
    @FXML
    private Tab tabReportPerm;
    @FXML
    private CheckBox chkReviewCashRegisterAduitReportPerm; //Ya estufas14
    @FXML
    private CheckBox chkCreateCashInReportPerm;//Ya estufas15
    @FXML
    private CheckBox chkCreateCashOutReportPerm; //Ya estufas 16
    @FXML
    private Tab tabUserPerm;
    @FXML
    private CheckBox chkCreateUserPerm;//Ya estufas 17
    @FXML
    private CheckBox chkEditUserPerm;//Ya estufas 18
    @FXML
    private CheckBox chkDeleteUserPerm;//Ya estufas 19
    @FXML
    private Tab tabSalePerm1;
    @FXML
    private CheckBox chkMakeSalePerm2;
    @FXML
    private CheckBox chkCancelSalePerm2;
    @FXML
    private CheckBox chkReviewSaleHistoryPerm2;
    @FXML
    private Tab tabProductsPerm1;
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
    private Tab tabInventoryPerm1;
    @FXML
    private Tab tabCustomerPerm1;
    @FXML
    private CheckBox chkCreateNewCustomerPerm2;
    @FXML
    private CheckBox chkEditCustomerPerm2;
    @FXML
    private CheckBox chkDeleteCustomerPerm2;
    @FXML
    private CheckBox chkEditStoreCreditPerm2;
    @FXML
    private Tab tabReportPerm1;
    @FXML
    private CheckBox chkReviewCashRegisterAduitReportPerm2;
    @FXML
    private CheckBox chkCreateCashInReportPerm2;
    @FXML
    private CheckBox chkCreateCashOutReportPerm2;
    @FXML
    private Tab tabUserPerm1;
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
    private TextField txtEditUserCompleteName;
    @FXML
    private PasswordField pwfUserPassword;
    @FXML
    private PasswordField pwfUserPasswordConfirm;
    @FXML
    private Button btnEditUser;
    @FXML
    private TextField txtFieldCreateUserCompleteName;
    @FXML
    private TextField txtFieldCreateUserName;
    @FXML
    private Button btnCreateUserAccount;

    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        try {
            if (evt.equals(btnEditUser)) {

                String editUserName = txtEditUserCompleteName.getText();
                String editUserPassword = pwfUserPassword.getText();
                String confirmUserPassword = pwfUserPasswordConfirm.getText();
                String confirUserAccountUserEdit = textEditUserName.getText();
                if (editUserPassword.equals(confirmUserPassword)) {
                    this.userEdit.setFullEmployeeName(editUserName);
                    this.userEdit.setPassword(editUserPassword);
                    this.userEdit.setUserName(confirUserAccountUserEdit);
                    userManager.updateUserInformation(this.userEdit);
                    ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getUserAccounts()); //todo NO DEBE SER ASI, EL CODIGO NO DEBERIA ESTAR LLENO DE LINEAS REPETIDAS DE ESTO
                    tblUserAccount.setItems(userAccounts);

                } else {
                    //throw Exception chepo = new Exception("Chepo");
                }

            } else if (evt.equals(btnCreateUserAccount)){
                registerUserAccount();


            }
        } catch (Exception e) {
            System.out.println("Chepo " + e.getMessage());
        }
    }


    private void configCheckBox() {
        checkBoxPermissionMap.put(chkMakeSalePerm2, Permission.MAKE_SALE);
        checkBoxPermissionMap.put(chkCancelSalePerm2, Permission.CANCEL_SALE);
        checkBoxPermissionMap.put(chkReviewSaleHistoryPerm2, Permission.LOOK_SALES_HISTORY);
        checkBoxPermissionMap.put(chkCreateProductPerrm2, Permission.CREATE_PRODUCT);
        checkBoxPermissionMap.put(chkEditProductPerm2, Permission.EDIT_PRODUCT);
        checkBoxPermissionMap.put(chkDeleteProductPerm2, Permission.DELETE_PRODUCT);
//        checkBoxPermissionMap.put(chkCreateComboPerm, Permission.CREATE_COMBO); //todo NO OLVIDAR QUITARLOS PORQUE USARA EL MISMO PERMISO PARA COMBO
        checkBoxPermissionMap.put(chkEditStockPerm2, Permission.EDIT_STOCK);
        checkBoxPermissionMap.put(chkEditStockMinMaxPerm2, Permission.EDIT_MAX_MIN);
        checkBoxPermissionMap.put(chkCreateNewCustomerPerm2, Permission.CREATE_CUSTOMER);
        checkBoxPermissionMap.put(chkEditCustomerPerm2, Permission.EDIT_CUSTOMER);
        checkBoxPermissionMap.put(chkDeleteCustomerPerm2, Permission.DELETE_CUSTOMER);
        checkBoxPermissionMap.put(chkEditStoreCreditPerm2, Permission.EDIT_CREDITS);
        checkBoxPermissionMap.put(chkReviewCashRegisterAduitReportPerm2, Permission.RECORD_CASH_AUDIT_REPORT);
        checkBoxPermissionMap.put(chkCreateCashInReportPerm2, Permission.RECORD_CASHIN);
        checkBoxPermissionMap.put(chkCreateCashOutReportPerm2, Permission.RECORD_CASHOUT);
        checkBoxPermissionMap.put(chkCreateUserPerm2, Permission.CREATE_USER);
        checkBoxPermissionMap.put(chkEditUserPerm2, Permission.EDIT_USER);
        checkBoxPermissionMap.put(chkDeleteUserPerm2, Permission.DELETE_USER);
    }

    private void configColumns() {
        colUserID.setReorderable(false);
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        colUserName.setReorderable(false);
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));


        colUserID.setReorderable(false);
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        colUserName.setReorderable(false);
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configCheckBox();
        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getUserAccounts());
        configColumns();

        tblUserAccount.setItems(userAccounts);
        // tblUserAccount.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showUserAccountInfoForEditButtonHolaJajajChepo(newValue));
        // tblUserAccount.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setUserEdit(oldValue));
    }

    private void showUserAccountInfoForEditButtonHolaJajajChepo(UserAccount userAccount) {
        this.userEdit = userAccount;
        if (userAccount == null) {
            System.out.println("Chepo23");
        }
        try {
            txtEditUserCompleteName.setText(userAccount.getFullEmployeeName());
            textEditUserName.setText(userAccount.getUserName());
            pwfUserPassword.setText(userAccount.getPassword());
            syncCheckBoxesWithPermissions(userAccount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void syncCheckBoxesWithPermissions(UserAccount userAccount) {
        // Itera sobre el Map para establecer el estado inicial de los CheckBox
        checkBoxPermissionMap.forEach((checkBox, permission) -> {
            checkBox.setSelected(userAccount.hasPermission(permission));
        });
    }

    @FXML
    private void displaySelected(javafx.scene.input.MouseEvent event) {
        UserAccount userAccount = tblUserAccount.getSelectionModel().getSelectedItem();
        showUserAccountInfoForEditButtonHolaJajajChepo(userAccount);
    }

    private void registerUserAccount() {
        String userCompleteName = txtFieldCreateUserCompleteName.getText();
        String userPassword = pwfUserPassword.getText();
        String userName = txtFieldCreateUserName.getText();
        UserAccount newUserAccount = new UserAccount(userCompleteName, userPassword, userName); // TODO Decirle a matero que no se registra por error en base de datos
        userManager.registerNewUser(newUserAccount);
        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getUserAccounts());
        tblUserAccount.setItems(userAccounts);
    }

}
