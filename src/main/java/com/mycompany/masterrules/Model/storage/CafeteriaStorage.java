package com.mycompany.masterrules.Model.storage;

import com.mycompany.masterrules.Database.ProductDatabase;
import com.mycompany.masterrules.Model.cafeteria.Product;

import java.util.HashMap;
import java.util.Map;

public class CafeteriaStorage {

    private Map<Product, StockInfo> productStockTable;
    private ProductDatabase productDatabase;

    public CafeteriaStorage() {
        this.productStockTable = new HashMap<>();
        this.productDatabase = new ProductDatabase();
        readFromDatabase();
    }

    private void readFromDatabase() {
        var productsFromDatabase = productDatabase.readAll();
        for (var product : productsFromDatabase) {
            this.productStockTable.put(product, product.getStockInfo());
        }
    }

    public boolean addProduct(Product product, StockInfo stockInfo) {
        if (!canAddProduct(product, stockInfo)) {
            return false;
        }
        product.setStockInfo(stockInfo);
        productStockTable.put(product, stockInfo);
        return productDatabase.update(product);
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
        product.setStockInfo(null);
        productStockTable.remove(product);
        return productDatabase.update(product);
    }

    public boolean editCurrentStock(Product product, int newQuantity) {//cambiae el nombre a editCurrentStock
        //*****Implementar funcion despues de acabar ventas
        if (!canEditStock(product, newQuantity)) {
            return false;
        }
        StockInfo newStockInfo = productStockTable.get(product);
        newStockInfo.setCurrentStock(newQuantity);
        productStockTable.put(product, newStockInfo);
        return productDatabase.update(product);
    }

    private boolean canEditStock(Product product, int newQuantity) {
        return isProductStored(product) && newQuantity >= 0;
    }

    public boolean editMinStock(Product product, int newQuantity) {
        if (!canEditStock(product, newQuantity)) {
            return false;
        }
        StockInfo newStockInfo = productStockTable.get(product);
        newStockInfo.setMinStock(newQuantity);
        productStockTable.put(product, newStockInfo);
        return productDatabase.update(product);
    }

    public boolean editMaxStock(Product product, int newQuantity) {
        if (!canEditStock(product, newQuantity)) {
            return false;
        }
        StockInfo newStockInfo = productStockTable.get(product);
        newStockInfo.setMaxStock(newQuantity);
        productStockTable.put(product, newStockInfo);
        return productDatabase.update(product);
    }

    public boolean incrementCurrentStock(Product product, int increment) {
        if (!canEditStock(product, increment)) {
            return false;
        }
        StockInfo newStockInfo = productStockTable.get(product);
        newStockInfo.setCurrentStock(newStockInfo.getCurrentStock() + increment);
        productStockTable.put(product, newStockInfo);
        return productDatabase.update(product);
    }

    public boolean decrementCurrentStock(Product product, int decrement) {
        if (!canEditStock(product, decrement) || !isEnoughStock(product, decrement)) {
            return false;
        }
        StockInfo newStockInfo = productStockTable.get(product);
        newStockInfo.setCurrentStock(newStockInfo.getCurrentStock() - decrement);
        productStockTable.put(product, newStockInfo);
        return productDatabase.update(product);
    }

    // TODO cambiar nombre de la funcion
    public boolean isEnoughStock(Product product, int quantity) {
        StockInfo stockInfo = productStockTable.get(product);
        int currentStock = stockInfo.getCurrentStock();
        return quantity <= currentStock;
    }

    public boolean isProductStored(Product product) {
        return productStockTable.containsKey(product);
    }

    public Map<Product, StockInfo> getProductStockTable() {
        return productStockTable;
    }

    //The type of "products" should be an interface such as "Map" rather than the implementation "HashMap".
    public void setProductStockTable(Map<Product, StockInfo> productStockTable) {
        this.productStockTable = productStockTable;
        for(Product product : productStockTable.keySet()){
            productDatabase.update(product);
        }
    }

    public StockInfo getStockInfo(Product product) {
        return productStockTable.get(product);
    }
}
