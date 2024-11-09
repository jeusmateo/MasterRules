package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "CombosDefinidos")
public class Combo {


    @Id
    private String comboID;//se cambio esto a string
    @Column(name = "ComboName")
    private String comboName;
    @OneToMany
    private List<Product> products;
    @Transient
    private CustomComboTemplate productsTemplate;
   // @Column(name = "price")
    private BigDecimal price;
   // @Column(name = "VIPPrice")
    private BigDecimal VIPPrice;

    public Combo() {

    }



    public Combo(String comboName, List<Product> products, CustomComboTemplate productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
        this.comboName = comboName;
        this.products = products;
        this.productsTemplate = productsTemplate;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    public Combo(List<Product> products, CustomComboTemplate productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
        this.comboName = "Combo Temporal";
        this.products = products;
        this.productsTemplate = productsTemplate;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    public Combo(List<Product>products, BigDecimal price, BigDecimal VIPPrice, String comboName) {
        this.comboName = comboName;
        this.products = products;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    public void setComboName(String comboName) {

        this.comboName = comboName;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setProductsTemplate(CustomComboTemplate productsTemplate) {
        this.productsTemplate = productsTemplate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setVIPPrice(BigDecimal VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

    public String getComboID() {//agregue el getter porque necesito el id 
        return comboID;
    }
    
    public String getComboName() {
        return comboName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public CustomComboTemplate getProductsTemplate() {
        return productsTemplate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVIPPrice() {
        return VIPPrice;
    }

    

}
