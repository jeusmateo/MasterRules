package com.mycompany.masterrules.Model.cafeteria;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// TODO: pensar un nombre mejor y justificar la existencia de este ADT
public class CustomComboCreator {

    private final Map<String, Integer> quantityPerCategory;
    private final Map<String, List<Product>> productsPerCategory;

    public CustomComboCreator(Map<String, Integer> quantityPerCategory) {
        this.quantityPerCategory = quantityPerCategory;
        this.productsPerCategory = new HashMap<>();

        initializeProductsPerCategory();
    }

    public CustomComboCreator(Map<String, Integer> quantityPerCategory, Map<String, List<Product>> productsPerCategory) {
        this.quantityPerCategory = quantityPerCategory;
        this.productsPerCategory = productsPerCategory;
    }

    public List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        for (List<Product> products : productsPerCategory.values()) {
            allProducts.addAll(products);
        }
        return allProducts;
    }

    public Combo createCombo(String comboName, BigDecimal price, BigDecimal VIPPrice) {
        return new Combo(comboName, getAllProducts(), price, VIPPrice);
    }

    private void initializeProductsPerCategory() {
        for (String category : quantityPerCategory.keySet()) {
            productsPerCategory.put(category, new ArrayList<>());
        }
    }

    //TODO: esto va aqui? como q añadir un producto a una categoria? no sería de cafeteriamanager?
    public void addProductToCategory(String categoryKey, Product product) {
        if (canAddProductToCategory(categoryKey)) {
            productsPerCategory.get(categoryKey).add(product);
        }
    }

    //TODO: actualizar el nombre de la funcion?
    public int getAllowedQuantity(String categoryKey) {
        return quantityPerCategory.getOrDefault(categoryKey, 0);
    }

    public void removeProductFromCategory(String categoryKey, Product product) {
        if (productsPerCategory.containsKey(categoryKey)) {
            productsPerCategory.get(categoryKey).remove(product);
        }
    }

    public void setProductsToCategory(String categoryKey, List<Product> products) {
        productsPerCategory.put(categoryKey, products);
    }

    public List<Product> getProductsPerCategory(String categoryKey) {
        return productsPerCategory.get(categoryKey);
    }

    private boolean canAddProductToCategory(String categoryKey) {
        return productsPerCategory.containsKey(categoryKey) &&
                productsPerCategory.get(categoryKey).size() <= quantityPerCategory.get(categoryKey);
    }

    public Map<String, Integer> getAllowedQuantity() {
        return quantityPerCategory;
    }

    public Map<String, List<Product>> getAllProductsPerCategory() {
        return productsPerCategory;
    }

}
