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
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class WnCashRegisterAuditReportController implements Initializable{
    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    private CashRegisterAuditReportManager cashRegisterAuditReportManager = new CashRegisterAuditReportManager();
    private final ObservableList<CashFlowReport> cashInFlowReports=null;
    private final ObservableList<CashFlowReport> cashOutFlowReports = null;


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

    @FXML
    private Button btnCloseCashRegisterAuditReport;
    @FXML
    private Button btnRealizarCorteDeVenta;

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    public void setCashRegisterAuditReportManager(CashRegisterAuditReportManager cashRegisterAuditReportManager) {
        this.cashRegisterAuditReportManager = cashRegisterAuditReportManager;
        tblCashInFlowReport.setItems(cashInFlowReports);
        tblCashOutFlowReport.setItems(cashOutFlowReports);
    }

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


