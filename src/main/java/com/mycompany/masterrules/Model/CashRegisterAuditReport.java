/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.util.List;

/**
 *
 * @author IGNITER
 */
public class CashRegisterAuditReport {
    private double initialCashAmount;
    private double finalCashAmount;
    private List<CashFlowReport> cashOutFlowReports;
    private List<CashFlowReport> cashInFlowReports;
    private List<Bill> bills;
    private String initialCutofDate;
    private String finalCutofDate;

    public CashRegisterAuditReport(double initialCashAmount){
        this.initialCashAmount = initialCashAmount;
        this.initialCutofDate = "Ejemplo";
    }

    public void addCashOutFlowReport(CashFlowReport cashOutFlowReport){
        cashOutFlowReports.add(cashOutFlowReport);
    }

    public void addCashInFlowReport(CashFlowReport cashInFlowReport){
        cashInFlowReports.add(cashInFlowReport);
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
        this.finalCashAmount = initialCashAmount + sellAmount + totalCashIn - totalCashOut;
    }

    public double getInitialCashAmount() {
        return initialCashAmount;
    }

    public void setInitialCashAmount(double initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
    }

    public double getFinalCashAmount() {
        return finalCashAmount;
    }

    public void setFinalCashAmount(double finalCashAmount) {
        this.finalCashAmount = finalCashAmount;
    }

    public List<CashFlowReport> getCashOutFlowReports() {
        return cashOutFlowReports;
    }

    public void setCashOutFlowReports(List<CashFlowReport> cashOutFlowReports) {
        this.cashOutFlowReports = cashOutFlowReports;
    }

    public List<CashFlowReport> getCashInFlowReports() {
        return cashInFlowReports;
    }

    public void setCashInFlowReports(List<CashFlowReport> cashInFlowReports) {
        this.cashInFlowReports = cashInFlowReports;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public String getInitialCutofDate() {
        return initialCutofDate;
    }

    public void setInitialCutofDate(String initialCutofDate) {
        this.initialCutofDate = initialCutofDate;
    }

    public String getFinalCutofDate() {
        return finalCutofDate;
    }

    public void setFinalCutofDate(String finalCutofDate) {
        this.finalCutofDate = finalCutofDate;
    }
    
    
}
