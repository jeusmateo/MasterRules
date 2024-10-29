package com.mycompany.masterrules.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.HashMap;

/**
 *
 * @author alexs
 */
public class CafeteriaStorage {

    private HashMap<String,Integer> products;

    public CafeteriaStorage(HashMap<String, Integer> products) {
        this.products = products;
    }

    public CafeteriaStorage() {
        this.products = new HashMap<String,Integer>();
    }
    
    public void addProduct(String id,int stock) throws Exception{
        if(stock<0){
            throw new Exception("ERROR:El stock es inválido");
        }
        
        if(isStored(id)){
            throw new Exception("ERROR:El producto ya existe");
        }
        else{
            products.put(id, stock);
        }
    }
    
    public void removeProduct(String id) throws Exception{
        if(isStored(id)){
            products.remove(id);
        }
        else{
            throw new Exception("El producto no existe en el inventario");//creo que no deberia marcar error pues algunos de los productos no existen en inventario. Aunque no se si conviene agregar un atributo en Product para que sepamos si es inventariable; o solo usar el metodo isStored() andtes de removeProduct()
        }
        
    }
    
    
    public void updateStock(String id,int newQuantity) throws Exception{
        //*****Implementar funcion despues de acabar ventas
        if(isStored(id)){
            products.put(id, newQuantity);
        }
        else{
            throw new Exception("ERROR: El producto no existe en el inventario");
        }
    }
    
    public void addToStock(String id,int increment) throws Exception{
        if(isStored(id)){
            int newQuantity=products.get(id)+increment;
            products.put(id, newQuantity);
        }
        else{
            throw new Exception("ERROR: No se encontro el producto");
        }
        
    }
    
    public void removeFromStock(String id,int decrement) throws Exception{
        if(isStored(id)){
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
    
    public boolean hasEnoughStock(String id,int quantity){
        if(quantity>products.get(id)){
            return false;
        }
        else{
            return true;
        }
    }
    
    public void searchProduct(){
        //falta y no se que se tiene que hacer aquí
    }
    
    public boolean isStored(String id){
        if(products.containsKey(id)){
            return true;
        }
        else{
            return false;
        }
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }
    
}
