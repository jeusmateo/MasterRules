package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.CashFlowReport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

public class WnCashRegisterAuditReport implements Initializable{

    @FXML
    private Label labelHourFrom;

    @FXML
    private Label labelHourTo;

    @FXML
    private Label labelTotalSales;

    @FXML
    private TableView<CashFlowReport> tblCashInFlow;

    @FXML
    private TableView<CashFlowReport> tblCashOutFlow;

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
    @FXML
    private TableColumn<CashFlowReport, String> colTablasakhdhjkasdhjkasjkhdsah;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

}
