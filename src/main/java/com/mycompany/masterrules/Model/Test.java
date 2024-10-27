/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

/**
 *
 * @author chepo
 */
public class Test {

    public static void main(String[] args) {
        CashRegisterAuditReportManager crarm = new CashRegisterAuditReportManager();
        crarm.finalizeCashRegisterAuditReport(new BigDecimal(1000));
        System.out.println("Jalamos sin pedos");
        crarm.withdrawCash("Prueba", new BigDecimal(10000));
        crarm.depositCash("Prueba", new BigDecimal(10000));

        System.out.println("En caja hay: " + crarm.currentCashRegisterAuditReport.getCurrentCashAmount());
        crarm.withdrawCash("Prueba", new BigDecimal(10000));
        System.out.println("En caja hay: " + crarm.currentCashRegisterAuditReport.getCurrentCashAmount());
    }
}
