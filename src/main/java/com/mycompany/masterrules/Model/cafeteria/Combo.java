package com.mycompany.masterrules.Model.cafeteria;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Combo extends Product {

    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;
    @Embedded
    private CustomCombo productsTemplate;

    protected Combo() {
    }

    public Combo(String comboName, List<Product> products, CustomCombo productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
        super(comboName, price, VIPPrice);
        this.products = products;
        this.productsTemplate = productsTemplate;

    }

//    public Combo(List<Product> products, CustomCombo productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
//        this.comboName = "Combo Temporal";
//        this.products = products;
//        this.productsTemplate = productsTemplate;
//        this.price = price;
//        this.VIPPrice = VIPPrice;
//    }


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

    public CustomCombo getProductsTemplate() {
        return productsTemplate;
    }

    public void setProductsTemplate(CustomCombo productsTemplate) {
        this.productsTemplate = productsTemplate;
    }

}
