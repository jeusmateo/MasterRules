package com.mycompany.masterrules.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for instances of CafeteriaMenu
 * @author alexs
 */
public class CafeteriaMenu {
    private String title;
    private HashMap<String,ArrayList<Product>> products;
    private ArrayList<Combo> combos;//falta combos
    
    /**
     * Constructor of class CafeteriaMenu
     * @param title 
     */
    public CafeteriaMenu(String title) {
        this.title = title;
        this.products = new HashMap<String,ArrayList<Product>>();
        this.combos = new ArrayList<Combo>();
    }
    
    /**
     * Constructor of class CafeteriaMenu
     * @param title Menu title
     * @param productos Menu products
     * @param combos Menu Combos
     */
    public CafeteriaMenu(String title, HashMap<String, ArrayList<Product>> productos, ArrayList<Combo> combos) {
        this.title = title;
        this.products = productos;
        this.combos = combos;
    }
    
    /**
     * Adds product on the menu
     * @param product Product to add on the menu
     */
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
    
    /**
     * Remove product on the menu by name
     * @param productName Name of the product to remove from the menu
     */
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
    
    /**
     * Modify product on the menu
     * @param product Product to modify on the menu
     * @throws Exception If the product is not on the menu, it causes an error
     */
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
    
    /**
     * Obtains the products that belong in a certain type
     * @param ProductType Product type
     * @return 
     */
    public ArrayList<Product> getProductosByType(String ProductType){
        return this.products.get(ProductType);
    }
    
    /**
     * Checks if the food category (clasified by food type) is empty
     * @param productType Food type
     * @return 
     */
    public boolean isFoodTypeEmpty(String productType){
        //Si no quedan productos en una categoría de comida se retorna verdadero
        if(products.get(productType).isEmpty()){
            return true;
        }
        return false;
        
    }
    
    /**
     * Adds combo on the menu 
     * @param combo Combo to add on the menu
     */
    public void addCombo(Combo combo){
        //falta integrarlo al menu
    }
    
    /**
     * Removes combo from the menu
     * @param comboName Name of the combo to remove from the menu
     */
    public void removeCombo(String comboName){
        //falta integrarlo al menu
    }

    /**
     * Modify combo on the menu
     * @param combo Combo to modify on the menu
     */
    public void editCombo(Combo combo){
        //falta integrarlo al menu
    }
    
    /**
     * Getter of menu title
     * @return Title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter of menu products
     * @return Products
     */
    public HashMap<String, ArrayList<Product>> getProducts() {
        return products;
    }

    /**
     * Getter of menu combos
     * @return Combos
     */
    public ArrayList<Combo> getCombos() {
        return combos;
    }
    
    /**
     * Setter of menu title
     * @param title Menu title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter of menu products
     * @param products Menu products
     */
    public void setProducts(HashMap<String, ArrayList<Product>> products) {
        this.products = products;
    }

    /**
     * Setter of menu combos
     * @param combos Menu combos
     */
    public void setCombos(ArrayList<Combo> combos) {
        this.combos = combos;
    }
    
}
