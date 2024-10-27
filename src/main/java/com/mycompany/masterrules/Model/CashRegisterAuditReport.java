/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author IGNITER
 */
public class CashRegisterAuditReport {
    private BigDecimal initialCashAmount;
    private BigDecimal currentCashAmount;
    private List<CashFlowReport> cashOutFlowReports;
    private List<CashFlowReport> cashInFlowReports;
    private List<Bill> bills;
    private LocalDateTime initialCutofDate;
    private LocalDateTime finalCutofDate;

    public CashRegisterAuditReport(BigDecimal initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
        this.currentCashAmount = BigDecimal.ZERO;
        this.initialCutofDate = LocalDateTime.now();
        this.cashOutFlowReports = new ArrayList<>();
        this.cashInFlowReports = new ArrayList<>();
        this.bills = new ArrayList<>();


    }

    public void addCashOutFlowReport(String reason, BigDecimal amount) {
//        if(amount <= currentCashAmount){
        if (amount.compareTo(currentCashAmount) <= 0) {
            cashOutFlowReports.add(new CashFlowReport(reason, amount));
            currentCashAmount = currentCashAmount.subtract(amount);
        } else {
            throw new IllegalArgumentException("No hay suficiente dinero en caja");
        }
    }

    public void addCashInFlowReport(String reason, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            cashInFlowReports.add(new CashFlowReport(reason, amount));
            currentCashAmount = currentCashAmount.add(amount);
        } else {
            throw new IllegalArgumentException("No se puede depositar una cantidad negativa");
        }

    }

    public void addBill(Bill bill) {
        bills.add(bill);
    }

    //Maybe deberan tener identificadores, de lo contrario no se podra eliminar
    public void removeCashOutFlowReport(CashFlowReport cashOutFlowReport) {
        cashOutFlowReports.remove(cashOutFlowReport);
    }

    public void removeCashInFlowReport(CashFlowReport cashInFlowReport) {
        cashInFlowReports.remove(cashInFlowReport);
    }

    public void calcualteFinalCashAmount() {
        BigDecimal totalCashIn = BigDecimal.ZERO;
        BigDecimal totalCashOut = BigDecimal.ZERO;
        BigDecimal sellAmount = BigDecimal.ZERO;
        for (CashFlowReport cashInFlowReport : cashInFlowReports) {
//            totalCashIn += cashInFlowReport.getCashAmount();
            totalCashIn = totalCashIn.add(cashInFlowReport.getCashAmount());
        }
        for (CashFlowReport cashOutFlowReport : cashOutFlowReports) {
//            totalCashOut += cashOutFlowReport.getCashAmount();
            totalCashOut = totalCashOut.add(cashOutFlowReport.getCashAmount());
        }

        for (Bill bill : bills) {
//            sellAmount += bill.getAmount();
            sellAmount = sellAmount.add(bill.getAmount());
        }
//        this.currentCashAmount = initialCashAmount + sellAmount + totalCashIn - totalCashOut;
        this.currentCashAmount = initialCashAmount.add(sellAmount).add(totalCashIn).subtract(totalCashOut);
    }

    public BigDecimal getInitialCashAmount() {
        return initialCashAmount;
    }

    public void setInitialCashAmount(BigDecimal initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
    }

    public BigDecimal getcurrentCashAmount() {
        return currentCashAmount;
    }

    public void setcurrentCashAmount(BigDecimal currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
    }

    public List<CashFlowReport> getCashOutFlowReports() {
        return cashOutFlowReports;
    }

    public void setCashOutFlowReports(ArrayList<CashFlowReport> cashOutFlowReports) {
        this.cashOutFlowReports = cashOutFlowReports;
    }

    public List<CashFlowReport> getCashInFlowReports() {
        return cashInFlowReports;
    }

    public void setCashInFlowReports(ArrayList<CashFlowReport> cashInFlowReports) {
        this.cashInFlowReports = cashInFlowReports;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public LocalDateTime getInitialCutofDate() {
        return initialCutofDate;
    }

    public void setInitialCutofDate(LocalDateTime initialCutofDate) {
        this.initialCutofDate = initialCutofDate;
    }

    public LocalDateTime getFinalCutofDate() {
        return finalCutofDate;
    }

    public void setFinalCutofDate(LocalDateTime finalCutofDate) {
        this.finalCutofDate = finalCutofDate;
    }

    public BigDecimal getCurrentCashAmount() {
        return currentCashAmount;
    }

    public void setCurrentCashAmount(BigDecimal currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
    }


}
