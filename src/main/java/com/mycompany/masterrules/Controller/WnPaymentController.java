package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.retailsystem.*;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controlador para la ventana de pago.
 * Gestiona la visualización y procesamiento de los pagos.
 *
 * @author Jimena Garcia
 */
public class WnPaymentController implements Initializable {

    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    /**
     * Cliente que realiza el pago.
     */
    private Customer customer;

    /**
     * Monto total a pagar.
     */
    private BigDecimal totalAmount;

    /**
     * Detalles del pago resultante.
     */
    private PaymentDetails resultPayementDetails;

    // Componentes de la vista
    // --------------------------------------------------------------------------------------------

    // Botones
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnPay;
    @FXML
    private Button btnPayMP;
    @FXML
    private Button btnPaywCreditCard;
    @FXML
    private Button btnPaywSC;

    // Etiquetas
    @FXML
    private Label labelTotal;
    @FXML
    private Label labelTotalPrice;
    @FXML
    private Label labelTotalPriceC;
    @FXML
    private Label labelTotalPriceCC;
    @FXML
    private Label labelTotalPriceSC;

    // Campos de texto
    @FXML
    private PasswordField pswrdCreditAccess;
    @FXML
    private TextField txtFieldCashIncome;
    @FXML
    private TextField txtFieldCashIncomeMP;
    @FXML
    private TextField txtFieldChange;
    @FXML
    private TextField txtFieldReferenceNum;
    @FXML
    private TextField txtFieldRemainingPayment;
    @FXML
    private TextField txtFieldCardIncomeMP;
    @FXML
    private TextField txtFieldStoreCreditIncomeMP;

    // Paneles
    @FXML
    private AnchorPane scrNoStoreCredit;

    // Pestañas
    @FXML
    private Tab tabCash;
    @FXML
    private Tab tabStoreCredit;

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    /**
     * Inicializa el controlador después de que su raíz haya sido procesada.
     *
     * @param url La ubicación utilizada para resolver rutas relativas para el objeto raíz, o null si no se conoce la ubicación.
     * @param rb Los recursos utilizados para localizar el objeto raíz, o null si no se han localizado recursos.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configureNumericField(txtFieldCashIncome);
        configureNumericField(txtFieldCashIncomeMP);
        configureNumericField(txtFieldCardIncomeMP);
        configureNumericField(txtFieldStoreCreditIncomeMP);

        txtFieldCashIncome.textProperty().addListener((observable, oldValue, newValue) -> {
            calcularCambio(newValue);
        });
        ChangeListener<String> mixedPaymentListener = (observable, oldValue, newValue) -> calcularFaltanteMixto();

        txtFieldCashIncomeMP.textProperty().addListener(mixedPaymentListener);
        txtFieldCardIncomeMP.textProperty().addListener(mixedPaymentListener);
        txtFieldStoreCreditIncomeMP.textProperty().addListener(mixedPaymentListener);
    }

    /**
     * Establece los datos del pedido.
     *
     * @param totalAmount El monto total a pagar.
     * @param customerInfo La información del cliente.
     */
    public void setOrderData(BigDecimal totalAmount, Customer customerInfo) {
        if (customerInfo != null) {
            this.customer = customerInfo;
        } else {
            tabStoreCredit.setDisable(true);
            txtFieldStoreCreditIncomeMP.setDisable(true);
        }
        this.totalAmount = totalAmount;
        labelTotalPriceCC.setText(String.valueOf(totalAmount));
        labelTotalPriceC.setText(String.valueOf(totalAmount));
        labelTotalPriceSC.setText(String.valueOf(totalAmount));
        labelTotalPrice.setText(String.valueOf(totalAmount));
    }

