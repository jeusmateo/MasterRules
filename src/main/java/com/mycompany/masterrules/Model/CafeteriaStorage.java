/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

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
    
    public void addProduct(String id,int stock){
        if(stock<0){
            System.out.println("ERROR:El stock es inválido");
            return;
        }
        
        if(products.containsKey(id)){
            System.out.println("ERROR:El producto ya existe");
        }
        else{
            products.put(id, stock);
        }
    }
    
    public void removeProduct(String id){
        if(products.containsKey(id)){
            products.remove(id);
        }
        else{
            System.out.println("ERROR:El producto no existe en el inventario");
        }
        
    }
    
    public void updateStock(String id,int newQuantity){
        // TODO: *****Implementar funcion despues de acabar ventas
    }
    
    public void addToStock(String id,int increment){
        if(products.containsKey(id)){
            int newQuantity=products.get(id)+increment;
            products.put(id, newQuantity);
        }
        else{
            System.out.println("ERROR: No se encontro el producto");
        }
        
    }
    
    public void removeFromStock(String id,int decrement){
        if(products.containsKey(id)){
            if(!hasEnoughStock(id,decrement)){
            return;
            }
            int newQuantity=products.get(id)-decrement;
            products.put(id, newQuantity);
        }
        else{
            System.out.println("ERROR:No se encontro el producto");
        }
    }
    
    public boolean hasEnoughStock(String id,int quantity){
        if(quantity>products.get(id)){
            System.out.println("ERROR:No hay suficiciente existencias del producto");
            return false;
        }
        else{
            return true;
        }
    }
    
    public void searchProduct(){
        //falta y no se que se tiene que hacer aquí
    }

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Integer> products) {
        this.products = products;
    }
}
