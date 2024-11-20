package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.finanzas.CashFlowReport;
import com.mycompany.masterrules.Model.finanzas.CashFlowReportManager;
import com.mycompany.masterrules.Model.finanzas.CashRegister;

import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.possystem.POSManager;
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

    //Atributos de la clase
    // --------------------------------------------------------------------------------------------

    private final CashRegister cashRegister = new CashRegister();
    CashFlowReportManager cashFlowReportManager = new CashFlowReportManager();

    // Componentes de la vista
    // --------------------------------------------------------------------------------------------

    // Botones relacionados con inflow y outflow
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

    // Contenedores (AnchorPanes)
    @FXML
    private AnchorPane scrDoInflowCash;
    @FXML
    private AnchorPane scrDoOutflowReport;
    @FXML
    private AnchorPane scrDoReport;
    @FXML
    private AnchorPane scrReports;

    // Campos de texto
    @FXML
    private TextField txtFieldAmountInflow;
    @FXML
    private TextField txtFieldAmountOutflow;
    @FXML
    private TextArea txtOutflowReason;
    @FXML
    private TextArea txtReasonInflow;

    // Tablas y columnas
    @FXML
    private TableView<CashFlowReport> tableViewlOutFlowReport;
    @FXML
    private TableColumn<CashFlowReport, String> colCashOutReason;
    @FXML
    private TableColumn<CashFlowReport, String> colCashOutDate;
    @FXML
    private TableColumn<CashFlowReport, String> colCashOutQuantity;

    @FXML
    private TableView<CashFlowReport> tableViewlInFlowReport;
    @FXML
    private TableColumn<CashFlowReport, String> colCashInReason;
    @FXML
    private TableColumn<CashFlowReport, String> colCashInDate;
    @FXML
    private TableColumn<CashFlowReport, String> colCashInQuantity;

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------


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
           cashRegister.depositCash(reason, cashAmount);

            initializeCashInFlowTable();
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
            cashRegister.withdrawCash(reason, cashAmount);
            txtFieldAmountOutflow.setText("00.0");
            txtOutflowReason.setText("");

            initializeCashOutFlowTable();

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
        var posManager = POSManager.getInstance();
        System.out.println(posManager.getCurrentUser());
        initializeCashOutFlowTable();
        initializeCashInFlowTable();
    }

    // Configura la tabla y los datos para Cash Out Flow
    private void initializeCashOutFlowTable() {

        ObservableList<CashFlowReport> cashOutReports = FXCollections.observableArrayList(
                cashFlowReportManager.getCashOutFlowReports()
        );

        colCashOutReason.setReorderable(false);
        colCashOutReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        colCashOutDate.setReorderable(false);
        colCashOutDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colCashOutQuantity.setReorderable(false);
        colCashOutQuantity.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));

        tableViewlOutFlowReport.setItems(cashOutReports);
        tableViewlOutFlowReport.refresh();
    }

    // Configura la tabla y los datos para Cash In Flow
    private void initializeCashInFlowTable() {
        ObservableList<CashFlowReport> cashInReports = FXCollections.observableArrayList(
                cashFlowReportManager.getCashInFlowReports()
        );

        colCashInReason.setReorderable(false);
        colCashInReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        colCashInDate.setReorderable(false);
        colCashInDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colCashInQuantity.setReorderable(false);
        colCashInQuantity.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));

        tableViewlInFlowReport.setItems(cashInReports);
        tableViewlInFlowReport.refresh();
    }


}
