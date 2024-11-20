package com.mycompany.masterrules.Model.cafeteria;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


public class ComboCT  {
    private String name;
    private BigDecimal price;
    private BigDecimal VIPPrice;
    private List<Product> products;




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
        ComboCT combo = (ComboCT) o;
        return Objects.equals(products, combo.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), products);
    }
}
