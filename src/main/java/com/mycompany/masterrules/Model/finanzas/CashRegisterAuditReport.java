package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Model.possystem.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CashRegisterAuditReport {


    private BigDecimal initialCashAmount;


    private BigDecimal currentCashAmount; //TODO QUITAR POR REF


    private ArrayList<CashFlowReport> cashOutFlowReports;


    private ArrayList<CashFlowReport> cashInFlowReports;


    private ArrayList<Bill> bills;


    private LocalDateTime initialCutofDate;


    private LocalDateTime finalCutofDate;


    public CashRegisterAuditReport(BigDecimal initialCashAmount){
        this.initialCashAmount = initialCashAmount;
        this.currentCashAmount=initialCashAmount;
        this.initialCutofDate = LocalDateTime.now();
        this.cashOutFlowReports = new ArrayList<>();
        this.cashInFlowReports = new ArrayList<>();
        this.bills = new ArrayList<>();
        
        
    }

    public void addBill(Bill bill){ //TODO ESTA COSA TIENE QUE ABSTRAERSE A TRAVES DE LA LISTA DE INVOICE
        currentCashAmount = currentCashAmount.add(bill.getAmount());
        bills.add(bill);
        System.out.println("Bill added");
    }

    private BigDecimal calculateTotalCashIn(){
        BigDecimal totalCashIn =  BigDecimal.ZERO;
        for (CashFlowReport cashInFlowReport : cashInFlowReports) {
            totalCashIn.add(cashInFlowReport.getCashAmount());
        }
        return totalCashIn;
    }

    private BigDecimal calculateTotalCashOut(){
        BigDecimal totalCashOut =  BigDecimal.ZERO;
        for (CashFlowReport cashOutFlowReport : cashOutFlowReports) {
            totalCashOut.add(cashOutFlowReport.getCashAmount());
        }
        return totalCashOut;
    }

    private BigDecimal calculateTotalBills(){
        BigDecimal totalBills =  BigDecimal.ZERO;
        for (Bill bill : bills) {
            totalBills.add(bill.getAmount());
        }
        return totalBills;
    }

    public void calculateFinalCashAmount(){
        BigDecimal totalCashIn = calculateTotalCashIn();
        BigDecimal totalCashOut = calculateTotalCashOut();
        BigDecimal totalSellAmount = calculateTotalBills();
        currentCashAmount=currentCashAmount.add(initialCashAmount);
        currentCashAmount=currentCashAmount.add(totalSellAmount);
        currentCashAmount=currentCashAmount.add(totalCashIn);
        currentCashAmount=currentCashAmount.subtract(totalCashOut);
        
    }

    public BigDecimal getInitialCashAmount() {
        return initialCashAmount;
    }

    public void setInitialCashAmount(BigDecimal initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
    }

    public BigDecimal getcurrentCashAmount() {
        return currentCashAmount;
    }

    public void setcurrentCashAmount(BigDecimal currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
    }

    public ArrayList<CashFlowReport> getCashOutFlowReports() {
        return cashOutFlowReports;
    }

    public void setCashOutFlowReports(ArrayList<CashFlowReport> cashOutFlowReports) {
        this.cashOutFlowReports = cashOutFlowReports;
    }

    public ArrayList<CashFlowReport> getCashInFlowReports() {
        return cashInFlowReports;
    }

    public void setCashInFlowReports(ArrayList<CashFlowReport> cashInFlowReports) {
        this.cashInFlowReports = cashInFlowReports;
    }

    public ArrayList<Bill> getBills() {
        return bills;
    }

    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    public LocalDateTime getInitialCutofDate() {
        return initialCutofDate;
    }

    public void setInitialCutofDate(LocalDateTime initialCutofDate) {
        this.initialCutofDate = initialCutofDate;
    }

    public  LocalDateTime  getFinalCutofDate() {
        return finalCutofDate;
    }

    public void setFinalCutofDate(LocalDateTime finalCutofDate) {
        this.finalCutofDate = finalCutofDate;
    }

    public BigDecimal getCurrentCashAmount() {
        return currentCashAmount;
    }

    public void setCurrentCashAmount(BigDecimal currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
    }
    
    
}
