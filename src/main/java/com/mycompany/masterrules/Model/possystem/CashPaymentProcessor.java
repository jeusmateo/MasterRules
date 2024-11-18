package com.mycompany.masterrules.Model.possystem;

import java.math.BigDecimal;

public class CashPaymentProcessor extends PaymentProcessor {
    private BigDecimal customerCashAmount;
    private BigDecimal changeAmount;

    public CashPaymentProcessor(BigDecimal totalAmount, BigDecimal customerCashAmount){
        super(totalAmount);
        this.customerCashAmount = customerCashAmount;
        this.changeAmount = customerCashAmount.subtract(totalAmount);

    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails = new PaymentDetails("CASH",this.getTotalAmount());
        paymentDetails.setCustomerCashAmount(this.customerCashAmount);
        paymentDetails.setChangeAmount(changeAmount);
        paymentDetails.setPaymentDescription(paymentDescription());
        return paymentDetails;
    }

    @Override
    public String paymentDescription() {
        StringBuilder description = new StringBuilder("PAGADO CON EN EFECTIVO: $");
        description.append(String.valueOf(this.getTotalAmount()));
        description.append("\n");
        description.append("CAMBIO: $"+String.valueOf(changeAmount));
        description.append("\n");

        return description.toString();
    }
}
