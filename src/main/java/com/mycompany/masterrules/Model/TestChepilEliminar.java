package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class TestChepilEliminar {

    public static void main(String[] args) {
        System.out.println("Debes pagar");
        BigDecimal chepo = new BigDecimal("100");

        CashPaymentMethod efectivo = new CashPaymentMethod(chepo, new BigDecimal("200"));
        System.out.println(efectivo.paymentDescription());

        System.out.println("Debes pagar");
        StoreCreditPayMethod credito = new StoreCreditPayMethod(chepo, new BigDecimal("100"));
        System.out.println(credito.paymentDescription());

        System.out.println("Debes pagar");
        DebitCardPaymenthMethod tarjeta = new DebitCardPaymenthMethod(chepo,new BigDecimal("100"), "OP01TER01");
        System.out.println(tarjeta.paymentDescription());

        System.out.println("Debes pagar------------------------------");
        BigDecimal multipleChepo = new BigDecimal("200");

        MixPaymentMethod mix=new MixPaymentMethod(multipleChepo);
        mix.addPaymentMethod(new CashPaymentMethod(new BigDecimal("100"), new BigDecimal("100")));
        mix.addPaymentMethod(new DebitCardPaymenthMethod(mix.getFaltante(), new BigDecimal("100"),"OP02TER01"));
        System.out.println(mix.paymentDescription());

    }
}