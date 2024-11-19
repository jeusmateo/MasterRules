package com.mycompany.masterrules.Model.finanzas;


import com.mycompany.masterrules.Model.possystem.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CashRegisterAuditReportManager {

    private ArrayList<CashRegisterAuditReport> cashRegisterAuditReports;
    CashRegisterAuditReport currentCashRegisterAuditReport;


    public CashRegisterAuditReport getCurrentCashRegisterAuditReport() {
        return currentCashRegisterAuditReport;
    }

    public CashRegisterAuditReportManager() {
        currentCashRegisterAuditReport = new CashRegisterAuditReport(new BigDecimal("0"));
        cashRegisterAuditReports = new ArrayList<>();
    }

    public void removeCashRegisterAuditReport(CashRegisterAuditReport cashRegisterAuditReport) {
        cashRegisterAuditReports.remove(cashRegisterAuditReport);
    }

    public void generateEndOfDaySalesReport(){ //todo nombre mal puesto
        currentCashRegisterAuditReport.configCashRegisterAuditReport();
        currentCashRegisterAuditReport.calculateCashFlowInTotalAmount();
        currentCashRegisterAuditReport.calculateCashFlowOutTotalAmount();
        currentCashRegisterAuditReport.calculateCashRevenue();
        currentCashRegisterAuditReport.calculateCardhRevenue();
        currentCashRegisterAuditReport.calculateStoreCreditRevenue();
        currentCashRegisterAuditReport.calculateCashBalance();
    }

    public void endCashRegisterAuditReport() {
        currentCashRegisterAuditReport.setFinalCutofDate(LocalDateTime.now());
        cashRegisterAuditReports.add(currentCashRegisterAuditReport);
        currentCashRegisterAuditReport = new CashRegisterAuditReport(new BigDecimal("0"));

    }






}