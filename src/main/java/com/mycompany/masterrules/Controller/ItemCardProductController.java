package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.cafeteria.Product;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
    private Label productPrice;

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

        // Se debe poner la imagen, pero por ahora no lo hare
        // String path="File:"+productData.getImage();
    }

    /**
     * Establece el nombre del producto en la tarjeta.
     * @param productName El nombre del producto.
     */
    public void setCardProductName(String productName) {
        this.productName.setText(productName);
    }

    /**
     * Establece el precio del producto en la tarjeta.
     * @param price El precio del producto.
     */
    public void setCardPrice(BigDecimal price) {
        productPrice.setText("$" + price);
    }

    /**
     * Establece la imagen del producto en la tarjeta.
     * @param path La ruta de la imagen.
     */
    public void setCardProductImage(String path) {
        Image image = new Image(path, 192, 139, false, true); // CAMBIAR EL TAMAÑO DE IMAGEN
        imageProduct.setImage(image);
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