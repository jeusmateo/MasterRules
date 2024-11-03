
package com.mycompany.masterrules.Controller;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Jimena Garcia
 */
public class WnPaymentController implements Initializable {


    @FXML
    private Button btnCancel;

    @FXML
    private Button btnPay;

    @FXML
    private Button btnPayMP;

    @FXML
    private Button btnPaywCreditCard;

    @FXML
    private Button btnPaywSC;

    @FXML
    private Label labelCashIncome;

    @FXML
    private Label labelChange;

    @FXML
    private Label labelReferenceNum;

    @FXML
    private Label labelTotal;

    @FXML
    private Label labelTotal1;

    @FXML
    private Label labelTotalPrice;

    @FXML
    private Label labelTotalPriceC;

    @FXML
    private Label labelTotalPriceCC;

    @FXML
    private Label labelTotalPriceSC;

    @FXML
    private PasswordField pswrdCreditAccess;

    @FXML
    private AnchorPane scrNoStoreCredit;

    @FXML
    private Tab tabCash;

    @FXML
    private TextField txtFieldCashIncome;

    @FXML
    private TextField txtFieldCashIncomeMP;

    @FXML
    private TextField txtFieldChange;

    @FXML
    private TextField txtFieldReferenceNum;

    @FXML
    private TextField txtFieldRemainingPayment;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
