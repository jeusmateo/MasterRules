package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;


/**
 *
 * @autor: David Torres
 *
 * Clase que representa un combo personalizado
 *
 */

@Entity
public class CustomComboTemplate {

    /** Identificador del combo */
    @Id
    private long customComboTemplateID;

    /** Precio del combo */
    private BigDecimal price;

    /** Precio VIP del combo */
    private BigDecimal VIPPrice;

    /** Cantidad de productos que se deben escoger por cada categoria correspondiente */
    @ElementCollection
    private Map<String, Integer> quantityByCategory;

    /** Lista de productos para escoger correspondiente por cada categoria */
    // TODO: ELIMINAR EL TRANSIENT MALDITASEA
    @Transient
    private Map<String, List<Product>> productsEnableByCategory;

    /** Lista de productos por defecto */
    @OneToMany
    private List<Product> defaultProducts;

    /** Nombre del combo */
    private String comboName;
    
    /**
     * Constructor para el combo personalizado en el caso de que el combo implemente productos por defecto
     * @param productosArg Lista de productos por defecto
     * @param priceArg Precio del combo
     * @param VIPPriceArg Precio VIP del combo
     */
    //TODO dice que no tiene constructor
    public CustomComboTemplate(ArrayList<Product> productosArg, BigDecimal priceArg, BigDecimal VIPPriceArg){
        defaultProducts = productosArg;
        this.quantityByCategory = new HashMap<>();
        this.productsEnableByCategory = new HashMap<String, List<Product>>();
        price=priceArg;
        VIPPrice = VIPPriceArg;
    }

    
    /**
     * Constructor para el combo personalizado en el caso de que el combo no implemente productos por defecto
     * @param priceArg Precio del combo
     * @param VIPPriceArg Precio VIP del combo
     */
    public CustomComboTemplate(BigDecimal priceArg, BigDecimal VIPPriceArg) {
        this.quantityByCategory = new HashMap<>();
        this.productsEnableByCategory = new HashMap<String, List<Product>>();
        price=priceArg;
        VIPPrice = VIPPriceArg;
    }


    /**
     * Devuelve la lista de productos permitidos por la categoria correspondiente
     * @param categoryKey La categoria de la cual se desea obtener los productos
     * @return La lista de productos permitidos por la categoria correspondiente
     */
    public List<Product> getProductListEnableByCategory(String categoryKey) {
        return productsEnableByCategory.getOrDefault(categoryKey, new ArrayList<>());

    }

    /**
     * Devuelve la cantidad de productos permitidos por la categoria correspondiente
     * @param categoryKey La categoria de la cual se desea obtener la cantidad de productos
     * @return La cantidad de productos permitidos por la categoria correspondiente
     */
    //TODO: actualizar el nombre de la funcion?
    public int getQuantityByCategory(String categoryKey) {
        return quantityByCategory.getOrDefault(categoryKey, 0);
    }

    /**
     * Actualiza la cantidad de productos permitidos por la categoria correspondiente
     * @param categoryKey La categoria de la cual se desea actualizar la cantidad de productos
     * @param quantity La nueva cantidad de productos permitidos
     */
    public void updateAllowedQuantity(String categoryKey, int quantity) {
        quantityByCategory.put(categoryKey, quantity);
    }

    /**
     * Agrega un producto a la categoria correspondiente
     * @param categoryKey La categoria a la cual se desea agregar el producto
     * @param product El producto a agregar
     */
    //TODO: esto va aqui? como q añadir un producto a una categoria? no sería de cafeteriamanager?
    public void addProductToCategory(String categoryKey, Product product) {
        productsEnableByCategory.putIfAbsent(categoryKey, new ArrayList<>());
        productsEnableByCategory.get(categoryKey).add(product);
    }

    /**
     * Obtiene el hashmap que contiene la informacion de las cantidades de productos por categoria
     */
    public Map<String, Integer> getQuantityByCategory() {
        return quantityByCategory;
    }

