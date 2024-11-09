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
        //no me gusta como quedo este pedazo de codigo, probablemente debamos de discutirlo y refactorizarlo
        //el problema es que el menu y storage tiene que tomar en cuenta distintos casos (cantidad correcta y que no haya nombres iguales) pero toman estos casos por separado
        if(isProductValidForCafeteria(newProduct,quantity)){
            if(inInventory){
                storage.addProduct(newProduct.getProductID(), quantity);
            }
            menu.addProduct(newProduct);
        }
        else{
            throw new Exception("ERROR: Problemas al crear el producto");
        }
    }

    private boolean isProductValidForCafeteria(Product product,int quantity){//tuve que agregar esto pero esta algo extraño
        return (quantity>=0 && !(menu.isProductNameTaken(product.getProductName())));
    }

    public void deleteProduct(String productID) throws Exception{//cambie por id
        storage.removeProduct(productID);
        menu.removeProduct(productID);//cambie por id
            
    }
    
    public void editProduct(Product product,boolean inInventory, int quantity) throws Exception{
        //hay que revisar esto
        //el problema es que el menu y storage tiene que tomar en cuenta distintos casos (cantidad correcta y que no haya nombres iguales (en el caso e que se quiera cambiar)) pero toman estos casos por separado
        
        if(inInventory){
            if(quantity>=0){
                menu.editProduct(product);
                if(storage.isStored(product.getProductID())){
                    storage.updateStock(product.getProductID(), quantity);
                }
                else{
                    storage.addProduct(product.getProductID(), quantity);
                }
            }
            else{
                throw new Exception("ERROR: El incremento no puede ser negativo");
            }
            
        }
        else{
            menu.editProduct(product);
        }
        
        
    }
    
    public void createCombo(){
        //falta
    }
    
    public void deleteCombo(Combo combo){
        //falta
    }
    
    public void editCombo(Combo combo){
        //falta
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
