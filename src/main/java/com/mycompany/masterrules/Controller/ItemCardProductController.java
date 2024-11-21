package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.cafeteria.Product;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Controlador para la tarjeta de producto en la interfaz de usuario.
 * Implementa la interfaz Initializable para inicializar los componentes de la vista.
 */
public class ItemCardProductController implements Initializable {
    // Atributos de la clase
    // -------------------------------------------------------------------------------------------

    private Product productData; // Probablemente luego se necesite el productID en cardProduct para el Inventario
    private ProductSelectionListener selectionListener;


    // Componentes de la vista de la tarjeta de producto
    // -------------------------------------------------------------------------------------------

    @FXML
    private Button productName;

    @FXML
    private ImageView imageProduct;

    @FXML
    private Label lbProductPrice;

    @FXML
    private Label lbProductVipPrice;

    // Métodos de la clase
    // -------------------------------------------------------------------------------------------

    /**
     * Establece los datos del producto en la tarjeta.
     * @param productData Los datos del producto.
     */
    public void setProductDataToCard(Product productData) {
        this.productData = productData;

        setCardProductName(productData.getName());
        setCardPrice(productData.getPrice());
        setCardVipPrice(productData.getVIPPrice());
        setCardProductImage(productData.getProductImage());
    }


    public void setCardProductName(String productName) {
        this.productName.setText(productName);
    }

    public void setCardPrice(BigDecimal price) {
        lbProductPrice.setText("Precio: $" + price);
    }

    public void setCardVipPrice(BigDecimal vipPrice) {
        lbProductVipPrice.setText("Precio Vip: $" + vipPrice);
    }

    public void setCardProductImage(String productImage) {
        String imagePath = productImage;
        if (imagePath != null && !imagePath.isEmpty()) {
            File imageFile = new File("src/main/resources/" + imagePath);
            if (imageFile.exists()) {
                imageProduct.setImage(new javafx.scene.image.Image(imageFile.toURI().toString()));
            }
        }
    }

    /**
     * Inicializa los componentes de la vista.
     * @param url La URL de la vista.
     * @param rb El recurso de la vista.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageProduct.setOnMouseClicked(event -> onProductImageClick());
    }

    /**
     * Maneja el evento de clic en la imagen del producto.
     */
    @FXML
    private void onProductImageClick() {
        if (selectionListener != null && productData != null) {
            selectionListener.onProductSelected(productData);
        }
    }

    /**
     * Maneja el evento de acción en la tarjeta de producto.
     * @param event El evento de acción.
     */
    @FXML
    private void eventAction(ActionEvent event) {
        if (selectionListener != null && productData != null) {
            try {
                selectionListener.onProductSelected(productData);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Establece el listener de selección de producto.
     * @param listener El listener de selección de producto.
     */
    public void setSelectionListener(ProductSelectionListener listener) {
        this.selectionListener = listener;
    }
}