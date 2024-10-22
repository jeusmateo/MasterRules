package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.List;

public class Combo {

    private String comboName;
    private List<Product> products;
    private ComboTemplate productsTemplate;
    private BigDecimal price;
    private BigDecimal VIPPrice;

    public Combo(String comboName, List<Product> products, ComboTemplate productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
        this.comboName = comboName;
        this.products = products;
        this.productsTemplate = productsTemplate;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    public Combo(List<Product> products, ComboTemplate productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
        this.comboName = "Combo Temporal";
        this.products = products;
        this.productsTemplate = productsTemplate;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    public void setComboName(String comboName) {

        this.comboName = comboName;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setProductsTemplate(ComboTemplate productsTemplate) {
        this.productsTemplate = productsTemplate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setVIPPrice(BigDecimal VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

    public String getComboName() {
        return comboName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ComboTemplate getProductsTemplate() {
        return productsTemplate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVIPPrice() {
        return VIPPrice;
    }

    

}
