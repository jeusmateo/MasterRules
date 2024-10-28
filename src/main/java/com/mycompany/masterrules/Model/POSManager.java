/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author IGNITER
 */
public class POSManager {

    private CustomerManager customerManager;
    private CashRegisterAuditReportManager cashRegisterAuditReportManager;
    private CafeteriaManager cafeteriaManager;
    private Printer printer;
    private UserAccount currentUser;
    private Order currentOrder;

    public Product buscarProducto(long id, String type){
       for(Product product : cafeteriaManager.getMenu().getProductos(type)){
           if(product.getID()== id){
               return product;
           }
       }
            return null;
    }

    public POSManager(CashRegisterAuditReportManager cashRegisterAuditReportManagerArg, CafeteriaManager cafeteriaManagerArg) {
        customerManager = new CustomerManager();
        cashRegisterAuditReportManager = cashRegisterAuditReportManagerArg;
        cafeteriaManager = cafeteriaManagerArg;
        printer = new Printer();
        currentOrder = new Order();
    }

    public POSManager(CashRegisterAuditReportManager cashRegisterAuditReportManagerArg, CafeteriaManager cafeteriaManagerArg, UserAccount userAccount) {
        customerManager = new CustomerManager();
        currentUser = userAccount;
        cashRegisterAuditReportManager = cashRegisterAuditReportManagerArg;
        cafeteriaManager = cafeteriaManagerArg;
        printer = new Printer();
        currentOrder = new Order();
    }

    public POSManager(CashRegisterAuditReportManager cashRegisterAuditReportManagerArg, CafeteriaManager cafeteriaManagerArg, String user) {
        if (user.equals("admin")) {
            currentUser = new UserAccount("admin", "admin");
        }
        customerManager = new CustomerManager();
        cashRegisterAuditReportManager = cashRegisterAuditReportManagerArg;
        cafeteriaManager = cafeteriaManagerArg;
        printer = new Printer();
        currentOrder = new Order();
    }

    public void addProductToOrder(Product product) {
        currentOrder.addProduct(product);

    }

    public void configureOrder(String eleccion, String comentario, Customer customer) {
        currentOrder.setDeliveryMethod(eleccion);
        currentOrder.setComment(comentario);
        currentOrder.setCustomer(customer);
        currentOrder.setDate(LocalDateTime.now());
    }

    public void configureOrder(String eleccion, String comentario) {
        currentOrder.setDeliveryMethod(eleccion);
        currentOrder.setComment(comentario);
    }

    public void sell() {
        BigDecimal amount = new BigDecimal("0");
        if (currentOrder.getCustomer() == null) {
            
            for (Product product : currentOrder.getProducts()) {
                amount = amount.add(product.getPrice());
            }
        } else {
            if (currentOrder.getCustomer().getCustomerAccount().isIsVIP()) {
                for (Product product : currentOrder.getProducts()) {
                    amount = amount.add(product.getVIPprice());
                }
            } else {
                for (Product product : currentOrder.getProducts()) {
                    amount = amount.add(product.getPrice());
                }
            }
        }
        Bill tempBill = new Bill(currentOrder, amount, currentUser.getEmployeeName());
        this.cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().addBill(tempBill);
        printer.imprimir(currentOrder);
        //Falta agregar la factura a la lista de facturas del cliente
        //Falta crear la nueva orden vacia
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

    public void createNewCustomer(String name, String phone) {
        customerManager.registerCustomer(name, phone);

    }

}
