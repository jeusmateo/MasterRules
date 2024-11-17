package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MixPaymentMethod extends PaymentMethod2{
    private List<PaymentMethod2> paymentMethods;
    public MixPaymentMethod(){
        super(new BigDecimal("0"));
        paymentMethods = new ArrayList();
    }

    public void addPaymentMethod(PaymentMethod2 paymentMethod){
        paymentMethods.add(paymentMethod);
        this.setTotalAmount(this.getTotalAmount().add(paymentMethod.getTotalAmount()));
    }

}
