package com.mycompany.masterrules.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Clase que representa al producto que se vende en MasterRules
 *
 * @author Alexandra Saavedra
 */

/*
 * TODO: Mapear correctamente la tabla de la base de datos con la clase Product
 *  y modificarlo segun los requerimientos
 * */
@Entity
public class Product {
    @Id
    /**
     * Identificador del producto
     */
    private String productID;
    //private long ID;//código de barras, si este no tiene código de barras entonces se le pone -1
    /**
     * Nombre del producto
     */
    private String productName;
    /**
     * Tipo de producto
     */
    private String productType;
    /**
     * Precio del producto
     */
    private BigDecimal price;
    /**
     * Precio vip del producto
     */
    private BigDecimal VIPPrice;



    /**
     * Constructor de clase
     *
     * @param productID   Identificador del producto
     * @param productName Nombre del producto
     * @param productType Tipo de producto
     * @param price       Precio del producto
     * @param VIPPrice    Precio vip del producto
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
     *
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
     * Establece el identificador del producto
     *
     * @param productID Identificador del producto
     */
    public void setProductID(String productID) {//creo que este se debe eliminar junto con los otros setters de ids
        this.productID = productID;
    }

    /**
     * Obtiene el nombre del producto
     *
     * @return Nombre del producto
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Establece el nombre del producto
     *
     * @param productName Nombre del producto
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Obtiene el tipo de producto
     *
     * @return Tipo de producto
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Establece el tipo de producto
     *
     * @param productType Tipo de producto
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /*
    public void setID(long ID) {//creo que este se debe eliminar junto con los otros setters de ids
    this.ID = ID;
    }
     */

    /**
     * Obtiene el precio del producto
     *
     * @return Precio del producto
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Establece el precio del producto
     *
     * @param price Precio del producto
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Obtiene el precio vip del producto
     *
     * @return Precio vip del producto
     */
    public BigDecimal getVIPPrice() {
        return VIPPrice;
    }

    /**
     * Establece el precio vip del producto
     *
     * @param VIPPrice Precio vip del producto
     */
    public void setVIPPrice(BigDecimal VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

    // Necesario para Hibernate

    /**
     * Constructor de clase NECESARIO PARA JPA (Java Persistence API) NO ELIMINAR LOS CONSTRUCTORES VACIOS >:(
     */
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
