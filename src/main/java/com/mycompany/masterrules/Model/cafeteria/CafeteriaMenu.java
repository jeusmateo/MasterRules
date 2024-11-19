package com.mycompany.masterrules.Model.cafeteria;

import com.mycompany.masterrules.Database.ComboDatabase;
import com.mycompany.masterrules.Database.ProductDatabase;

import java.util.List;


public class CafeteriaMenu {


    private final ProductDatabase productDatabase;
    private final ComboDatabase comboDatabase;
    private String title;

    public CafeteriaMenu() {
        productDatabase = new ProductDatabase();
        comboDatabase = new ComboDatabase();
    }

    public void addProductToMenu(Product product) {
        if (!isProductOnMenu(product.getId())) {
            productDatabase.save(product);
        } else {
            throw new IllegalArgumentException("ERROR: El producto ya existe");
        }
    }

    public void removeProductOnMenu(String productID) {
        if (productDatabase.delete(productDatabase.findById(productID))) {
            return;
        }
        throw new IllegalArgumentException("El producto con ID " + productID + " no existe en el menú.");
    }


    public boolean isProductOnMenu(String productID) {
        return productDatabase.findById(productID) != null;
    }

    public boolean isProductNameTaken(String productName) {
        return productDatabase.findByName(productName) != null;
    }

    public void editProduct(Product product) {
        if (productDatabase.update(product)) {
            return;
        }
        throw new IllegalArgumentException("ERROR: El producto no existe");
    }


    public List<Product> getProductsByType(String productType) {

        List<Product> productsWithType = productDatabase.findByType(productType);

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
        return comboDatabase.findById(combo.getId()) != null;
    }


    public void removeComboOnMenu(String comboID) {
        if (comboDatabase.delete(comboDatabase.findById(comboID))) {
            return;
        }

        throw new IllegalArgumentException("El combo con ID " + comboID + " no existe en el menú.");

    }


    public void editCombo(Combo combo) {
        if (comboDatabase.update(combo)) {
            return;
        }

        throw new IllegalArgumentException("ERROR: El combo no existe");

    }

    private boolean isComboNameTaken(String comboName) { //TODO no esta implemenmtado
        return false;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return productDatabase.readAll();
    }

    //TODO: esto tiene uso?
    public void setProducts(List<Product> products) {
        for (Product product : products) {
            addProductToMenu(product);
        }
    }

    public List<Combo> getCombos() {
        return comboDatabase.readAll();
    }

    public void setCombos(List<Combo> combos) {
        for (Combo combo : combos) {
            addComboToMenu(combo);
        }
    }

}
