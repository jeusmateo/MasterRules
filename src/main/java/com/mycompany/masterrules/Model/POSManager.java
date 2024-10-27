/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.util.ArrayList;

/**
 *
 * @author IGNITER
 */
public class POSManager {
    private CustomerManager customerManager;
    private CashRegisterAuditReportManager cashRegisterAuditReportManager;
    private CafeteriaManager cafeteriaManager;
    private Printer printer;

    public POSManager(CashRegisterAuditReportManager cashRegisterAuditReportManagerArg, CafeteriaManager cafeteriaManagerArg) {
        customerManager = new CustomerManager();
        cashRegisterAuditReportManager = cashRegisterAuditReportManagerArg;
        cafeteriaManager = cafeteriaManagerArg;
        printer = new Printer();
    }

    public void vender(long id, String type){
        
        ArrayList<Product> temp= this.cafeteriaManager.getMenu().getProductos(type);
        Product item = new Product();
        for(int i=0;i<temp.size();i++){
            if(id==temp.get(i).getID()){
                item= temp.get(i);
            }
        }
        Order temporalOrder = new Order(item);
        Bill billTemp = new Bill(temporalOrder);
        this.cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().addBill(billTemp);
        printer.imprimir(temporalOrder);
        
        
    }
    
    public CustomerManager getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }

    public CashRegisterAuditReportManager getCashRegisterAuditReportManager() {
        return cashRegisterAuditReportManager;
    }

    public void setCashRegisterAuditReportManager(CashRegisterAuditReportManager cashRegisterAuditReportManager) {
        this.cashRegisterAuditReportManager = cashRegisterAuditReportManager;
    }

    public CafeteriaManager getCafeteriaManager() {
        return cafeteriaManager;
    }

    public void setCafeteriaManager(CafeteriaManager cafeteriaManager) {
        this.cafeteriaManager = cafeteriaManager;
    }
    
    public void createNewCustomer(String name, String phone){
        customerManager.registerCustomer(name, phone);
        
        
    }

}
