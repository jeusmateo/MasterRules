package com.mycompany.masterrules.Model.cafeteria;

import com.mycompany.masterrules.Database.ComboDatabase;
import com.mycompany.masterrules.Database.ProductDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase que representa el menú de la cafetería.
 * Gestiona productos y combos disponibles en el menú.
 */
public class CafeteriaMenu {

    private final List<Product> availableProducts;
    private final List<Combo> availableCombos;

    private final ProductDatabase productDatabase;
    private final ComboDatabase comboDatabase;

    /**
     * Constructor que inicializa las listas de productos y combos disponibles,
     * y carga los datos desde la base de datos.
     */
    public CafeteriaMenu() {
        availableProducts = new ArrayList<>();
        availableCombos = new ArrayList<>();

        productDatabase = new ProductDatabase();
        comboDatabase = new ComboDatabase();

        readFromDatabase();
    }

    /**
     * Método privado que lee los datos de productos y combos desde la base de datos.
     */
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

    /**
     * Añade un producto al menú.
     * @param product El producto a añadir.
     * @throws IllegalArgumentException si el producto ya existe en el menú.
     */
    public void addProductToMenu(Product product) {



        if (isProductNotOnMenu(product)) {
            productDatabase.save(product);
        } else {
            throw new IllegalArgumentException("ERROR: El producto ya existe");
        }
    }

    /**
     * Elimina un producto del menú.
     * @param product El producto a eliminar.
     * @throws IllegalArgumentException si el producto no existe en el menú.
     */
    public void removeProductOnMenu(Product product) {
        if (isProductNotOnMenu(product)) {
            throw new IllegalArgumentException("El producto con ID " + product + " no existe en el menú.");
        }
        availableProducts.remove(product);
        productDatabase.delete(product);
    }

    /**
     * Verifica si un producto está en el menú.
     * @param product El producto a verificar.
     * @return true si el producto está en el menú, false en caso contrario.
     */
    private boolean isProductNotOnMenu(Product product) {
        return !availableProducts.contains(product);
    }

    /**
     * Edita un producto en el menú.
     * @param product El producto a editar.
     * @throws IllegalArgumentException si el producto no existe en el menú.
     */
    public void editProduct(Product product) {
        if (isProductNotOnMenu(product)) {
            throw new IllegalArgumentException("ERROR: El producto no existe");
        }

        availableProducts.set(availableProducts.indexOf(product), product);
        productDatabase.update(product);
    }

    /**
     * Obtiene una lista de productos por tipo.
     * @param productType El tipo de producto a buscar.
     * @return Una lista de productos del tipo especificado.
     * @throws IllegalArgumentException si no existen productos del tipo especificado en el menú.
     */
    public List<Product> getProductsByType(String productType) {
        List<Product> productsWithType = availableProducts.stream()
                .filter(product -> product.getType().equals(productType))
                .collect(Collectors.toList());

        if (productsWithType.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El tipo de producto no existe en el menú");
        }

        return productsWithType;
    }

    /**
     * Añade un combo al menú.
     * @param combo El combo a añadir.
     * @throws IllegalArgumentException si el combo ya existe en el menú.
     */
    public void addComboToMenu(Combo combo) {
        if (isComboNotOnMenu(combo)) {
            comboDatabase.save(combo);
        } else {
            throw new IllegalArgumentException("ERROR: El combo ya existe");
        }
    }

    /**
     * Verifica si un combo está en el menú.
     * @param combo El combo a verificar.
     * @return true si el combo está en el menú, false en caso contrario.
     */
    private boolean isComboNotOnMenu(Combo combo) {
        return !availableCombos.contains(combo);
    }

    /**
     * Elimina un combo del menú.
     * @param combo El combo a eliminar.
     * @throws IllegalArgumentException si el combo no existe en el menú.
     */
    public void removeComboOnMenu(Combo combo) {
        comboDatabase.delete(combo);
    }

    /**
     * Edita un combo en el menú.
     * @param combo El combo a editar.
     */
    public void editCombo(Combo combo) {
        comboDatabase.update(combo);
    }

    /**
     * Obtiene la lista de productos disponibles en el menú.
     * @return Una lista de productos disponibles.
     */
    public List<Product> getProducts() {
        readFromDatabase();
        return availableProducts;
    }

    /**
     * Establece la lista de productos en el menú.
     * @param products La lista de productos a establecer.
     */
    public void setProducts(List<Product> products) {
        for (Product product : products) {
            addProductToMenu(product);
        }
    }

    /**
     * Obtiene la lista de combos disponibles en el menú.
     * @return Una lista de combos disponibles.
     */
    public List<Combo> getCombos() {
        return availableCombos;
    }
}