    /**
     * Maneja el evento del botón de cancelar.
     */
    @FXML
    private void handleCancelButton() {
        // Obtener el Stage (ventana) y cerrarla
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    /**
     * Maneja las acciones de los botones en la interfaz de usuario.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        try {
            if (evt.equals(btnPay)) {
                handleCashPayment();
            } else if (evt.equals(btnPaywCreditCard)) {
                handleDebitCardPayment();
            } else if (evt.equals(btnPaywSC)) {
                handleStoreCreditPayment();
            } else if (evt.equals(btnPayMP)) {
                handleMixedPayment();
            }
        } catch (Exception e) {
            showAlert("Error de Pago", "Ocurrió un error al procesar el pago.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Maneja el pago en efectivo.
     */
    private void handleCashPayment() {
        BigDecimal cashIncome = parseBigDecimal(txtFieldCashIncome.getText());
        PaymentProcessor processor = new CashPaymentProcessor(totalAmount, cashIncome);
        try {
            this.resultPayementDetails = processor.paymentProcess();
        } catch (PaymentException e) {
            throw new RuntimeException(e);
        }
        closePaymentWindow(btnPay);
    }

    /**
     * Maneja el pago con tarjeta de débito.
     */
    private void handleDebitCardPayment() {
        String transactionReferenceNum = txtFieldReferenceNum.getText();
        PaymentProcessor processor = new DebitCardPaymentProcessor(totalAmount, transactionReferenceNum);
        try {
            this.resultPayementDetails = processor.paymentProcess();
        } catch (PaymentException e) {
            throw new RuntimeException(e);
        }
        closePaymentWindow(btnPaywCreditCard);
    }

    /**
     * Maneja el pago con crédito de la tienda.
     */
    private void handleStoreCreditPayment() {
        if (customer.getCustomerAccount().getAccessCode().equals(pswrdCreditAccess.getText())) {
            PaymentProcessor processor = new StoreCreditPayProcessor(totalAmount, customer);
            try {
                this.resultPayementDetails = processor.paymentProcess();
            } catch (PaymentException e) {
                throw new RuntimeException(e);
            }
            closePaymentWindow(btnPaywSC);
        } else {
            showAlert("Acceso Denegado", "El código de acceso es incorrecto.");
        }
    }

    /**
     * Maneja el pago mixto.
     */
    private void handleMixedPayment() {
        BigDecimal cashIncome = parseBigDecimal(txtFieldCashIncomeMP.getText());
        BigDecimal cardIncome = parseBigDecimal(txtFieldCardIncomeMP.getText());
        BigDecimal storeCreditIncome = parseBigDecimal(txtFieldStoreCreditIncomeMP.getText());

        BigDecimal totalPaid = cashIncome.add(cardIncome).add(storeCreditIncome);
        BigDecimal remainingPayment = totalAmount.subtract(totalPaid);

        if (remainingPayment.compareTo(BigDecimal.ZERO) > 0) {
            showAlert("Error de Pago", "El monto total no ha sido cubierto.");
            txtFieldRemainingPayment.setText(remainingPayment.toString());
        } else {
            MixPaymentProcessor processor = new MixPaymentProcessor(totalAmount);
            addPaymentMethodToProcessor(processor, cashIncome, cardIncome, storeCreditIncome);
            try {
                this.resultPayementDetails = processor.paymentProcess();
            } catch (PaymentException e) {
                throw new RuntimeException(e);
            }
            closePaymentWindow(btnPayMP);
        }
    }

    /**
     * Convierte una cadena de texto a BigDecimal.
     *
     * @param text El texto a convertir.
     * @return El valor BigDecimal correspondiente.
     */
    private BigDecimal parseBigDecimal(String text) {
        try {
            return text == null || text.isEmpty() ? BigDecimal.ZERO : new BigDecimal(text);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    /**
     * Agrega métodos de pago al procesador de pagos mixtos.
     *
     * @param processor El procesador de pagos mixtos.
     * @param cashIncome El ingreso en efectivo.
     * @param cardIncome El ingreso con tarjeta.
     * @param storeCreditIncome El ingreso con crédito de la tienda.
     */
    private void addPaymentMethodToProcessor(MixPaymentProcessor processor, BigDecimal cashIncome, BigDecimal cardIncome, BigDecimal storeCreditIncome) {
        if (cardIncome.compareTo(BigDecimal.ZERO) > 0) {
            processor.addPaymentMethod(new DebitCardPaymentProcessor(cardIncome, ""));
        }
        if (cashIncome.compareTo(BigDecimal.ZERO) > 0) {
            processor.addPaymentMethod(new CashPaymentProcessor(cashIncome, cashIncome));
        }
        if (storeCreditIncome.compareTo(BigDecimal.ZERO) > 0 && customer != null) {
            processor.addPaymentMethod(new StoreCreditPayProcessor(storeCreditIncome, customer));
        }
    }

    /**
     * Cierra la ventana de pago.
     *
     * @param button El botón que desencadenó el cierre.
     */
    private void closePaymentWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra una alerta con un mensaje de error.
     *
     * @param title El título de la alerta.
     * @param message El mensaje de la alerta.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Obtiene los detalles del pago.
     *
     * @return Los detalles del pago.
     */
    public PaymentDetails getPaymentDetails() {
        return resultPayementDetails;
    }

    /**
     * Calcula el cambio a devolver.
     *
     * @param cashIncome El ingreso en efectivo.
     */
    private void calcularCambio(String cashIncome) {
        try {
            BigDecimal cash = new BigDecimal(cashIncome); // Convertir el texto a BigDecimal
            BigDecimal change = cash.subtract(totalAmount); // Calcular el cambio
            txtFieldChange.setText(change.compareTo(BigDecimal.ZERO) >= 0 ? change.toString() : "0.00"); // Mostrar cambio si es positivo
        } catch (NumberFormatException e) {
            txtFieldChange.setText("0.00"); // Si el input no es un número válido, mostrar 0.00
        }
    }

    /**
     * Calcula el monto faltante en un pago mixto.
     */
    private void calcularFaltanteMixto() {
        try {
            BigDecimal cashIncome = parseBigDecimal(txtFieldCashIncomeMP.getText());
            BigDecimal cardIncome = parseBigDecimal(txtFieldCardIncomeMP.getText());
            BigDecimal storeCreditIncome = parseBigDecimal(txtFieldStoreCreditIncomeMP.getText());

            BigDecimal totalPaid = cashIncome.add(cardIncome).add(storeCreditIncome);
            BigDecimal remainingPayment = totalAmount.subtract(totalPaid);

            txtFieldRemainingPayment.setText(remainingPayment.compareTo(BigDecimal.ZERO) >= 0 ? remainingPayment.toString() : "0.00");
        } catch (NumberFormatException e) {
            txtFieldRemainingPayment.setText("0.00"); // Si hay un error en el formato, se muestra 0.00 como faltante
        }
    }

    /**
     * Configura un campo de texto para aceptar solo entradas numéricas.
     *
     * @param textField El campo de texto a configurar.
     */
    private void configureNumericField(TextField textField) {
        textField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) { // Permite solo dígitos y un punto
                return change;
            }
            return null; // Rechaza el cambio si no cumple la expresión regular
        }));
    }
}