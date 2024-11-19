package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.customers.Customer;

import java.math.BigDecimal;

public class PaymentDetails {

    private String paymentDescription;
    private String paymentMethod;
    private BigDecimal totalAmount;
    private BigDecimal customerCashAmount;
    private String CardTransactionReference;
    private BigDecimal changeAmount;
    private Customer customer;

    public PaymentDetails(String metodoDePago, BigDecimal totalAmount){
        this.paymentMethod = metodoDePago;
        this.totalAmount = totalAmount;
    }

    public String getPaymentDescription() {
        return paymentDescription;
    }

    public void setPaymentDescription(String paymentDescription) {
        this.paymentDescription = paymentDescription;
    }
    public String getMetodoDePago() {
        return paymentMethod;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.paymentMethod = metodoDePago;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }
    public BigDecimal getChangeAmount() {
        return changeAmount;
    }
    public String getReference() {
        return CardTransactionReference;
    }


    public void setReference(String reference) {
        // Noncompliant - method is empty
    }

    public Customer getCustomer(){return this.customer;}
    public BigDecimal getCustomerCashAmount() {
        return customerCashAmount;
    }

    public void setCustomerCashAmount(BigDecimal customerCashAmount) {
        this.customerCashAmount = customerCashAmount;
    }
}
