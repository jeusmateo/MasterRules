/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author IGNITER
 */
public class Debt {
    private Order order;
    private BigDecimal amount;
    private LocalDate date; //pensar en guardarlo como string

    public Debt(Order order, BigDecimal amount) {
        this.order = order;
        this.amount = amount;
        this.date = LocalDate.now();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    
}
