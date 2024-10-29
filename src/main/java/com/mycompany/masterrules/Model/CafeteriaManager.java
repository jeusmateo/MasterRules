package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

/**
 * Class for instances of CafeteriaManager
 * @author alexs
 */
public class CafeteriaManager {
    /** ? */
    private ProductQueries productQueries;
    private CafeteriaMenu menu;
    private CafeteriaStorage storage;

    
    public CafeteriaManager(ProductQueries productQueries, CafeteriaMenu menu, CafeteriaStorage storage) {
        this.productQueries = productQueries;
        this.menu = menu;
        this.storage = storage;
    }

    public CafeteriaManager() {
        this.productQueries = new ProductQueries();
        this.menu = new CafeteriaMenu("MR menu");
        this.storage = new CafeteriaStorage();
    }

    
    public void createProduct(Product newProduct,boolean inInventory, int quantity) throws Exception{
        if(productQueries.register(newProduct)){
            System.out.println("Producto registrado");
        }
        else{
            throw new Exception("ERROR: Problema al registrar el producto");
        }
        
        if(inInventory){
            storage.addProduct(newProduct.getID()+"", quantity);
        }
        
        menu.addProduct(newProduct);
    }
    
    public void createProduct(long id, String name, String type, BigDecimal price, BigDecimal VIPprice) throws Exception{
        Product newProduct= new Product(id,name,type,price,VIPprice);
        if(productQueries.register(newProduct)){
            System.out.println("Producto registrado");
        }
        else{
            throw new Exception("ERROR: Problema al registrar el producto");
        }

        
        menu.addProduct(newProduct);
    }


    public void deleteProduct(String productName) throws Exception{
            Product foundProduct=productQueries.findByName(productName);
            
            if(foundProduct!=null){
                if(productQueries.delete(foundProduct)){
                    System.out.println("Producto eliminado");
                }
                else{
                    throw new Exception("ERROR: Problema al eliminar el producto");
                }
            }
            else{
                throw new Exception("ERROR: No se encontro el producto con el nombre "+productName);
            }
            if(storage.isStored(foundProduct.getID()+"")){
                storage.removeProduct(foundProduct.getID()+"");
            }
            
            menu.removeProduct(foundProduct.getProductName());
            
    }
    
    public void editProduct(Product product,boolean inInventory, int quantity) throws Exception{
        if(productQueries.modify(product)){
            System.out.println("Producto modificado");
        }
        else{
            throw new Exception("ERROR: Problema al modificar el producto");
        }
        if(inInventory){
            if(storage.isStored(product.getID()+"")){
                storage.updateStock(product.getID()+"", quantity);
            }
            else{
                storage.addProduct(product.getID()+"", quantity);
            }
        }
        menu.editProduct(product);
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

    public ProductQueries getProductQueries() {
        return productQueries;
    }

    public void setProductQueries(ProductQueries productQueries) {
        this.productQueries = productQueries;
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
