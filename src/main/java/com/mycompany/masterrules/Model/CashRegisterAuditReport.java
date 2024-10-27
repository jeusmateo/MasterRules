/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.List;
/**
 *
 * @author IGNITER
 */
public class CashRegisterAuditReport {
    private BigDecimal initialCashAmount;
    private BigDecimal finalCashAmount; //cambiar a currentCashAmount
    private List<CashFlowReport> cashOutFlowReports;
    private List<CashFlowReport> cashInFlowReports;
    private List<Bill> bills;
    private LocalDateTime initialCutofDate;
    private LocalDateTime finalCutofDate;

    public CashRegisterAuditReport(BigDecimal initialCashAmount){
        this.initialCashAmount = initialCashAmount;
        this.initialCutofDate = LocalDateTime.now();
        this.cashOutFlowReports = new ArrayList<>();
        this.cashInFlowReports = new ArrayList<>();
        this.bills = new ArrayList<>();
        
        
    }

    public void addCashOutFlowReport(String reason, double amount){
        if(amount <= currentCashAmount){
        cashOutFlowReports.add(new CashFlowReport(reason, amount));
        currentCashAmount=currentCashAmount - amount;
        }
        else{
            throw new IllegalArgumentException("No hay suficiente dinero en caja");
        }
    }

    public void addCashInFlowReport(String reason, double amount){
        if(amount > 0){
            cashInFlowReports.add(new CashFlowReport(reason, amount));
            currentCashAmount=currentCashAmount + amount;
        }
        else{
            throw new IllegalArgumentException("No se puede depositar una cantidad negativa");
        }
        
    }

    public void addBill(Bill bill){
        bills.add(bill);
    }

    //Maybe deberan tener identificadores, de lo contrario no se podra eliminar
    public void removeCashOutFlowReport(CashFlowReport cashOutFlowReport){
        cashOutFlowReports.remove(cashOutFlowReport);
    }

    public void removeCashInFlowReport(CashFlowReport cashInFlowReport){
        cashInFlowReports.remove(cashInFlowReport);
    }

    public void calcualteFinalCashAmount(){
        BigDecimal totalCashIn = new BigDecimal(0);
        BigDecimal totalCashOut = new BigDecimal(0);
        BigDecimal sellAmount = new BigDecimal(0);

        for (CashFlowReport cashInFlowReport : cashInFlowReports) {
            totalCashIn.add(cashInFlowReport.getCashAmount());
        }
        for (CashFlowReport cashOutFlowReport : cashOutFlowReports) {
            totalCashOut.add(cashOutFlowReport.getCashAmount());
        }

        for (Bill bill : bills) {
            sellAmount.add(bill.getAmount());
        }

//        this.finalCashAmount = initialCashAmount + sellAmount + totalCashIn - totalCashOut;
        this.finalCashAmount = initialCashAmount.add(sellAmount).add(totalCashIn).subtract(totalCashOut);
    }

    public BigDecimal getInitialCashAmount() {
        return initialCashAmount;
    }

    public void setInitialCashAmount(BigDecimal initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
    }

    public BigDecimal getFinalCashAmount() {
        return finalCashAmount;
    }

    public void setFinalCashAmount(BigDecimal finalCashAmount) {
        this.finalCashAmount = finalCashAmount;
    }

    public ArrayList<CashFlowReport> getCashOutFlowReports() {
        return cashOutFlowReports;
    }

    public void setCashOutFlowReports(ArrayList<CashFlowReport> cashOutFlowReports) {
        this.cashOutFlowReports = cashOutFlowReports;
    }

    public ArrayList<CashFlowReport> getCashInFlowReports() {
        return cashInFlowReports;
    }

    public void setCashInFlowReports(ArrayList<CashFlowReport> cashInFlowReports) {
        this.cashInFlowReports = cashInFlowReports;
    }

    public ArrayList<Bill> getBills() {
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

    public  LocalDateTime  getFinalCutofDate() {
        return finalCutofDate;
    }

    public void setFinalCutofDate(LocalDateTime finalCutofDate) {
        this.finalCutofDate = finalCutofDate;
    }

    public double getCurrentCashAmount() {
        return currentCashAmount;
    }

    public void setCurrentCashAmount(double currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
    }
    
    
}
