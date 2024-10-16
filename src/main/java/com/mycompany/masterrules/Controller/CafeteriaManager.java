/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.CafeteriaMenu;
import com.mycompany.masterrules.Model.CafeteriaStorage;
import com.mycompany.masterrules.Model.Combo;
import com.mycompany.masterrules.Model.Product;
import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author alexs
 */
public class CafeteriaManager {
    private CafeteriaMenu menu;
    private CafeteriaStorage storage;

    public CafeteriaManager(CafeteriaMenu menu, CafeteriaStorage storage) {
        this.menu = menu;
        this.storage = storage;
    }
    
    public void createProduct(){
        Scanner scanStr=new Scanner(System.in);
        Scanner scanInt=new Scanner(System.in);
        Scanner scanLong=new Scanner(System.in);
        
        System.out.println("Dame el ID(Código de barras u otro) del producto:");
        long id=scanLong.nextLong();
        
        System.out.println("Dame el nombre del producto:");
        String name=scanStr.nextLine();
        
        System.out.println("Dame el tipo del producto:");
        String type=scanStr.nextLine();//problemente esta termine con un conjunto de opciones cerrado de tipo
        
        System.out.println("Dame el precio del producto:");
        BigDecimal price= new BigDecimal(scanStr.nextLine());
        
        System.out.println("Dame el precio preferencial del producto:");
        BigDecimal VIPprice= new BigDecimal(scanStr.nextLine());
        
        System.out.println("¿Se guarda en el inventario? 1)Si 2)No");
        int opcion=scanInt.nextInt();
        
        Product newProduct;
        newProduct=new Product(id,name,type,price,VIPprice);
        
        switch(opcion){
            case 1:
                System.out.println("Dame la cantidad que se tiene en el inventario:");
                int quantity=scanInt.nextInt();
                
                storage.addProduct(newProduct.getID()+"", quantity);
                menu.addProduct(newProduct);
                break;
            case 2:
                menu.addProduct(newProduct);
                break;
            default:
                System.out.println("ERROR: Opción no válida");
                break;
        }
    }
    
    public void deleteProduct(Product product){
        menu.removeProduct(product.getProductName());
        storage.removeProduct(product.getID()+"");
    }
    
    public void editProduct(Product product){
        //falta esto, creo que necesitamos en menu y storage un método de actualizar (upgradeProduct)
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
}
