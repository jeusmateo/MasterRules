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
public class CashRegisterAuditReportManager {
    private ArrayList<CashRegisterAuditReport> cashRegisterAuditReports;
    CashRegisterAuditReport currentCashRegisterAuditReport;

    public CashRegisterAuditReportManager(){
        currentCashRegisterAuditReport = new CashRegisterAuditReport(0);
        cashRegisterAuditReports= new ArrayList<>();
    }

    private void addCashRegisterAuditReport(CashRegisterAuditReport cashRegisterAuditReport){
        cashRegisterAuditReports.add(cashRegisterAuditReport);
    }
    
    public void removeCashRegisterAuditReport(CashRegisterAuditReport cashRegisterAuditReport){
        cashRegisterAuditReports.remove(cashRegisterAuditReport);
    }

    public void AddSaleToCashRegisterAuditReport(Bill sale){
        currentCashRegisterAuditReport.addBill(sale);
    }

    public void withdrawCash(String reason, double amount){
        currentCashRegisterAuditReport.addCashOutFlowReport(reason, amount);
    }

    public void depositCash(String reason, double amount){
        currentCashRegisterAuditReport.addCashInFlowReport(reason, amount);
    }

    public void finalizeCashRegisterAuditReport(double initialCashAmount){
        currentCashRegisterAuditReport.calcualteFinalCashAmount();
        currentCashRegisterAuditReport.setFinalCutofDate(LocalDateTime.now());
        cashRegisterAuditReports.add(currentCashRegisterAuditReport);
        currentCashRegisterAuditReport = new CashRegisterAuditReport(initialCashAmount);
    }

    public void startCashRegisterAuditReport(double initialCashAmount){
        currentCashRegisterAuditReport = new CashRegisterAuditReport(initialCashAmount);
    }

    public ArrayList<CashRegisterAuditReport> getCashRegisterAuditReports() {
        return cashRegisterAuditReports;
    }

    public void setCashRegisterAuditReports(ArrayList<CashRegisterAuditReport> cashRegisterAuditReports) {
        this.cashRegisterAuditReports = cashRegisterAuditReports;
    }

    public CashRegisterAuditReport getCurrentCashRegisterAuditReport() {
        return currentCashRegisterAuditReport;
    }

    public void setCurrentCashRegisterAuditReport(CashRegisterAuditReport currentCashRegisterAuditReport) {
        this.currentCashRegisterAuditReport = currentCashRegisterAuditReport;
    }

}
