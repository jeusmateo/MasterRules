package com.mycompany.masterrules.Model.finanzas;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class CashFlowReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String reason;
    @Enumerated(EnumType.STRING)
    private FlowType flowType;
    private BigDecimal cashAmount;
    private LocalDateTime date;

    public CashFlowReport(String reason, BigDecimal cashAmount) {
        this.reason = reason;
        this.cashAmount = cashAmount;
        this.date = LocalDateTime.now();
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }

    public long getId() {
        return id;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    protected CashFlowReport() {

    }
}

