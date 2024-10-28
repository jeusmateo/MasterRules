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
public class Test2PorqueElUnoYaNoMeDeja {
    public static void main(String[] args){
        try{
        CashRegisterAuditReportManager crarm = new CashRegisterAuditReportManager();
        CafeteriaManager cm = new CafeteriaManager();

        POSManager pos = new POSManager(crarm, cm);

        pos.getCashRegisterAuditReportManager().finalizeCashRegisterAuditReport(new BigDecimal("10000"));
        System.out.println("Jalamos sin pedos");
        pos.getCashRegisterAuditReportManager().withdrawCash("Prueba", new BigDecimal("10000"));
        pos.getCashRegisterAuditReportManager().depositCash("Prueba", new BigDecimal("10000"));

        System.out.println("En caja hay: " + pos.getCashRegisterAuditReportManager().currentCashRegisterAuditReport.getCurrentCashAmount());
        pos.getCashRegisterAuditReportManager().withdrawCash("Prueba", new BigDecimal("10000"));
        System.out.println("En caja hay: " + pos.getCashRegisterAuditReportManager().currentCashRegisterAuditReport.getCurrentCashAmount());


        
        pos.getCafeteriaManager().createProduct(1, "Chepo", "Comida", new BigDecimal("4.0"), new BigDecimal("44.0"));
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
            
        }
    }
}
