package com.mycompany.masterrules.model.possystem;

import com.mycompany.masterrules.model.cafeteria.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

@Entity
public class PedidoComanda2 {
    private int quantity;
    @Id
    @OneToOne
    private String product;
    private BigDecimal amount;

    protected PedidoComanda2() {
    }

    public PedidoComanda2(Product product, int quantity) {
        this.quantity =  quantity;
        this.product = product.getProductName();
        this.amount = product.getPrice().multiply(new BigDecimal(quantity));
    }

    public void addQuantity(){
        quantity++;
    }
    public int getQuantity() {
        return quantity;
    }


}
