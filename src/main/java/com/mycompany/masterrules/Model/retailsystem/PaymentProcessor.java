package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;


//CLASE DE BAJO NIVEL DE ABSTRACCION, DEBEMOS VER COMO OCULTARLA PARA QUE SE VUELVA UN ADT O TRABAJARLA EN OTRO NIVEL DE ABSTRACCION
//ES BAJO PORQUE ELLA Y SUS HIJAS TRABAJAN LOGICA SOBRE PAGO Y OPERACIONES NECESARIAS PARA VENDER.
public abstract class PaymentProcessor {
    private BigDecimal totalAmountToPay;

    public PaymentProcessor(BigDecimal totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }

    public abstract PaymentDetails paymentProcess() throws PaymentException;

    public abstract String paymentDescription();

    public BigDecimal getTotalAmountToPay() {
        return totalAmountToPay;
    }

    public void setTotalAmountToPay(BigDecimal totalAmountToPay) {
        this.totalAmountToPay = totalAmountToPay;
    }
}

