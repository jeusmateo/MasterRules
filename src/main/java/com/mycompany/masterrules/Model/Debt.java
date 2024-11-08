package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author David Torres
 *
 */
@Entity
@Table(name = "DEUDAS")
public class Debt {

    // Si da mucho problema estoy considerando poner el ID del cliente en lugar de la orden
    @EmbeddedId
    private Order order;

    @Column(name = "Monto")
    private BigDecimal amount;

    @Column(name = "Fecha")
    private LocalDate date; //pensar en guardarlo como string


    /**
     * Constructor por defecto para JPA
     * */
    public Debt() {
    }

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
