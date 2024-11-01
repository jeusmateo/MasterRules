package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author IGNITER
 */
public class CashRegisterAuditReportManager {
    private ArrayList<CashRegisterAuditReport> cashRegisterAuditReports;
    CashRegisterAuditReport currentCashRegisterAuditReport;

    public CashRegisterAuditReportManager(){
        currentCashRegisterAuditReport = new CashRegisterAuditReport(new BigDecimal("0"));
        cashRegisterAuditReports= new ArrayList<>();
    }

    private void addCashRegisterAuditReport(CashRegisterAuditReport cashRegisterAuditReport){
        cashRegisterAuditReports.add(cashRegisterAuditReport);
    }
    
    public void removeCashRegisterAuditReport(CashRegisterAuditReport cashRegisterAuditReport){
        cashRegisterAuditReports.remove(cashRegisterAuditReport);
    }

    public void AddSaleToCashRegisterAuditReport(Bill sale){
        currentCashRegisterAuditReport.addBill(sale);
    }

    public void withdrawCash(String reason, BigDecimal amount){
        currentCashRegisterAuditReport.addCashOutFlowReport(reason, amount);
    }

    public void depositCash(String reason, BigDecimal amount){
        currentCashRegisterAuditReport.addCashInFlowReport(reason, amount);
    }
    /**
     * Metodo para finalizar el corte actual.
     * @param initialCashAmount Monto con el cual iniciara el siguiente corte.
     */
    public void finalizeCashRegisterAuditReport(BigDecimal initialCashAmount){
        currentCashRegisterAuditReport.calcualteFinalCashAmount();
        currentCashRegisterAuditReport.setFinalCutofDate(LocalDateTime.now());
        cashRegisterAuditReports.add(currentCashRegisterAuditReport);
        currentCashRegisterAuditReport = new CashRegisterAuditReport(initialCashAmount);
    }

    public void startCashRegisterAuditReport(BigDecimal initialCashAmount){
        currentCashRegisterAuditReport = new CashRegisterAuditReport(initialCashAmount);
    }

    public ArrayList<CashRegisterAuditReport> getCashRegisterAuditReports() {
        return cashRegisterAuditReports;
    }

    public void setCashRegisterAuditReports(ArrayList<CashRegisterAuditReport> cashRegisterAuditReports) {
        this.cashRegisterAuditReports = cashRegisterAuditReports;
    }

    public CashRegisterAuditReport getCurrentCashRegisterAuditReport() {
        return currentCashRegisterAuditReport;
    }

    public void setCurrentCashRegisterAuditReport(CashRegisterAuditReport currentCashRegisterAuditReport) {
        this.currentCashRegisterAuditReport = currentCashRegisterAuditReport;
    }

}
