package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;

public class DebitCardPaymentProcessor extends PaymentProcessor {
    private final String CardTransactionReference;

    public DebitCardPaymentProcessor(BigDecimal totalAmountToPay, String reference) {
        super(totalAmountToPay);
        this.CardTransactionReference = reference;
    }

    @Override
    public PaymentDetails paymentProcess() throws PaymentException {
        // Aquí se realizaría la transacción con la tarjeta
        // se llama a la API de la entidad bancaria
        var paymentDetails = new PaymentDetails(PaymentMethod.CARD, this.getTotalAmountToPay());
        paymentDetails.setReference(CardTransactionReference);
        paymentDetails.setPaymentDescription(paymentDescription());
        return paymentDetails;
    }

    @Override
    public String paymentDescription() {
        return "PAGADO CON TARJETA: $" + this.getTotalAmountToPay() +
                "\n" + "REFERENCIA : " + this.CardTransactionReference + "\n";
    }
}
