package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class StoreCreditPayMethod extends PaymentMethod2{
    private BigDecimal storeCreditUsed;
    public StoreCreditPayMethod(BigDecimal totalAmount, BigDecimal customerStoreCredit){
        super(totalAmount);
        this.storeCreditUsed = customerStoreCredit;

    }
}
