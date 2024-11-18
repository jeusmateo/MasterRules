package com.mycompany.masterrules.model.possystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MixPaymentMethod extends PaymenthProcesser {
    private List<PaymenthProcesser> paymentMethods;
    private BigDecimal recibido;
    private BigDecimal faltante;
    public MixPaymentMethod(BigDecimal totalAmount){
        super(totalAmount);
        paymentMethods = new ArrayList();
        recibido = BigDecimal.ZERO;
        faltante = totalAmount;
    }

    public void addPaymentMethod(PaymenthProcesser paymentMethod){
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
        for (PaymenthProcesser pago : paymentMethods) {
            description.append("  ").append(pago.paymentDescription()).append("\n");
        }
        return description.toString();
    }
}
