package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String date;


    /**
     *  Constructor para el Reporte de movimiento de caja.
     * @param reason Motivo por el cual se realiza el movimiento
     * @param cashAmount Cantidad de dinero que se mueve
     */
    public CashFlowReport(String reason, BigDecimal cashAmount) {
        this.reason = reason;
        this.cashAmount = cashAmount;
        LocalDateTime actualDateTime = LocalDateTime.now();
        
        // Define el formato deseado, por ejemplo, "dd-MM-yyyy HH:mm:ss"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        // Formatea la fecha y hora actual
        this.date = actualDateTime.format(format);
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}
