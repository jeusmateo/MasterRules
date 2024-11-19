package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Database.CashFlowReportDatabase;

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
        return cashOutFlowReports;
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
