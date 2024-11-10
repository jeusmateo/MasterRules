package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author David Torres
 */
public class CashFlowReport {

    /**Motivo por el cual se realiza el reporte */
    private String reason;

    /**Cantidad de dinero que se mueve */
    private BigDecimal cashAmount;

    /**Fecha en la que se realiza el reporte */
    private LocalDate date;


    /**
     *  Constructor para el Reporte de movimiento de caja.
     * @param reason Motivo por el cual se realiza el movimiento
     * @param cashAmount Cantidad de dinero que se mueve
     */
    public CashFlowReport(String reason, BigDecimal cashAmount) {
        this.reason = reason;
        this.cashAmount = cashAmount;
        this.date = LocalDate.now();
    }

    /**
     * Obtiene la fecha en la que se realiza el reporte
     */
    public String getReason() {
        return reason;
    }

    /**
     * Establece la fecha en la que se realiza el reporte
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Obtiene la cantidad de dinero que se mueve
     */
    public BigDecimal getCashAmount() {
        return cashAmount;
    }

    /**
     * Establece la cantidad de dinero que se mueve
     */
    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
}
