package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.finanzas.CashFlow;

import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.finanzas.CashRegisterAuditReportManager;
import com.mycompany.masterrules.Model.possystem.POSManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class WnCashRegisterAuditReportController implements Initializable{
    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    private final CashRegisterAuditReportManager cashRegisterAuditReportManager = POSManager.getInstance().getCashRegisterAuditReportManager();
    private final ObservableList<CashFlow> cashInFlowReports=null;
    private final ObservableList<CashFlow> cashOutFlowReports = null;


    // Componentes de la vista
    // --------------------------------------------------------------------------------------------

    @FXML
    private Label labelHourFrom;
    @FXML
    private Label labelHourTo;
    @FXML
    private Label lbTotalSale;

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
    private TableView<CashFlow> tblCashInFlowReport;
    @FXML
    private TableColumn<CashFlow, String> colReasonCashInFlow;
    @FXML
    private TableColumn<CashFlow, String> colDateTimeCashInFlow;
    @FXML
    private TableColumn<CashFlow, String> colAmountCashInFlow;
    @FXML
    private TableView<CashFlow> tblCashOutFlowReport;
    @FXML
    private TableColumn<CashFlow, String> colReasonCashOutFlow;
    @FXML
    private TableColumn<CashFlow, String> colDateTimeCashOutFlow;
    @FXML
    private TableColumn<CashFlow, String> colAmountCashOutFlow;

    @FXML
    private Button btnCloseCashRegisterAuditReport;
    @FXML
    private Button btnRealizarCorteDeVenta;

    @FXML
    private Button btnConfirmPart1;


    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

//    public void setCashRegisterAuditReportManager(CashRegisterAuditReportManager cashRegisterAuditReportManager) {
//        this.cashRegisterAuditReportManager = cashRegisterAuditReportManager;
//        tblCashInFlowReport.setItems(cashInFlowReports);
//        tblCashOutFlowReport.setItems(cashOutFlowReports);
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupCashInFlowTable();
        setupCashOutFlowTable();
        initializeTableData();
    }

    // Métod0 para configurar la tabla de Cash In Flow
    private void setupCashInFlowTable() {
        colReasonCashInFlow.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colDateTimeCashInFlow.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmountCashInFlow.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));
    }

    // Métod0 para configurar la tabla de Cash Out Flow
    private void setupCashOutFlowTable() {
        colReasonCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colDateTimeCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmountCashOutFlow.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));
    }

    // Métod0 para inicializar los datos de las tablas
    private void initializeTableData() {
        tblCashInFlowReport.setItems(cashInFlowReports);
        tblCashOutFlowReport.setItems(cashOutFlowReports);
    }

    // Métod0 para actualizar los datos de las tablas
    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        if(evt.equals(btnConfirmPart1)){
            cashRegisterAuditReportManager.generateEndOfDaySalesReport();
            var currentCashRegisterAuditReport = cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport();
            txtTSCash.setText(String.valueOf("+ $ "+currentCashRegisterAuditReport.getCashRevenue()));
            txtTSCreditCard.setText(String.valueOf("+ $ "+currentCashRegisterAuditReport.getCardRevenue()));
            txtTSStoreCard.setText(String.valueOf("+ $ "+currentCashRegisterAuditReport.getStoreCreditRevenue()));
            txtCashFunds.setText(String.valueOf("+  "+currentCashRegisterAuditReport.getCashBalance()));
            txtCashSales.setText(String.valueOf("+ $ "+currentCashRegisterAuditReport.getCashRevenue()));
            txtInFlowCash.setText(String.valueOf("+ $ "+currentCashRegisterAuditReport.getCashFlowInTotalAmount()));
            txtOutFlowCash.setText(String.valueOf("- $ "+currentCashRegisterAuditReport.getCashFlowOutTotalAmount()));
        }
        else if(evt.equals(btnRealizarCorteDeVenta)){
            cashRegisterAuditReportManager.endCashRegisterAuditReport();
        }

    }


}


