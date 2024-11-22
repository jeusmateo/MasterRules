package com.mycompany.masterrules.Model.retailsystem;

import com.mycompany.masterrules.Model.customers.Customer;

import java.math.BigDecimal;

public class StoreCreditPayProcessor extends PaymentProcessor {
    private final Customer customer;

    public StoreCreditPayProcessor(BigDecimal totalAmount, Customer customer) {
        super(totalAmount);
        this.customer = customer;
    }

    @Override
    public PaymentDetails paymentProcess() throws PaymentException {
        if (hasEnoughCredit()) {
            var customerAccount = customer.getCustomerAccount();
            var newStoreCredit = customerAccount.getStoreCredit().subtract(this.getTotalAmount());
            customerAccount.setStoreCredit(newStoreCredit);

            PaymentDetails paymentDetails = new PaymentDetails(PaymentMethod.STORE_CREDIT, this.getTotalAmount());
            paymentDetails.setCustomer(customer);
            paymentDetails.setPaymentDescription(paymentDescription());
            return paymentDetails;
        } else {
            throw new PaymentException("No tiene suficiente crédito de tienda para realizar la compra");
        }
    }

    private boolean hasEnoughCredit() {
        return customer.getCustomerAccount().getStoreCredit().compareTo(this.getTotalAmount()) >= 0;
    }

    @Override
    public String paymentDescription() {

        return String.format("PAGADO CON CRÉDITO DE TIENDA :$" + getTotalAmount() + "\n");
    }
}
