package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Database.CashFlowReportDatabase;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;

public class CashFlowReportManager {

    private ArrayList<CashFlowReport> cashOutFlowReports;
    private ArrayList<CashFlowReport> cashInFlowReports;
    private final CashFlowReportDatabase cashFlowReportDatabase;

    public CashFlowReportManager() {
        this.cashOutFlowReports = new ArrayList<>();
        this.cashInFlowReports = new ArrayList<>();
        this.cashFlowReportDatabase = new CashFlowReportDatabase();
    }

    public ArrayList<CashFlowReport> getCashOutFlowReports() {
        return cashOutFlowReports;
    }

    public void setCashOutFlowReports(ArrayList<CashFlowReport> cashOutFlowReports) {
        this.cashOutFlowReports = cashOutFlowReports;
        for(CashFlowReport cashFlowReport : cashOutFlowReports){
            cashFlowReportDatabase.save(cashFlowReport);
        }
    }

    public void addNewCashOutFlowReport(CashFlowReport cashOutFlowReport) {
        this.cashOutFlowReports.add(cashOutFlowReport);
        cashFlowReportDatabase.save(cashOutFlowReport);
    }

    public void addNewCashInFlowReport(CashFlowReport cashInFlowReport) {
        cashInFlowReports.add(cashInFlowReport);
        cashFlowReportDatabase.save(cashInFlowReport);
    }

    public ArrayList<CashFlowReport> getCashInFlowReports() {
        return cashInFlowReports;
    }

    public void setCashInFlowReports(ArrayList<CashFlowReport> cashInFlowReports) {
        this.cashInFlowReports = cashInFlowReports;
        for(CashFlowReport cashFlowReport : cashInFlowReports){
            cashFlowReportDatabase.save(cashFlowReport);
        }
    }
}
