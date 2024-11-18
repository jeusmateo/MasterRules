package com.mycompany.masterrules.Model.storage;

import com.mycompany.masterrules.Database.ProductDBManager;
import com.mycompany.masterrules.Model.cafeteria.Product;

import java.util.HashMap;
import java.util.Map;


public class CafeteriaStorage {
    private final ProductDBManager productDBManager;
    private Map<Product, StockInfo> products;


    public CafeteriaStorage() {
        this.products = new HashMap<>();
        this.productDBManager = new ProductDBManager();
    }

    public boolean addProduct(Product product, StockInfo stockInfo) {
        if (!canAddProduct(product, stockInfo)) {
            return false;
        }

        products.put(product, stockInfo);

        return true;
    }

    private boolean canAddProduct(Product product, StockInfo stockInfo) {
        return !isProductStored(product) && isStockInfoValid(stockInfo);
    }

    private boolean isStockInfoValid(StockInfo stockInfo) {
        return stockInfo.getCurrentStock() >= 0 && stockInfo.getMinStock() >= 0 && stockInfo.getMaxStock() >= 0;
    }

    public boolean removeProduct(Product product) {
        if (!isProductStored(product)) {
            return false;
        }
        products.remove(product);
        return true;
    }

    public boolean editCurrentStock(Product product, int newQuantity) {//cambiae el nombre a editCurrentStock
        //*****Implementar funcion despues de acabar ventas
        if (!canEditStock(product, newQuantity)) {
            return false;
        }
        StockInfo newStockInfo = products.get(product);
        newStockInfo.setCurrentStock(newQuantity);
        products.put(product, newStockInfo);
        return true;
    }

    private boolean canEditStock(Product product, int newQuantity) {
        return isProductStored(product) && newQuantity >= 0;
    }

    public boolean editMinStock(Product product, int newQuantity) {
        if (!canEditStock(product, newQuantity)) {
            return false;
        }
        StockInfo newStockInfo = products.get(product);
        newStockInfo.setMinStock(newQuantity);
        products.put(product, newStockInfo);
        return true;
    }

    public boolean editMaxStock(Product product, int newQuantity) {
        if (!canEditStock(product, newQuantity)) {
            return false;
        }
        StockInfo newStockInfo = products.get(product);
        newStockInfo.setMaxStock(newQuantity);
        products.put(product, newStockInfo);
        return true;
    }

    public boolean incrementCurrentStock(Product product, int increment) {
        if (!canEditStock(product, increment)) {
            return false;
        }
        StockInfo newStockInfo = products.get(product);
        newStockInfo.setCurrentStock(newStockInfo.getCurrentStock() + increment);
        products.put(product, newStockInfo);
        return true;
    }

    public boolean decrementCurrentStock(Product product, int decrement) {
        if (!canEditStock(product, decrement) || !isEnoughStock(product, decrement)) {
            return false;
        }
        StockInfo newStockInfo = products.get(product);
        newStockInfo.setCurrentStock(newStockInfo.getCurrentStock() - decrement);
        products.put(product, newStockInfo);
        return true;
    }

    // TODO cambiar nombre de la funcion
    public boolean isEnoughStock(Product product, int quantity) {
        StockInfo stockInfo = products.get(product);
        int currentStock = stockInfo.getCurrentStock();
        return quantity <= currentStock;
    }


    public boolean isProductStored(Product product) {
        return products.containsKey(product);
    }

    public Map<Product, StockInfo> getProducts() {
        return products;
    }

    //The type of "products" should be an interface such as "Map" rather than the implementation "HashMap".
    public void setProducts(HashMap<Product, StockInfo> products) {
        this.products = products;
    }
}
