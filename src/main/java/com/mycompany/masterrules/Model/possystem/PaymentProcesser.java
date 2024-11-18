package com.mycompany.masterrules.Model.possystem;

public class PaymentProcesser {

    public PaymentProcesser() {}

    public void processPay(PaymentDetails data){
        switch(data.getMetodoDePago()){
            case "CASH":
                
                break;
            case "CARD":

                break;
            case "STORE_CREDIT":
                if(data.getCustomer().getCustomerAccount().getLoyaltyCard().getAccessCode().equals(data.getAccessCustomerCode())){

                }
                break;

            }

        }
    }