    /**
     * Establece el hashmap que contiene la informacion de las cantidades de productos por categoria
     */
    public void setQuantityByCategory(HashMap<String, Integer> quantityByCategory) {
        this.quantityByCategory = quantityByCategory;
    }

    /**
     * Obtiene el hashmap que contiene la informacion de los productos permitidos por categoria
     */
    public Map<String, List<Product>> getProductsEnableByCategory() {
        return productsEnableByCategory;
    }

    /**
     * Establece el hashmap que contiene la informacion de los productos permitidos por categoria
     */
    public void setProductsEnableByCategory(HashMap<String, List<Product>> productsByCategory) {
        this.productsEnableByCategory = productsByCategory;
    }

    /**
     * Obtiene el nombre del combo
     * @return El nombre del combo en un dato de tipo String
     */

    public String getComboName() {
        return comboName;
    }

    /**
     * Establece el nombre del combo
     * @param comboName El nombre del combo en un dato de tipo String
     */
    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    /**
     * Obtiene la lista de productos por defecto
     * @return La lista de productos por defecto en un dato de tipo ArrayList de Product
     */
    public List<Product> getDefaultProducts() {
        return defaultProducts;
    }

    /**
     * Establece la lista de productos por defecto
     * @param defaultProducts La lista de productos por defecto en un dato de tipo ArrayList de Product
     */
    public void setDefaultProducts(List<Product> defaultProducts) {
        this.defaultProducts = defaultProducts;
    }

    /**
     * Obtiene el identificador del combo
     * @return El identificador del combo en un dato de tipo long
     */
    public long getCustomComboTemplateID() {
        return customComboTemplateID;
    }

    /**
     * Establece el identificador del combo
     * @param customComboTemplateID El identificador del combo en un dato de tipo long
     */
    public void setCustomComboTemplateID(long customComboTemplateID) {
        this.customComboTemplateID = customComboTemplateID;
    }

    /**
     * Obtiene el precio del combo
     * @return El precio del combo en un dato de tipo BigDecimal
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Establece el precio del combo
     * @param price El precio del combo en un dato de tipo BigDecimal
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Obtiene el precio VIP del combo
     * @return El precio para un cliente VIP del combo en un dato de tipo BigDecimal
     */
    public BigDecimal getVIPPrice() {
        return VIPPrice;
    }

    /**
     * Establece el precio VIP del combo
     * @param VIPPrice El precio para un cliente VIP del combo en un dato de tipo BigDecimal
     */
    public void setVIPPrice(BigDecimal VIPPrice) {
        this.VIPPrice = VIPPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomComboTemplate that = (CustomComboTemplate) o;
        return customComboTemplateID == that.customComboTemplateID &&
                price.compareTo(that.price) == 0 &&
                VIPPrice.compareTo(that.VIPPrice) == 0 &&
                Objects.equals(quantityByCategory, that.quantityByCategory) &&
                Objects.equals(productsEnableByCategory, that.productsEnableByCategory) &&
                Objects.equals(defaultProducts, that.defaultProducts) &&
                Objects.equals(comboName, that.comboName);
    }

    // Necesario para Hibernate

    protected CustomComboTemplate() {
    }


    @Override
    public int hashCode() {
        return Objects.hash(customComboTemplateID, price, VIPPrice, quantityByCategory, productsEnableByCategory, defaultProducts, comboName);
    }

    @Override
    public String toString() {
        return "CustomComboTemplate{" +
                "customComboTemplateID=" + customComboTemplateID +
                ", price=" + price +
                ", VIPPrice=" + VIPPrice +
                ", quantityByCategory=" + quantityByCategory +
                ", productsEnableByCategory=" + productsEnableByCategory +
                ", defaultProducts=" + defaultProducts +
                ", comboName='" + comboName + '\'' +
                '}';
    }
}
