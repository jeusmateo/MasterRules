package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.ArrayList;

//probablemente esta clase se vaya a eliminar por completo
/**
 * Class for instances of ProductQueries
 * @author alexs
 */
public class ProductQueries {
    ArrayList<Product> products;

    /**
     * Constructor of class ProductQueries
     */
    public ProductQueries() {
        products=new ArrayList<Product>();
    }
    
    /**
     * Registers a product in the database
     * @param product Product to register
     * @return True, if the operation was sucessful. False, if the operation was unsucessful
     */
    public boolean register(Product product) {
        products.add(product);
        return true;
    }

    /**
     * Modifies a product stored in the database
     * @param product Product to modify
     * @return True, if the operation was sucessful. False, if the operation was unsucessful
     */
    public boolean modify(Product product) {
        for(int i=0;i<products.size();i++){
            if(product.getID()==products.get(i).getID()){
                products.get(i).setPrice(new BigDecimal(product.getPrice()+""));
                products.get(i).setProductName(product.getProductName());
                products.get(i).setProductType(product.getProductType());
                products.get(i).setVIPprice(new BigDecimal(product.getVIPprice()+""));
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes product from the database
     * @param product Product to delete
     * @return True, if the operation was sucessful. False, if the operation was unsucessful
     */
    public boolean delete(Product product) {
        for(int i=0;i<products.size();i++){
            if(product.getID()==products.get(i).getID()){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Searches a product by their identification
     * @param id Identification of the product to search
     * @return Found product
     */
    public Product findByID(long id) throws Exception {
        for(int i=0;i<products.size();i++){
            if(id==products.get(i).getID()){
                return products.get(i);
            }
        }
        throw new Exception ("ERROR: No se encontro el producto");
    }
    
    /**
     * Searches a product by their identification
     * @param name Name of the product to search
     * @return Found product
     */
    public Product findByName(String name) throws Exception {
        for(int i=0;i<products.size();i++){
            if(name.equals(products.get(i).getProductName())){
                return products.get(i);
            }
        }
        throw new Exception ("ERROR: No se encontro el producto");
    }
    
}
