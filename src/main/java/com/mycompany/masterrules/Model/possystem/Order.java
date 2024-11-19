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
    private long orderId; //Chepo necesidad: Este es el numero de la comanda
    @ManyToOne
    private Customer customer; //Chepo necesidad: el nombre del cliente a quien entregar, Tomando en cuenta, esto deberia ser solo un nombre??? <- Nota muy
    // @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "order_employee_name")
    private String employeeName;
    private String tableNumber;
    private String additionalComment;
    private String deliveryMethod;
    @Column(name = "order_date")
    private LocalDateTime date; //Chepo necesidad: TODO NO DEBERIA SER DATE SINO QUE LA FECHA DE ENVIADO A COCINA.
    private BigDecimal totalAmount;
    @ElementCollection
    private List<OrderDetail> orderDetailList;


    public Order() {
        orderDetailList = new ArrayList<>();
    }

    public List<OrderDetail> getPedidoComandaList() {
        return orderDetailList;
    }

    public BigDecimal calculateTotalAmount() {
        BigDecimal calculatedTotalAmount = BigDecimal.ZERO;
        for (OrderDetail currentProduct : orderDetailList) {
            calculatedTotalAmount = calculatedTotalAmount.add(currentProduct.getTotalPrice());
        }
        return calculatedTotalAmount;
    }
    public void addProductToOrderItemList(OrderDetail newOrderDetail) {

        boolean found = false;

        if (!orderDetailList.isEmpty()) {
            for (OrderDetail p : orderDetailList) {
                if (newOrderDetail.getProduct().getId().equals(p.getProduct().getId())) {
                    p.addQuantity();
                    found = true;
                    break; // Ya lo encontramos, no es necesario seguir iterando.
                }
            }
            if (!found) {
                orderDetailList.add(newOrderDetail);

            }
        } else {
            orderDetailList.add(newOrderDetail);

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
        return additionalComment;
    }

    public void setComment(String comment) {
        this.additionalComment = comment;
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
        return orderId;
    }

    public void setId(Long id) {
        this.orderId = id;
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
                "id=" + orderId +
                ", customer=" + customer +
                ", employeeName='" + employeeName + '\'' +
                ", numeroDeMesa='" + tableNumber + '\'' +
                ", comment='" + additionalComment + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", products=" + orderDetailList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(employeeName, order.employeeName) &&
                Objects.equals(tableNumber, order.tableNumber) &&
                Objects.equals(additionalComment, order.additionalComment) &&
                Objects.equals(deliveryMethod, order.deliveryMethod) &&
                Objects.equals(date, order.date) &&
                Objects.equals(totalAmount, order.totalAmount) &&
                Objects.equals(orderDetailList, order.orderDetailList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customer, employeeName, tableNumber, additionalComment, deliveryMethod, date, totalAmount, orderDetailList);
    }
}

