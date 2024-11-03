package com.mycompany.masterrules.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Class for instances of Product
 * @author alexs
 */

/*
* TODO: Mapear correctamente la tabla de la base de datos con la clase Product
*  y modificarlo segun los requerimientos
* */
@Entity
@Table(name = "PRODUCTOS")

public class Product {
    @Id
    @Column(name = "ProductoID")
    private String productID;
    //private long ID;//código de barras, si este no tiene código de barras entonces se le pone -1
    @Column(name = "Nombre")
    private String productName;
    @Column(name = "Tipo")
    private String productType;
    @Column(name = "Precio")
    private BigDecimal price;
    @Column(name = "PrecioVIP")
    private BigDecimal VIPprice;

    /**
     * Constructor of class Product NECESARIO PARA JPA (Java Persistence API) NO ELIMINAR LOS CONSTRUCTORES VACIOS >:(
     */
    public Product() {
    }

    /**
     * Constructor of class Product
     * @param productID Identification of product
     * @param productName Name of product
     * @param productType Type of product
     * @param price Price of product
     * @param VIPprice Vip price of product
     */
    public Product(String productID, String productName, String productType, BigDecimal price, BigDecimal VIPprice) {
        this.productID = productID;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.VIPprice = VIPprice;
    }

    /**
     * Getter of identification(ID) of product
     * @return Product identification
     */
    public String getProductID() {
        return productID;
    }
    /*
    public long getID() {
    return ID;
    }
     */
    /**
     * Getter of product name
     * @return Product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Getter of product type
     * @return Product type
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Getter of product price
     * @return Product price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Getter of product vip price
     * @return Vip price
     */
    public BigDecimal getVIPprice() {
        return VIPprice;
    }

    /**
     * Setter of identification(ID) of product 
     * @param productID Identification of product
     */
    public void setProductID(String productID) {//creo que este se debe eliminar junto con los otros setters de ids
        this.productID = productID;
    }

    /*
    public void setID(long ID) {//creo que este se debe eliminar junto con los otros setters de ids
    this.ID = ID;
    }
     */
    /**
     * Setter of product name
     * @param productName Product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Setter of product type
     * @param productType Product type
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Setter of product price
     * @param price Price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Setter of vip price
     * @param VIPprice Vip price
     */
    public void setVIPprice(BigDecimal VIPprice) {
        this.VIPprice = VIPprice;
    }
}
