package com.mycompany.masterrules.Model.finanzas;

import com.mycompany.masterrules.Model.possystem.Bill;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CashAuditReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CashRegisterAuditReportId;

    @OneToMany
    private List<CashFlow> cashFlowInReport;

    @OneToMany
    private List<CashFlow> cashFlowOutReport;
    private BigDecimal initialCashAmount;
    @OneToMany
    private List<Bill> bills;
    private BigDecimal cashBalance;
    private BigDecimal cashRevenue;
    private BigDecimal cardRevenue;
    private BigDecimal storeCreditRevenue;
    private LocalDateTime initialCutofDate;
    private LocalDateTime finalCutofDate;
    private BigDecimal cashFlowInTotalAmount;
    private BigDecimal cashFlowOutTotalAmount;

    public CashAuditReport(BigDecimal initialCashAmount) {
        this.initialCashAmount = initialCashAmount;
        this.initialCutofDate = LocalDateTime.now();
        this.bills = new ArrayList<>();
        this.cashFlowInReport = new ArrayList<>();
        this.cashFlowOutReport = new ArrayList<>();
        this.cashRevenue = BigDecimal.ZERO;
        this.cardRevenue = BigDecimal.ZERO;
        this.storeCreditRevenue = BigDecimal.ZERO;
        this.cashBalance = BigDecimal.ZERO;
        this.cashFlowInTotalAmount = BigDecimal.ZERO;
        this.cashFlowOutTotalAmount = BigDecimal.ZERO;

    }

    protected CashAuditReport() {
    }

    public void calculateCashFlowInTotalAmount() {
        for (CashFlow cashFlow : cashFlowInReport) {
            this.cashFlowInTotalAmount = this.cashFlowInTotalAmount.add(cashFlow.getCashAmount());
        }
    }

    public void calculateCashFlowOutTotalAmount() {
        for (CashFlow cashFlow : cashFlowOutReport) {
            this.cashFlowOutTotalAmount = this.cashFlowOutTotalAmount.add(cashFlow.getCashAmount());
        }
    }

    public void calculateCashBalance() {
        this.cashBalance = this.cashBalance.add(this.initialCashAmount);
        this.cashBalance = this.cashBalance.add(this.cashRevenue);
        this.cashBalance = this.cashBalance.add(this.cashFlowInTotalAmount);
        this.cashBalance = this.cashBalance.subtract(this.cashFlowOutTotalAmount);
    }

    public void configCashRegisterAuditReport() {
        CashierSupervisor cashierSupervisor = new CashierSupervisor();
        this.cashFlowInReport = cashierSupervisor.getCashInFlowsByDateRange(initialCutofDate, LocalDateTime.now());
        this.cashFlowOutReport = cashierSupervisor.getCashOutFlowsByDateRange(initialCutofDate, LocalDateTime.now());
        ArchiveInvoice archiveInvoice = new ArchiveInvoice();
        this.bills = archiveInvoice.getBillsByDateRange(initialCutofDate, LocalDateTime.now());
    }

    public void calculateCashRevenue() {
        for (Bill currentBill : this.bills) {
            if (currentBill.getPaymentMethod().equals("CASH") || currentBill.getPaymentMethod().equals("MIX") && currentBill.getPagadoEnEfectivo().compareTo(BigDecimal.ZERO) != 0) {
                cashRevenue = cashRevenue.add(currentBill.getAmount());
            }
        }
    }

    public void calculateCardhRevenue() {
        for (Bill currentBill : this.bills) {
            if (currentBill.getPaymentMethod().equals("CARD") || currentBill.getPaymentMethod().equals("MIX") && currentBill.getPagadoEnTajeta().compareTo(BigDecimal.ZERO) != 0) {
                cardRevenue = cardRevenue.add(currentBill.getAmount());
            }
        }
    }

    public void calculateStoreCreditRevenue() {
        for (Bill currentBill : this.bills) {
            if (currentBill.getPaymentMethod().equals("STORE_CREDIT") || currentBill.getPaymentMethod().equals("MIX") && currentBill.getPagadoEnCreditoDeTienda().compareTo(BigDecimal.ZERO) != 0) {
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

    public LocalDateTime getFinalCutofDate() {
        return finalCutofDate;
    }

    public void setFinalCutofDate(LocalDateTime finalCutofDate) {
        this.finalCutofDate = finalCutofDate;
    }

    public Long getId() {
        return CashRegisterAuditReportId;
    }

    public void setId(Long id) {
        this.CashRegisterAuditReportId = id;
    }

    public List<CashFlow> getCashFlowInReport() {
        return cashFlowInReport;
    }

    public void setCashFlowInReport(List<CashFlow> cashFlowInReport) {
        this.cashFlowInReport = cashFlowInReport;
    }

    public List<CashFlow> getCashFlowOutReport() {
        return cashFlowOutReport;
    }

    public void setCashFlowOutReport(List<CashFlow> cashFlowOutReport) {
        this.cashFlowOutReport = cashFlowOutReport;
    }

    public BigDecimal getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(BigDecimal cashBalance) {
        this.cashBalance = cashBalance;
    }

    public BigDecimal getCashRevenue() {
        return cashRevenue;
    }

    public void setCashRevenue(BigDecimal cashRevenue) {
        this.cashRevenue = cashRevenue;
    }

    public BigDecimal getCardRevenue() {
        return cardRevenue;
    }

    public void setCardRevenue(BigDecimal cardRevenue) {
        this.cardRevenue = cardRevenue;
    }

    public BigDecimal getStoreCreditRevenue() {
        return storeCreditRevenue;
    }

    public void setStoreCreditRevenue(BigDecimal storeCreditRevenue) {
        this.storeCreditRevenue = storeCreditRevenue;
    }

    public BigDecimal getCashFlowInTotalAmount() {
        return cashFlowInTotalAmount;
    }

    public void setCashFlowInTotalAmount(BigDecimal cashFlowInTotalAmount) {
        this.cashFlowInTotalAmount = cashFlowInTotalAmount;
    }

    public BigDecimal getCashFlowOutTotalAmount() {
        return cashFlowOutTotalAmount;
    }

    public void setCashFlowOutTotalAmount(BigDecimal cashFlowOutTotalAmount) {
        this.cashFlowOutTotalAmount = cashFlowOutTotalAmount;
    }


}
