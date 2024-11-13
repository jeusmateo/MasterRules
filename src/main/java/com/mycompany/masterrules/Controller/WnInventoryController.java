package com.mycompany.masterrules.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controlador de la ventana de Inventario
 * @author Jimena Garcia
 */


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


    /**
     * Muestra la ventana de edicion de informacion
     */
    public void showScrEditInfo(){
        scrEditInfo.setVisible(true);
        scrEditInfo.toFront();
    }

    /**
     * Muestra la ventana de ver cliente
     */
    public void exit() {
        txtFieldMinInv.clear();
        txtFieldStock.clear();
        txtFieldMaxInv.clear();
        scrEditInfo.setVisible(false);
    }


    public void saveInfo(){
        scrEditInfo.setVisible(false);
        scrSeeClient.setVisible(true);
        txtFieldMinInv.clear();
        txtFieldStock.clear();
        txtFieldMaxInv.clear();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}