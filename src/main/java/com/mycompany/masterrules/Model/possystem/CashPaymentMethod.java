package com.mycompany.masterrules.Model.possystem;

import java.math.BigDecimal;

public class CashPaymentMethod extends PaymentMethod {
    private BigDecimal customerCashAmount;
    private BigDecimal changeAmount;

    public CashPaymentMethod(BigDecimal totalAmount, BigDecimal customerCashAmount){
        super(totalAmount);
        this.customerCashAmount = customerCashAmount;
        this.changeAmount = customerCashAmount.subtract(totalAmount);

    }

    @Override
    public PaymentDetails paymentProcess() {
        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setChangeAmount(changeAmount);

        return null;
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
