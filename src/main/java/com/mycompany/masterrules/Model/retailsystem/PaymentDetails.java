package com.mycompany.masterrules.Model.retailsystem;

import com.mycompany.masterrules.Model.customers.Customer;

import java.math.BigDecimal;

public class PaymentDetails {

    private Customer customer;
    private String paymentDescription;
    private PaymentMethod paymentMethod;
    private BigDecimal totalAmount = BigDecimal.ZERO;
    private BigDecimal customerCashAmount = BigDecimal.ZERO;
    private String CardTransactionReference;
    private BigDecimal changeAmount = BigDecimal.ZERO;


    public PaymentDetails(PaymentMethod metodoDePago, BigDecimal totalAmount) {
        this.paymentMethod = metodoDePago;
        this.totalAmount = this.totalAmount.add(totalAmount);
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod metodoDePago) {
        this.paymentMethod = metodoDePago;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getReference() {
        return CardTransactionReference;
    }

    public void setReference(String reference) {
        // Noncompliant - method is empty
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getCustomerCashAmount() {
        return customerCashAmount;
    }

    public void setCustomerCashAmount(BigDecimal customerCashAmount) {
        this.customerCashAmount = customerCashAmount;
    }
}
