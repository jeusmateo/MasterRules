package com.mycompany.masterrules.model.possystem;

import java.math.BigDecimal;


//CLASE DE BAJO NIVEL DE ABSTRACCION, DEBEMOS VER COMO OCULTARLA PARA QUE SE VUELVA UN ADT O TRABAJARLA EN OTRO NIVEL DE ABSTRACCION
//ES BAJO PORQUE ELLA Y SUS HIJAS TRABAJAN LOGICA SOBRE PAGO Y OPERACIONES NECESARIAS PARA VENDER.
public abstract class PaymenthProcesser {
    private BigDecimal totalAmount;

    public PaymenthProcesser(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }


    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public abstract PaymentDetails paymentProcess();

    public abstract String paymentDescription();

}

