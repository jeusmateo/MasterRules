package com.mycompany.masterrules.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class WnCashRegisterAuditReport {

    @FXML
    private Label labelHourFrom;

    @FXML
    private Label labelHourTo;

    @FXML
    private Label labelTotalSales;

    @FXML
    private TableView<?> tblCashInFlow;

    @FXML
    private TableView<?> tblCashOutFlow;

    @FXML
    private Text txtCashFunds;

    @FXML
    private Text txtCashSales;

    @FXML
    private Text txtInFlowCash;

    @FXML
    private Text txtOutFlowCash;

    @FXML
    private Text txtTSCash;

    @FXML
    private Text txtTSCreditCard;

    @FXML
    private Text txtTSStoreCard;

}
