package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;

public class DebitCardPaymentProcessor extends PaymentProcessor {
    private final String CardTransactionReference;

    public DebitCardPaymentProcessor(BigDecimal totalAmount, String reference) {
        super(totalAmount);
        this.CardTransactionReference = reference;
    }

    @Override
    public PaymentDetails paymentProcess() throws PaymentException {
        // Aquí se realizaría la transacción con la tarjeta
        // se llama a la API de la entidad bancaria
        var paymentDetails = new PaymentDetails(PaymentMethod.CARD, this.getTotalAmount());
        paymentDetails.setReference(CardTransactionReference);
        paymentDetails.setPaymentDescription(paymentDescription());
        return paymentDetails;
    }

    @Override
    public String paymentDescription() {
        return "PAGADO CON TARJETA: $" + this.getTotalAmount() +
                "\n" + "REFERENCIA : " + this.CardTransactionReference + "\n";
    }
}
