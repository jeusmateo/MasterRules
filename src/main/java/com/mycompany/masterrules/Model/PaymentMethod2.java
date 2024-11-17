package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public abstract class PaymentMethod2 {
    private BigDecimal totalAmount;

    public PaymentMethod2(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }


    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public abstract PaymentDetails paymentProcess();

    protected abstract String paymentDescription();

}

