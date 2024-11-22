package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;

public class DebitCardPaymenthProcessor extends PaymentProcessor {
    private String CardTransactionReference;
    private BigDecimal recibido;
    public DebitCardPaymenthProcessor(BigDecimal totalAmount, BigDecimal recibido, String reference){
        super(totalAmount);
        this.CardTransactionReference = reference;
        this.recibido = recibido;
    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails = new PaymentDetails(PaymentMethod.CARD, this.getTotalAmount());

        paymentDetails.setReference(CardTransactionReference);
        paymentDetails.setPaymentDescription(paymentDescription());
        return paymentDetails;
    }

    @Override
    public String paymentDescription() {
        StringBuilder description = new StringBuilder("PAGADO CON TARJETA: $");
        description.append(String.valueOf(this.getTotalAmount()));
        description.append("\n").append("REFERENCIA : "+this.CardTransactionReference).append("\n");
        return description.toString();
    }
}
