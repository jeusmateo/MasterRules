package com.mycompany.masterrules.Model.possystem;

import java.math.BigDecimal;

public class StoreCreditPayMethod extends PaymenthProcesser {
    private BigDecimal recibido;

    public StoreCreditPayMethod(BigDecimal totalAmount, BigDecimal customerStoreCredit){
        super(totalAmount);
        this.recibido = customerStoreCredit;

    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails= new PaymentDetails(PaymentMethod.STORE_CREDIT,this.getTotalAmount());

        return paymentDetails;
    }

    @Override
    public String paymentDescription() {

        return String.format("PAGADO CON CRÉDITO DE TIENDA :$"+ String.valueOf(getTotalAmount())+"\n");
    }
}
