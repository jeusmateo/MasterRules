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
        /*
        if(productQueries.register(newProduct)){
            System.out.println("Producto registrado");
        }
        else{
            throw new Exception("ERROR: Problema al registrar el producto");
        }
        */
        
        menu.addProduct(newProduct);//cambie la posicion porque el menu tiene que revisar el nombre, y el storage no lo hace
        
        if(inInventory){
            storage.addProduct(newProduct.getProductID(), quantity);
        }
        
        
    }


    public void deleteProduct(String productID) throws Exception{//cambie por id
        /*    
        Product foundProduct=productQueries.findByName(productID);
            
        if(foundProduct!=null){
            if(productQueries.delete(foundProduct)){
                System.out.println("Producto eliminado");
            }
            else{
                throw new Exception("ERROR: Problema al eliminar el producto");
            }
        }
        else{
            throw new Exception("ERROR: No se encontro el producto con el ID "+productID);
        }
        */
        
        menu.removeProduct(productID);//cambie por id
        storage.removeProduct(productID);
            
    }
    
    public void editProduct(Product product,boolean inInventory, int quantity) throws Exception{
        /*
        if(productQueries.modify(product)){
            System.out.println("Producto modificado");
        }
        else{
            throw new Exception("ERROR: Problema al modificar el producto");
        }
        */
        
        menu.editProduct(product);//cambie la posicion porque el menu tiene que revisar el nombre, y el storage no lo hace
        if(inInventory){
            if(storage.isStored(product.getProductID())){
                storage.updateStock(product.getProductID(), quantity);
            }
            else{
                storage.addProduct(product.getProductID(), quantity);
            }
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
