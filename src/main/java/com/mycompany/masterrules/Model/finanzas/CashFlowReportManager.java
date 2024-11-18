package com.mycompany.masterrules.Model.finanzas;

import java.util.ArrayList;

public class CashFlowReportManager {


    private ArrayList<CashFlowReport> cashOutFlowReports;


    private ArrayList<CashFlowReport> cashInFlowReports;

    public ArrayList<CashFlowReport> getCashOutFlowReports() {
        return cashOutFlowReports;
    }

    public void setCashOutFlowReports(ArrayList<CashFlowReport> cashOutFlowReports) {
        this.cashOutFlowReports = cashOutFlowReports;
    }

    public void addNewCashOutFlowReport(CashFlowReport cashOutFlowReport) {
        this.cashOutFlowReports.add(cashOutFlowReport);
    }

    public void addNewCashInFlowReport(CashFlowReport cashInFlowReport){
        cashInFlowReports.add(cashInFlowReport);
    }


    public ArrayList<CashFlowReport> getCashInFlowReports() {
        return cashInFlowReports;
    }

    public void setCashInFlowReports(ArrayList<CashFlowReport> cashInFlowReports) {
        this.cashInFlowReports = cashInFlowReports;
    }
}
