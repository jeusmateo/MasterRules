/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author IGNITER
 */
public class CashRegisterAuditReportManager {
    private List<CashRegisterAuditReport> cashRegisterAuditReports;
    private CashRegisterAuditReport currentCashRegisterAuditReport;

    public void addCashRegisterAuditReport(CashRegisterAuditReport cashRegisterAuditReport){
        cashRegisterAuditReports.add(cashRegisterAuditReport);
    }
    
    public void removeCashRegisterAuditReport(CashRegisterAuditReport cashRegisterAuditReport){
        cashRegisterAuditReports.remove(cashRegisterAuditReport);
    }

    public void AddSaleToCashRegisterAuditReport(Bill sale){
        currentCashRegisterAuditReport.addBill(sale);
    }

    public void withdrawCash(String reason, BigDecimal amount){
        currentCashRegisterAuditReport.addCashOutFlowReport(new CashFlowReport(reason, amount));
    }

    public void depositCash(String reason, BigDecimal amount){
        currentCashRegisterAuditReport.addCashInFlowReport(new CashFlowReport(reason, amount));
    }

    public void generateCashRegisterAuditReport(BigDecimal initialCashAmount){
        currentCashRegisterAuditReport.calcualteFinalCashAmount();
        currentCashRegisterAuditReport.setFinalCutofDate("EJEMPLO");
        cashRegisterAuditReports.add(currentCashRegisterAuditReport);
        currentCashRegisterAuditReport = new CashRegisterAuditReport(initialCashAmount);
    }
}
