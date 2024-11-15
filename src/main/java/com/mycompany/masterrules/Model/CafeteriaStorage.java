package com.mycompany.masterrules.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.HashMap;

/**
 * Class for instances of CafeteriaStorage
 * @author alexs
 */
public class CafeteriaStorage {
private HashMap<Product,StockInfo> products;//HashMap<id,stock>

    /**
     * Constructor of class CafeteriaStorage
     */
    public CafeteriaStorage() {
        this.products = new HashMap<Product,StockInfo>();
    }
    /**
     * Constructor of class CafeteriaStorage
     * @param products Products with their stocks
     */
    public CafeteriaStorage(HashMap<Product, StockInfo> products){
        this.products = products;
    }

    /**
     * Adds a product in the storage
     * @param product Identification of the product
     * @param stockInfo Quantity available in the storage
     * @throws Exception If the product already exists, it causes an error
     */
    public void addProduct(Product product, StockInfo stockInfo) throws Exception {
        //se elimino la excepcion cuando el stock es negativo
        if(!isStored(product)){//inverti por el criterio de if (el caso deseado primero)
            if(isStockInfoValid(stockInfo)){
                products.put(product, stockInfo);
            }
            else{
                throw new Exception("ERROR: La informacion del Stock es incorrecta");
            }
        }
        else{
            throw new Exception("ERROR:El producto ya existe");
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
    
    /**
     * Removes a product from the storage
     * @param product Identification of the product
     * @throws Exception If the product is not in inventory, it causes an error
     */
    public void removeProduct(Product product) throws Exception{
        if(isStored(product)){
            products.remove(product);
        }
        else{
            throw new Exception("ERROR: El producto no existe en el inventario");//creo que no deberia marcar error pues algunos de los productos no existen en inventario. Aunque no se si conviene agregar un atributo en Product para que sepamos si es inventariable; o solo usar el metodo isStored() andtes de removeProduct()
        }
        
    }
    
    /**
     * Updates the stock of a product
     * @param product Identification of the product
     * @param newQuantity New quantity of the product
     * @throws Exception If the product is not in inventory, it causes an error
     */
    public void editCurrentStock(Product product,int newQuantity) throws Exception{//cambiae el nombre a editCurrentStock
        //*****Implementar funcion despues de acabar ventas
        if(isStored(product)){
            if(newQuantity>=0){
                StockInfo newStockInfo=products.get(product);
                newStockInfo.setCurrentStock(newQuantity);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }
        else{
            throw new Exception("ERROR: El producto no existe en el inventario");
        }
    }
    
    public void editMinStock(Product product,int newQuantity) throws Exception{
        if(isStored(product)){
            if(newQuantity>=0){
                StockInfo newStockInfo=products.get(product);
                newStockInfo.setMinStock(newQuantity);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }
        else{
            throw new Exception("ERROR: El producto no existe en el inventario");
        }
    }
    
    public void editMaxStock(Product product,int newQuantity) throws Exception{
        if(isStored(product)){
            if(newQuantity>=0){
                StockInfo newStockInfo = products.get(product);
                newStockInfo.setMaxStock(newQuantity);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: La cantidad no puede ser negativa");
            }
        }
        else{
            throw new Exception("ERROR: El producto no existe en el inventario");
        }
    }
    
    /**
     * Increments the stock of a product
     * @param product Identification of the product
     * @param increment Quantity to add in the product's stock
     * @throws Exception If the product isn't in storage or the increment is negative, it causes an error
     */
    public void addToCurrentStock(Product product,int increment) throws Exception{//!!!tengo que cambiar el nombre a addToCurrentStock
        if(isStored(product)){
            if(increment>=0){//inverti por el criterio de if (el caso deseado primero)
                StockInfo newStockInfo=products.get(product);
                newStockInfo.setCurrentStock(newStockInfo.getCurrentStock()+increment);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: El incremento no puede ser negativo");
            }
        }
        else{
            throw new Exception("ERROR: El producto no existe en el inventario");
        }
        
    }
    /**
     * Decrements the stock of a product
     * @param product Identification of the product
     * @param decrement Quantity to remove from the product's stock
     * @throws Exception If the product isn't in storage, the decrement is negative or the decrement is greater than the current stock, it causes an error
     */
    public void removeFromCurrentStock(Product product,int decrement) throws Exception{//!!!tengo que cambiar el nombre a removeFromCurrentStock
        if(isStored(product)){
            if(decrement>=0 && hasEnoughStock(product,decrement)){
                StockInfo newStockInfo=products.get(product);
                newStockInfo.setCurrentStock(newStockInfo.getCurrentStock()-decrement);
                products.put(product, newStockInfo);
            }
            else{
                throw new Exception("ERROR: El decremento no es apropiado");
            }
            
            /*
            if(decrement<0){
                throw new Exception("ERROR: El decremento no puede ser negativo");
            }
            if(!hasEnoughStock(product,decrement)){
                throw new Exception("ERROR:El decremento excede la cantidad almacenada del producto");
            }
            */
        }
        else{
            throw new Exception("ERROR:No se encontro el producto");
        }
    }
    
    /**
     * Checks if the product has enough stock for an operation involving the substraction of stock
     * @param product Identification of the product
     * @param quantity Quantity to compare to current stock
     * @return True, if the quantity less than or equal the stock of the product. False, if the quantity is greater than the stock of the product
     */
    public boolean hasEnoughStock(Product product,int quantity){//!!!cambiar nombre a hasEnoughCurrentStock
        StockInfo stockInfo=products.get(product);
        int currentStock=stockInfo.getCurrentStock();
        if(quantity>currentStock){
            return false;
        }
        else{
            return true;
        }
    }
    
    /**
     * Searches for a prodcut in storage
     */
    public void searchProduct(){
        //falta y no se que se tiene que hacer aquí
    }
    
    /**
     * Checks if a product is stored in storage
     * @param product Identification of the product
     * @return True, if the product is in storage. False, if the product isn't in storage
     */
    public boolean isStored(Product product){
        if(products.containsKey(product)){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Getter of product with stock
     * @return Product with stock
     */
    public HashMap<Product, StockInfo> getProducts() {    
        return products;
    }
    
    /**
     * Setter of product with stock
     * @param products Product with stock
     */
    public void setProducts(HashMap<Product, StockInfo> products) {
        this.products = products;
    }
}
