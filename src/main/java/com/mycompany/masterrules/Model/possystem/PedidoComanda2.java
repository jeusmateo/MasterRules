package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.cafeteria.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class PedidoComanda2 {
    private int quantity;
    @Id
    private String product;
    private BigDecimal amount;

    protected PedidoComanda2() {
    }

    public PedidoComanda2(Product product, int quantity) {
        this.quantity =  quantity;
        this.product = product.getName();
        this.amount = product.getPrice().multiply(new BigDecimal(quantity));
    }

    public void addQuantity(){
        quantity++;
    }
    public int getQuantity() {
        return quantity;
    }


}
