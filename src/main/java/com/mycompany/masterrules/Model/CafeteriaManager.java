package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

/**
 * Class for instances of CafeteriaManager
 * @author alexs
 */
public class CafeteriaManager {
    /** ? */
    //private ProductQueries productQueries;
    private CafeteriaMenu menu;
    private CafeteriaStorage storage;

    public CafeteriaManager() {
        this.menu = new CafeteriaMenu("MR menu");
        this.storage = new CafeteriaStorage();
    }
    
    public CafeteriaManager(CafeteriaMenu menu, CafeteriaStorage storage) {
        this.menu = menu;
        this.storage = storage;
    }
    
    public void createProduct(Product newProduct,boolean inInventory, int quantity) throws Exception{
        //se modifico la excepcion
        if(quantity<0){
            throw new Exception("ERROR: La cantidad no puede ser negativa");
        }
        if(menu.isProductNameTaken(newProduct.getProductName())){
            throw new Exception("ERROR: El nombre del producto ya está tomado");
        }
        
        if(inInventory){
            storage.addProduct(newProduct.getProductID(), quantity);
        }
        menu.addProduct(newProduct);
        
    }

    public void deleteProduct(String productID) throws Exception{//cambie por id
        storage.removeProduct(productID);
        menu.removeProduct(productID);//cambie por id
            
    }
    
    public void editProduct(Product product,boolean inInventory, int quantity) throws Exception{
        if(quantity<0){
            throw new Exception("ERROR: La cantidad no puede ser negativa");
        }
        
        menu.editProduct(product);
        if(inInventory){
            if(storage.isStored(product.getProductID())){
                storage.updateStock(product.getProductID(), quantity);   
            }
            else{
                storage.addProduct(product.getProductID(), quantity);    
            }
        }
        
        
    }
    
    public void createCombo(Combo newCombo) throws Exception{
        if(menu.isComboNameTaken(newCombo.getComboName())){
            throw new Exception("ERROR: El nombre del combo ya está tomado");
        }
        
        menu.addCombo(newCombo);
    }
    
    public void deleteCombo(String comboID) throws Exception{
        menu.removeCombo(comboID);
    }
    
    public void editCombo(Combo combo) throws Exception{
        menu.editCombo(combo);
    }
    
    public void editMenu(){//creo que ya no vamos a utilizar esta, a menos que se quiera
        //falta
    }

    public CafeteriaMenu getMenu() {
        return menu;
    }

    public void setMenu(CafeteriaMenu menu) {
        this.menu = menu;
    }

    public CafeteriaStorage getStorage() {
        return storage;
    }

    public void setStorage(CafeteriaStorage storage) {
        this.storage = storage;
    }
    
    
    
}
