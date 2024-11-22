package com.mycompany.masterrules.Model.cafeteria;

import java.math.BigDecimal;


public class ProductCT {
    private String name;
    private String category;
    private BigDecimal price;
    private BigDecimal VIPPrice;


    public ProductCT(String productName, String productType, BigDecimal price, BigDecimal VIPPrice) {
        this.name = productName;
        this.category = productType;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    public ProductCT(String productName, BigDecimal price, BigDecimal VIPPrice) {
        this.name = productName;
        this.category = "combo";
        this.price = price;
        this.VIPPrice = VIPPrice;
    }


    protected ProductCT() {

    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String productType) {
        this.category = productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVIPPrice() {
        return VIPPrice;
    }

    public void setVIPPrice(BigDecimal VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

}
