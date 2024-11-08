/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.Product;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author campv
 */
public class ItemCardProductController implements Initializable{
    @FXML
    private Button productName;

    @FXML
    private ImageView imageProduct;

    @FXML
    private Label productPrice;
    
    //OTROS OBJETOS
    //-------------------------------------------------------------------------------------------
    private Product productData; //probablemente luego se necesite el productID en cardProduct para el Inventario
    
    public void setProductDataToCard(Product productData){
        this.productData=productData;
        
        setCardProductName(productData.getProductName());
        setCardPrice(productData.getPrice());
        
        //Se debe poner la imagen, pero por ahora no lo hare
        //String path="File:"+productData.getImage();
    }
    
    public void setCardProductName(String productName){
        this.productName.setText(productName);
    }
    
    public void setCardPrice(BigDecimal price){
        productPrice.setText("$"+price);
    }
    
    public void setCardProductImage(String path){
        Image image = new Image(path,192,139,false,true);//CAMBIAR EL TAMAÑO DE IMAGEN
        imageProduct.setImage(image);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
