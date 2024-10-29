package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

// TODO: arreglar el ADT de esta clase
public class CustomComboTemplate {

    /** Identificador del combo */
    private long customComboTemplateID;
    /** Precio del combo */
    private BigDecimal price;
    /** Precio VIP del combo */
    private BigDecimal VIPPrice;
    /** Cantidad de productos que se deben escoger por cada categoria correspondiente */
    private HashMap<String, Integer> quantityByCategory;
    /** Lista de productos para escoger correspondiente por cada categoria */
    private HashMap<String, ArrayList<Product>> productsEnableByCategory;
    /** Lista de productos por defecto */
    private ArrayList<Product> defaultProducts;
    /** Nombre del combo */
    private String comboName;
    
    /**
     * Constructor para el combo personalizado en el caso de que el combo implemente productos por defecto
     * @param productosArg Lista de productos por defecto
     * @param priceArg Precio del combo
     * @param VIPPriceArg Precio VIP del combo
     */
    public CustomComboTemplate(ArrayList<Product> productosArg, BigDecimal priceArg, BigDecimal VIPPriceArg){
        defaultProducts = productosArg;
        this.quantityByCategory = new HashMap<>();
        this.productsEnableByCategory = new HashMap<>();
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
        this.productsEnableByCategory = new HashMap<>();
        price=priceArg;
        VIPPrice = VIPPriceArg;
    }

    /**
     * Devuelve la lista de productos permitidos por la categoria correspondiente
     * @param categoryKey La categoria de la cual se desea obtener los productos
     * @return La lista de productos permitidos por la categoria correspondiente
     */
    public ArrayList<Product> getProductListEnableByCategory(String categoryKey) {
        return productsEnableByCategory.getOrDefault(categoryKey, new ArrayList<>());

    }

    /**
     * Devuelve la cantidad de productos permitidos por la categoria correspondiente
     * @param categoryKey La categoria de la cual se desea obtener la cantidad de productos
     * @return La cantidad de productos permitidos por la categoria correspondiente
     */
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
    public void addProductToCategory(String categoryKey, Product product) {
        productsEnableByCategory.putIfAbsent(categoryKey, new ArrayList<>());
        productsEnableByCategory.get(categoryKey).add(product);
    }

    /**
     * Obtiene el hashmap que contiene la informacion de las cantidades de productos por categoria
     */
    public HashMap<String, Integer> getQuantityByCategory() {
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
    public HashMap<String, ArrayList<Product>> getProductsEnableByCategory() {
        return productsEnableByCategory;
    }

    /**
     * Establece el hashmap que contiene la informacion de los productos permitidos por categoria
     */
    public void setProductsEnableByCategory(HashMap<String, ArrayList<Product>> productsByCategory) {
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
    public ArrayList<Product> getDefaultProducts() {
        return defaultProducts;
    }

    /**
     * Establece la lista de productos por defecto
     * @param defaultProducts La lista de productos por defecto en un dato de tipo ArrayList de Product
     */
    public void setDefaultProducts(ArrayList<Product> defaultProducts) {
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
    
}
