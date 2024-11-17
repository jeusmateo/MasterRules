package com.mycompany.masterrules.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;



/*
 * TODO: Mapear correctamente la tabla de la base de datos con la clase Product
 *  y modificarlo segun los requerimientos
 * */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {
    @Id
    private String productID;
    private String productName;
    private String productType;
    private BigDecimal price;
    private BigDecimal VIPPrice;


    public Product(String productID, String productName, String productType, BigDecimal price, BigDecimal VIPPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    public Product(String productName, BigDecimal price, BigDecimal VIPPrice) {
        // id generado automaticamente
        this.productID = UUID.randomUUID().toString();
        this.productName = productName;
        this.productType = "combo";
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    public String getProductID() {
        return productID;
    }
    /*
    public long getID() {
    return ID;
    }
     */

    public void setProductID(String productID) {//creo que este se debe eliminar junto con los otros setters de ids
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    /*
    public void setID(long ID) {//creo que este se debe eliminar junto con los otros setters de ids
    this.ID = ID;
    }
     */


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

    protected Product() {
    }


    @Override
    public String toString() {
        return "Product{" +
                "productID='" + productID + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", price=" + price +
                ", VIPPrice=" + VIPPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productID, product.productID) &&
                Objects.equals(productName, product.productName) &&
                Objects.equals(productType, product.productType) &&
                price.compareTo(product.price) == 0 &&
                VIPPrice.compareTo(product.VIPPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID, productName, productType, price, VIPPrice);
    }
}
