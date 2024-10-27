/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author IGNITER
 */
public class CashRegisterAuditReport {
    private double initialCashAmount;
    private double currentCashAmount;
    private ArrayList<CashFlowReport> cashOutFlowReports;
    private ArrayList<CashFlowReport> cashInFlowReports;
    private ArrayList<Bill> bills;
    private LocalDateTime initialCutofDate;
    private LocalDateTime finalCutofDate;

    public CashRegisterAuditReport(double initialCashAmount){
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
        double totalCashIn = 0;
        double totalCashOut = 0;
        double sellAmount = 0;
        for (CashFlowReport cashInFlowReport : cashInFlowReports) {
            totalCashIn += cashInFlowReport.getCashAmount();
        }
        for (CashFlowReport cashOutFlowReport : cashOutFlowReports) {
            totalCashOut += cashOutFlowReport.getCashAmount();
        }

        for (Bill bill : bills) {
            sellAmount += bill.getAmount();
        }
        this.currentCashAmount = initialCashAmount + sellAmount + totalCashIn - totalCashOut;
    }

    public double getInitialCashAmount() {
        return initialCashAmount;
    }

    public void setInitialCashAmount(double initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
    }

    public double getcurrentCashAmount() {
        return currentCashAmount;
    }

    public void setcurrentCashAmount(double currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
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
