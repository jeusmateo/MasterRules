package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Combo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String comboID;//se cambio esto a string
    private String comboName;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;
    @Transient
    private CustomComboTemplate productsTemplate;
    private BigDecimal price;
    private BigDecimal VIPPrice;



    public Combo(String comboName, List<Product> products, CustomComboTemplate productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
        this.comboName = comboName;
        this.products = products;
        this.productsTemplate = productsTemplate;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

//    public Combo(List<Product> products, CustomComboTemplate productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
//        this.comboName = "Combo Temporal";
//        this.products = products;
//        this.productsTemplate = productsTemplate;
//        this.price = price;
//        this.VIPPrice = VIPPrice;
//    }



    public Combo(String comboName, List<Product>products, BigDecimal price, BigDecimal VIPPrice) {
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Combo combo = (Combo) o;
        return comboID == combo.comboID && Objects.equals(comboName, combo.comboName) && Objects.equals(products, combo.products) && Objects.equals(productsTemplate, combo.productsTemplate) && Objects.equals(price, combo.price) && Objects.equals(VIPPrice, combo.VIPPrice);
    }

    // Necesario para Hibernate

    protected Combo() {

    }

    @Override
    public int hashCode() {
        return Objects.hash(comboID, comboName, products, productsTemplate, price, VIPPrice);
    }

    @Override
    public String toString() {
        return "Combo{" +
                "comboID=" + comboID +
                ", comboName='" + comboName + '\'' +
                ", products=" + products +
                ", productsTemplate=" + productsTemplate +
                ", price=" + price +
                ", VIPPrice=" + VIPPrice +
                '}';
    }
}
