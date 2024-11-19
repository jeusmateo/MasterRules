package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Model.possystem.Bill;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CashRegisterAuditReport {

    private List<CashFlowReport> cashFlowInReport;
    private List<CashFlowReport> cashFlowOutReport;
    private BigDecimal initialCashAmount;

    private List<Bill> bills;
    private BigDecimal cashBalance;
    private BigDecimal cashRevenue;
    private BigDecimal cardRevenue;
    private BigDecimal storeCreditRevenue;
    private LocalDateTime initialCutofDate;


    private LocalDateTime finalCutofDate;


    public CashRegisterAuditReport(BigDecimal initialCashAmount){
        this.initialCashAmount = initialCashAmount;
        this.initialCutofDate = LocalDateTime.now();
        this.bills = new ArrayList<>();
        this.cashFlowInReport = new ArrayList<>();
        this.cashFlowOutReport = new ArrayList<>();
        this.cashRevenue = BigDecimal.ZERO;
        this.cardRevenue = BigDecimal.ZERO;
        this.storeCreditRevenue = BigDecimal.ZERO;
    }

    public void configCashRegisterAuditReport(){
        CashFlowReportManager cashFlowReportManager = new CashFlowReportManager();
        this.cashFlowInReport = cashFlowReportManager.getCashInFlowReports(); //todo que tengan el mismo comportamiento de dia a dia.
        this.cashFlowOutReport = cashFlowReportManager.getCashOutFlowReports();
        ArchiveInvoice archiveInvoice = new ArchiveInvoice();
        this.bills= archiveInvoice.getBillsByDateRange(this.initialCutofDate.toLocalDate(), LocalDate.now());
    }

    public void calculateCashRevenue(){
        for(Bill currentBill: this.bills){
            if(currentBill.getPaymentMethod().equals("CASH") || currentBill.getPaymentMethod().equals("MIX") && currentBill.getPagadoEnEfectivo().compareTo(BigDecimal.ZERO)!=0){
                cashRevenue = cashRevenue.add(currentBill.getAmount());
            }
        }
    }

    public void calculateCardhRevenue(){
        for(Bill currentBill: this.bills){
            if(currentBill.getPaymentMethod().equals("CARD") || currentBill.getPaymentMethod().equals("MIX") && currentBill.getPagadoEnTajeta().compareTo(BigDecimal.ZERO)!=0){
                cardRevenue = cardRevenue.add(currentBill.getAmount());
            }
        }
    }

    public void calculateStoreCreditRevenue(){
        for(Bill currentBill: this.bills){
            if(currentBill.getPaymentMethod().equals("STORE_CREDIT") || currentBill.getPaymentMethod().equals("MIX") && currentBill.getPagadoEnCreditoDeTienda().compareTo(BigDecimal.ZERO)!=0){
                storeCreditRevenue = storeCreditRevenue.add(currentBill.getAmount());
            }
        }
    }

    public BigDecimal getInitialCashAmount() {
        return initialCashAmount;
    }

    public void setInitialCashAmount(BigDecimal initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
    }




    public List<Bill> getBills() {
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
