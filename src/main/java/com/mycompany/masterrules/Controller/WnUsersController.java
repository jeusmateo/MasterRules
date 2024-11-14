package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.UserAccount;
import com.mycompany.masterrules.Model.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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

    private UserManager userManager=new UserManager();

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
    private CheckBox chkMakeSalePerm;
    @FXML
    private CheckBox chkCancelSalePerm;
    @FXML
    private CheckBox chkReviewSaleHistoryPerm;
    @FXML
    private Tab tabProductsPerm;
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
    private Tab tabInventoryPerm;
    @FXML
    private CheckBox chkEditStockPerm;
    @FXML
    private CheckBox chkEditStockMinMaxPerm;
    @FXML
    private Tab tabCustomerPerm;
    @FXML
    private CheckBox chkCreateNewCustomerPerm;
    @FXML
    private CheckBox chkEditCustomerPerm;
    @FXML
    private CheckBox chkDeleteCustomerPerm;
    @FXML
    private CheckBox chkEditStoreCreditPerm;
    @FXML
    private Tab tabReportPerm;
    @FXML
    private CheckBox chkReviewCashRegisterAduitReportPerm;
    @FXML
    private CheckBox chkCreateCashInReportPerm;
    @FXML
    private CheckBox chkCreateCashOutReportPerm;
    @FXML
    private Tab tabUserPerm;
    @FXML
    private CheckBox chkCreateUserPerm;
    @FXML
    private CheckBox chkEditUserPerm;
    @FXML
    private CheckBox chkDeleteUserPerm;
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
        ObservableList<UserAccount> userAccounts = FXCollections.observableArrayList(userManager.getUserAccounts());
        colUserID.setReorderable(false);
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
   
        colUserName.setReorderable(false);
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        
        
        tblUserAccount.setItems(userAccounts);
    }
}
