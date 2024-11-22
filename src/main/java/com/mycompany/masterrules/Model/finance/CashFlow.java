package com.mycompany.masterrules.Model.finance;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class CashFlow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reportId;
    private String reasonForReport;
    @Enumerated(EnumType.STRING)
    private FlowType flowType;
    private BigDecimal cashAmount;
    private LocalDateTime cashFlowDate;

    public CashFlow(String reason, BigDecimal cashAmount) {
        this.reasonForReport = reason;
        this.cashAmount = cashAmount;
        this.cashFlowDate = LocalDateTime.now();
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return cashFlowDate.format(formatter);
    }

    public long getId() {
        return reportId;
    }

    public String getReason() {
        return reasonForReport;
    }


    public void setReason(String reason) {
        this.reasonForReport = reason;
    }


    public BigDecimal getCashAmount() {
        return cashAmount;
    }


    public void setCashAmount(BigDecimal cashAmount) {
        this.cashAmount = cashAmount;
    }

    public LocalDateTime getDate() {
        return cashFlowDate;
    }

    public void setDate(LocalDateTime date) {
        this.cashFlowDate = date;
    }

    public FlowType getFlowType() {
        return flowType;
    }

    public void setFlowType(FlowType flowType) {
        this.flowType = flowType;
    }

    protected CashFlow() {

    }
}

