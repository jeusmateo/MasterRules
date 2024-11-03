package com.mycompany.masterrules.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class for instances of CafeteriaMenu
 * @author alexs
 */
public class CafeteriaMenu {
    private String title;
    //private HashMap<String,ArrayList<Product>> products;
    private ArrayList<Product> products;//cambie products a arraylist //no se si luego se requiera del conjunto de tipo de producto en un Set?
    private ArrayList<Combo> combos;//falta combos
    
    /**
     * Constructor of class CafeteriaMenu
     * @param title 
     */
    public CafeteriaMenu(String title) {
        this.title = title;
        this.products = new ArrayList<Product>();
        this.combos = new ArrayList<Combo>();
    }
    
    /**
     * Constructor of class CafeteriaMenu
     * @param title Menu title
     * @param products Menu products
     * @param combos Menu Combos
     */
    public CafeteriaMenu(String title, ArrayList<Product> products, ArrayList<Combo> combos) {
        this.title = title;
        this.products = products;
        this.combos = combos;
    }
    
    /**
     * Adds product on the menu
     * @param product Product to add on the menu
     */
    public void addProduct(Product product) throws Exception{
        /*
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
        */
        if(!isProductOnMenu(product.getProductID())){
            if(!isProductNameTaken(product.getProductName())){
                products.add(product);
            }
            else{
                throw new Exception("ERROR: El nombre del producto ya esta tomado");
            }
        }
        else{
            throw new Exception("ERROR: El producto ya existe");
        }
    }
    
    /**
     * Remove product on the menu by name
     * @param productID Product identification
     */
    public void removeProduct(String productID) throws Exception{//cambie el parametro por id del producto en vez de nombre
        /*
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
        */
        for(int registeredProductCount=0;registeredProductCount<products.size();registeredProductCount++){//hay que ver si este nombre del indice esta bien
            if(productID.equals(products.get(registeredProductCount).getProductID())){
                products.remove(registeredProductCount);
                return;
            }
        }
        throw new Exception("ERROR: El producto no existe");
    }
    
    public boolean isProductOnMenu(String productID){//continuo trabajando con esto
        for(Product registeredProduct : products){
            if(productID.equals(registeredProduct.getProductID())){
                return true;
            }
        }
        return false;
    }
    
    public boolean isProductNameTaken(String productName){
        for(Product registeredProduct : products){
            if(productName.equals(registeredProduct.getProductName())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Modify product on the menu
     * @param product Product to modify on the menu
     * @throws Exception If the product is not on the menu, it causes an error
     */
    public void editProduct(Product product) throws Exception{//cambie el metodo
        /*
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
        */
        for(int registeredProductCount=0;registeredProductCount<products.size();registeredProductCount++){//hay que ver si este nombre del indice esta bien
            if(product.getProductID().equals(products.get(registeredProductCount).getProductID())){
                
                if(!product.getProductName().equals(products.get(registeredProductCount).getProductName())){
                    
                    if(!isProductNameTaken(product.getProductName())){
                        products.get(registeredProductCount).setProductName(product.getProductName());
                    }
                    else{
                        throw new Exception("ERROR: El nombre del producto ya esta tomado");
                    }
                    
                }
                products.get(registeredProductCount).setProductType(product.getProductType());
                products.get(registeredProductCount).setPrice(product.getPrice());
                products.get(registeredProductCount).setVIPprice(product.getVIPprice());
                
                return;
            }
        }
        throw new Exception("ERROR: El producto no existe");
        
    }
    
    /**
     * Obtains the products that belong in a certain type
     * @param ProductType Product type
     * @return List of product that belong to the determined type
     */
    public ArrayList<Product> getProductsByType(String ProductType) throws Exception{
        //return this.products.get(ProductType);
        ArrayList<Product> productsWithType= new ArrayList<Product>();
        
        for(Product product: products){
            if(product.getProductType().equals(ProductType)){
                productsWithType.add(product);
            }
        }
        
        if(productsWithType.isEmpty()){
            throw new Exception("ERROR: El tipo de producto no existe en el menu");
        }
        
        return productsWithType;
    }
    
    /**
     * Checks if the food category (clasified by food type) is empty
     * @param productType Food type
     * @return 
     */
    /*//creo que ya no vamos a emplear este pues el arrayList no toma en cuenta esto
    public boolean isFoodTypeEmpty(String productType){
        //Si no quedan productos en una categoría de comida se retorna verdadero
        
        if(products.get(productType).isEmpty()){
            return true;
        }
        return false;
    }
    */
    
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
     * @return Menu title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter of menu products
     * @return Menu products
     */
    public ArrayList<Product> getProducts() {    
        return products;
    }

    /**
     * Getter of menu combos
     * @return Menu combos
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
     * @param products Mennu products
     */
    public void setProducts(ArrayList<Product> products) {    
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
