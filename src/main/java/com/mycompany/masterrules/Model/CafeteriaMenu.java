/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author alexs
 */
public class CafeteriaMenu {
    private String title;
    private HashMap<String,ArrayList<Product>> products;
    private ArrayList<Combo> combos;//falta combos

    public CafeteriaMenu(String title, HashMap<String, ArrayList<Product>> productos, ArrayList<Combo> combos) {
        this.title = title;
        this.products = productos;
        this.combos = combos;
    }

    public CafeteriaMenu(String title) {
        this.title = title;
        this.products = new HashMap<String,ArrayList<Product>>();
        this.combos = new ArrayList<Combo>();
    }
    
    public void addProduct(Product product){
        String productType=product.getProductType();
        ArrayList<Product> newValue=new ArrayList<Product>();
        
        if(products.containsKey(productType)){//habria que asegurar que los productos dentro del tipo no sean lo mismo
            newValue= products.get(productType);
            newValue.add(product);
        }
        else{
            newValue.add(product);
        }
        products.put(productType, newValue);
        
    }
    
    public void removeProduct(String productName){
        ArrayList<Product> newValue=new ArrayList<Product>();
        
        for(String key: products.keySet()){
            ArrayList<Product> currentList=products.get(key);
            
            for(int i=0; i<currentList.size();i++){
                if(productName.equals(currentList.get(i).getProductName())){
                    currentList.remove(i);
                    return;
                }
                
            }
        }
        System.out.println("ERROR:No se encontro el producto");
    }
    
    public void addCombo(Combo combo){
        //falta integrarlo al menu
    }
    
    public void removeCombo(String comboName){
        //falta integrarlo al menu
    }

    public String getTitle() {
        return title;
    }

    public HashMap<String, ArrayList<Product>> getProducts() {
        return products;
    }

    public ArrayList<Combo> getCombos() {
        return combos;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public void setProducts(HashMap<String, ArrayList<Product>> products) {
        this.products = products;
    }

    public void setCombos(ArrayList<Combo> combos) {
        this.combos = combos;
    }
}
