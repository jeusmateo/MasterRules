package com.mycompany.masterrules.Model.cafeteria;

import com.mycompany.masterrules.Model.storage.StockInfo;
import jakarta.persistence.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;

/*
 * TODO: Mapear correctamente la tabla de la base de datos con la clase Product
 *  y modificarlo segun los requerimientos
 * */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Product {
    @Id
    private String id;
    private String name;
    private String type;
    private BigDecimal price;
    private BigDecimal VIPPrice;
    private String productImage;

    @Embedded
    private StockInfo stockInfo;

    public Product(String productID, String productName, String productType, BigDecimal price, BigDecimal VIPPrice) {
        this.id = productID;
        this.name = productName;
        this.type = productType;
        this.price = price;
        this.VIPPrice = VIPPrice;
        this.stockInfo = new StockInfo();
    }

    public Product(String productName,  BigDecimal price, BigDecimal VIPPrice) {
        this.name = productName;
        this.type = "combo";
        this.price = price;
        this.VIPPrice = VIPPrice;
        this.stockInfo = new StockInfo();
        this.id = String.valueOf(hashCode());
    }

    public String getId() {
        return id;
    }
    /*
    public long getID() {
    return ID;
    }
     */

    public void setId(String productID) {//creo que este se debe eliminar junto con los otros setters de ids
        this.id = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String productType) {
        this.type = productType;
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

    public StockInfo getStockInfo() {
        return stockInfo;
    }

    public void setStockInfo(StockInfo stockInfo) {
        this.stockInfo = stockInfo;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID='" + id + '\'' +
                ", productName='" + name + '\'' +
                ", productType='" + type + '\'' +
                ", price=" + price +
                ", VIPPrice=" + VIPPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(type, product.type) &&
                price.compareTo(product.price) == 0 &&
                VIPPrice.compareTo(product.VIPPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, price, VIPPrice);
    }
}
