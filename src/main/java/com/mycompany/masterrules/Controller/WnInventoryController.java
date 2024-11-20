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

    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    private CafeteriaStorage cafeteriaStorage;
    private ObservableList<Product> productData;

    //Componentes de la vista
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

    @FXML
    private TableView<Product> tblInventory;
    @FXML
    private TableColumn<Product, String> colProductIDInventory;
    @FXML
    private TableColumn<Product, String> colProductNameInventory;
    @FXML
    private TableColumn<Product, String> colProductCategoryInventory;
    @FXML
    private TableColumn<Product, BigDecimal> colProductPriceInventory;
    @FXML
    private TableColumn<Product, BigDecimal> colProductVipPriceInventory;
    @FXML
    private TableColumn<Product, Integer> colProductStockInventory;
    @FXML
    private TableColumn<Product, Integer> colProductMinStockInventory;
    @FXML
    private TableColumn<Product, Integer> colProductMaxStockInventory;

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cafeteriaStorage = new CafeteriaStorage();
        productData = FXCollections.observableArrayList();
        tblInventory.setOnMouseClicked(event -> {
            if (event.getClickCount() > 1) {
                event.consume();
            }
        });
        configureTableColumns();
        loadProductsToInventory();
        tblInventory.setItems(productData);
    }

    private void loadProductsToInventory() {
        CafeteriaMenu cafeteriaMenu = new CafeteriaMenu();

        // Obtén todos los productos de la base de datos
        List<Product> products = cafeteriaMenu.getProducts();

        // Revisa cada producto para inicializar su StockInfo correctamente
        for (Product product : products) {
//            // Busca si ya existe StockInfo asociado al producto
//            Product stockInfo = cafeteriaStorage.getStockInfo(product);
//
//            if (stockInfo == null) {
//                // Si no existe, inicializa un nuevo StockInfo
//                stockInfo = new StockInfo(0, 0, 0); // Valores iniciales predeterminados
//                stockInfo.setProduct(product);
//                cafeteriaStorage.addProduct(product, stockInfo);
//            }
//
//            // Asegúrate de que la tabla de datos muestre este StockInfo
//            if (!productData.contains(stockInfo)) {
//                productData.add(stockInfo);
//            }
            if(!productData.contains(product)){
                productData.add(product);
            }
        }
    }


    private void configureTableColumns() {
        // Configurar las columnas para obtener datos de los métodos getter
        colProductIDInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getId()));
        colProductNameInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        colProductCategoryInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getType()));
        colProductPriceInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrice()));
        colProductVipPriceInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getVIPPrice()));
        colProductStockInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStockInfo().getCurrentStock()));
        colProductMinStockInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStockInfo().getMinStock()));
        colProductMaxStockInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStockInfo().getMaxStock()));
    }


    public void showScrEditInfo() {
        Product selectedProduct = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            showAlert("Error", "Por favor, selecciona un producto para editar.");
            return;
        }

        // Mostrar la información de StockInfo en los campos editables
        txtFieldStock.setText(String.valueOf(selectedProduct.getStockInfo().getCurrentStock()));
        txtFieldMinInv.setText(String.valueOf(selectedProduct.getStockInfo().getMinStock()));
        txtFieldMaxInv.setText(String.valueOf(selectedProduct.getStockInfo().getMaxStock()));

        // Mostrar la pantalla de edición
        scrEditInfo.setVisible(true);
        scrEditInfo.toFront();
    }

    public void exit() {
        scrEditInfo.setVisible(false);
    }

    @FXML
    public void buttonSaveInfo() {
        // Obtener el producto actual seleccionado desde la tabla
        Product selectedProduct = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            return;
        }
        try {
            // Obtener los valores ingresados en los campos de texto
            int newStock = Integer.parseInt(txtFieldStock.getText());
            int newMinStock = Integer.parseInt(txtFieldMinInv.getText());
            int newMaxStock = Integer.parseInt(txtFieldMaxInv.getText());

            // Validar los valores del stock
            stockValidation(newStock, newMinStock, newMaxStock);

            // Actualizar el StockInfo con los nuevos valores
            selectedProduct.getStockInfo().setCurrentStock(newStock);
            selectedProduct.getStockInfo().setMinStock(newMinStock);
            selectedProduct.getStockInfo().setMaxStock(newMaxStock);

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
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void stockValidation(int newStock, int newMinStock, int newMaxStock) throws IllegalArgumentException {
        // Validar que el stock actual esté dentro del rango permitido
        if (newStock < newMinStock || newStock > newMaxStock) {
            System.out.println("El stock actual no puede ser menor que el stock mínimo ni mayor que el stock máximo.");
            throw new IllegalArgumentException(
                    "El stock actual no puede ser menor que el stock mínimo ni mayor que el stock máximo."
            );
        }
    }

    @FXML
    private void displaySelected(javafx.scene.input.MouseEvent event) {
        // Obtener el producto seleccionado desde la tabla
        Product selectedProduct = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            return;
        }
        // Mostrar la información de StockInfo en los campos editables
        txtFieldStock.setText(String.valueOf(selectedProduct.getStockInfo().getCurrentStock()));
        txtFieldMinInv.setText(String.valueOf(selectedProduct.getStockInfo().getMinStock()));
        txtFieldMaxInv.setText(String.valueOf(selectedProduct.getStockInfo().getMaxStock()));

        // Asegurarse de que el producto esté seleccionado para editarlo
        scrEditInfo.setVisible(true);
        scrEditInfo.toFront();
    }

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if (source.equals(btnEditStockInfo)) {
            showScrEditInfo();
        } else if (source.equals(btnExit)) {
            exit();
        } else if (source.equals(btnSave)) {
            buttonSaveInfo();
        } else if (source.equals(btnScanProduct)) {
            displaySelected(null);
        }
    }

}