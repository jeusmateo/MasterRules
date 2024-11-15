package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class PaymentDetails {
    private PaymentMethod paymentMethod;
    private CustomerAccount customerAccount;
    private String customerAccountAccess;
    private BigDecimal customerCashAmount;

    public PaymentDetails(PaymentMethod paymentMethod, CustomerAccount customerAccount, String customerAccountAccess,BigDecimal customerCashAmount) {
        this.paymentMethod = paymentMethod;
        this.customerAccount = customerAccount;
        this.customerAccountAccess = customerAccountAccess;
        this.customerCashAmount = customerCashAmount;

    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }
    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }
    public String getCustomerAccountAccess() {
        return customerAccountAccess;
    }
    public void setCustomerAccountAccess(String customerAccountAccess) {
        this.customerAccountAccess = customerAccountAccess;
    }
    public BigDecimal getCustomerCashAmount() {
        return customerCashAmount;
    }
    public void setCustomerCashAmount(BigDecimal customerCashAmount) {
        this.customerCashAmount = customerCashAmount;
    }
}
