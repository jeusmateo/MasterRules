/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author IGNITER
 */
public class CashFlowReport {
    private String reason;
    private BigDecimal cashAmount;
    private LocalDate date;


    /**
     *  Constructor para el Reporte de movimiento de caja.
     * @param reason Motivo por el cual se realiza el movimiento
     * @param cashAmount Cantidad de dinero que se mueve
     */
    public CashFlowReport(String reason, BigDecimal cashAmount) {
        this.reason = reason;
        this.cashAmount = cashAmount;
        this.date = LocalDate.now();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }
}
