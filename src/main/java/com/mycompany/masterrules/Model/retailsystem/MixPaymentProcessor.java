package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MixPaymentProcessor extends PaymentProcessor {
    private final List<PaymentProcessor> paymentProcessors;
    //    private BigDecimal amountReceived;
    private final BigDecimal amountToPay;

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
        if (paymentProcessors.isEmpty()) {
            throw new PaymentException("No se ha seleccionado ningún método de pago");
        } else {
            var newPaymentDetails = new PaymentDetails(PaymentMethod.MIX, amountToPay);

            newPaymentDetails.setCustomer(newPaymentDetails.getCustomer());

            // process each payment method and append the payment details
            var totalCustomerCashAmount = BigDecimal.ZERO;
            var totalChangeAmount = BigDecimal.ZERO;
            var totalReference = new StringBuilder();
            var totalPaymentDescription = new StringBuilder();

            for (var paymentProcessor : paymentProcessors) {
                var actualPaymentDetails = paymentProcessor.paymentProcess();

                totalChangeAmount = totalChangeAmount.add(actualPaymentDetails.getChangeAmount());
                totalCustomerCashAmount = totalCustomerCashAmount.add(actualPaymentDetails.getCustomerCashAmount());
                totalReference.append(actualPaymentDetails.getReference());
                totalPaymentDescription.append(paymentProcessor.paymentDescription()).append("\n");

            }

            newPaymentDetails.setCustomerCashAmount(totalCustomerCashAmount);
            newPaymentDetails.setChangeAmount(totalChangeAmount);
            newPaymentDetails.setReference(totalReference.toString());
            newPaymentDetails.setPaymentDescription(totalPaymentDescription.toString());

            return newPaymentDetails;
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
