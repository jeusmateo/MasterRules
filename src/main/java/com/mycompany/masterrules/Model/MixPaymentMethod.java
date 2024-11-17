package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MixPaymentMethod extends PaymentMethod2{
    private List<PaymentMethod2> paymentMethods;
    private BigDecimal recibido;
    private BigDecimal faltante;
    public MixPaymentMethod(BigDecimal totalAmount){
        super(totalAmount);
        paymentMethods = new ArrayList();
        recibido = BigDecimal.ZERO;
        faltante = totalAmount;
    }

    public void addPaymentMethod(PaymentMethod2 paymentMethod){
        paymentMethods.add(paymentMethod);
        this.recibido = (this.getTotalAmount().add(paymentMethod.getTotalAmount()));
        faltante = faltante.subtract(paymentMethod.getTotalAmount());
    }

    public BigDecimal getFaltante(){
        return faltante;
    }

    @Override
    public PaymentDetails paymentProcess() {

        return null;
    }

    @Override
    public String paymentDescription() {
        StringBuilder description = new StringBuilder("Pago Mixto:\n");
        for (PaymentMethod2 pago : paymentMethods) {
            description.append("  ").append(pago.paymentDescription()).append("\n");
        }
        return description.toString();
    }
}
