package com.mycompany.masterrules.Model.cafeteria;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Embeddable
public class CustomCombo {

    @ElementCollection
    private final Map<String, Integer> quantityPerCategory;

    @Transient
    private Map<String, List<Product>> productsPerCategory;

    @OneToMany
    private List<Product> allProducts;

    public CustomCombo(Map<String, Integer> quantityPerCategory) {
        this.quantityPerCategory = quantityPerCategory;
    }

    public CustomCombo(Map<String, Integer> quantityPerCategory, List<Product> allProducts) {
        this.allProducts = allProducts;
        this.quantityPerCategory = new HashMap<>();
        this.productsPerCategory = new HashMap<>();
    }

    protected CustomCombo() {
        this.quantityPerCategory = new HashMap<>();
    }

    //TODO: actualizar el nombre de la funcion?
    public int getQuantityPerCategory(String categoryKey) {
        return quantityPerCategory.getOrDefault(categoryKey, 0);
    }

    public void updateAllowedQuantity(String categoryKey, int quantity) {
        quantityPerCategory.put(categoryKey, quantity);
    }

    //TODO: esto va aqui? como q añadir un producto a una categoria? no sería de cafeteriamanager?
    public void addProductToCategory(String categoryKey, Product product) {
        if (canAddProductToCategory(categoryKey)) {
            productsPerCategory.get(categoryKey).add(product);
            allProducts.add(product);
        }
    }

    public void removeProductFromCategory(String categoryKey, Product product) {
        if (productsPerCategory.containsKey(categoryKey)) {
            productsPerCategory.get(categoryKey).remove(product);
            allProducts.remove(product);
        }
    }

    public void addCategory(String categoryKey) {
        productsPerCategory.put(categoryKey, null);
    }

    public void removeCategory(String categoryKey) {
        productsPerCategory.remove(categoryKey);
    }

    public void setProductsToCategory(String categoryKey, List<Product> products) {
        productsPerCategory.put(categoryKey, products);
    }

    public List<Product> getProductsByCategory(String categoryKey) {
        return productsPerCategory.get(categoryKey);
    }

    public void setProductsByCategory(String categoryKey, List<Product> products) {
        productsPerCategory.put(categoryKey, products);
    }

    private boolean canAddProductToCategory(String categoryKey) {
        return productsPerCategory.containsKey(categoryKey) && productsPerCategory.get(categoryKey).size() <= quantityPerCategory.get(categoryKey);
    }

    public Map<String, Integer> getQuantityPerCategory() {
        return quantityPerCategory;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> products) {
        this.allProducts = products;
    }

    public Map<String, List<Product>> getAllProductsPerCategory() {
        return productsPerCategory;
    }

    public void setProductsPerCategory(Map<String, List<Product>> productsPerCategory) {
        this.productsPerCategory = productsPerCategory;
    }

    // Necesario para Hibernate

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        CustomCombo that = (CustomCombo) o;
//        return customComboTemplateID == that.customComboTemplateID && price.compareTo(that.price) == 0 && VIPPrice.compareTo(that.VIPPrice) == 0 && Objects.equals(quantityPerCategory, that.quantityPerCategory) && Objects.equals(productsEnableByCategory, that.productsEnableByCategory) && Objects.equals(products, that.products) && Objects.equals(comboName, that.comboName);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(customComboTemplateID, price, VIPPrice, quantityPerCategory, productsEnableByCategory, products, comboName);
//    }
//
//    @Override
//    public String toString() {
//        return "CustomCombo{" + "customComboTemplateID=" + customComboTemplateID + ", price=" + price + ", VIPPrice=" + VIPPrice + ", quantityByCategory=" + quantityPerCategory + ", productsEnableByCategory=" + productsEnableByCategory + ", defaultProducts=" + products + ", comboName='" + comboName + '\'' + '}';
//    }
}
