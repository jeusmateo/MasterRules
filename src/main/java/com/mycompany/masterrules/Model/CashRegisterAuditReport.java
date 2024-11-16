package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @autor: Torres David
 *
 * Clase que representa un reporte de corte de venta
 */
public class CashRegisterAuditReport {

    /**Monto inicial de la caja */
    private BigDecimal initialCashAmount;

    /**Monto actual de la caja */
    private BigDecimal currentCashAmount;

    /**Lista de reportes de salida de dinero */
    private ArrayList<CashFlowReport> cashOutFlowReports;

    /**Lista de reportes de entrada de dinero */
    private ArrayList<CashFlowReport> cashInFlowReports;

    /**Lista de facturas */
    private ArrayList<Bill> bills;

    /**Fecha inicial de corte  */
    private LocalDateTime initialCutofDate;

    /**Fecha final de corte  */
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
     * Metodo para agregar una factura a la caja
     * @param bill La factura a agregar
     */
    public void addBill(Bill bill){ //TODO ESTA COSA TIENE QUE ABSTRAERSE A TRAVES DE LA LISTA DE INVOICE
        currentCashAmount = currentCashAmount.add(bill.getAmount());
        bills.add(bill);
        System.out.println("Bill added");
    }


    /**
     * Metodo para calcular el total de dinero ingresado por medio de efectivo.
     * @return El total de dinero ingresado por medio de efectivo en un dato tipo BigDecimal
     */
    private BigDecimal calculateTotalCashIn(){
        BigDecimal totalCashIn =  BigDecimal.ZERO;
        for (CashFlowReport cashInFlowReport : cashInFlowReports) {
            totalCashIn.add(cashInFlowReport.getCashAmount());
        }
        return totalCashIn;
    }

    /**
     * Metodo para calcular el total de dinero retirado por medio de efectivo.
     * @return El total de dinero retirado por medio de efectivo en un dato tipo BigDecimal
     */
    private BigDecimal calculateTotalCashOut(){
        BigDecimal totalCashOut =  BigDecimal.ZERO;
        for (CashFlowReport cashOutFlowReport : cashOutFlowReports) {
            totalCashOut.add(cashOutFlowReport.getCashAmount());
        }
        return totalCashOut;
    }

    /**
     * Metodo para calcular el total de las facturas
     * @return El total de las facturas en un dato tipo BigDecimal
     */
    private BigDecimal calculateTotalBills(){
        BigDecimal totalBills =  BigDecimal.ZERO;
        for (Bill bill : bills) {
            totalBills.add(bill.getAmount());
        }
        return totalBills;
    }

    /**
     * Metodo para calcular el monto final de la caja
     */
    public void calculateFinalCashAmount(){
        BigDecimal totalCashIn = calculateTotalCashIn();
        BigDecimal totalCashOut = calculateTotalCashOut();
        BigDecimal totalSellAmount = calculateTotalBills();
        currentCashAmount=currentCashAmount.add(initialCashAmount);
        currentCashAmount=currentCashAmount.add(totalSellAmount);
        currentCashAmount=currentCashAmount.add(totalCashIn);
        currentCashAmount=currentCashAmount.subtract(totalCashOut);
        
    }

    /**
     * Obtiene el monto inicial de la caja
     * @return El monto inicial de la caja en un dato tipo BigDecimal
     */
    public BigDecimal getInitialCashAmount() {
        return initialCashAmount;
    }

    /**
     * Establece el monto inicial de la caja
     * @param initialCashAmount Recibe un dato tipo BigDecimal para establecer el monto inicial de la caja
     */
    public void setInitialCashAmount(BigDecimal initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
    }

    /**
     * Obtiene el monto actual de la caja
     * @return El monto actual de la caja en un dato tipo BigDecimal
     */
    public BigDecimal getcurrentCashAmount() {
        return currentCashAmount;
    }

    /**
     * Establece el monto actual de la caja
     * @param currentCashAmount Recibe un dato de tipo BigDecimal para establecer el monto actual de la caja
     */
    public void setcurrentCashAmount(BigDecimal currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
    }

    /**
     * Obtiene la lista de reportes de salida de dinero
     * @return La lista de reportes de salida de dinero en tipo de dato CashFlowReport
     */
    public ArrayList<CashFlowReport> getCashOutFlowReports() {
        return cashOutFlowReports;
    }

    /**
     * Establece la lista de reportes de salida de dinero
     * @param cashOutFlowReports Recibe una lista de reportes de salida de dinero
     */
    public void setCashOutFlowReports(ArrayList<CashFlowReport> cashOutFlowReports) {
        this.cashOutFlowReports = cashOutFlowReports;
    }

    /**
     * Obtiene la lista de reportes de entrada de dinero
     * @return La lista de reportes de entrada de dinero en tipo de dato CashFlowReport
     */
    public ArrayList<CashFlowReport> getCashInFlowReports() {
        return cashInFlowReports;
    }

    /**
     * Establece la lista de reportes de entrada de dinero
     * @param cashInFlowReports Recibe una lista de reportes de entrada de dinero
     */
    public void setCashInFlowReports(ArrayList<CashFlowReport> cashInFlowReports) {
        this.cashInFlowReports = cashInFlowReports;
    }

    /**
     * Obtiene la lista de facturas
     * @return La lista de facturas en tipo de dato Bill
     */
    public ArrayList<Bill> getBills() {
        return bills;
    }

    /**
     * Establece la lista de facturas
     * @param bills Recibe una lista de facturas
     */
    public void setBills(ArrayList<Bill> bills) {
        this.bills = bills;
    }

    /**
     * Obtiene la fecha inicial de corte
     * @return La fecha inicial de corte en un dato tipo LocalDateTime
     */
    public LocalDateTime getInitialCutofDate() {
        return initialCutofDate;
    }

    /**
     * Establece la fecha inicial de corte
     * @param initialCutofDate La fecha inicial de corte en un dato tipo LocalDateTime
     */
    public void setInitialCutofDate(LocalDateTime initialCutofDate) {
        this.initialCutofDate = initialCutofDate;
    }

    /**
     * Obtiene la fecha final de corte
     * @return La fecha final de corte en un dato tipo LocalDateTime
     */
    public  LocalDateTime  getFinalCutofDate() {
        return finalCutofDate;
    }

    /**
     * Establece la fecha final de corte
     * @param finalCutofDate La fecha final de corte en un dato tipo LocalDateTime
     */
    public void setFinalCutofDate(LocalDateTime finalCutofDate) {
        this.finalCutofDate = finalCutofDate;
    }

    /**
     * Obtiene el monto actual de la caja
     * @return El monto actual de la caja en un dato tipo BigDecimal
     */
    public BigDecimal getCurrentCashAmount() {
        return currentCashAmount;
    }

    /**
     * Establece el monto actual de la caja
     * @param currentCashAmount El monto actual de la caja en un dato tipo BigDecimal
     */
    public void setCurrentCashAmount(BigDecimal currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
    }
    
    
}
