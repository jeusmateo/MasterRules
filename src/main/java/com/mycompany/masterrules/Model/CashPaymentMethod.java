package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class CashPaymentMethod extends PaymentMethod2{
    private BigDecimal customerCashAmount;
    public CashPaymentMethod(BigDecimal totalAmount, BigDecimal customerCashAmount){
        super(totalAmount);
        this.customerCashAmount = customerCashAmount;


    }
}
