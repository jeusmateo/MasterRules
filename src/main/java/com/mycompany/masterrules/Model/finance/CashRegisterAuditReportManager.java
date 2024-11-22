package com.mycompany.masterrules.Model.finance;

import com.mycompany.masterrules.Database.CashAuditReportDatabase;
import com.mycompany.masterrules.Model.retailsystem.POSManager;
import com.mycompany.masterrules.Model.users.Permission;

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

        var currentUser = POSManager.getInstance().getCurrentUser();
        if (!currentUser.hasPermission(Permission.RECORD_CASH_AUDIT_REPORT)) {
            throw new IllegalStateException("User does not have permission to record cash audit reports");
        }

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

        var currentUser = POSManager.getInstance().getCurrentUser();
        if (!currentUser.hasPermission(Permission.RECORD_CASH_AUDIT_REPORT)) {
            throw new IllegalStateException("User does not have permission to record cash audit reports");
        }

        currentCashAuditReport.setFinalCutofDate(LocalDateTime.now());
        cashAuditReports.add(currentCashAuditReport);
    }

}