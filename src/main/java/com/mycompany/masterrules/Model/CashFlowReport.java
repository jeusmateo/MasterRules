package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CashFlowReport {


    private String reason;


    private BigDecimal cashAmount;


    private String date;



    public CashFlowReport(String reason, BigDecimal cashAmount) {
        this.reason = reason;
        this.cashAmount = cashAmount;
        LocalDateTime actualDateTime = LocalDateTime.now();
        
        // Define el formato deseado, por ejemplo, "dd-MM-yyyy HH:mm:ss"
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        
        // Formatea la fecha y hora actual
        this.date = actualDateTime.format(format);
    }


    public String getReason() {
        return reason;
    }


    public void setReason(String reason) {
        this.reason = reason;
    }


    public BigDecimal getCashAmount() {
        return cashAmount;
    }


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
