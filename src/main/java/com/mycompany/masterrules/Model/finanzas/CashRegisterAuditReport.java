package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Model.possystem.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CashRegisterAuditReport {


    private BigDecimal initialCashAmount;

    private ArrayList<Bill> bills;


    private LocalDateTime initialCutofDate;


    private LocalDateTime finalCutofDate;


    public CashRegisterAuditReport(BigDecimal initialCashAmount){
        this.initialCashAmount = initialCashAmount;

        this.initialCutofDate = LocalDateTime.now();
//        this.cashOutFlowReports = new ArrayList<>();
//        this.cashInFlowReports = new ArrayList<>();
        this.bills = new ArrayList<>();
        
        
    }

    public void addBill(Bill bill){ //TODO ESTA COSA TIENE QUE ABSTRAERSE A TRAVES DE LA LISTA DE INVOICE

        bills.add(bill);
        System.out.println("Bill added");
    }

    private BigDecimal calculateTotalCashIn(){
        BigDecimal totalCashIn =  BigDecimal.ZERO;
//        for (CashFlowReport cashInFlowReport : cashInFlowReports) {
//            totalCashIn.add(cashInFlowReport.getCashAmount());
//        }
        return totalCashIn;
    }

    private BigDecimal calculateTotalCashOut(){
        BigDecimal totalCashOut =  BigDecimal.ZERO;
//        for (CashFlowReport cashOutFlowReport : cashOutFlowReports) {
//            totalCashOut.add(cashOutFlowReport.getCashAmount());
//        }
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

        
    }

    public BigDecimal getInitialCashAmount() {
        return initialCashAmount;
    }

    public void setInitialCashAmount(BigDecimal initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
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

    
    
}
