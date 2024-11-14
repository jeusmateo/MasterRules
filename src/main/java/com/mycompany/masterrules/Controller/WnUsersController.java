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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador de la ventana de Usuarios
 *
 * @author Jimena Garcia
 */
public class WnUsersController implements Initializable {

    private UserManager userManager = new UserManager();

    private Map<CheckBox, Permission> checkBoxPermissionMap = new HashMap();

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
    private TableView<UserAccount> tblUserAccount2;
    @FXML
    private TableColumn<UserAccount, String> colUserID2;
    @FXML
    private TableColumn<UserAccount, String> colUserName2;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        checkBoxPermissionMap.put(chkMakeSalePerm, Permission.MAKE_SALE);
        checkBoxPermissionMap.put(chkCancelSalePerm, Permission.CANCEL_SALE);
        checkBoxPermissionMap.put(chkReviewSaleHistoryPerm, Permission.LOOK_SALES_HISTORY);
        checkBoxPermissionMap.put(chkCreateProductPerrm, Permission.CREATE_PRODUCT);
        checkBoxPermissionMap.put(chkEditProductPerm, Permission.EDIT_PRODUCT);
        checkBoxPermissionMap.put(chkDeleteProductPerm, Permission.DELETE_PRODUCT);
//        checkBoxPermissionMap.put(chkCreateComboPerm, Permission.CREATE_COMBO); //NO OLVIDAR QUITARLOS PORQUE USARA EL MISMO PERMISO PARA COMBO
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

        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getUserAccounts());
        colUserID.setReorderable(false);
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));

        colUserName.setReorderable(false);
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));

        tblUserAccount.setItems(userAccounts);
    }

    private void syncCheckBoxesWithPermissions(UserAccount userAccount) {
        // Itera sobre el Map para establecer el estado inicial de los CheckBox
        checkBoxPermissionMap.forEach((checkBox, permission) -> {
            checkBox.setSelected(userAccount.hasPermission(permission));
        });
    }
}
