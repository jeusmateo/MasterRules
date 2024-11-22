package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.finance.CashAuditReport;
import com.mycompany.masterrules.Model.finance.CashFlow;
import com.mycompany.masterrules.Model.finance.CashRegisterAuditReportManager;
import com.mycompany.masterrules.Model.retailsystem.POSManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class WnCashRegisterAuditReportController implements Initializable {
    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    private final CashRegisterAuditReportManager cashRegisterAuditReportManager = POSManager.getInstance().getCashRegisterAuditReportManager();
    private ObservableList<CashFlow> cashInFlowReports = FXCollections.observableArrayList();
    private ObservableList<CashFlow> cashOutFlowReports = FXCollections.observableArrayList();

    private CashAuditReport currentCashAuditReport;

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

        try {
            if (evt.equals(btnConfirmPart1)) {
                currentCashAuditReport = cashRegisterAuditReportManager.generateEndOfDaySalesReport();
                txtTSCash.setText("+ $ " + currentCashAuditReport.getCashRevenue());
                txtTSCreditCard.setText("+ $ " + currentCashAuditReport.getCardRevenue());
                txtTSStoreCard.setText("+ $ " + currentCashAuditReport.getStoreCreditRevenue());
                txtCashFunds.setText("+  " + currentCashAuditReport.getCashBalance());
                txtCashSales.setText("+ $ " + currentCashAuditReport.getCashRevenue());
                txtInFlowCash.setText("+ $ " + currentCashAuditReport.getCashFlowInTotalAmount());
                txtOutFlowCash.setText("- $ " + currentCashAuditReport.getCashFlowOutTotalAmount());

                cashInFlowReports = FXCollections.observableArrayList(currentCashAuditReport.getCashFlowInReport());
                cashOutFlowReports = FXCollections.observableArrayList(currentCashAuditReport.getCashFlowOutReport());
                tblCashInFlowReport.setItems(cashInFlowReports);
                tblCashOutFlowReport.setItems(cashOutFlowReports);
                tblCashInFlowReport.refresh();
                tblCashOutFlowReport.refresh();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, hh:mm:ss a");
                String formattedDate = (currentCashAuditReport.getInitialCutofDate()).format(formatter);
                labelHourFrom.setText(formattedDate);
                LocalDateTime local = LocalDateTime.now();
                String formattedDateFinal = local.format(formatter);
                labelHourTo.setText(formattedDateFinal);
            } else if (evt.equals(btnRealizarCorteDeVenta)) {
                cashRegisterAuditReportManager.endCashRegisterAuditReport(currentCashAuditReport);
            }
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}


