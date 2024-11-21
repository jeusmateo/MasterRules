package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Database.CashAuditReportDatabase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CashRegisterAuditReportManager {

    private final List<CashAuditReport> cashAuditReports;
    private final CashAuditReportDatabase cashAuditReportDatabase;
    private CashAuditReport currentCashAuditReport;

    public CashRegisterAuditReportManager() {
        cashAuditReportDatabase = new CashAuditReportDatabase();
        cashAuditReports = new ArrayList<>();
        readFromDatabase();

        if (cashAuditReports.isEmpty()) {
            currentCashAuditReport = new CashAuditReport(BigDecimal.ZERO, LocalDateTime.now());
        } else {
            var lastDate = cashAuditReports.getLast().getFinalCutofDate();
            currentCashAuditReport = new CashAuditReport(BigDecimal.ZERO, lastDate);
        }
    }

    private void readFromDatabase() {
        var cashAuditReportDatabase = new CashAuditReportDatabase();
        cashAuditReports.addAll(cashAuditReportDatabase.readAll());
    }

    public CashAuditReport getCurrentCashRegisterAuditReport() {
        return currentCashAuditReport;
    }

    public void removeCashRegisterAuditReport(CashAuditReport cashAuditReport) {
        cashAuditReports.remove(cashAuditReport);
    }

    public void generateEndOfDaySalesReport() { //todo nombre mal puesto
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

        currentCashAuditReport = new CashAuditReport(new BigDecimal("0"), LocalDateTime.now());

    }

}