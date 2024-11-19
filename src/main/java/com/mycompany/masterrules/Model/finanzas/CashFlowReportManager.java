package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Database.CashFlowReportDatabase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CashFlowReportManager {

    private final CashFlowReportDatabase cashFlowReportDatabase;
    private List<CashFlowReport> cashOutFlowReports;
    private List<CashFlowReport> cashInFlowReports;

    public CashFlowReportManager() {
        this.cashOutFlowReports = new ArrayList<>();
        this.cashInFlowReports = new ArrayList<>();
        this.cashFlowReportDatabase = new CashFlowReportDatabase();

        readAllFromDatabase();
    }

    public List<CashFlowReport> getCashOutFlowReports() {

        readAllFromDatabase();return cashOutFlowReports;
    }

    public List<CashFlowReport> getCashOutFlowReportsByDateRange(LocalDate beginDate, LocalDate endDate){
        readAllFromDatabase();
        return cashOutFlowReports.stream()
                .filter(cashFlowReport -> cashFlowReport.getDate().isAfter(beginDate.atStartOfDay()) &&
                        cashFlowReport.getDate().isBefore(endDate.atStartOfDay()))
                .toList();
    }

    public List<CashFlowReport> getCashInFlowReportsByDateRange(LocalDate beginDate, LocalDate endDate){
        readAllFromDatabase();
        return cashInFlowReports.stream()
                .filter(cashFlowReport -> cashFlowReport.getDate().isAfter(beginDate.atStartOfDay()) &&
                        cashFlowReport.getDate().isBefore(endDate.atStartOfDay()))
                .toList();
    }

    public BigDecimal calculateTotalCashOutFlow(List<CashFlowReport> cashOutFlowReports){
        return cashOutFlowReports.stream()
                .map(CashFlowReport::getCashAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateTotalCashInFlot(List<CashFlowReport> cashInFlowReports){
        return cashInFlowReports.stream()
                .map(CashFlowReport::getCashAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void makeCashRegisterAuditReport(BigDecimal initialCashAmount){
        CashRegisterAuditReport cashRegisterAuditReport = new CashRegisterAuditReport(initialCashAmount);
        cashRegisterAuditReport.configCashRegisterAuditReport();
        cashRegisterAuditReport.calculateCashRevenue();
    }

    public void setCashOutFlowReports(ArrayList<CashFlowReport> cashOutFlowReports) {
        for (CashFlowReport cashFlowReport : cashOutFlowReports) {
            cashFlowReport.setFlowType(FlowType.CASH_OUT);
            cashFlowReportDatabase.save(cashFlowReport);
        }
        this.cashOutFlowReports = cashOutFlowReports;
    }

    public void addNewCashOutFlowReport(CashFlowReport cashOutFlowReport) {
        cashOutFlowReport.setFlowType(FlowType.CASH_OUT);
        this.cashOutFlowReports.add(cashOutFlowReport);
        cashFlowReportDatabase.save(cashOutFlowReport);
    }

    public void addNewCashInFlowReport(CashFlowReport cashInFlowReport) {
        cashInFlowReport.setFlowType(FlowType.CASH_IN);
        cashInFlowReports.add(cashInFlowReport);
        cashFlowReportDatabase.save(cashInFlowReport);
    }

    public List<CashFlowReport> getCashInFlowReports() {
        readAllFromDatabase();
        return cashInFlowReports;
    }

    public void setCashInFlowReports(ArrayList<CashFlowReport> cashInFlowReports) {
        for (CashFlowReport cashFlowReport : cashInFlowReports) {
            cashFlowReport.setFlowType(FlowType.CASH_IN);
            cashFlowReportDatabase.save(cashFlowReport);
        }
        this.cashInFlowReports = cashInFlowReports;
    }

    private void readAllFromDatabase() {
        var databaseReports = cashFlowReportDatabase.readAll();
        for (CashFlowReport cashFlowReport : databaseReports) {
            if (cashFlowReport.getFlowType() == FlowType.CASH_OUT) {
                this.cashOutFlowReports.add(cashFlowReport);
            } else {
                this.cashInFlowReports.add(cashFlowReport);
            }
        }
    }
}
