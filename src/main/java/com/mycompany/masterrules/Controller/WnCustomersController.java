package com.mycompany.masterrules.Controller;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WnCustomersController {

    @FXML
    private Button btnEditAccount;

    @FXML
    private Button btnSaveNewCustomer;

    @FXML
    private Button btnShowInformation;

    @FXML
    private Button btnBackViewInfoCustomer;

    @FXML
    private Button btnBackEditCustomerAccount;

    @FXML
    private Button btnUpdateCustomerAccount;

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

    public void setBtnBackViewInfoCustomer() {
        scrMainViewCustomerAccount.setVisible(true);
        scrViewInfoCustomer.setVisible(false);
        clearTextFields(new TextField());

    }

    public void setBtnEditAccount() {
        scrEditCustomerAccount.setVisible(true);
        scrMainViewCustomerAccount.setVisible(false);

    }

    public void setBtnBackEditCustomerAccount() {
        scrMainViewCustomerAccount.setVisible(true);
        scrEditCustomerAccount.setVisible(false);
        clearTextFields();

    }

    public void setBtnUpdateCustomerAccount() {
        //setBtnBackEditCustomerAccount();//regresa a la vista principal
    }

    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear(); // Limpia cada campo de texto proporcionado
        }
    }




}
