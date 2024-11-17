package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class OrderItem {
    private int quantity;
    @Id
    @OneToOne
    private Product product;
    private BigDecimal amount;

    protected OrderItem() {
    }

    public OrderItem(Product product) {
        this.quantity = 1;
        this.product = product;
        this.amount = product.getPrice().multiply(new BigDecimal(quantity));
    }

    public void addQuantity(){
        quantity++;
    }
    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

    public String getProductName() {
        return product.getProductName();
    }
}
