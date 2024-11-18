package com.mycompany.masterrules.Model.finanzas;

import java.math.BigDecimal;

public class CashRegister {
    private BigDecimal currentCashAmount;

//    public void withdrawCash(String reason, String amount) throws Exception {
//
//        BigDecimal amountBigDecimal = new BigDecimal(amount);
//        if (currentCashRegisterAuditReport.getCurrentCashAmount().compareTo(amountBigDecimal) >= 0) {
//            currentCashRegisterAuditReport.getCashOutFlowReports().add(new CashFlowReport(reason, amountBigDecimal));
//            currentCashRegisterAuditReport.setCurrentCashAmount(currentCashRegisterAuditReport.getCurrentCashAmount().subtract(amountBigDecimal));
//        } else {
//            throw new IllegalArgumentException("No hay suficiente dinero en caja");
//        }
//
//    }
//
//    public void depositCash(String reason, String amount) throws Exception {
//        if (amount.matches("\\d+")) {
//            BigDecimal amountBigDecimal = new BigDecimal(amount);
//            if (amountBigDecimal.compareTo(BigDecimal.ZERO) >= 0) {
//                currentCashRegisterAuditReport.getCashInFlowReports().add(new CashFlowReport(reason, amountBigDecimal));
//                currentCashRegisterAuditReport.setCurrentCashAmount(currentCashRegisterAuditReport.getCurrentCashAmount().add(amountBigDecimal));
//            } else {
//                throw new IllegalArgumentException("No se puede depositar una cantidad menor o igual a cero");
//            }
//        }
//    }

}
