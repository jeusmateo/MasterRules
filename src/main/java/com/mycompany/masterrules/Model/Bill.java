/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

/**
 *
 * @author IGNITER
 */
public class Bill {
    private Order order;
    private CustomerAccount customer;
    private String employeeName;
    private BigDecimal amount;

    public Bill(Order orderArg){
        order=orderArg;
        employeeName= "Chepo";
        amount = new BigDecimal("0");
        for (Product product : order.getProducts()) {
            amount.add(product.getPrice());
        }
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
    
}
