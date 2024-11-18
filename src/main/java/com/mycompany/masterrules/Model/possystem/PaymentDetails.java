package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.customers.CustomerAccount;

import java.math.BigDecimal;

public class PaymentDetails {

    private PaymentMethod paymentMethod;
    private String metodoDePago;
    private BigDecimal customerCashAmount;
    private String reference;
    private BigDecimal totalPaymentAmount;
    private BigDecimal changeAmount;

    public PaymentDetails(PaymentMethod paymentMethod, CustomerAccount customerAccount, String customerAccountAccess, BigDecimal customerCashAmount) {
        this.paymentMethod = paymentMethod;

        this.customerCashAmount = customerCashAmount;

    }

    public PaymentDetails(PaymentMethod paymentMethod, BigDecimal totalPaymentAmount) {
        this.paymentMethod = paymentMethod;
        this.totalPaymentAmount = totalPaymentAmount;

    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }
    public BigDecimal getChangeAmount() {
        return changeAmount;
    }
    public String getReference() {
        return reference;
    }


    public void setReference(String reference) {
    }

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPaymentAmount) {
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


    public BigDecimal getCustomerCashAmount() {
        return customerCashAmount;
    }

    public void setCustomerCashAmount(BigDecimal customerCashAmount) {
        this.customerCashAmount = customerCashAmount;
    }
}
