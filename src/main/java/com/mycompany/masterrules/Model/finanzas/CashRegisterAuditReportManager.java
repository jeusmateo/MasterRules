package com.mycompany.masterrules.Model.finanzas;


import com.mycompany.masterrules.Model.possystem.Bill;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class CashRegisterAuditReportManager {

    private ArrayList<CashRegisterAuditReport> cashRegisterAuditReports;
    CashRegisterAuditReport currentCashRegisterAuditReport;

    public CashRegisterAuditReportManager() {
        currentCashRegisterAuditReport = new CashRegisterAuditReport(new BigDecimal("0"));
        cashRegisterAuditReports = new ArrayList<>();
    }

    public void removeCashRegisterAuditReport(CashRegisterAuditReport cashRegisterAuditReport) {
        cashRegisterAuditReports.remove(cashRegisterAuditReport);
    }


    public void addSaleToCashRegisterAuditReport(Bill sale) {
        currentCashRegisterAuditReport.addBill(sale);
    }


    // esto, seguro que va aqui? pero si hace lo que dice el metodo
    public void withdrawCash(String reason, String amount) throws Exception {

        BigDecimal amountBigDecimal = new BigDecimal(amount);
        if (currentCashRegisterAuditReport.getCurrentCashAmount().compareTo(amountBigDecimal) >= 0) {
            currentCashRegisterAuditReport.getCashOutFlowReports().add(new CashFlowReport(reason, amountBigDecimal));
            currentCashRegisterAuditReport.setCurrentCashAmount(currentCashRegisterAuditReport.getCurrentCashAmount().subtract(amountBigDecimal));
        } else {
            throw new IllegalArgumentException("No hay suficiente dinero en caja");
        }

    }

    // esto, seguro que va aqui? pero si hace lo que dice el metodo
    public void depositCash(String reason, String amount) throws Exception {
        if (amount.matches("\\d+")) {
            BigDecimal amountBigDecimal = new BigDecimal(amount);
            if (amountBigDecimal.compareTo(BigDecimal.ZERO) >= 0) {
                currentCashRegisterAuditReport.getCashInFlowReports().add(new CashFlowReport(reason, amountBigDecimal));
                currentCashRegisterAuditReport.setCurrentCashAmount(currentCashRegisterAuditReport.getCurrentCashAmount().add(amountBigDecimal));
            } else {
                throw new IllegalArgumentException("No se puede depositar una cantidad menor o igual a cero");
            }
        }
    }

    /**
     * Metodo para finalizar el corte actual.
     *
     * @param initialCashAmount Monto con el cual iniciara el siguiente corte.
     */
    public void finalizeCashRegisterAuditReport(BigDecimal initialCashAmount) {
        currentCashRegisterAuditReport.calculateFinalCashAmount();
        currentCashRegisterAuditReport.setFinalCutofDate(LocalDateTime.now());
        cashRegisterAuditReports.add(currentCashRegisterAuditReport);
        currentCashRegisterAuditReport = new CashRegisterAuditReport(initialCashAmount);
    }

    public void startCashRegisterAuditReport(BigDecimal initialCashAmount) {
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