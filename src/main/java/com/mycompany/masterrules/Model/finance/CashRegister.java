package com.mycompany.masterrules.Model.finance;

import com.mycompany.masterrules.Model.retailsystem.POSManager;
import com.mycompany.masterrules.Model.users.Permission;

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

        var currentUser = POSManager.getInstance().getCurrentUser();
        if (!currentUser.hasPermission(Permission.RECORD_CASHIN)) {
            throw new IllegalArgumentException("El usuario no tiene permisos para retirar dinero de caja");
        }

        BigDecimal amountBigDecimal = new BigDecimal(amount);
        if (currentCashAmount.compareTo(amountBigDecimal) >= 0) {

            supervisor.addNewCashOutFlow(new CashFlow(reason, amountBigDecimal));
            currentCashAmount = currentCashAmount.subtract(amountBigDecimal);

        } else {
            throw new IllegalArgumentException("No hay suficiente dinero en caja");
        }

    }

    public void depositCash(String reason, String amount) throws Exception {

        var currentUser = POSManager.getInstance().getCurrentUser();
        if (!currentUser.hasPermission(Permission.RECORD_CASHOUT)) {
            throw new IllegalArgumentException("El usuario no tiene permisos para depositar dinero en caja");
        }

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
