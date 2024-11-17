package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;




@Entity
public class CustomComboTemplate {

    @Id
    private long customComboTemplateID;


    private BigDecimal price;


    private BigDecimal VIPPrice;


    @ElementCollection
    private Map<String, Integer> quantityByCategory;


    // TODO: ELIMINAR EL TRANSIENT MALDITASEA
    @Transient
    private Map<String, List<Product>> productsEnableByCategory;


    @OneToMany
    private List<Product> defaultProducts;


    private String comboName;
    

    //TODO dice que no tiene constructor
    public CustomComboTemplate(ArrayList<Product> productosArg, BigDecimal priceArg, BigDecimal VIPPriceArg){
        defaultProducts = productosArg;
        this.quantityByCategory = new HashMap<>();
        this.productsEnableByCategory = new HashMap<String, List<Product>>();
        price=priceArg;
        VIPPrice = VIPPriceArg;
    }


    public CustomComboTemplate(BigDecimal priceArg, BigDecimal VIPPriceArg) {
        this.quantityByCategory = new HashMap<>();
        this.productsEnableByCategory = new HashMap<String, List<Product>>();
        price=priceArg;
        VIPPrice = VIPPriceArg;
    }


    public List<Product> getProductListEnableByCategory(String categoryKey) {
        return productsEnableByCategory.getOrDefault(categoryKey, new ArrayList<>());

    }


    //TODO: actualizar el nombre de la funcion?
    public int getQuantityByCategory(String categoryKey) {
        return quantityByCategory.getOrDefault(categoryKey, 0);
    }


    public void updateAllowedQuantity(String categoryKey, int quantity) {
        quantityByCategory.put(categoryKey, quantity);
    }

    //TODO: esto va aqui? como q añadir un producto a una categoria? no sería de cafeteriamanager?
    public void addProductToCategory(String categoryKey, Product product) {
        productsEnableByCategory.putIfAbsent(categoryKey, new ArrayList<>());
        productsEnableByCategory.get(categoryKey).add(product);
    }

    public Map<String, Integer> getQuantityByCategory() {
        return quantityByCategory;
    }

    public void setQuantityByCategory(HashMap<String, Integer> quantityByCategory) {
        this.quantityByCategory = quantityByCategory;
    }

    public Map<String, List<Product>> getProductsEnableByCategory() {
        return productsEnableByCategory;
    }

    public void setProductsEnableByCategory(HashMap<String, List<Product>> productsByCategory) {
        this.productsEnableByCategory = productsByCategory;
    }


    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public List<Product> getDefaultProducts() {
        return defaultProducts;
    }

    public void setDefaultProducts(List<Product> defaultProducts) {
        this.defaultProducts = defaultProducts;
    }

    public long getCustomComboTemplateID() {
        return customComboTemplateID;
    }

    public void setCustomComboTemplateID(long customComboTemplateID) {
        this.customComboTemplateID = customComboTemplateID;
    }

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
