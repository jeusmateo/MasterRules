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
    private String employeeName;
    private Customer customerAccount;
    private BigDecimal amount;
    private Order order;

    public BigDecimal determineProductPrice(){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public BigDecimal determineAmount(){
        throw new UnsupportedOperationException("Not supported yet.");
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
