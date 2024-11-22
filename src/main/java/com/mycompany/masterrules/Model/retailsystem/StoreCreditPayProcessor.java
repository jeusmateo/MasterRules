package com.mycompany.masterrules.Model.retailsystem;

import com.mycompany.masterrules.Model.customers.Customer;

import java.math.BigDecimal;

public class StoreCreditPayProcessor extends PaymentProcessor {
    private final Customer customer;

    public StoreCreditPayProcessor(BigDecimal totalAmountToPay, Customer customer) {
        super(totalAmountToPay);
        this.customer = customer;
    }

    @Override
    public PaymentDetails paymentProcess() throws PaymentException {
        if (hasEnoughCredit()) {
            var customerAccount = customer.getCustomerAccount();
            BigDecimal actualStoreCredit = customerAccount.getStoreCredit();
            BigDecimal newStoreCredit = actualStoreCredit.subtract(this.getTotalAmountToPay());
            customerAccount.setStoreCredit(newStoreCredit);

            PaymentDetails paymentDetails = new PaymentDetails(PaymentMethod.STORE_CREDIT, this.getTotalAmountToPay());
            paymentDetails.setCustomer(customer);
            paymentDetails.setPaymentDescription(paymentDescription());
            return paymentDetails;
        } else {
            throw new PaymentException("No tiene suficiente crédito de tienda para realizar la compra");
        }
    }

    private boolean hasEnoughCredit() {
        return customer.getCustomerAccount().getStoreCredit().compareTo(this.getTotalAmountToPay()) >= 0;
    }

    @Override
    public String paymentDescription() {

        return String.format("PAGADO CON CRÉDITO DE TIENDA :$" + getTotalAmountToPay() + "\n");
    }
}
