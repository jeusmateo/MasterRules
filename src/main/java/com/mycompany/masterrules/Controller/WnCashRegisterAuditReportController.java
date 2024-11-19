package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.finanzas.CashFlowReport;
import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.finanzas.CashRegisterAuditReportManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class WnCashRegisterAuditReportController implements Initializable{

    private CashRegisterAuditReportManager cashRegisterAuditReportManager = new CashRegisterAuditReportManager();

    @FXML
    private Label labelHourFrom;

    @FXML
    private Label labelHourTo;


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



//    private final ObservableList<CashFlowReport> cashInFlowReports = FXCollections.observableArrayList(this.cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashInFlowReports());
//    private final ObservableList<CashFlowReport> cashOutFlowReports = FXCollections.observableArrayList( this.cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashOutFlowReports());
    private final ObservableList<CashFlowReport> cashInFlowReports=null;
    private final ObservableList<CashFlowReport> cashOutFlowReports = null;
    @FXML
    private Label lbTotalSale;
    @FXML
    private Button btnConfirmPart1;
    @FXML
    private Button btnRealizarCorteDeVenta;

    public void setCashRegisterAuditReportManager(CashRegisterAuditReportManager cashRegisterAuditReportManager) {
        this.cashRegisterAuditReportManager = cashRegisterAuditReportManager;

        // Use the manager to get the current cash flow reports
//        cashInFlowReports.setAll(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashInFlowReports());
//        cashOutFlowReports.setAll(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashOutFlowReports());

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
        setupCashInFlowTable();
        setupCashOutFlowTable();
        initializeTableData();
    }

    // Método para configurar la tabla de Cash In Flow
    private void setupCashInFlowTable() {
        colReasonCashInFlow.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colDateTimeCashInFlow.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmountCashInFlow.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));
    }

    // Método para configurar la tabla de Cash Out Flow
    private void setupCashOutFlowTable() {
        colReasonCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colDateTimeCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmountCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));
    }

    // Método para inicializar los datos de las tablas
    private void initializeTableData() {
        tblCashInFlowReport.setItems(cashInFlowReports);
        tblCashOutFlowReport.setItems(cashOutFlowReports);
    }

    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        if(evt.equals(btnRealizarCorteDeVenta)){
            cashRegisterAuditReportManager.generateEndOfDaySalesReport();
            txtTSCash.setText(String.valueOf("+ $ "+cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashRevenue()));
            txtTSCreditCard.setText(String.valueOf("+ $ "+cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCardRevenue()));
            txtTSStoreCard.setText(String.valueOf("+ $ "+cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getStoreCreditRevenue()));
            txtCashFunds.setText(String.valueOf("+  "+cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashBalance()));
            txtCashSales.setText(String.valueOf("+ $ "+cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashRevenue()));
            txtInFlowCash.setText(String.valueOf("+ $ "+cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashFlowInTotalAmount()));
            txtOutFlowCash.setText(String.valueOf("- $ "+cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashFlowOutTotalAmount()));
        }

    }


}


