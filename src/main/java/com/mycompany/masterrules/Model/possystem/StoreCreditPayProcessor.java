package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.customers.CustomerAccount;

import java.math.BigDecimal;

public class StoreCreditPayProcessor extends PaymentProcessor {
    private BigDecimal recibido;
    private Customer customer;

    public StoreCreditPayProcessor(BigDecimal totalAmount, BigDecimal customerStoreCredit, Customer customer){
        super(totalAmount);
        this.recibido = customerStoreCredit;
        this.customer =  customer;

    }

    @Override
    public PaymentDetails paymentProcess() {
        if(customer.getCustomerAccount().getStoreCredit().compareTo(this.getTotalAmount())>=0){
            customer.getCustomerAccount().setStoreCredit(customer.getCustomerAccount().getStoreCredit().subtract(this.getTotalAmount()));
        }
        PaymentDetails paymentDetails= new PaymentDetails("STORE_CREDIT",this.getTotalAmount());
        paymentDetails.setCustomer(customer);
        paymentDetails.setPaymentDescription(paymentDescription());;
        return paymentDetails;
    }

    @Override
    public String paymentDescription() {

        return String.format("PAGADO CON CRÉDITO DE TIENDA :$"+ String.valueOf(getTotalAmount())+"\n");
    }
}
