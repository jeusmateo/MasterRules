package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Combo extends Product{


    @OneToMany(fetch = FetchType.EAGER)
    private List<Product> products;
    @Transient
    private CustomComboTemplate productsTemplate;




    public Combo(String comboName, List<Product> products, CustomComboTemplate productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
        super(comboName,price,VIPPrice);
        this.products = products;
        this.productsTemplate = productsTemplate;

    }

//    public Combo(List<Product> products, CustomComboTemplate productsTemplate, BigDecimal price, BigDecimal VIPPrice) {
//        this.comboName = "Combo Temporal";
//        this.products = products;
//        this.productsTemplate = productsTemplate;
//        this.price = price;
//        this.VIPPrice = VIPPrice;
//    }



    public Combo(String comboName, List<Product>products, BigDecimal price, BigDecimal VIPPrice) {
        super(comboName,price,VIPPrice);
        this.products = products;

    }





    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setProductsTemplate(CustomComboTemplate productsTemplate) {
        this.productsTemplate = productsTemplate;
    }



    public List<Product> getProducts() {
        return products;
    }

    public CustomComboTemplate getProductsTemplate() {
        return productsTemplate;
    }


/*

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

 */
}
