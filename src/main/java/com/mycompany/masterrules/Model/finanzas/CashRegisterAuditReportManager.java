package com.mycompany.masterrules.Model.finanzas;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CashRegisterAuditReportManager {

    private ArrayList<CashAuditReport> cashAuditReports;
    private CashAuditReport currentCashAuditReport;

    public CashAuditReport getCurrentCashRegisterAuditReport() {
        return currentCashAuditReport;
    }

    public CashRegisterAuditReportManager() {
        currentCashAuditReport = new CashAuditReport(new BigDecimal("0"));
        cashAuditReports = new ArrayList<>();
    }

    public void removeCashRegisterAuditReport(CashAuditReport cashAuditReport) {
        cashAuditReports.remove(cashAuditReport);
    }

    public void generateEndOfDaySalesReport(){ //todo nombre mal puesto
        currentCashAuditReport.configCashRegisterAuditReport();
        currentCashAuditReport.calculateCashFlowInTotalAmount();
        currentCashAuditReport.calculateCashFlowOutTotalAmount();
        currentCashAuditReport.calculateCashRevenue();
        currentCashAuditReport.calculateCardhRevenue();
        currentCashAuditReport.calculateStoreCreditRevenue();
        currentCashAuditReport.calculateCashBalance();
    }

    public void endCashRegisterAuditReport() {
        currentCashAuditReport.setFinalCutofDate(LocalDateTime.now());
        cashAuditReports.add(currentCashAuditReport);
        currentCashAuditReport = new CashAuditReport(new BigDecimal("0"));

    }

}