package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.cafeteria.CafeteriaMenu;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.storage.CafeteriaStorage;
import com.mycompany.masterrules.Model.storage.StockInfo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Controlador de la ventana de Inventario
 * @author Jimena Garcia
 */

//TODO ¿Todos los atributos se usan? ¿Cuales se pueden eliminar?
public class WnInventoryController implements Initializable {

    //COMPONENTES DE LA VENTANA DE INVENTARIO
    //-------------------------------------------------------------------------------------------

    @FXML
    private Button btnEditStockInfo;

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
    private TableView<StockInfo> tblInventory;
    @FXML
    private TableColumn<StockInfo, String> colProductIDInventory;
    @FXML
    private TableColumn<StockInfo, String> colProductNameInventory;
    @FXML
    private TableColumn<StockInfo, String> colProductCategoryInventory;
    @FXML
    private TableColumn<StockInfo, BigDecimal> colProductPriceInventory;
    @FXML
    private TableColumn<StockInfo, BigDecimal> colProductVipPriceInventory;
    @FXML
    private TableColumn<StockInfo, String> colProductStockInventory;
    @FXML
    private TableColumn<StockInfo, Integer> colProductMinStockInventory;
    @FXML
    private TableColumn<StockInfo, Integer> colProductMaxStockInventory;


    private CafeteriaStorage cafeteriaStorage;
    private ObservableList<StockInfo> stockData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cafeteriaStorage = new CafeteriaStorage();
        stockData = FXCollections.observableArrayList();

        configureTableColumns();
        loadProductsToInventory();
        tblInventory.setItems(stockData);
    }

    private void loadProductsToInventory() {
        CafeteriaMenu cafeteriaMenu = new CafeteriaMenu();

        // Obtén todos los productos de la base de datos
        List<Product> products = cafeteriaMenu.getProducts();

        // Crea StockInfo para cada producto y añádelo a la lista observable
        for (Product product : products) {
            StockInfo stockInfo = new StockInfo(0, 0, 0);
            stockInfo.setProduct(product);
            cafeteriaStorage.addProduct(product, stockInfo);
            stockData.add(stockInfo);
        }
    }





    private void configureTableColumns() {
        // Configurar las columnas para obtener datos de los métodos getter
        colProductIDInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getProduct().getId()));
        colProductNameInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getProduct().getName()));
        colProductCategoryInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getProduct().getType()));
        colProductPriceInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getProduct().getPrice()));
        colProductVipPriceInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getProduct().getVIPPrice()));
        colProductStockInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(data.getValue().getCurrentStock())));
        colProductMinStockInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getMinStock()));
        colProductMaxStockInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getMaxStock()));
    }


    public void showScrEditInfo(){
        scrEditInfo.setVisible(true);
        scrEditInfo.toFront();
    }

    public void exit() {
        txtFieldMinInv.clear();
        txtFieldStock.clear();
        txtFieldMaxInv.clear();
        scrEditInfo.setVisible(false);
    }

    @FXML
    public void buttonSaveInfo() {
        // Obtener el stockInfo seleccionado desde la tabla
        StockInfo selectedStockInfo = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedStockInfo != null) {
            try {
                // Obtener los valores ingresados en los campos de texto
                int newStock = Integer.parseInt(txtFieldStock.getText());
                int newMinStock = Integer.parseInt(txtFieldMinInv.getText());
                int newMaxStock = Integer.parseInt(txtFieldMaxInv.getText());

                // Actualizar el StockInfo con los nuevos valores
                selectedStockInfo.setCurrentStock(newStock);
                selectedStockInfo.setMinStock(newMinStock);
                selectedStockInfo.setMaxStock(newMaxStock);

                // Refrescar la tabla para mostrar los nuevos valores
                tblInventory.refresh();

                // Ocultar la pantalla de edición y limpiar los campos
                scrEditInfo.setVisible(false);
                txtFieldStock.clear();
                txtFieldMinInv.clear();
                txtFieldMaxInv.clear();

                // Volver a la vista de clientes
                scrSeeClient.setVisible(true);
            } catch (NumberFormatException e) {
                showAlert("Error", "Por favor, ingresa datos válidos en todos los campos.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void displaySelected(javafx.scene.input.MouseEvent event) {
        // Obtener el producto seleccionado desde la tabla
        StockInfo selectedStockInfo = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedStockInfo != null) {
            // Mostrar la información de StockInfo en los campos editables
            txtFieldStock.setText(String.valueOf(selectedStockInfo.getCurrentStock()));
            txtFieldMinInv.setText(String.valueOf(selectedStockInfo.getMinStock()));
            txtFieldMaxInv.setText(String.valueOf(selectedStockInfo.getMaxStock()));

            // Asegurarse de que el producto esté seleccionado para editarlo
            scrEditInfo.setVisible(true);
            scrEditInfo.toFront();
        }
    }

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        if (event.equals(btnEditStockInfo)) {
            showScrEditInfo();
        } else if (event.equals(btnExit)) {
            exit();
        } else if (event.equals(btnSave)) {
            buttonSaveInfo();
        } else if (event.equals(btnScanProduct)) {
            displaySelected(null);
        }
    }

}