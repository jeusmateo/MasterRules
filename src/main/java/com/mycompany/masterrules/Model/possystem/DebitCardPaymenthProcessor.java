package com.mycompany.masterrules.Model.possystem;

import java.math.BigDecimal;

public class DebitCardPaymenthProcessor extends PaymentProcessor {
    private String reference;
    private BigDecimal recibido;
    public DebitCardPaymenthProcessor(BigDecimal totalAmount, BigDecimal recibido, String reference){
        super(totalAmount);
        this.reference = reference;
        this.recibido = recibido;
    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails = new PaymentDetails("PAGADO CON TARJETA", this.getTotalAmount());

        paymentDetails.setReference(reference);
        paymentDetails.setPaymentDescription(paymentDescription());
        return paymentDetails;
    }

    @Override
    public String paymentDescription() {
        StringBuilder description = new StringBuilder("PAGADO CON TARJETA: $");
        description.append(String.valueOf(this.getTotalAmount()));
        description.append("\n").append("REFERENCIA : "+this.reference).append("\n");
        return description.toString();
    }
}