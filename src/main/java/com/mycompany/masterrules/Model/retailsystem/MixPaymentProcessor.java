package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MixPaymentProcessor extends PaymentProcessor {
    private final List<PaymentProcessor> paymentProcessors;
    private BigDecimal cashReceived;
    private BigDecimal faltante;

    public MixPaymentProcessor(BigDecimal totalAmount) {
        super(totalAmount);
        paymentProcessors = new ArrayList<>();
        cashReceived = BigDecimal.ZERO;
        faltante = totalAmount;
    }

    public void addPaymentMethod(PaymentProcessor paymentProcessor) {
        paymentProcessors.add(paymentProcessor);
        this.cashReceived = (this.getTotalAmount().add(paymentProcessor.getTotalAmount()));
        faltante = faltante.subtract(paymentProcessor.getTotalAmount());
    }

    public BigDecimal getFaltante() {
        return faltante;
    }

    @Override
    public PaymentDetails paymentProcess() throws PaymentException {
        PaymentDetails paymentDetails = null;
        for (PaymentProcessor paymentProcessor : paymentProcessors) {
            paymentDetails = paymentProcessor.paymentProcess();
        }
        return paymentDetails;
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
