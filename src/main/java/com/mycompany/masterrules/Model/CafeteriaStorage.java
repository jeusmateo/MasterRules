package com.mycompany.masterrules.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for instances of CafeteriaStorage
 *
 * @author alexs
 */
public class CafeteriaStorage {
    private Map<Product, StockInfo> products;//HashMap<id,stock>

    /**
     * Constructor of class CafeteriaStorage
     */
    public CafeteriaStorage() {
        this.products = new HashMap<>();
    }

    /**
     * Adds a product in the storage
     *
     * @param product   Identification of the product
     * @param stockInfo Quantity available in the storage
     * @throws Exception If the product already exists, it causes an error
     */
    public void addProduct(Product product, StockInfo stockInfo) throws Exception {
        if (isProductStored(product)) {
            return;
        }
        if (isStockInfoValid(stockInfo)) {
            products.put(product, stockInfo);
        } else {
            throw new Exception("ERROR: La informacion del Stock es incorrecta");
        }

    }

    public boolean isStockInfoValid(StockInfo stockInfo) {
        return stockInfo.getCurrentStock() >= 0 && stockInfo.getMinStock() >= 0 && stockInfo.getMaxStock() >= 0;
    }

    /**
     * Removes a product from the storage
     *
     * @param product Identification of the product
     * @throws Exception If the product is not in inventory, it causes an error
     */
    public void removeProduct(Product product) throws Exception {
        if (isProductStored(product)) {
            products.remove(product);
        }


    }

    /**
     * Updates the stock of a product
     *
     * @param product     Identification of the product
     * @param newQuantity New quantity of the product
     * @throws Exception If the product is not in inventory, it causes an error
     */
    public void editCurrentStock(Product product, int newQuantity) throws Exception {//cambiae el nombre a editCurrentStock
        //*****Implementar funcion despues de acabar ventas
        if (isProductStored(product)) {
            if (newQuantity >= 0) {
                StockInfo newStockInfo = products.get(product);
                newStockInfo.setCurrentStock(newQuantity);
                products.put(product, newStockInfo);
            } else {
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }

    }

    public void editMinStock(Product product, int newQuantity) throws Exception {
        if (isProductStored(product)) {
            if (newQuantity >= 0) {
                StockInfo newStockInfo = products.get(product);
                newStockInfo.setMinStock(newQuantity);
                products.put(product, newStockInfo);
            } else {
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }

    }

    public void editMaxStock(Product product, int newQuantity) throws Exception {
        if (isProductStored(product)) {
            if (newQuantity >= 0) {
                StockInfo newStockInfo = products.get(product);
                newStockInfo.setMaxStock(newQuantity);
                products.put(product, newStockInfo);
            } else {
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }

    }

    /**
     * Increments the stock of a product
     *
     * @param product   Identification of the product
     * @param increment Quantity to add in the product's stock
     * @throws Exception If the product isn't in storage or the increment is negative, it causes an error
     */
    public void incrementCurrentStock(Product product, int increment) throws Exception {
        if (isProductStored(product)) {
            if (increment >= 0) {//inverti por el criterio de if (el caso deseado primero)
                StockInfo newStockInfo = products.get(product);
                newStockInfo.setCurrentStock(newStockInfo.getCurrentStock() + increment);
                products.put(product, newStockInfo);
            } else {
                throw new Exception("ERROR: El incremento no puede ser negativo");
            }
        }

    }

    /**
     * Decrements the stock of a product
     *
     * @param product   Identification of the product
     * @param decrement Quantity to remove from the product's stock
     * @throws Exception If the product isn't in storage, the decrement is negative or the decrement is greater than the current stock, it causes an error
     */
    public void decrementCurrentStock(Product product, int decrement) throws Exception {
        if (isProductStored(product)) {
            if (decrement >= 0 && isEnoughStock(product, decrement)) {
                StockInfo newStockInfo = products.get(product);
                newStockInfo.setCurrentStock(newStockInfo.getCurrentStock() - decrement);
                products.put(product, newStockInfo);
            } else {
                throw new Exception("ERROR: El decremento no es apropiado");
            }
        }

    }

    /**
     * Checks if the product has enough stock for an operation involving the substraction of stock
     *
     * @param product  Identification of the product
     * @param quantity Quantity to compare to current stock
     * @return True, if the quantity less than or equal the stock of the product. False, if the quantity is greater than the stock of the product
     */
    public boolean isEnoughStock(Product product, int quantity) { // TODO cambiar nombre de la funcion
        StockInfo stockInfo = products.get(product);
        int currentStock = stockInfo.getCurrentStock();
        return quantity <= currentStock;
    }


    /**
     * Checks if a product is stored in storage
     *
     * @param product Identification of the product
     * @return True, if the product is in storage. False, if the product isn't in storage
     */
    public boolean isProductStored(Product product) throws Exception {
        if (products.containsKey(product)) {
            return true;
        } else {
            throw new Exception("ERROR:No se encontro el producto");

        }
    }

    /**
     * Getter of product with stock
     *
     * @return Product with stock
     */
    public Map<Product, StockInfo> getProducts() {
        return products;
    }

    /**
     * Setter of product with stock
     *
     * @param products Product with stock
     */
    public void setProducts(HashMap<Product, StockInfo> products) {
        this.products = products;
    }
}
