package com.mycompany.masterrules.Model.retailsystem;

import java.math.BigDecimal;


//CLASE DE BAJO NIVEL DE ABSTRACCION, DEBEMOS VER COMO OCULTARLA PARA QUE SE VUELVA UN ADT O TRABAJARLA EN OTRO NIVEL DE ABSTRACCION
//ES BAJO PORQUE ELLA Y SUS HIJAS TRABAJAN LOGICA SOBRE PAGO Y OPERACIONES NECESARIAS PARA VENDER.
public abstract class PaymentProcessor {
    private BigDecimal totalAmount;

    public PaymentProcessor(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public abstract PaymentDetails paymentProcess() throws PaymentException;

    public abstract String paymentDescription();

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}

