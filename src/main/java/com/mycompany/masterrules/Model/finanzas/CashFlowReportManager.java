package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Database.CashFlowReportDatabase;

import java.util.ArrayList;
import java.util.List;

public class CashFlowReportManager {

    private List<CashFlowReport> cashOutFlowReports;
    private List<CashFlowReport> cashInFlowReports;
    private final CashFlowReportDatabase cashFlowReportDatabase;

    public CashFlowReportManager() {
        this.cashOutFlowReports = new ArrayList<>();
        this.cashInFlowReports = new ArrayList<>();
        this.cashFlowReportDatabase = new CashFlowReportDatabase();
    }

    public List<CashFlowReport> getCashOutFlowReports() {
        return cashOutFlowReports;
    }

    public void setCashOutFlowReports(ArrayList<CashFlowReport> cashOutFlowReports) {
        for(CashFlowReport cashFlowReport : cashOutFlowReports){
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
        for(CashFlowReport cashFlowReport : cashInFlowReports){
            cashFlowReport.setFlowType(FlowType.CASH_IN);
            cashFlowReportDatabase.save(cashFlowReport);
        }
        this.cashInFlowReports = cashInFlowReports;
    }
}
