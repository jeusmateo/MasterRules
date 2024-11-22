package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.finance.CashFlow;
import com.mycompany.masterrules.Model.finance.CashierSupervisor;
import com.mycompany.masterrules.Model.finance.CashRegister;

import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.retailsystem.POSManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Controlador para la ventana de reportes de caja en la aplicación.
 */
public class WnReportsController implements Initializable {

    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    /**
     * Registro de caja utilizado para las operaciones de flujo de efectivo.
     */
    private CashRegister cashRegister;

    /**
     * Supervisor de cajeros que gestiona los flujos de efectivo.
     */
    CashierSupervisor cashierSupervisor = new CashierSupervisor();

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

    // Contenedores (AnchorPanes)
    @FXML
    private AnchorPane scrDoInflowCash;
    @FXML
    private AnchorPane scrDoOutflowReport;
    @FXML
    private AnchorPane scrDoReport;

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
    private TableView<CashFlow> tblViewlOutFlowReport;
    @FXML
    private TableColumn<CashFlow, String> colCashOutReason;
    @FXML
    private TableColumn<CashFlow, String> colCashOutDate;
    @FXML
    private TableColumn<CashFlow, String> colCashOutQuantity;

    @FXML
    private TableView<CashFlow> tblViewlInFlowReport;
    @FXML
    private TableColumn<CashFlow, String> colCashInReason;
    @FXML
    private TableColumn<CashFlow, String> colCashInDate;
    @FXML
    private TableColumn<CashFlow, String> colCashInQuantity;

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    /**
     * Maneja las acciones de los botones en la interfaz de usuario.
     *
     * @param event El evento de acción.
     */
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
     * Acepta la entrada de efectivo y actualiza la tabla de flujo de entrada de efectivo.
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
          showAlert("Error de entrada de efectivo", "Ocurrió un error al procesar la entrada de efectivo: " + e.getMessage());
        }
    }

    /**
     * Acepta la salida de efectivo y actualiza la tabla de flujo de salida de efectivo.
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
    showAlert("Error de salida de efectivo", "Ocurrió un error al procesar la salida de efectivo: " + e.getMessage());
}
}

    /**
     * Sale de la ventana de reportes de salida de efectivo y muestra la ventana principal de reportes.
     */
    public void exitDoOutflow() {
        clearTextFields(txtFieldAmountOutflow); // Limpia los campos de texto proporcionados
        txtOutflowReason.clear(); // Limpia el campo de texto de la razón de la salida de efectivo
        scrDoOutflowReport.setVisible(false); // Oculta la ventana de reportes de salida de efectivo
        scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
    }

    /**
     * Limpia los campos de texto proporcionados.
     *
     * @param textFields Los campos de texto a limpiar.
     */
    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

    /**
     * Inicializa el controlador después de que su raíz haya sido procesada.
     *
     * @param location La ubicación utilizada para resolver rutas relativas para el objeto raíz, o null si no se conoce la ubicación.
     * @param resources Los recursos utilizados para localizar el objeto raíz, o null si no se han localizado recursos.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var posManager = POSManager.getInstance();
        this.cashRegister = posManager.getCashRegister();
        System.out.println(posManager.getCurrentUser());
        initializeCashOutFlowTable();
        initializeCashInFlowTable();
        configTextFields();
    }

    /**
     * Configura la tabla y los datos para el flujo de salida de efectivo.
     */
    private void initializeCashOutFlowTable() {
        ObservableList<CashFlow> cashOutReports = FXCollections.observableArrayList(
                cashierSupervisor.getCashFlows()
        );

        colCashOutReason.setReorderable(false);
        colCashOutReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        colCashOutDate.setReorderable(false);
        colCashOutDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colCashOutQuantity.setReorderable(false);
        colCashOutQuantity.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));

        tblViewlOutFlowReport.setItems(cashOutReports);
        tblViewlOutFlowReport.refresh();
    }

    /**
     * Configura la tabla y los datos para el flujo de entrada de efectivo.
     */
    private void initializeCashInFlowTable() {
        ObservableList<CashFlow> cashInReports = FXCollections.observableArrayList(
                cashierSupervisor.getCashInFlows()
        );

        colCashInReason.setReorderable(false);
        colCashInReason.setCellValueFactory(new PropertyValueFactory<>("reason"));

        colCashInDate.setReorderable(false);
        colCashInDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colCashInQuantity.setReorderable(false);
        colCashInQuantity.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));

        tblViewlInFlowReport.setItems(cashInReports);
        tblViewlInFlowReport.refresh();
    }

    /**
     * Configura los campos de texto para aceptar solo entradas numéricas.
     */
    private void configTextFields() {
        txtFieldAmountInflow.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtFieldAmountOutflow.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }

    /**
     * Maneja los eventos de teclado para los campos de texto, permitiendo solo entradas numéricas.
     *
     * @param event El evento de teclado.
     */
    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(txtFieldAmountInflow) || evt.equals(txtFieldAmountOutflow)) {
            if (!event.getCharacter().matches("\\d")) {
                event.consume();
            }
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