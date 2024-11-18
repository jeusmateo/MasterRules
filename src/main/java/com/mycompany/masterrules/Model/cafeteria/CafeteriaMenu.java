package com.mycompany.masterrules.Model.cafeteria;

import com.mycompany.masterrules.Database.ProductDBManager;
import com.mycompany.masterrules.Model.users.UserAccount;

import java.util.ArrayList;
import java.util.NoSuchElementException;


public class CafeteriaMenu {

    private String title;
    private ArrayList<Product> products;//TODO cambie products a arraylist //no se si luego se requiera del conjunto de tipo de producto en un Set?
    private ArrayList<Combo> combos;//falta combos
    private final ProductDBManager productDBManager = new ProductDBManager();

    public CafeteriaMenu() {
        this.products = new ArrayList<Product>();
        this.combos = new ArrayList<Combo>();
    }

    public void addProductToMenu(Product product) {
        if (!isProductOnMenu(product.getId())) {
            products.add(product);
        } else {
            throw new IllegalArgumentException("ERROR: El producto ya existe");
        }
    }

    public void registerNewProduct(Product product) {//cambie el nombre del parametro
        productDBManager.save(product);
    }

    public void removeProductOnMenu(String productID) {
        for (int registeredProductCount = 0; registeredProductCount < products.size(); registeredProductCount++) {
            if (productID.equals(products.get(registeredProductCount).getId())) {
                products.remove(registeredProductCount);
                return;
            }
        }
        throw new IllegalArgumentException("El producto con ID " + productID + " no existe en el menú.");
    }


    public boolean isProductOnMenu(String productID){//continuo trabajando con esto
        for(Product registeredProduct : products){
            if(productID.equals(registeredProduct.getId())){
                return true;
            }
        }
        return false;
    }

    public boolean isProductNameTaken(String productName){ // TODO cambiar a isProductTaken es demasialdo informal
        for(Product registeredProduct : products){
            if(productName.equals(registeredProduct.getName())){
                return true;
            }
        }
        return false;
    }

    public void editProduct(Product product) {
        for (int registeredProductCount = 0; registeredProductCount < products.size(); registeredProductCount++) {
            if (product.getId().equals(products.get(registeredProductCount).getId())) {
                if (!product.getName().equals(products.get(registeredProductCount).getName())) {
                    if (!isProductNameTaken(product.getName())) {
                        products.get(registeredProductCount).setName(product.getName());
                    } else {
                        throw new IllegalArgumentException("ERROR: El nombre del producto ya está tomado");
                    }
                }
                products.get(registeredProductCount).setType(product.getType());
                products.get(registeredProductCount).setPrice(product.getPrice());
                products.get(registeredProductCount).setVIPPrice(product.getVIPPrice());
                return;
            }
        }
        throw new IllegalArgumentException("ERROR: El producto no existe");
    }


    public ArrayList<Product> getProductsByType(String productType) {
        ArrayList<Product> productsWithType = new ArrayList<>();

        for (Product product : products) {
            if (product.getType().equals(productType)) {
                productsWithType.add(product);
            }
        }

        if (productsWithType.isEmpty()) {
            throw new IllegalArgumentException("ERROR: El tipo de producto no existe en el menú");
        }

        return productsWithType;
    }


    public void addComboToMenu(Combo combo) {
        if (!isComboOnMenu(combo.getId() + "")) {
            combos.add(combo);
        } else {
            throw new IllegalArgumentException("ERROR: El combo ya existe");
        }
    }


    public void removeComboOnMenu(String comboID) {
        for (int registeredComboCount = 0; registeredComboCount < combos.size(); registeredComboCount++) {
            if (comboID.equals(combos.get(registeredComboCount).getId())) {
                combos.remove(registeredComboCount);
                return;
            }
        }
        throw new NoSuchElementException("ERROR: El combo no existe");
    }


    public void editCombo(Combo combo) {
        for (int registeredComboCount = 0; registeredComboCount < combos.size(); registeredComboCount++) {
            if (combo.getId().equals(combos.get(registeredComboCount).getId())) {
                if (!combo.getName().equals(combos.get(registeredComboCount).getName())) {
                    if (!isComboNameTaken(combo.getName())) {
                        combos.get(registeredComboCount).setName(combo.getName());
                    } else {
                        throw new IllegalArgumentException("ERROR: El nombre del combo ya existe");
                    }
                }
                combos.get(registeredComboCount).setProducts(combo.getProducts());
                combos.get(registeredComboCount).setPrice(combo.getPrice());
                combos.get(registeredComboCount).setVIPPrice(combo.getVIPPrice());
                return;
            }
        }
        throw new NoSuchElementException("ERROR: El combo no existe");
    }



    public boolean isComboOnMenu(String comboID){
        for(Combo registeredCombo : combos){
            if(comboID.equals(registeredCombo.getId()+"")){
                return true;
            }
        }
        return false;
    }
    

    public boolean isComboNameTaken(String comboName){ //TODO cambiar a isComboTaken es demasialdo informal
        for(Combo registeredProduct : combos){
            if(comboName.equals(registeredProduct.getName())){
                return true;
            }
        }
        return false;
    }
    

    public String getTitle() {
        return title;
    }

    public ArrayList<Product> getProducts() {    
        return products;
    }

    public ArrayList<Combo> getCombos() {
        return combos;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProducts(ArrayList<Product> products) {    
        this.products = products;
    }

    public void setCombos(ArrayList<Combo> combos) {
        this.combos = combos;
    }
    
}
