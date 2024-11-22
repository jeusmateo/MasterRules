package com.mycompany.masterrules.Model.finance;

import com.mycompany.masterrules.Database.CashAuditReportDatabase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CashRegisterAuditReportManager {

    private final List<CashAuditReport> cashAuditReports;
    private final CashAuditReportDatabase cashAuditReportDatabase;
    private LocalDateTime lastDate;



    public CashRegisterAuditReportManager() {
        cashAuditReportDatabase = new CashAuditReportDatabase();
        cashAuditReports = new ArrayList<>();
        readFromDatabase();

        if (cashAuditReports.isEmpty()) {
            lastDate = LocalDateTime.now();
        } else {
            lastDate = cashAuditReports.getLast().getFinalCutofDate();
        }
    }

    private void readFromDatabase() {
        var cashAuditReportDatabase = new CashAuditReportDatabase();
        cashAuditReports.addAll(cashAuditReportDatabase.readAll());
    }

    public void removeCashRegisterAuditReport(CashAuditReport cashAuditReport) {
        cashAuditReports.remove(cashAuditReport);
    }

    public CashAuditReport generateEndOfDaySalesReport() { //todo nombre mal puesto
        var endOfDaySalesReport = new CashAuditReport(BigDecimal.ZERO, lastDate);
        endOfDaySalesReport.configCashRegisterAuditReport();
        endOfDaySalesReport.calculateCashFlowInTotalAmount();
        endOfDaySalesReport.calculateCashFlowOutTotalAmount();
        endOfDaySalesReport.calculateCashRevenue();
        endOfDaySalesReport.calculateCardhRevenue();
        endOfDaySalesReport.calculateStoreCreditRevenue();
        endOfDaySalesReport.calculateCashBalance();

        return endOfDaySalesReport;
    }

    public void endCashRegisterAuditReport(CashAuditReport currentCashAuditReport) {
        currentCashAuditReport.setFinalCutofDate(LocalDateTime.now());
        cashAuditReports.add(currentCashAuditReport);
    }

}