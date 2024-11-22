package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.storage.CafeteriaStorage;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador de la ventana de Inventario
 * Gestiona la visualización y edición de productos en el inventario.
 *
 * @author Jimena Garcia
 */
public class WnInventoryController implements Initializable {

    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    /**
     * Almacenamiento de la cafetería.
     */
    private CafeteriaStorage cafeteriaStorage;

    /**
     * Lista observable de productos.
     */
    private ObservableList<Product> productData;

    /**
     * Lista filtrada de productos.
     */
    private FilteredList<Product> filteredData;

    //Componentes de la vista
    //-------------------------------------------------------------------------------------------

    // Botones
    @FXML
    private Button btnEditStockInfo;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnSave;

    // Paneles
    @FXML
    private AnchorPane scrEditInfo;
    @FXML
    private AnchorPane scrSeeClient;

    // Campos de texto
    @FXML
    private TextField txtFieldSearchProduct;
    @FXML
    private TextField txtFieldMinInv;
    @FXML
    private TextField txtFieldStock;
    @FXML
    private TextField txtFieldMaxInv;

    // Tabla
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

    /**
     * Inicializa el controlador después de que su raíz haya sido procesada.
     *
     * @param location La ubicación utilizada para resolver rutas relativas para el objeto raíz, o null si no se conoce la ubicación.
     * @param resources Los recursos utilizados para localizar el objeto raíz, o null si no se han localizado recursos.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cafeteriaStorage = new CafeteriaStorage();
        productData = FXCollections.observableArrayList();
        filteredData = new FilteredList<>(productData, p -> true);
        tblInventory.setOnMouseClicked(event -> {
            if (event.getClickCount() > 1) {
                event.consume();
            }
        });
        configureTableColumns();
        loadProductsToInventory();
        tblInventory.setItems(filteredData);
        setupSearchFilter();
    }

    /**
     * Carga los productos en el inventario desde el almacenamiento de la cafetería.
     */
    private void loadProductsToInventory() {
        productData.setAll(cafeteriaStorage.getProductStockTable().keySet());
    }

    /**
     * Configura las columnas de la tabla para mostrar los datos de los productos.
     */
    private void configureTableColumns() {
        colProductIDInventory.setReorderable(false);
        colProductIDInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getId()));
        colProductNameInventory.setReorderable(false);
        colProductNameInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        colProductCategoryInventory.setReorderable(false);
        colProductCategoryInventory.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getType()));
        colProductPriceInventory.setReorderable(false);
        colProductPriceInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getPrice()));
        colProductVipPriceInventory.setReorderable(false);
        colProductVipPriceInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getVIPPrice()));
        colProductStockInventory.setReorderable(false);
        colProductStockInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStockInfo().getCurrentStock()));
        colProductMinStockInventory.setReorderable(false);
        colProductMinStockInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStockInfo().getMinStock()));
        colProductMaxStockInventory.setReorderable(false);
        colProductMaxStockInventory.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().getStockInfo().getMaxStock()));
    }

    /**
     * Muestra la pantalla de edición de información de stock.
     */
    public void showScrEditInfo() {
        Product selectedProduct = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            showAlert("Error", "Por favor, selecciona un producto para editar.");
            return;
        }

        txtFieldStock.setText(String.valueOf(selectedProduct.getStockInfo().getCurrentStock()));
        txtFieldMinInv.setText(String.valueOf(selectedProduct.getStockInfo().getMinStock()));
        txtFieldMaxInv.setText(String.valueOf(selectedProduct.getStockInfo().getMaxStock()));

        scrEditInfo.setVisible(true);
        scrEditInfo.toFront();
    }

    /**
     * Oculta la pantalla de edición.
     */
    public void exit() {
        scrEditInfo.setVisible(false);
    }

    /**
     * Guarda la información de stock editada.
     */
    @FXML
    public void buttonSaveInfo() {
        Product selectedProduct = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            return;
        }
        try {
            int newStock = Integer.parseInt(txtFieldStock.getText());
            int newMinStock = Integer.parseInt(txtFieldMinInv.getText());
            int newMaxStock = Integer.parseInt(txtFieldMaxInv.getText());

            stockValidation(newStock, newMinStock, newMaxStock);

            selectedProduct.getStockInfo().setCurrentStock(newStock);
            selectedProduct.getStockInfo().setMinStock(newMinStock);
            selectedProduct.getStockInfo().setMaxStock(newMaxStock);
            cafeteriaStorage.editCurrentStock(selectedProduct, newStock);
            cafeteriaStorage.editMinStock(selectedProduct, newMinStock);
            cafeteriaStorage.editMaxStock(selectedProduct, newMaxStock);

            tblInventory.refresh();

            scrEditInfo.setVisible(false);
            txtFieldStock.clear();
            txtFieldMinInv.clear();
            txtFieldMaxInv.clear();

            scrSeeClient.setVisible(true);
        } catch (NumberFormatException e) {
            showAlert("Error", "Por favor, ingresa datos válidos en todos los campos.");
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        }
    }

    /**
     * Muestra una alerta con un mensaje de error.
     *
     * @param title El título de la alerta.
     * @param message El mensaje de la alerta.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Valida los valores de stock ingresados.
     *
     * @param newStock El nuevo valor de stock.
     * @param newMinStock El nuevo valor de stock mínimo.
     * @param newMaxStock El nuevo valor de stock máximo.
     * @throws IllegalArgumentException Si los valores de stock no son válidos.
     */
    private void stockValidation(int newStock, int newMinStock, int newMaxStock) throws IllegalArgumentException {
        if (0 > newMinStock || 0 > newStock || 0 > newMaxStock) {
            throw new IllegalArgumentException(
                    "El stock actual no puede ser menor que el stock mínimo ni mayor que el stock máximo."
            );
        }
    }

    /**
     * Configura el filtro de búsqueda para la tabla de inventario.
     */
    private void setupSearchFilter() {
        txtFieldSearchProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (product.getName().toLowerCase().contains(lowerCaseFilter) ||
                    product.getType().toLowerCase().contains(lowerCaseFilter) ||
                    product.getId().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
            tblInventory.refresh();
        });
    }

    /**
     * Muestra la información del producto seleccionado en los campos editables.
     *
     * @param event El evento de selección.
     */
    @FXML
    private void displaySelected(javafx.scene.input.MouseEvent event) {
        Product selectedProduct = tblInventory.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            return;
        }
        txtFieldStock.setText(String.valueOf(selectedProduct.getStockInfo().getCurrentStock()));
        txtFieldMinInv.setText(String.valueOf(selectedProduct.getStockInfo().getMinStock()));
        txtFieldMaxInv.setText(String.valueOf(selectedProduct.getStockInfo().getMaxStock()));

        scrEditInfo.setVisible(true);
        scrEditInfo.toFront();
    }

    /**
     * Maneja las acciones de los botones en la interfaz de usuario.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();

        if (source.equals(btnEditStockInfo)) {
            showScrEditInfo();
        } else if (source.equals(btnExit)) {
            exit();
        } else if (source.equals(btnSave)) {
            buttonSaveInfo();
        }
    }
}