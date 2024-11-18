
package com.mycompany.masterrules.Controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.possystem.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Jimena Garcia
 */
public class WnPaymentController implements Initializable {

    private Customer customer;
    private BigDecimal totalAmount;
    private PaymentDetails resultPayementDetails;


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



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtFieldCashIncome.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*\\.?\\d*")) { // Permite solo dígitos y un punto
                return change;
            }
            return null; // Rechaza el cambio si no cumple la expresión regular
        }));

        txtFieldCashIncome.textProperty().addListener((observable, oldValue, newValue) ->
            calcularCambio(newValue)
    );
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

    //TODO Remove this unused private "setScrNoStoreCredit" method.
    private void setScrNoStoreCredit() {
        scrNoStoreCredit.setVisible(true);
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
        if (evt.equals(btnPay)) {
            BigDecimal cashIncome = new BigDecimal(txtFieldCashIncome.getText());
            PaymentProcessor processor = new CashPaymentProcessor(totalAmount, cashIncome);
            this.resultPayementDetails= processor.paymentProcess();
            Stage stage = (Stage) btnPay.getScene().getWindow();
            stage.close();
        } else if (evt.equals(btnPaywCreditCard)) {
            String transactionReferenceNum = txtFieldReferenceNum.getText();
            PaymentProcessor processor = new DebitCardPaymenthProcessor(totalAmount, totalAmount, transactionReferenceNum);
            this.resultPayementDetails= processor.paymentProcess();
            Stage stage = (Stage) btnPaywCreditCard.getScene().getWindow();
            stage.close();
        } else if (evt.equals(btnPaywSC)) {
            if(customer.getCustomerAccount().getLoyaltyCard().getAccessCode().equals(pswrdCreditAccess.getText())) {
                PaymentProcessor processor = new StoreCreditPayProcessor(totalAmount, totalAmount, customer);
                this.resultPayementDetails= processor.paymentProcess();
                Stage stage = (Stage) btnPaywSC.getScene().getWindow();
                stage.close();
            }
        } else if (evt.equals(btnPayMP)) {
            // Either remove or fill this block of code.

        }

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

}
