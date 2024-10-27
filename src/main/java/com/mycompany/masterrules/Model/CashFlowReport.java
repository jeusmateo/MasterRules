/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

/**
 *
 * @author IGNITER
 */
public class CashFlowReport {
    private String reason;
    private BigDecimal cashAmount;

    public CashFlowReport(String reason, BigDecimal cashAmount) {
        this.reason = reason;
        this.cashAmount = cashAmount;
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
