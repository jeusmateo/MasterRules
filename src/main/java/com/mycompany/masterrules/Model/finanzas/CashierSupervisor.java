package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Database.CashFlowDatabase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CashierSupervisor {

    private final CashFlowDatabase cashFlowDatabase;
    private List<CashFlow> cashOutFlows;

    private List<CashFlow> cashInFlows;

    public CashierSupervisor() {
        this.cashOutFlows = new ArrayList<>();
        this.cashInFlows = new ArrayList<>();
        this.cashFlowDatabase = new CashFlowDatabase();

        readAllFromDatabase();
    }

    public List<CashFlow> getCashFlows() {
        readAllFromDatabase();
        return cashOutFlows;
    }

    public List<CashFlow> getCashOutFlowsByDateRange(LocalDateTime beginDate, LocalDateTime endDate){
        readAllFromDatabase();
        return cashOutFlows.stream()
                .filter(cashFlowReport -> cashFlowReport.getDate().isAfter(beginDate) && cashFlowReport.getDate().isBefore(endDate))
                .toList();
    }

    public List<CashFlow> getCashInFlowsByDateRange(LocalDateTime beginDate, LocalDateTime endDate){
        readAllFromDatabase();
        return cashInFlows.stream()
                .filter(cashFlowReport -> cashFlowReport.getDate().isAfter(beginDate) && cashFlowReport.getDate().isBefore(endDate))
                .toList();
    }

    public void setCashFlows(ArrayList<CashFlow> cashFlows) {
        for (CashFlow cashFlow : cashFlows) {
            cashFlow.setFlowType(FlowType.CASH_OUT);
            cashFlowDatabase.save(cashFlow);
        }
        this.cashOutFlows = cashFlows;
    }

    public void addNewCashOutFlow(CashFlow cashOutFlowReport) {
        cashOutFlowReport.setFlowType(FlowType.CASH_OUT);
        this.cashOutFlows.add(cashOutFlowReport);
        cashFlowDatabase.save(cashOutFlowReport);
    }

    public void addNewCashInFlow(CashFlow cashInFlowReport) {
        cashInFlowReport.setFlowType(FlowType.CASH_IN);
        cashInFlows.add(cashInFlowReport);
        cashFlowDatabase.save(cashInFlowReport);
    }

    public List<CashFlow> getCashInFlows() {
        readAllFromDatabase();
        return cashInFlows;
    }

    public void setCashInFlows(ArrayList<CashFlow> cashInFlows) {
        for (CashFlow cashFlow : cashInFlows) {
            cashFlow.setFlowType(FlowType.CASH_IN);
            cashFlowDatabase.save(cashFlow);
        }
        this.cashInFlows = cashInFlows;
    }

    private void readAllFromDatabase() {
        this.cashOutFlows = new ArrayList<>();
        this.cashInFlows = new ArrayList<>();
        var databaseReports = cashFlowDatabase.readAll();
        for (CashFlow currentCashFlow : databaseReports) {
            if (currentCashFlow.getFlowType() == FlowType.CASH_OUT && !this.cashOutFlows.contains(currentCashFlow)) {
                this.cashOutFlows.add(currentCashFlow);
            } else {
                if(!this.cashOutFlows.contains(currentCashFlow)) {
                    this.cashInFlows.add(currentCashFlow);
                }

            }
        }
    }
}
