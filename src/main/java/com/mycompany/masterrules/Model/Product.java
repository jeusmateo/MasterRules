/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
@Table(name = "Productos")

public class Product {
    @Id
    @Column(name = "clvProducto")
    private long ID;//código de barras, si este no tiene código de barras entonces se le pone -1
    @Column(name = "nombreProducto")
    private String productName;
    @Column(name = "tipoProducto")
    private String productType;
    @Column(name = "precio")
    private BigDecimal price;
    @Column(name = "precioVIP")
    private BigDecimal VIPprice;

    /**
     * Constructor of class Product
     * @param id Identification for product
     * @param productName Name of product
     * @param productType Type of product
     * @param price Price of product
     * @param VIPprice Vip price of product
     */
    public Product(long id, String productName, String productType, BigDecimal price, BigDecimal VIPprice) {
        this.ID = id;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.VIPprice = VIPprice;
    }

    /**
     * Getter of identification(ID) of product
     * @return Id
     */
    public long getID() {
        return ID;
    }

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
     * @param ID Identification of product 
     */
    public void setID(long ID) {
        this.ID = ID;
    }

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
