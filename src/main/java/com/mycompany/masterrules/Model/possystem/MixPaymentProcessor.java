package com.mycompany.masterrules.Model.possystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MixPaymentProcessor extends PaymentProcessor {
    private List<PaymentProcessor> paymentProcessors;
    private BigDecimal recibido;
    private BigDecimal faltante;
    public MixPaymentProcessor(BigDecimal totalAmount){
        super(totalAmount);
        paymentProcessors = new ArrayList();
        recibido = BigDecimal.ZERO;
        faltante = totalAmount;
    }

    public void addPaymentMethod(PaymentProcessor paymentProcessor){
        paymentProcessors.add(paymentProcessor);
        this.recibido = (this.getTotalAmount().add(paymentProcessor.getTotalAmount()));
        faltante = faltante.subtract(paymentProcessor.getTotalAmount());
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
        for (PaymentProcessor pago : paymentProcessors) {
            description.append("  ").append(pago.paymentDescription()).append("\n");
        }
        return description.toString();
    }
}
