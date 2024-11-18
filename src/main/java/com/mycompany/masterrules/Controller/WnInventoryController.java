package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.cafeteria.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * Controlador de la ventana de Inventario
 * @author Jimena Garcia
 */

//TODO ¿Todos los atributos se usan? ¿Cuales se pueden eliminar?
public class WnInventoryController implements Initializable {

    //COMPONENTES DE LA VENTANA DE INVENTARIO
    //-------------------------------------------------------------------------------------------

    @FXML
    private Button btnEditInfo;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnScanProduct;

    @FXML
    private AnchorPane scrEditInfo;

    @FXML
    private AnchorPane scrSeeClient;

    @FXML
    private TextField txtFieldMinInv;

    @FXML
    private TextField txtFieldStock;

    @FXML
    private TextField txtFieldMaxInv;

    // metodos de la ventana de inventario
    //-------------------------------------------------------------------------------------------
    @FXML
    private TableView<Product> tblInventory;
    @FXML
    private TableColumn<Product, String> colProductID;
    @FXML
    private TableColumn<Product, String> colProductName;
    @FXML
    private TableColumn<Product, String> colProductCategory;
    @FXML
    private TableColumn<Product, String> colProductPrice;
    @FXML
    private TableColumn<Product, String> colProductVipPrice;
    @FXML
    private TableColumn<Product, String> colProductStock; // Objeto incorrecto, en productos no almacenamos esta informacion
    @FXML
    private TableColumn<Product, String> colProductMinStock; // Objeto incorrecto, en productos no almacenamos esta informacion
    @FXML
    private TableColumn<Product, String> colProductMaxStock; // Objeto incorrecto, en productos no almacenamos esta informacion

    //TODO esto
    public void displaySelected(MouseEvent event) {
    //Product productSelected =tblInventory.getSelectionModel().getSelectedItem();
    //poner la logica de eso, se supone que esto te entrega el cosito seleciconado, igual seria bueno cambiar el de los demas metodos porque hay que hacerlo bien gggggggg
    }
    /**
     * Muestra la ventana de edicion de informacion
     */
    @FXML
    public void showScrEditInfo(){
        scrEditInfo.setVisible(true);
        scrEditInfo.toFront();
    }

    /**
     * Muestra la ventana de ver cliente
     */
    @FXML
    public void exit() {
        txtFieldMinInv.clear();
        txtFieldStock.clear();
        txtFieldMaxInv.clear();
        scrEditInfo.setVisible(false);
    }


    @FXML
    public void buttonSaveInfo(){
        scrEditInfo.setVisible(false);
        scrSeeClient.setVisible(true);
        txtFieldMinInv.clear();
        txtFieldStock.clear();
        txtFieldMaxInv.clear();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void displaySelected(javafx.scene.input.MouseEvent event) {
    }
}