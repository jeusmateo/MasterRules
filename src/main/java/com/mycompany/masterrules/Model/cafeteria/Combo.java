package com.mycompany.masterrules.Model.cafeteria;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Combo extends Product {

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    protected Combo() {
        throw new IllegalArgumentException("No se puede crear un combo sin productos");
    }

    public Combo(String comboName, List<Product> products, BigDecimal price, BigDecimal VIPPrice) {
        super(comboName, price, VIPPrice);
        this.products = products;

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
