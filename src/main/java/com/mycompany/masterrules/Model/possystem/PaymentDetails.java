package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.customers.CustomerAccount;

import java.math.BigDecimal;

public class PaymentDetails {

    private String metodoDePago;
    private BigDecimal customerCashAmount;
    private String accessCustomerCode;
    private String reference;
    private BigDecimal changeAmount;
    private Customer customer;

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }

    public String getAccessCustomerCode() {
        return accessCustomerCode;
    }

    public void setAccessCustomerCode(String accessCustomerCode) {
        this.accessCustomerCode = accessCustomerCode;
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

    public Customer getCustomer(){return this.customer;}
    public BigDecimal getCustomerCashAmount() {
        return customerCashAmount;
    }

    public void setCustomerCashAmount(BigDecimal customerCashAmount) {
        this.customerCashAmount = customerCashAmount;
    }
}
