package com.mycompany.masterrules.Model.finance;

import java.math.BigDecimal;

public class CashRegister {
    private final CashierSupervisor supervisor;
    private BigDecimal currentCashAmount;

    public CashRegister() {
        currentCashAmount = BigDecimal.ZERO;
        supervisor = new CashierSupervisor();
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

            supervisor.addNewCashOutFlow(new CashFlow(reason, amountBigDecimal));
            currentCashAmount = currentCashAmount.subtract(amountBigDecimal);

        } else {
            throw new IllegalArgumentException("No hay suficiente dinero en caja");
        }

    }

    public void depositCash(String reason, String amount) throws Exception {
        if (amount.matches("\\d+")) {
            BigDecimal amountBigDecimal = new BigDecimal(amount);
            if (amountBigDecimal.compareTo(BigDecimal.ZERO) >= 0) {
                supervisor.addNewCashInFlow(new CashFlow(reason, amountBigDecimal));
                currentCashAmount = currentCashAmount.add(amountBigDecimal);
            } else {
                throw new IllegalArgumentException("No se puede depositar una cantidad menor o igual a cero");
            }
        }
    }

}
