package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class StoreCreditPayMethod extends PaymentMethod2{
    private BigDecimal storeCreditUsed;
    public StoreCreditPayMethod(BigDecimal totalAmount, BigDecimal customerStoreCredit){
        super(totalAmount);
        this.storeCreditUsed = customerStoreCredit;

    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails= new PaymentDetails(PaymentMethod.STORE_CREDIT,this.getTotalAmount());

        return paymentDetails;
    }

    @Override
    protected String paymentDescription() {

        return String.format("PAGADO CON CRÉDITO DE TIENDA :$"+ String.valueOf(getTotalAmount()));
    }
}
