package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Embeddable
public class OrderItem {
    private String itemName;

    private int quantity;
    @ManyToOne
    private Product product;
    private BigDecimal amount;

    protected OrderItem() {
    }

    public void removeQuantity() {
        this.quantity--;
    }

    public OrderItem(Product product) {
        this.itemName = product.getName();
        this.quantity = 1;
        this.product = product;
        this.amount = product.getPrice().multiply(new BigDecimal(quantity));
    }

    public BigDecimal getTotalVipPrice(){
        return product.getVIPPrice().multiply(new BigDecimal(quantity));
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
        return product.getName();
    }
}
