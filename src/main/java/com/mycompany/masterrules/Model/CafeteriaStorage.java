package com.mycompany.masterrules.Model;

import java.util.HashMap;


public class CafeteriaStorage {
private HashMap<Product,StockInfo> products;//HashMap<id,stock>

    public CafeteriaStorage() {
        this.products = new HashMap<Product,StockInfo>();
    }

    public void addProduct(Product product, StockInfo stockInfo) throws Exception {
        //se elimino la excepcion cuando el stock es negativo
        if(!isProductStored(product)){//inverti por el criterio de if (el caso deseado primero)
            if(isStockInfoValid(stockInfo)){
                products.put(product, stockInfo);
            }
            else{
                throw new Exception("ERROR: La informacion del Stock es incorrecta");
            }
        }

    }

    public boolean isStockInfoValid(StockInfo stockInfo){
        if(stockInfo.getCurrentStock()>=0 && stockInfo.getMinStock()>=0 && stockInfo.getMaxStock()>=0){
            return true;
        }
        else{
            return false;
        }
    }

    public void removeProduct(Product product) throws Exception{
        if(isProductStored(product)){
            products.remove(product);
        }

        
    }

    public void editCurrentStock(Product product,int newQuantity) throws Exception{//cambiae el nombre a editCurrentStock
        //*****Implementar funcion despues de acabar ventas
        if(isProductStored(product)){
            if(newQuantity>=0){
                StockInfo newStockInfo=products.get(product);
                newStockInfo.setCurrentStock(newQuantity);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }

    }
    
    public void editMinStock(Product product,int newQuantity) throws Exception{
        if(isProductStored(product)){
            if(newQuantity>=0){
                StockInfo newStockInfo=products.get(product);
                newStockInfo.setMinStock(newQuantity);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }

    }
    
    public void editMaxStock(Product product,int newQuantity) throws Exception{
        if(isProductStored(product)){
            if(newQuantity>=0){
                StockInfo newStockInfo = products.get(product);
                newStockInfo.setMaxStock(newQuantity);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }

    }

    public void incrementCurrentStock(Product product, int increment) throws Exception{
        if(isProductStored(product)){
            if(increment>=0){//inverti por el criterio de if (el caso deseado primero)
                StockInfo newStockInfo=products.get(product);
                newStockInfo.setCurrentStock(newStockInfo.getCurrentStock()+increment);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: El incremento no puede ser negativo");
            }
        }
        
    }

    public void decrementCurrentStock(Product product, int decrement) throws Exception{
        if(isProductStored(product)){
            if(decrement>=0 && isEnoughStock(product,decrement)){
                StockInfo newStockInfo=products.get(product);
                newStockInfo.setCurrentStock(newStockInfo.getCurrentStock()-decrement);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: El decremento no es apropiado");
            }
        }

    }

    public boolean isEnoughStock(Product product, int quantity){ // TODO cambiar nombre de la funcion
        StockInfo stockInfo=products.get(product);
        int currentStock=stockInfo.getCurrentStock();
        if(quantity>currentStock){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean isProductStored(Product product) throws Exception {
        if(products.containsKey(product)){
            return true;
        }
        else{
            throw new Exception("ERROR:No se encontro el producto");

        }
    }

    public HashMap<Product, StockInfo> getProducts() {    
        return products;
    }
    

    public void setProducts(HashMap<Product, StockInfo> products) {
        this.products = products;
    }
}
