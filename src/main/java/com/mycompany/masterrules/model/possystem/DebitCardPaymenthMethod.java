package com.mycompany.masterrules.model.possystem;

import java.math.BigDecimal;

public class DebitCardPaymenthMethod extends PaymenthProcesser {
    private String reference;
    private BigDecimal recibido;
    public DebitCardPaymenthMethod(BigDecimal totalAmount, BigDecimal recibido,String reference){
        super(totalAmount);
        this.reference = reference;
        this.recibido = recibido;
    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails = new PaymentDetails(PaymentMethod.CARD,this.getTotalAmount());
        paymentDetails.setReference(reference);
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
