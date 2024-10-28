/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author IGNITER
 */
public class CashRegisterAuditReport {
    private BigDecimal initialCashAmount;
    private BigDecimal currentCashAmount;
    private ArrayList<CashFlowReport> cashOutFlowReports;
    private ArrayList<CashFlowReport> cashInFlowReports;
    private ArrayList<Bill> bills;
    private LocalDateTime initialCutofDate;
    private LocalDateTime finalCutofDate;

    /**
     * Constructor para el reporte de la caja
     * @param initialCashAmount El monto inicial de la caja
     */

    public CashRegisterAuditReport(BigDecimal initialCashAmount){
        this.initialCashAmount = initialCashAmount;
        this.currentCashAmount=initialCashAmount;
        this.initialCutofDate = LocalDateTime.now();
        this.cashOutFlowReports = new ArrayList<>();
        this.cashInFlowReports = new ArrayList<>();
        this.bills = new ArrayList<>();
        
        
    }
    
    /**
     * Metodo para agregar un reporte de salida de dinero
     * @param reason Motivo por el cual se realiza el movimiento
     * @param amount Cantidad de dinero que se retira.
     */
    public void addCashOutFlowReport(String reason, BigDecimal amount){
        if(currentCashAmount.compareTo(amount)>=0){
        cashOutFlowReports.add(new CashFlowReport(reason, amount));
        currentCashAmount=currentCashAmount.subtract(amount);
        }
        else{
            throw new IllegalArgumentException("No hay suficiente dinero en caja");
        }
    }

    /**
     * Metodo para agregar un reporte de entrada de dinero
     * @param reason Motivo por el cual se realiza el movimiento
     * @param amount Cantidad de dinero que se deposita.
     */
    public void addCashInFlowReport(String reason, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO)>0){
            cashInFlowReports.add(new CashFlowReport(reason, amount));
            currentCashAmount=currentCashAmount.add(amount);
        }
        else{
            throw new IllegalArgumentException("No se puede depositar una cantidad negativa");
        }
        
    }

    /**
     * Metodo para agregar una factura a la caja
     * @param bill La factura a agregar
     */
    public void addBill(Bill bill){
        currentCashAmount = currentCashAmount.add(bill.getAmount());
        bills.add(bill);
        System.out.println("Bill added");
    }

    //Maybe deberan tener identificadores, de lo contrario no se podra eliminar
    public void removeCashOutFlowReport(CashFlowReport cashOutFlowReport){
        cashOutFlowReports.remove(cashOutFlowReport);
    }

    public void removeCashInFlowReport(CashFlowReport cashInFlowReport){
        cashInFlowReports.remove(cashInFlowReport);
    }

    /**
     * Metodo para calcular cuanto dinero deberia tener la caja al final del corte.
     */
    public void calcualteFinalCashAmount(){
        BigDecimal totalCashIn = new BigDecimal("0");
        BigDecimal totalCashOut = new BigDecimal("0");
        BigDecimal sellAmount = new BigDecimal("0");
        for (CashFlowReport cashInFlowReport : cashInFlowReports) {
            totalCashIn.add(cashInFlowReport.getCashAmount());
        }
       

        for (Bill bill : bills) {
            sellAmount.add(bill.getAmount());
        }
         for (CashFlowReport cashOutFlowReport : cashOutFlowReports) {
            totalCashOut.add(cashOutFlowReport.getCashAmount());
        }
        currentCashAmount=currentCashAmount.add(initialCashAmount);
        currentCashAmount=currentCashAmount.add(sellAmount);
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
