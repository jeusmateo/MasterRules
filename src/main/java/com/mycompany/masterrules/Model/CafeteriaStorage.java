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
    private HashMap<Long,Integer> products;

    public CafeteriaStorage(HashMap<Long, Integer> products) {
        this.products = products;
    }

    public CafeteriaStorage() {
        this.products = new HashMap<Long,Integer>();
    }
    
    public void addProduct(long id,int stock){
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
    
    public void removeProduct(long id){
        for(Long idKey: products.keySet()){
            if(idKey==id){
                products.remove(idKey);
            }
        }
        System.out.println("ERROR:El producto no exite en el inventario");
    }
    
    public void updateStock(long id,int newQuantity){
        //*****Implementar funcion despues de acabar ventas
    }
    
    public void addToStock(long id,int increment){
        for(Long idKey: products.keySet()){
            if(idKey==id){
                int newQuantity=products.get(idKey)+increment;
                products.put(idKey, newQuantity);
            }
            return;
        }
        System.out.println("ERROR:No se encontro el producto");
    }
    
    public void removeFromStock(long id,int decrement){
        for(Long idKey: products.keySet()){
            if(idKey==id){
                if(!hasEnoughStock(id,decrement)){
                    return;
                }
                int newQuantity=products.get(idKey)-decrement;
                products.put(idKey, newQuantity);
            }
            return;
        }
        System.out.println("ERROR:No se encontro el producto");
    }
    
    public boolean hasEnoughStock(long id,int quantity){
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

    public HashMap<Long, Integer> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Long, Integer> products) {
        this.products = products;
    }
}
