package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

/**
 * Class for instances of CafeteriaManager
 */
public class CafeteriaManager {

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


    public void createProduct(Product newProduct,boolean inInventory, StockInfo stockInfo) throws Exception{//se modifico acorde al nuevo storage
        //se modifico la excepcion
        if(!storage.isStockInfoValid(stockInfo)){
            throw new Exception("ERROR: La cantidad no puede ser negativa");
        }
        if(menu.isProductNameTaken(newProduct.getProductName())){
            throw new Exception("ERROR: El nombre del producto ya está tomado");
        }
        
        if(inInventory){
            storage.addProduct(newProduct, stockInfo);
        }
        menu.addProductToMenu(newProduct);
        
    }

    public void deleteProduct(Product product) throws Exception{//cambie param por product
        storage.removeProduct(product);
        menu.removeProductOnMenu(product.getProductID());//cambie por id
            
    }
    
    public void editProduct(Product product,boolean inInventory, StockInfo stockInfo) throws Exception{//cambie la cantidad por el stockInfo
        if(!storage.isStockInfoValid(stockInfo)){
            throw new Exception("ERROR: La cantidad no puede ser negativa");
        }
        
        menu.editProduct(product);
        if(inInventory){
            if(storage.isStored(product)){
                storage.editCurrentStock(product, stockInfo.getCurrentStock());//se cambio a editCurrentStock
                storage.editMinStock(product, stockInfo.getMinStock());
                storage.editMaxStock(product, stockInfo.getMaxStock());
            }
            else{
                storage.addProduct(product, stockInfo);    
            }
        }
        
        
    }

    public void createCombo(Combo newCombo) throws Exception{
        if(menu.isComboNameTaken(newCombo.getProductName())){
            throw new Exception("ERROR: El nombre del combo ya está tomado");
        }
        
        menu.addComboToMenu(newCombo);
    }


    
    public void deleteCombo(String comboID) throws Exception{
        menu.removeComboOnMenu(comboID);
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
