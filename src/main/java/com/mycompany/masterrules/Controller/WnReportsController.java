package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.finanzas.CashFlowReport;
import com.mycompany.masterrules.Model.finanzas.CashRegisterAuditReportManager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class WnReportsController implements Initializable {

    private CashRegisterAuditReportManager cashRegisterAuditReportManager = new CashRegisterAuditReportManager();

    @FXML
    private Button btnAcceptInflow;

    @FXML
    private Button btnAcceptOutflow;

    @FXML
    private Button btnDoInFlowReport;

    @FXML
    private Button btnDoOutFlowReport;

    @FXML
    private Button btnExitDoInflow;

    @FXML
    private Button btnExitDoOutflow;

    @FXML
    private AnchorPane scrDoInflowCash;

    @FXML
    private AnchorPane scrDoOutflowReport;

    @FXML
    private AnchorPane scrDoReport;

    @FXML
    private AnchorPane scrReports;

    @FXML
    private TextField txtFieldAmountInflow;

    @FXML
    private TextField txtFieldAmountOutflow;

    @FXML
    private TextArea txtOutflowReason;

    @FXML
    private TextArea txtReasonInflow;
    @FXML
    private TableView<CashFlowReport> tableViewlOutFlowReport;
    @FXML
    private TableColumn<CashFlowReport, String> TableColumnCashOutReason;
    @FXML
    private TableColumn<CashFlowReport, String> TableColumnCashOutDate;
    @FXML
    private TableColumn<CashFlowReport, String> TableColumnCashOutQuantity;
    @FXML
    private TableView<CashFlowReport> tableViewlInFlowReport;
    @FXML
    private TableColumn<CashFlowReport, String> TableColumnCashInReason;
    @FXML
    private TableColumn<CashFlowReport, String> TableColumnCashInDate;
    @FXML
    private TableColumn<CashFlowReport, String> TableColumnCashInQuantity;

    @FXML
    void exitDoInflow(ActionEvent event) {

    }

    void exitDoOutflow(ActionEvent event) {

    }

    @FXML
    void setBtnAcceptInflow(ActionEvent event) {

    }

    @FXML
    void setBtnAcceptOutflow(ActionEvent event) {

    }

    void setBtnDoInFlowReport(ActionEvent event) {

    }

    void setBtnDoOutFlowReport(ActionEvent event) {

    }

    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(btnDoInFlowReport)) {
            scrDoInflowCash.setVisible(true); // Muestra la ventana de reportes de entrada de efectivo
            scrDoReport.setVisible(false); // Oculta la ventana principal de reportes
        } else if (evt.equals(btnDoOutFlowReport)) {
            scrDoOutflowReport.setVisible(true); // Muestra la ventana de reportes de salida de efectivo
            scrDoReport.setVisible(false); // Oculta la ventana principal de reportes
        } else if (evt.equals(btnExitDoInflow)) {
            clearTextFields(txtFieldAmountInflow); // Limpia los campos de texto proporcionados
            txtReasonInflow.clear(); // Limpia el campo de texto de la razón de la entrada de efectivo
            scrDoInflowCash.setVisible(false); // Oculta la ventana de reportes de entrada de efectivo
            scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
        } else if (evt.equals(btnAcceptOutflow)) {
            setBtnAcceptOutflow();
        } else if (evt.equals(btnAcceptInflow)) {
            setBtnAcceptInflow();
        }

    }

    /**
     * Muestra la ventana principal de reportes. Sale de la ventana DoInflow.
     * Acepta la entrada de efectivo
     */
    public void setBtnAcceptInflow() {
        try {
            String cashAmount = txtFieldAmountInflow.getText();
            String reason = txtReasonInflow.getText();
            cashRegisterAuditReportManager.depositCash(reason, cashAmount);
            ObservableList<CashFlowReport> cashInReports = FXCollections.observableArrayList(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashInFlowReports());
            tableViewlInFlowReport.setItems(cashInReports);
            scrDoInflowCash.setVisible(false); // Oculta la ventana de reportes de entrada de efectivo
            scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra la ventana principal de reportes. Sale de la ventana DoOutflow.
     * Acepta la salida de efectivo
     */
    public void setBtnAcceptOutflow() {
        try {
            String cashAmount = txtFieldAmountOutflow.getText();
            String reason = txtOutflowReason.getText();
            cashRegisterAuditReportManager.withdrawCash(reason, cashAmount);
            txtFieldAmountOutflow.setText("00.0");
            txtOutflowReason.setText("");
            ObservableList<CashFlowReport> cashOutReports = FXCollections.observableArrayList(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashOutFlowReports());
            tableViewlOutFlowReport.setItems(cashOutReports);

            scrDoOutflowReport.setVisible(false); // Oculta la ventana de reportes de salida de efectivo
            scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Muestra la ventana principal de reportes. Sale de la ventana DoOutflow
     */
    public void exitDoOutflow() {
        clearTextFields(txtFieldAmountOutflow); // Limpia los campos de texto proporcionados
        txtOutflowReason.clear(); // Limpia el campo de texto de la razón de la salida de efectivo
        scrDoOutflowReport.setVisible(false); // Oculta la ventana de reportes de salida de efectivo
        scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
    }


    private void clearTextFields(TextField... textFields) {
        // Limpia cada campo de texto proporcionado
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeCashOutFlowTable();
        initializeCashInFlowTable();
    }

    // Configura la tabla y los datos para Cash Out Flow
    private void initializeCashOutFlowTable() {
        ObservableList<CashFlowReport> cashOutReports = FXCollections.observableArrayList(
                cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashOutFlowReports()
        );

        TableColumnCashOutReason.setReorderable(false);
        TableColumnCashOutReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        TableColumnCashOutDate.setReorderable(false);
        TableColumnCashOutDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumnCashOutQuantity.setReorderable(false);
        TableColumnCashOutQuantity.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));

        tableViewlOutFlowReport.setItems(cashOutReports);
    }

    // Configura la tabla y los datos para Cash In Flow
    private void initializeCashInFlowTable() {
        ObservableList<CashFlowReport> cashInReports = FXCollections.observableArrayList(
                cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashInFlowReports()
        );

        TableColumnCashInReason.setReorderable(false);
        TableColumnCashInReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        TableColumnCashInDate.setReorderable(false);
        TableColumnCashInDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumnCashInQuantity.setReorderable(false);
        TableColumnCashInQuantity.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));

        tableViewlInFlowReport.setItems(cashInReports);
    }


}
