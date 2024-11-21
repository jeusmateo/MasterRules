package com.mycompany.masterrules.Model.cafeteria;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
public class Combo extends Product {

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    protected Combo() {

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Combo combo = (Combo) o;
        return Objects.equals(products, combo.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), products);
    }
}
