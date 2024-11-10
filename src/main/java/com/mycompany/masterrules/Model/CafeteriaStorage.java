package com.mycompany.masterrules.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.HashMap;

/**
 * Class for instances of CafeteriaStorage
 * @author alexs
 */
public class CafeteriaStorage {

    private HashMap<String,Integer> products;//HashMap<id,stock>

    /**
     * Constructor of class CafeteriaStorage
     */
    public CafeteriaStorage() {
        this.products = new HashMap<String,Integer>();
    }
    /**
     * Constructor of class CafeteriaStorage
     * @param products Products with their stocks
     */
    public CafeteriaStorage(HashMap<String, Integer> products) {
        this.products = products;
    }
    
    /**
     * Adds a product in the storage
     * @param id Identification of the product
     * @param stock Quantity available in the storage
     * @throws Exception If the product already exists, it causes an error
     */
    public void addProduct(String id,int stock) throws Exception{
        //se elimino la excepcion cuando el stock es negativo
        if(isStored(id)){
            throw new Exception("ERROR:El producto ya existe");
        }
        else{
            products.put(id, stock);
        }
    }
    
    /**
     * Removes a product from the storage
     * @param id Identification of the product
     * @throws Exception If the product is not in inventory, it causes an error
     */
    public void removeProduct(String id) throws Exception{
        if(isStored(id)){
            products.remove(id);
        }
        else{
            throw new Exception("El producto no existe en el inventario");//creo que no deberia marcar error pues algunos de los productos no existen en inventario. Aunque no se si conviene agregar un atributo en Product para que sepamos si es inventariable; o solo usar el metodo isStored() andtes de removeProduct()
        }
        
    }
    
    /**
     * Updates the stock of a product
     * @param id Identification of the product
     * @param newQuantity New quantity of the product
     * @throws Exception If the product is not in inventory, it causes an error
     */
    public void updateStock(String id,int newQuantity) throws Exception{
        //*****Implementar funcion despues de acabar ventas
        if(isStored(id)){
            products.put(id, newQuantity);
        }
        else{
            throw new Exception("ERROR: El producto no existe en el inventario");
        }
    }
    
    /**
     * Increments the stock of a product
     * @param id Identification of the product
     * @param increment Quantity to add in the product's stock
     * @throws Exception If the product isn't in storage or the increment is negative, it causes an error
     */
    public void addToStock(String id,int increment) throws Exception{
        if(isStored(id)){
            if(increment<0){
                throw new Exception("ERROR: El incremento no puede ser negativo");
            }
            int newQuantity=products.get(id)+increment;
            products.put(id, newQuantity);
        }
        else{
            throw new Exception("ERROR: No se encontro el producto");
        }
        
    }
    /**
     * Decrements the stock of a product
     * @param id Identification of the product
     * @param decrement Quantity to remove from the product's stock
     * @throws Exception If the product isn't in storage, the decrement is negative or the decrement is greater than the current stock, it causes an error
     */
    public void removeFromStock(String id,int decrement) throws Exception{
        if(isStored(id)){
            if(decrement<0){
                throw new Exception("ERROR: El decremento no puede ser negativo");
            }
            if(!hasEnoughStock(id,decrement)){
                throw new Exception("ERROR:El decremento excede la cantidad almacenada del producto");
            }
            int newQuantity=products.get(id)-decrement;
            products.put(id, newQuantity);
        }
        else{
            throw new Exception("ERROR:No se encontro el producto");
        }
    }
    
    /**
     * Checks if the product has enough stock for an operation involving the substraction of stock
     * @param id Identification of the product
     * @param quantity Quantity to compare to current stock
     * @return True, if the quantity less than or equal the stock of the product. False, if the quantity is greater than the stock of the product
     */
    public boolean hasEnoughStock(String id,int quantity){
        if(quantity>products.get(id)){
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
     * @param id Identification of the product
     * @return True, if the product is in storage. False, if the product isn't in storage
     */
    public boolean isStored(String id){
        if(products.containsKey(id)){
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
    public HashMap<String, Integer> getProducts() {
        return products;
    }

    /**
     * Setter of product with stock
     * @param products Product with stock
     */
    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }
    
}
