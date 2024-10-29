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
        for(String key: products.keySet()){
            ArrayList<Product> currentList=products.get(key);
            
            for(int i=0; i<currentList.size();i++){
                if(productName.equals(currentList.get(i).getProductName())){
                    currentList.remove(i);
                    
                    //Si al eliminar un producto, la categoría de comida queda vacía; entonces la categoria se elimina
                    //-----No veo porque se debe eliminar la categoria, si no hay productos en ella.
                    if(isFoodTypeEmpty(key)){
                        products.remove(key);
                    }
                    return;
                }
                
            }
        }
        System.out.println("ERROR:No se encontro el producto");//no se si esto necesite una excepcion, aunque es probable que nunca ocurra porque prmero se ve desde la BD
    }
    
    public void editProduct(Product product) throws Exception{
        for(String key : products.keySet()){
            ArrayList<Product> currentList=products.get(key);
            
            for(int i=0;i<currentList.size();i++){
                if(product.getProductName().equals(currentList.get(i).getProductName())){
                    currentList.get(i).setPrice(product.getPrice());
                    currentList.get(i).setProductName(product.getProductName());
                    currentList.get(i).setVIPprice(product.getVIPprice());
                    
                    if(!product.getProductType().equals(currentList.get(i).getProductType())){
                        removeProduct(product.getProductName());//lo removemos de esa categoria
                        addProduct(product);//se inserta en la categoria correspondiente
                    }
                    return;
                }
            }
        }
        throw new Exception("ERROR: No se encontro el producto");
    }
    
    public boolean isFoodTypeEmpty(String key){
        //Si no quedan productos en una categoría de comida se retorna verdadero
        if(products.get(key).isEmpty()){
            return true;
        }
        return false;
        
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
    
    public ArrayList<Product> getProductos(String type){
        return this.products.get(type);
    }
    
    
}
