package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class DebitCardPaymenthMethod extends PaymentMethod2{
    private String reference;
    public DebitCardPaymenthMethod(BigDecimal totalAmount, String reference){
        super(totalAmount);
        this.reference = reference;
    }
}
