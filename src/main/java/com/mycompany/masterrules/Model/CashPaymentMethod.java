package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class CashPaymentMethod extends PaymentMethod2{
    private BigDecimal customerCashAmount;
    public CashPaymentMethod(BigDecimal totalAmount, BigDecimal customerCashAmount){
        super(totalAmount);
        this.customerCashAmount = customerCashAmount;


    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails = new PaymentDetails(PaymentMethod.CASH,this.getTotalAmount());
        paymentDetails.setChangeAmount(customerCashAmount.subtract(this.getTotalAmount()));

        return null;
    }

    @Override
    protected String paymentDescription() {
        StringBuilder description = new StringBuilder("PAGADO CON EN EFECTIVO: $");
        description.append(String.valueOf(this.getTotalAmount()));
        description.append("/n");
        description.append("CAMBIO: $"+String.valueOf(customerCashAmount.subtract(this.getTotalAmount())));
        description.append("/n");

        return description.toString();
    }
}
