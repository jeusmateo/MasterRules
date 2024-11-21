package com.mycompany.masterrules.Model.possystem;

import java.math.BigDecimal;

public class CashPaymentProcessor extends PaymentProcessor {
    private final BigDecimal customerCashAmount;
    private final BigDecimal changeAmount;

    public CashPaymentProcessor(BigDecimal totalAmount, BigDecimal customerCashAmount){
        super(totalAmount);
        this.customerCashAmount = customerCashAmount;
        this.changeAmount = customerCashAmount.subtract(totalAmount);

    }

    @Override
    public PaymentDetails paymentProcess() {
        if(getTotalAmount().compareTo(customerCashAmount) < 0){
            PaymentDetails paymentDetails = new PaymentDetails("CASH",this.getTotalAmount());
            paymentDetails.setCustomerCashAmount(this.customerCashAmount);
            paymentDetails.setChangeAmount(changeAmount);
            paymentDetails.setPaymentDescription(paymentDescription());
            var posManager = POSManager.getInstance();
            posManager.getCashRegister().setCurrentCashAmount(posManager.getCashRegister().getCurrentCashAmount().add(this.getTotalAmount()));
            return paymentDetails;
        }
        else{
            return null;
        }

    }

    @Override
    public String paymentDescription() {
        StringBuilder description = new StringBuilder("PAGADO CON EN EFECTIVO: $");
        description.append(String.valueOf(this.getTotalAmount()));
        description.append("\n");
        description.append("CAMBIO: $"+ changeAmount);
        description.append("\n");

        return description.toString();
    }
}
