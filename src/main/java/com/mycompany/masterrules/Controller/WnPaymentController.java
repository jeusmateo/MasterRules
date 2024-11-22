
package com.mycompany.masterrules.Controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.retailsystem.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;



/**
 * FXML Controller class
 *
 * @author Jimena Garcia
 */
public class WnPaymentController implements Initializable {

    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    private Customer customer;
    private BigDecimal totalAmount;
    private PaymentDetails resultPayementDetails;

    // Componentes de la vista
    // --------------------------------------------------------------------------------------------
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

    @FXML
    private Label labelCashIncome;
    @FXML
    private Label labelChange;
    @FXML
    private Label labelReferenceNum;
    @FXML
    private Label labelTotal;
    @FXML
    private Label labelTotal1;
    @FXML
    private Label labelTotalPrice;
    @FXML
    private Label labelTotalPriceC;
    @FXML
    private Label labelTotalPriceCC;
    @FXML
    private Label labelTotalPriceSC;

    @FXML
    private PasswordField pswrdCreditAccess;

    @FXML
    private AnchorPane scrNoStoreCredit;

    @FXML
    private Tab tabCash;
    @FXML
    private Tab tabStoreCredit;

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

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    /**
     * Initializes the controller class.
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

    public void setOrderData(BigDecimal totalAmount, Customer customerInfo) {
        if (customerInfo != null) {
            this.customer = customerInfo;
        } else {
            tabStoreCredit.setDisable(true);
        }
        this.totalAmount = totalAmount;
        labelTotalPriceCC.setText(String.valueOf(totalAmount));
        labelTotalPriceC.setText(String.valueOf(totalAmount));
        labelTotalPriceSC.setText(String.valueOf(totalAmount));
        labelTotalPrice.setText(String.valueOf(totalAmount));
    }


    @FXML
    private void handleCancelButton() {
        // Obtener el Stage (ventana) y cerrarla
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    // metodo que al no reconocer la tarjeta del cliente salta la vista scrNoStoreCredit

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
        }
    }

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

    private void handleStoreCreditPayment() {
        if (customer.getCustomerAccount().getLoyaltyCard().getAccessCode().equals(pswrdCreditAccess.getText())) {
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

    private BigDecimal parseBigDecimal(String text) {
        try {
            return text == null || text.isEmpty() ? BigDecimal.ZERO : new BigDecimal(text);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    private void addPaymentMethodToProcessor(MixPaymentProcessor processor, BigDecimal cashIncome, BigDecimal cardIncome, BigDecimal storeCreditIncome) {
        if (cardIncome.compareTo(BigDecimal.ZERO) > 0) {
            processor.addPaymentMethod(new DebitCardPaymentProcessor(cardIncome, ""));
        }
        if (cashIncome.compareTo(BigDecimal.ZERO) > 0) {
            processor.addPaymentMethod(new CashPaymentProcessor(cashIncome, cashIncome));
        }
        if (storeCreditIncome.compareTo(BigDecimal.ZERO) > 0) {
            processor.addPaymentMethod(new StoreCreditPayProcessor(storeCreditIncome, customer));
        }
    }

    private void closePaymentWindow(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public PaymentDetails getPaymentDetails(){
        return resultPayementDetails;
    }

    private void calcularCambio(String cashIncome) {
        try {
            BigDecimal cash = new BigDecimal(cashIncome); // Convertir el texto a BigDecimal
            BigDecimal change = cash.subtract(totalAmount); // Calcular el cambio
            txtFieldChange.setText(change.compareTo(BigDecimal.ZERO) >= 0 ? change.toString() : "0.00"); // Mostrar cambio si es positivo
        } catch (NumberFormatException e) {
            txtFieldChange.setText("0.00"); // Si el input no es un número válido, mostrar 0.00
        }
    }

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