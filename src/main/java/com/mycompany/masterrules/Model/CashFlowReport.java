/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.time.LocalDate;

/**
 *
 * @author IGNITER
 */
public class CashFlowReport {
    private String reason;
    private double cashAmount;
    private LocalDate date;

    public CashFlowReport(String reason, double cashAmount) {
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

    public double getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }
}
