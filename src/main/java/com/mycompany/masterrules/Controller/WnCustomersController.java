package com.mycompany.masterrules.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

public class WnCustomersController {

    @FXML
    private Button btnEditAccount;

    @FXML
    private Button btnSaveNewCustomer;

    @FXML
    private Button btnShowInformation;

    @FXML
    private AnchorPane guardarClienteNvo;

    @FXML
    private AnchorPane scrCustomerAccount;

    @FXML
    private AnchorPane scrEditCustomerAccount;

    @FXML
    private AnchorPane scrMainViewCustomerAccount;

    @FXML
    private AnchorPane scrViewInfoCustomer;

    @FXML
    private AnchorPane scrWarningCredit;

    @FXML
    private Tab tabCustomerAccount;

    @FXML
    private Tab tabNewCustomer;

    public void setBtnShowInformation() {
        scrViewInfoCustomer.setVisible(true);
        scrMainViewCustomerAccount.setVisible(false);
    }

    public void setBtnEditAccount() {
        scrEditCustomerAccount.setVisible(true);
        scrMainViewCustomerAccount.setVisible(false);
    }



}
