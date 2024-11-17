package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @autor: Raul Magaña
 */
@Embeddable
public class Order {

    @Transient
    private long id;
    @ManyToOne
    private Customer customer;
    // @ManyToMany(fetch = FetchType.EAGER)
    @ElementCollection
    private Map<Product, Integer> products;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Combo> combos;
    private String comment;
    private String deliveryMethod;
    private LocalDateTime date;
    private BigDecimal totalAmount;

    //private List<OrderItem> orderItems;


    public Order() {
        products = new HashMap<>();
        combos = new ArrayList<>();
        date = LocalDateTime.now();
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

    public void addProductToOrder(Product product) {
        if (products.containsKey(product)) {
            products.put(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    public void addProduct(Product product, int quantity) {
        products.put(product, quantity);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void addCombo(Combo combo) {
        combos.add(combo);
    }

    public void removeCombo(Combo combo) {
        combos.remove(combo);
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

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public List<Combo> getCombos() {
        return combos;
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


    public void setCombos(ArrayList<Combo> combos) {
        this.combos = combos;
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

