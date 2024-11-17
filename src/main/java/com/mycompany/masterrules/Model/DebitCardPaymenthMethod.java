package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class DebitCardPaymenthMethod extends PaymentMethod2{
    private String reference;
    public DebitCardPaymenthMethod(BigDecimal totalAmount, String reference){
        super(totalAmount);
        this.reference = reference;
    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails = new PaymentDetails(PaymentMethod.CARD,this.getTotalAmount());
        paymentDetails.setReference(reference);
        return paymentDetails;
    }
}
