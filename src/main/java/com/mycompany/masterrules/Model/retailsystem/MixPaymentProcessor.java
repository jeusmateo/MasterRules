package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MixPaymentProcessor extends PaymentProcessor {
    private final List<PaymentProcessor> paymentProcessors;
//    private BigDecimal amountReceived;
    private BigDecimal amountToPay;

    public MixPaymentProcessor(BigDecimal totalAmountToPay) {
        super(totalAmountToPay);
        paymentProcessors = new ArrayList<>();
//        amountReceived = BigDecimal.ZERO;
        amountToPay = totalAmountToPay;
    }

    public void addPaymentMethod(PaymentProcessor paymentProcessor) {
        paymentProcessors.add(paymentProcessor);
//        amountReceived = this.getTotalAmountToPay().add(paymentProcessor.getTotalAmountToPay());
//        amountToPay = amountToPay.subtract(paymentProcessor.getTotalAmountToPay());
    }

    public BigDecimal getAmountToPay() {
        return amountToPay;
    }

    @Override
    public PaymentDetails paymentProcess() throws PaymentException {
        if(paymentProcessors.isEmpty()) {
            throw new PaymentException("No se ha seleccionado ningún método de pago");
        }else{
            var finalPaymentDetails = new PaymentDetails(PaymentMethod.MIX, BigDecimal.ZERO);
            // process each payment method and append the payment details

            for (PaymentProcessor paymentProcessor : paymentProcessors) {
                var paymentDetails = paymentProcessor.paymentProcess();
            }

            // check if the total amount paid is enough
            BigDecimal totalAmountPaid = paymentProcessors.stream()
                    .map(PaymentProcessor::getTotalAmountToPay)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if(totalAmountPaid.compareTo(this.getTotalAmountToPay()) < 0) {
                throw new PaymentException("El monto pagado no es suficiente");
            }else{
                return new PaymentDetails(PaymentMethod.MIX, totalAmountPaid);
            }
        }

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
