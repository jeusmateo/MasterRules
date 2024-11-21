package com.mycompany.masterrules.Model.cafeteria;

import com.mycompany.masterrules.Database.ComboDatabase;
import com.mycompany.masterrules.Database.ProductDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CafeteriaMenu {

    private final List<Product> availableProducts;
    private final List<Combo> availableCombos;

    private final ProductDatabase productDatabase;
    private final ComboDatabase comboDatabase;

    public CafeteriaMenu() {
        availableProducts = new ArrayList<>();
        availableCombos = new ArrayList<>();

        productDatabase = new ProductDatabase();
        comboDatabase = new ComboDatabase();

        readFromDatabase();
    }

    private void readFromDatabase() {

        availableProducts.clear();
        availableCombos.clear();
        var allProducts = productDatabase.readAll();
        for (var actualProduct : allProducts) {
            if (actualProduct instanceof Combo combo) {
                if (!availableCombos.contains(combo)) {
                    availableCombos.add(combo);
                }
            } else {
                if (!availableProducts.contains(actualProduct)) {
                    availableProducts.add(actualProduct);
                }
            }
        }
    }


    public void addProductToMenu(Product product) {
        if (!isProductOnMenu(product)) {
            //availableProducts.add(product);
            productDatabase.save(product);
        } else {
            throw new IllegalArgumentException("ERROR: El producto ya existe");
        }
    }

    public void removeProductOnMenu(Product product) {
        if (!isProductOnMenu(product)) {
            throw new IllegalArgumentException("El producto con ID " + product + " no existe en el menú.");
        }
        availableProducts.remove(product);
        productDatabase.delete(product);
    }


    private boolean isProductOnMenu(Product product) {
        return availableProducts.contains(product);
    }

    private boolean isProductNameTaken(String productName) {
        return availableProducts.stream().anyMatch(product -> product.getName().equals(productName));
    }

    public void editProduct(Product product) {
        if (!isProductOnMenu(product)) {
            throw new IllegalArgumentException("ERROR: El producto no existe");
        }

        availableProducts.set(availableProducts.indexOf(product), product);
        productDatabase.update(product);
    }


    public List<Product> getProductsByType(String productType) {

        List<Product> productsWithType = availableProducts.stream()
                .filter(product -> product.getType().equals(productType))
                .collect(Collectors.toList());

        if (productsWithType.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El tipo de producto no existe en el menú");
        }

        return productsWithType;
    }


    public void addComboToMenu(Combo combo) {
        if (!isComboOnMenu(combo)) {
            comboDatabase.save(combo);
        } else {
            throw new IllegalArgumentException("ERROR: El combo ya existe");
        }
    }

    private boolean isComboOnMenu(Combo combo) {
        return availableCombos.contains(combo);
    }


    public void removeComboOnMenu(Combo combo) {
        if (!isComboOnMenu(combo)) {
            throw new IllegalArgumentException("El combo con ID " + combo + " no existe en el menú.");
        }
        availableCombos.remove(combo);
        comboDatabase.delete(combo);
    }


    public void editCombo(Combo combo) {
        if (!isComboOnMenu(combo)) {
            throw new IllegalArgumentException("ERROR: El combo no existe");
        }

        availableCombos.set(availableCombos.indexOf(combo), combo);
        comboDatabase.update(combo);
    }

    public List<Product> getProducts() {
        readFromDatabase();
        return availableProducts;
    }

    public void setProducts(List<Product> products) {
        for (Product product : products) {
            addProductToMenu(product);
        }
    }

    public List<Combo> getCombos() {
        //readFromDatabase();
        return availableCombos;
    }



}
