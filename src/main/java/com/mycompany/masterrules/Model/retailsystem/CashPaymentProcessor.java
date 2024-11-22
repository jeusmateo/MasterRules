package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;

public class CashPaymentProcessor extends PaymentProcessor {
    private final BigDecimal customerCashAmount;
    private final BigDecimal changeAmount;

    public CashPaymentProcessor(BigDecimal totalAmountToPay, BigDecimal customerCashAmount) {
        super(totalAmountToPay);
        this.customerCashAmount = customerCashAmount;
        this.changeAmount = customerCashAmount.subtract(totalAmountToPay);
    }

    @Override
    public PaymentDetails paymentProcess() throws PaymentException {
        if (hasEnoughCash()) {
            var cashRegister = POSManager.getInstance().getCashRegister();
            var newCashAmount = cashRegister.getCurrentCashAmount().subtract(this.getTotalAmountToPay());
            cashRegister.setCurrentCashAmount(newCashAmount);

            PaymentDetails paymentDetails = new PaymentDetails(PaymentMethod.CASH, this.getTotalAmountToPay());
            paymentDetails.setCustomerCashAmount(this.customerCashAmount);
            paymentDetails.setChangeAmount(changeAmount);
            paymentDetails.setPaymentDescription(paymentDescription());
            return paymentDetails;
        } else {
            throw new PaymentException("El pago no es suficiente");
        }
    }

    private boolean hasEnoughCash() {
        return getTotalAmountToPay().compareTo(customerCashAmount) <= 0;
    }

    @Override
    public String paymentDescription() {
        return "PAGADO CON EN EFECTIVO: $" + this.getTotalAmountToPay() +
                "\n" +
                "CAMBIO: $" + changeAmount +
                "\n";
    }
}
