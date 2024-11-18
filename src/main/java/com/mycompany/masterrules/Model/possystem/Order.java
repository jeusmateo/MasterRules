package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.customers.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Embeddable
public class Order {
    @Column(name = "order_id")
    private long id; //Chepo necesidad: Este es el numero de la comanda
    @ManyToOne
    private Customer customer; //Chepo necesidad: el nombre del cliente a quien entregar, Tomando en cuenta, esto deberia ser solo un nombre??? <- Nota muy
    // @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "order_employee_name")
    private String employeeName;
    private String tableNumber;
    private String comment;
    private String deliveryMethod;
    private LocalDateTime date; //Chepo necesidad: TODO NO DEBERIA SER DATE SINO QUE LA FECHA DE ENVIADO A COCINA.
    private BigDecimal totalAmount;
    @OneToMany
    private List<PedidoComanda> pedidoComandaList;


    public Order() {
        pedidoComandaList = new ArrayList<>();
    }

    public List<PedidoComanda> getPedidoComandaList() {
        return pedidoComandaList;
    }

    public BigDecimal calculateTotalAmount() {
        BigDecimal calculatedTotalAmount = BigDecimal.ZERO;
        for (PedidoComanda currentProduct : pedidoComandaList) {
            calculatedTotalAmount = calculatedTotalAmount.add(currentProduct.getTotalPrice());
        }
        return calculatedTotalAmount;
    }
    public void addProductToOrderItemList(PedidoComanda newPedidoComanda) {

        boolean found = false;

        if (!pedidoComandaList.isEmpty()) {
            for (PedidoComanda p : pedidoComandaList) {
                if (newPedidoComanda.getProduct().getId().equals(p.getProduct().getId())) {
                    p.addQuantity();
                    found = true;
                    break; // Ya lo encontramos, no es necesario seguir iterando.
                }
            }
            if (!found) {
                pedidoComandaList.add(newPedidoComanda);

            }
        } else {
            pedidoComandaList.add(newPedidoComanda);

        }

    }



    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }



    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDateNow() {
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return calculateTotalAmount();
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", employeeName='" + employeeName + '\'' +
                ", numeroDeMesa='" + tableNumber + '\'' +
                ", comment='" + comment + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", products=" + pedidoComandaList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(employeeName, order.employeeName) &&
                Objects.equals(tableNumber, order.tableNumber) &&
                Objects.equals(comment, order.comment) &&
                Objects.equals(deliveryMethod, order.deliveryMethod) &&
                Objects.equals(date, order.date) &&
                Objects.equals(totalAmount, order.totalAmount) &&
                Objects.equals(pedidoComandaList, order.pedidoComandaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, employeeName, tableNumber, comment, deliveryMethod, date, totalAmount, pedidoComandaList);
    }
}

