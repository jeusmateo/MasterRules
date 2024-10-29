package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author alexs
 */
public class ProductQueries {
    ArrayList<Product> products=new ArrayList<Product>();
    
    public boolean register(Product product) {
        products.add(product);
        return true;
    }

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

    public boolean delete(Product product) {
        for(int i=0;i<products.size();i++){
            if(product.getID()==products.get(i).getID()){
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product findByID(long id) throws Exception {
        for(int i=0;i<products.size();i++){
            if(id==products.get(i).getID()){
                return products.get(i);
            }
        }
        throw new Exception ("ERROR: No se encontro el producto");
    }
    
    public Product findByName(String name) throws Exception {
        for(int i=0;i<products.size();i++){
            if(name.equals(products.get(i).getProductName())){
                return products.get(i);
            }
        }
        throw new Exception ("ERROR: No se encontro el producto");
    }
    
}
