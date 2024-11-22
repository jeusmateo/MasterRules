package com.mycompany.masterrules.Model.retailsystem;

import com.mycompany.masterrules.Model.customers.Customer;


import java.math.BigDecimal;

public class StoreCreditPayProcessor extends PaymentProcessor {
    private Customer customer;

    public StoreCreditPayProcessor(BigDecimal totalAmount, Customer customer){
        super(totalAmount);
        this.customer =  customer;
    }

    @Override
    public PaymentDetails paymentProcess() {
        if(hasEnoughCredit()){
            customer.getCustomerAccount().setStoreCredit(customer.getCustomerAccount().getStoreCredit().subtract(this.getTotalAmount()));
        }else{
            return null;
        }
        PaymentDetails paymentDetails= new PaymentDetails(PaymentMethod.STORE_CREDIT,this.getTotalAmount());
        paymentDetails.setCustomer(customer);
        paymentDetails.setPaymentDescription(paymentDescription());;
        return paymentDetails;
    }

    private boolean hasEnoughCredit() {
        return customer.getCustomerAccount().getStoreCredit().compareTo(this.getTotalAmount()) >= 0;
    }

    @Override
    public String paymentDescription() {

        return String.format("PAGADO CON CRÉDITO DE TIENDA :$"+ String.valueOf(getTotalAmount())+"\n");
    }
}
