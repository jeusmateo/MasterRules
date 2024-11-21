package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.customers.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
    private List<OrderItem> orderItemList;


    public Order() {
        orderItemList = new ArrayList<>();
    }

    public List<OrderItem> getPedidoComandaList() {
        return orderItemList;
    }
    //public List<Combo> combos;

    public void calculateTotalAmount() {
        BigDecimal calculatedTotalAmount = BigDecimal.ZERO;
        if(customer!= null && customer.getCustomerAccount().isIsVIP()){
            for (OrderItem currentProduct : orderItemList) {
                calculatedTotalAmount = calculatedTotalAmount.add(currentProduct.getTotalVipPrice());
            }
        }else{
            for (OrderItem currentProduct : orderItemList) {
                calculatedTotalAmount = calculatedTotalAmount.add(currentProduct.getTotalPrice());
            }
        }
        this.totalAmount= calculatedTotalAmount;
    }
    public void addProductToOrderItemList(OrderItem newOrderItem) {
        if(newOrderItem.getProduct().getStockInfo().getCurrentStock()>0){
            boolean found = false;

            if (!orderItemList.isEmpty()) {
                for (OrderItem orderItem : orderItemList) {
                    if (newOrderItem.getProduct().getId().equals(orderItem.getProduct().getId())) {

                        orderItem.addQuantity();
                        found = true;
                        break; // Ya lo encontramos, no es necesario seguir iterando.
                    }
                }
                if (!found) {
                    orderItemList.add(newOrderItem);

                }
            } else {
                orderItemList.add(newOrderItem);

            }

        }

        if(newOrderItem.getProduct().getStockInfo() == null){
            boolean found = false;

            if (!orderItemList.isEmpty()) {
                for (OrderItem orderItem : orderItemList) {
                    if (newOrderItem.getProduct().getId().equals(orderItem.getProduct().getId())) {

                        orderItem.addQuantity();
                        found = true;
                        break; // Ya lo encontramos, no es necesario seguir iterando.
                    }
                }
                if (!found) {
                    orderItemList.add(newOrderItem);

                }
            } else {
                orderItemList.add(newOrderItem);

            }
        }


    }

    public void removeProductFromOrderItemList(OrderItem newOrderItem) {
        if (!orderItemList.isEmpty()) {
            Iterator<OrderItem> iterator = orderItemList.iterator();
            while (iterator.hasNext()) {
                OrderItem orderItem = iterator.next();
                if (newOrderItem.getProduct().getId().equals(orderItem.getProduct().getId())) {
                    if (orderItem.getQuantity() > 0) {
                        orderItem.removeQuantity();
                    }
                    if (orderItem.getQuantity() == 0) {
                        iterator.remove(); // Elimina directamente el elemento de la lista
                    }
                    break; // No es necesario seguir iterando
                }
            }
        }
    }

    public void calculateVipTotalAmount(){
        if(customer.getCustomerAccount().isIsVIP()){
                BigDecimal calculatedTotalAmount = BigDecimal.ZERO;
                for (OrderItem currentProduct : orderItemList) {
                    calculatedTotalAmount = calculatedTotalAmount.add(currentProduct.getTotalVipPrice());
                }

            totalAmount= calculatedTotalAmount;
        }else{
            calculateTotalAmount();
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
        if(customer==null){
            calculateTotalAmount();
        }else{
            calculateVipTotalAmount();
        }



        return this.totalAmount;
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
                ", products=" + orderItemList +
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
                Objects.equals(orderItemList, order.orderItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, customer, employeeName, tableNumber, additionalComment, deliveryMethod, date, totalAmount, orderItemList);
    }
}

