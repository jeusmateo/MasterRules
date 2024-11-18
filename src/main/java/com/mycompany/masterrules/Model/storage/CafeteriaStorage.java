package com.mycompany.masterrules.Model.storage;

import com.mycompany.masterrules.Database.ProductDBManager;
import com.mycompany.masterrules.Model.cafeteria.Product;

import java.util.HashMap;
import java.util.Map;


public class CafeteriaStorage {
    private Map<Product, StockInfo> products;
    private final ProductDBManager productDBManager;


    public CafeteriaStorage() {
        this.products = new HashMap<>();
        this.productDBManager = new ProductDBManager();
    }

    public boolean addProduct(Product product, StockInfo stockInfo) {
        if (isProductStored(product)) {
            return false;
        }

        if (!isStockInfoValid(stockInfo)) {
            return false;
        }

        products.put(product, stockInfo);

        return true;
    }

    public boolean isStockInfoValid(StockInfo stockInfo) {
        return stockInfo.getCurrentStock() >= 0 && stockInfo.getMinStock() >= 0 && stockInfo.getMaxStock() >= 0;
    }

    public void removeProduct(Product product) throws Exception {
        if (isProductStored(product)) {
            products.remove(product);
        }


    }

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


    public void setProducts(HashMap<Product, StockInfo> products) {
        this.products = products;
    }
}
