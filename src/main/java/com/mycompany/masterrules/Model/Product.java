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
 *
 * @author alexs
 */

@Entity
@Table(name = "Productos")

/*
* TODO: Mapear correctamente la tabla de la base de datos con la clase Product
*  y modificarlo segun los requerimientos
* */
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

    public Product() {

    }

    public Product(long id, String productName, String productType, BigDecimal price, BigDecimal VIPprice) {
        this.ID = id;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.VIPprice = VIPprice;
    }

    public Product(String productName, String productType, BigDecimal price, BigDecimal VIPprice) {
        this.ID=-1;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.VIPprice = VIPprice;
    }

    public long getID() {
        return ID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductType() {
        return productType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVIPprice() {
        return VIPprice;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setVIPprice(BigDecimal VIPprice) {
        this.VIPprice = VIPprice;
    }
}
