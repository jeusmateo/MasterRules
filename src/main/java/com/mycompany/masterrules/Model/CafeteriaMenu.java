package com.mycompany.masterrules.Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que representa el menu en MasterRules
 * @author Alexandra Saavedra
 */
public class CafeteriaMenu {
    /**
     * Titulo del menu
     */
    private String title;
    /**
     * Productos disponibles en el menu
     */
    private ArrayList<Product> products;//cambie products a arraylist //no se si luego se requiera del conjunto de tipo de producto en un Set?
    /**
     * Combos disponibles en el menu
     */
    private ArrayList<Combo> combos;//falta combos
    
    /**
     * Constructor de clase
     * @param title Titulo del menu
     */
    public CafeteriaMenu(String title) {
        this.title = title;
        this.products = new ArrayList<Product>();
        this.combos = new ArrayList<Combo>();
    }
    
    /**
     * Constructor de clase
     * @param title Titulo del menu
     * @param products Productos disponibles en el menu
     * @param combos Combos disponibles en el menu
     */
    public CafeteriaMenu(String title, ArrayList<Product> products, ArrayList<Combo> combos) {
        this.title = title;
        this.products = products;
        this.combos = combos;
    }
    
    /**
     * Agrega un producto al menu
     * @param product Producto a agregar al menu
     */
    public void addProduct(Product product) throws Exception{
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
     * Remover el product del menu
     * @param productID Identificador del menu
     */
    public void removeProduct(String productID) throws Exception{
        for(int registeredProductCount=0;registeredProductCount<products.size();registeredProductCount++){//hay que ver si este nombre del indice esta bien
            if(productID.equals(products.get(registeredProductCount).getProductID())){
                products.remove(registeredProductCount);
                return;
            }
        }
        throw new Exception("ERROR: El producto no existe");
    }
    
    /**
     * Checa si el producto esta en el menu
     * @param productID Identificador del menu
     * @return Verdadero, si el producto se encuentra en el menu. Falso, si el producto no se encuentra en el menu
     */
    public boolean isProductOnMenu(String productID){//continuo trabajando con esto
        for(Product registeredProduct : products){
            if(productID.equals(registeredProduct.getProductID())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checa si el nombre del producto ha sido tomado
     * @param productName Nombre del producto
     * @return Verdadero, si el nombre del producto ha sido tomado. Falso, si el nombre no ha sido tomado
     */
    public boolean isProductNameTaken(String productName){
        for(Product registeredProduct : products){
            if(productName.equals(registeredProduct.getProductName())){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Modifica un producto en el menu
     * @param product Producto a modificar en el menu
     * @throws Exception Si el producto no existe en el menu, ocasiona un error
     */
    public void editProduct(Product product) throws Exception{
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
                products.get(registeredProductCount).setVIPPrice(product.getVIPPrice());
                
                return;
            }
        }
        throw new Exception("ERROR: El producto no existe");
        
    }
    
    /**
     * Obtiene a los productos que pertenecen a un cierto tipo
     * @param ProductType Tipo de productos
     * @return Lista de productos que pertenecen a un cierto tipo
     * @throws java.lang.Exception Si el tipo de productos solicitados no existen en el 
     */
    public ArrayList<Product> getProductsByType(String ProductType) throws Exception{
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
     * Agregar un combo al menu
     * @param combo Combo a agregar al menu
     */
    public void addCombo(Combo combo){
        //falta integrarlo al menu
    }
    
    /**
     * Remover un combo del menu
     * @param comboID Identificador del combo
     */
    public void removeCombo(String comboID){//lo cambie por id
        //falta integrarlo al menu
    }

    /**
     * Modificar un combo del menu
     * @param combo Combo a modificar en el menu
     */
    public void editCombo(Combo combo){
        //falta integrarlo al menu
    }
    
    /**
     * Obtiene el titulo del menu
     * @return Titulo del menu
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtiene los productos del menu
     * @return Productos del menu
     */
    public ArrayList<Product> getProducts() {    
        return products;
    }

    /**
     * Obtiene los combos del menu
     * @return Combos del menu
     */
    public ArrayList<Combo> getCombos() {
        return combos;
    }
    
    /**
     * Establece el titulo del menu
     * @param title Titulo del menu
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Establece los productos del menu
     * @param products Productos del menu
     */
    public void setProducts(ArrayList<Product> products) {    
        this.products = products;
    }

    /**
     * Establece los combos del menu
     * @param combos Combos del menu
     */
    public void setCombos(ArrayList<Combo> combos) {
        this.combos = combos;
    }
    
}
