package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Database.CashAuditReportDatabase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CashRegisterAuditReportManager {

    private final List<CashAuditReport> cashAuditReports;
    private CashAuditReport currentCashAuditReport;
    private final CashAuditReportDatabase cashAuditReportDatabase = new CashAuditReportDatabase();

    public CashRegisterAuditReportManager() {
        cashAuditReports = new ArrayList<>();
        readFromDatabase();
        currentCashAuditReport = new CashAuditReport(new BigDecimal("0"));
        if(cashAuditReports.isEmpty())
            currentCashAuditReport.setInitialCutofDate(LocalDateTime.now());
        else
            currentCashAuditReport.setInitialCutofDate(cashAuditReports.getLast().getFinalCutofDate());
    }

    private void readFromDatabase(){
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

        currentCashAuditReport = new CashAuditReport(new BigDecimal("0"));

    }

}