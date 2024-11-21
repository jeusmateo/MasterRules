package com.mycompany.masterrules.Model.finanzas;

import java.math.BigDecimal;

public class CashRegister {
    private BigDecimal currentCashAmount;
    private CashFlowReportManager cfrm = new CashFlowReportManager();
    public CashRegister() {
        currentCashAmount = BigDecimal.ZERO;
    }

    public BigDecimal getCurrentCashAmount() {
        return currentCashAmount;
    }

    public void setCurrentCashAmount(BigDecimal currentCashAmount) {
        this.currentCashAmount = currentCashAmount;
    }

    public void withdrawCash(String reason, String amount) throws Exception {


        BigDecimal amountBigDecimal = new BigDecimal(amount);
        if (currentCashAmount.compareTo(amountBigDecimal) >= 0) {

            cfrm.addNewCashOutFlowReport(new CashFlowReport(reason, amountBigDecimal));
            currentCashAmount = currentCashAmount.subtract(amountBigDecimal);

        } else {
            throw new IllegalArgumentException("No hay suficiente dinero en caja");
        }

    }

    public void depositCash(String reason, String amount) throws Exception {
        if (amount.matches("\\d+")) {
            BigDecimal amountBigDecimal = new BigDecimal(amount);
            if (amountBigDecimal.compareTo(BigDecimal.ZERO) >= 0) {
                cfrm.addNewCashInFlowReport(new CashFlowReport(reason, amountBigDecimal));
                currentCashAmount = currentCashAmount.add(amountBigDecimal);
            } else {
                throw new IllegalArgumentException("No se puede depositar una cantidad menor o igual a cero");
            }
        }
    }

}
