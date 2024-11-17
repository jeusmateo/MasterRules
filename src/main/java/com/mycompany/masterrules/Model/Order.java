package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Embeddable
public class Order {

    @Transient
    private long id; //Chepo necesidad: Este es el numero de la comanda
    @ManyToOne
    private Customer customer; //Chepo necesidad: el nombre del cliente a quien entregar, Tomando en cuenta, esto deberia ser solo un nombre??? <- Nota muy
    // @ManyToMany(fetch = FetchType.EAGER)
    private String employeeName; //TODO debe ser un nombre mas claro que diga que es el empleado de turno quien toma la comanda
    private String numeroDeMesa; //TODO CAMBIAR NOMBRE A INGLES
    @ElementCollection

    @ManyToMany(fetch = FetchType.EAGER)
    private String comment;
    private String deliveryMethod; //Chepo necesidad: esto aun falta por checar
    private LocalDateTime date; //Chepo necesidad: TODO NO DEBERIA SER DATE SINO QUE LA FECHA DE ENVIADO A COCINA.
    private BigDecimal totalAmount; //Chepo necesidad: TODO ESTO DEBERIA SER EL SUBTOTAL

    private List<OrderItem> products;


    public Order() {
        products = new ArrayList<>();
    }


/*
    public void calculateTotalAmount(){
        BigDecimal total = new BigDecimal(0);
        for(Product product: products){
            total=total.add(product.getPrice());
        }
        for(Combo combo: combos){
            total = total.add(combo.getPrice());
        }
        totalAmount = total;
    }

 */
    //TODO no funciona, si deberiamos checar que sean iguales y acceder al index del array y editarlo
    public void addProductToOrder(OrderItem product) {
        if (products.contains(product)) {
            product.addQuantity();
        }
    }


    public void removeProduct(Product product) {
        products.remove(product);
    }



    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }


    public String getComment() {
        return comment;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(customer, order.customer) && Objects.equals(products, order.products) && Objects.equals(combos, order.combos) && Objects.equals(comment, order.comment) && Objects.equals(deliveryMethod, order.deliveryMethod) && Objects.equals(date, order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, products, combos, comment, deliveryMethod, date);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                ", combos=" + combos +
                ", comment='" + comment + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", date=" + date +
                '}';
    }
}

