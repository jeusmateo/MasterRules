package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.CashFlowReport;
import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.CashRegisterAuditReportManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class WnCashRegisterAuditReportController implements Initializable{

    private CashRegisterAuditReportManager cashRegisterAuditReportManager = new CashRegisterAuditReportManager();

    @FXML
    private Label labelHourFrom;

    @FXML
    private Label labelHourTo;

    @FXML
    private Label labelTotalSales;


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
    private TableView<CashFlowReport> tblCashInFlowReport;
    @FXML
    private TableColumn<CashFlowReport, String> colReasonCashInFlow;
    @FXML
    private TableColumn<CashFlowReport, String> colDateTimeCashInFlow;
    @FXML
    private TableColumn<CashFlowReport, String> colAmountCashInFlow;
    @FXML
    private TableView<CashFlowReport> tblCashOutFlowReport;
    @FXML
    private TableColumn<CashFlowReport, String> colReasonCashOutFlow;
    @FXML
    private TableColumn<CashFlowReport, String> colDateTimeCashOutFlow;
    @FXML
    private TableColumn<CashFlowReport, String> colAmountCashOutFlow;



    private ObservableList<CashFlowReport> cashInFlowReports = FXCollections.observableArrayList(this.cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashInFlowReports());
    private ObservableList<CashFlowReport> cashOutFlowReports = FXCollections.observableArrayList( this.cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashOutFlowReports());

    public void setCashRegisterAuditReportManager(CashRegisterAuditReportManager cashRegisterAuditReportManager) {
        this.cashRegisterAuditReportManager = cashRegisterAuditReportManager;

        // Use the manager to get the current cash flow reports
        cashInFlowReports.setAll(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashInFlowReports());
        cashOutFlowReports.setAll(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashOutFlowReports());

        // Update tables with new data
        tblCashInFlowReport.setItems(cashInFlowReports);
        tblCashOutFlowReport.setItems(cashOutFlowReports);
    }

//    public void updateCashRegisterAuditReport() {
//        // Update the text fields with the current cash register audit report data
//        txtCashFunds.setText(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCurrentCashAmount().toString());
//        txtCashSales.setText(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getTotalSales().toString());
//        txtInFlowCash.setText(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getTotalCashInFlow().toString());
//        txtOutFlowCash.setText(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getTotalCashOutFlow().toString());
//        txtTSCash.setText(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getTotalCash().toString());
//        txtTSCreditCard.setText(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getTotalCreditCard().toString());
//        txtTSStoreCard.setText(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getTotalStoreCard().toString());
//    }

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up table columns for Cash In Flow
        colReasonCashInFlow.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colDateTimeCashInFlow.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmountCashInFlow.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));

        // Set up table columns for Cash Out Flow
        colReasonCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colDateTimeCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmountCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));

        // Initially set empty tables; data will be set in setCashRegisterAuditReportManager
        tblCashInFlowReport.setItems(cashInFlowReports);
        tblCashOutFlowReport.setItems(cashOutFlowReports);
    }

}


