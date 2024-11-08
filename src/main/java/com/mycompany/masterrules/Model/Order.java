package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @autor: Raul Magaña
 *
 *
 */
public class Order {
    private long id;
    private Customer customer;
    private List<Product> products;
    private List<Combo> combos;
    private String comment;
    private String deliveryMethod;
    private LocalDateTime date;

    public Order(Product pro){
        // TODO: NO OLVIDAR CAMBIAR ESTE ID=4
        id=4;
        products = new ArrayList();
        products.add(pro);
        date=LocalDateTime.now();
    }

    public Order(){
        products = new ArrayList();
        combos = new ArrayList();
        date= LocalDateTime.now();
    }

    public Order(Product pro, Customer customerArg){
        customer=customerArg;
        id=4;
        products = new ArrayList();
        products.add(pro);
        date=LocalDateTime.now();
    }

    public void addProduct(Product product) {
        products.add(product);
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

    public List<Product> getProducts() {
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

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
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


}

