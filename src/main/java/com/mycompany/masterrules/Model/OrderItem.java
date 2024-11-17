package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

public class OrderItem {
    private int quantity;
    private Product product;
    private BigDecimal amount;


    public OrderItem(int quantity, Product product) {
        this.quantity = quantity;
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
