package com.mycompany.masterrules.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Clase que representa al producto que se vende en MasterRules
 * @author Alexandra Saavedra
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
    /**
     * Identificador del producto
     */
    private String productID;
    //private long ID;//código de barras, si este no tiene código de barras entonces se le pone -1
    @Column(name = "nombreProducto")
    /**
     * Nombre del producto
     */
    private String productName;
    @Column(name = "tipoProducto")
    /**
     * Tipo de producto
     */
    private String productType;
    @Column(name = "precio")
    /**
     * Precio del producto
     */
    private BigDecimal price;
    @Column(name = "precioVIP")
    /**
     * Precio vip del producto
     */
    private BigDecimal VIPPrice;//cambie VIPprice por VIPPrice

    /**
     * Constructor de clase NECESARIO PARA JPA (Java Persistence API) NO ELIMINAR LOS CONSTRUCTORES VACIOS >:(
     */
    public Product() {
    }

    /**
     * Constructor de clase
     * @param productID Identificador del producto
     * @param productName Nombre del producto
     * @param productType Tipo de producto
     * @param price Precio del producto
     * @param VIPPrice Precio vip del producto
     */
    public Product(String productID, String productName, String productType, BigDecimal price, BigDecimal VIPPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.VIPPrice = VIPPrice;
    }

    /**
     * Obtiene el identificador del producto
     * @return Identificador del producto
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
     * Obtiene el nombre del producto
     * @return Nombre del producto
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Obtiene el tipo de producto
     * @return Tipo de producto
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Obtiene el precio del producto
     * @return Precio del producto
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Obtiene el precio vip del producto
     * @return Precio vip del producto
     */
    public BigDecimal getVIPPrice() {
        return VIPPrice;
    }

    /**
     * Establece el identificador del producto
     * @param productID Identificador del producto
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
     * Establece el nombre del producto
     * @param productName Nombre del producto
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Establece el tipo de producto
     * @param productType Tipo de producto
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * Establece el precio del producto
     * @param price Precio del producto
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Establece el precio vip del producto
     * @param VIPPrice Precio vip del producto
     */
    public void setVIPPrice(BigDecimal VIPPrice) {
        this.VIPPrice = VIPPrice;
    }
}
