package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;

public class CashPaymentProcessor extends PaymentProcessor {
    private final BigDecimal customerCashAmount;
    private final BigDecimal changeAmount;

    public CashPaymentProcessor(BigDecimal totalAmount, BigDecimal customerCashAmount) {
        super(totalAmount);
        this.customerCashAmount = customerCashAmount;
        this.changeAmount = customerCashAmount.subtract(totalAmount);
    }

    @Override
    public PaymentDetails paymentProcess() {

        if (isPaymentSufficient()) {
            PaymentDetails paymentDetails = new PaymentDetails(PaymentMethod.CASH, this.getTotalAmount());
            paymentDetails.setCustomerCashAmount(this.customerCashAmount);
            paymentDetails.setChangeAmount(changeAmount);
            paymentDetails.setPaymentDescription(paymentDescription());
            var posManager = POSManager.getInstance();
            var cashRegister = posManager.getCashRegister();
            cashRegister.setCurrentCashAmount(cashRegister.getCurrentCashAmount().add(this.getTotalAmount()));
            return paymentDetails;
        } else {
            return null;
        }

    }

    private boolean isPaymentSufficient() {
        return getTotalAmount().compareTo(customerCashAmount) <= 0;
    }

    @Override
    public String paymentDescription() {
        String description = "PAGADO CON EN EFECTIVO: $" + this.getTotalAmount() +
                "\n" +
                "CAMBIO: $" + changeAmount +
                "\n";

        return description;
    }
}
