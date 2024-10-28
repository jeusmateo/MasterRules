package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: arreglar el ADT de esta clase
public class CustomComboTemplate {
    
    private long customComboTemplateID;
    private BigDecimal price;
    private BigDecimal VIPPrice;
    private HashMap<String, Integer> quantityByCategory;
    private HashMap<String, ArrayList<Product>> productsEnableByCategory;
    private ArrayList<Product> defaultProducts;
    private String name;
    
    public CustomComboTemplate(ArrayList<Product> productosArg, BigDecimal priceArg, BigDecimal VIPPriceArg){
        defaultProducts = productosArg;
        this.quantityByCategory = new HashMap<>();
        this.productsEnableByCategory = new HashMap<>();
        price=priceArg;
        VIPPrice = VIPPriceArg;
    }
    
    public CustomComboTemplate(BigDecimal priceArg, BigDecimal VIPPriceArg) {
        this.quantityByCategory = new HashMap<>();
        this.productsEnableByCategory = new HashMap<>();
        price=priceArg;
        VIPPrice = VIPPriceArg;
    }

    public ArrayList<Product> getProductListEnableByCategory(String categoryKey) {
        return productsEnableByCategory.getOrDefault(categoryKey, new ArrayList());

    }

    public int getQuantityByCategory(String categoryKey) {
        return quantityByCategory.getOrDefault(categoryKey, 0);
    }

    public void updateAllowedQuantity(String categoryKey, int quantity) {
        quantityByCategory.put(categoryKey, quantity);
    }

    public void addProductToCategory(String categoryKey, Product product) {
        productsEnableByCategory.putIfAbsent(categoryKey, new ArrayList<>());
        productsEnableByCategory.get(categoryKey).add(product);
    }

    public HashMap<String, Integer> getQuantityByCategory() {
        return quantityByCategory;
    }

    public void setQuantityByCategory(HashMap<String, Integer> quantityByCategory) {
        this.quantityByCategory = quantityByCategory;
    }

    public HashMap<String, ArrayList<Product>> getProductsEnableByCategory() {
        return productsEnableByCategory;
    }

    public void setProductsEnableByCategory(HashMap<String, ArrayList<Product>> productsByCategory) {
        this.productsEnableByCategory = productsByCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getDefaultProducts() {
        return defaultProducts;
    }

    public void setDefaultProducts(ArrayList<Product> defaultProducts) {
        this.defaultProducts = defaultProducts;
    }
    
}
