package com.mycompany.masterrules.Model.possystem;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Debt {

    // TODO FIXME CONFIRMAR SI ESTA CORRECTO
    // Si da mucho problema estoy considerando poner el ID del cliente en lugar de la orden
    // Me rendí, lo dejo así (Copilot me entiende)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Constructor por defecto para JPA
     * */
    protected Debt() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Debt debt = (Debt) o;
        return id == debt.id && Objects.equals(order, debt.order) && Objects.equals(amount, debt.amount) && Objects.equals(date, debt.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, amount, date);
    }

    @Override
    public String toString() {
        return "Debt{" +
                "id=" + id +
                ", order=" + order +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
