package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Database.ProductDatabase;
import com.mycompany.masterrules.Model.cafeteria.CafeteriaMenu;
import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.storage.CafeteriaStorage;
import com.mycompany.masterrules.Model.storage.StockInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WnProductsController implements Initializable, ProductSelectionListener {

private CafeteriaMenu cafeteriaMenu = new CafeteriaMenu();

    @FXML
    private Button btnAddCategoryCustomeCombo;

    @FXML
    private Button btnContinueCustomCombo;

    @FXML
    private Button btnContinueDefinedCombo;


    @FXML
    private Button btnCustomCombo;

    @FXML
    private Button btnDefinedCombo;

    @FXML
    private Button btnImportImage;


    @FXML
    private Button buttonConfirmCombo;

    @FXML
    private Button buttonCreateCombo;

    @FXML
    private Button buttonIconSearchFood;


    @FXML
    private FlowPane flowPaneMenuCards;


    @FXML
    private ImageView imgCombo;

    @FXML
    private AnchorPane scrCreateComboFinalStep;

    @FXML
    private AnchorPane scrCreateComboStart;

    @FXML
    private AnchorPane scrCreateComboStepTwo;

    @FXML
    private AnchorPane scrCreateCustomeCombo;

    @FXML
    private AnchorPane scrCreateDefinedCombo;

    @FXML
    protected ScrollPane scrollPaneMenuCreatorCombo;


    //Tabla para combos
    @FXML
    private Tab tabCombos;

    @FXML
    private Tab tabCreateCombo;


    @FXML
    private Tab tabEditCombo;


    @FXML
    private TableView<?> tableComboProducts;

    @FXML
    private TableColumn<?, ?> tableProductCombo;


    @FXML
    private TextField textFieldName111;


    @FXML
    private TextField txtFieldIDCombo;

    @FXML
    private TextField txtFieldNameCombo;

    @FXML
    private TextField txtFieldPriceCombo;

    @FXML
    private TextField txtFieldVIPPriceCombo;

    @FXML
    private AnchorPane wnCreateComboStepOne;


    @FXML
    private AnchorPane wnCreateFood;

    @FXML
    private AnchorPane wnEditCombo;


    @FXML
    private AnchorPane wnEditFood;

    @FXML
    private AnchorPane wnFood;
    @FXML
    private TableView<Product> tblFood;
    @FXML
    private TableView<Product> tblFood2;
    @FXML
    private TableView<Combo> tblCombos;
    @FXML
    private TableColumn<Combo, String> colComboID;
    @FXML
    private TableColumn<Combo, String> colComboName;
    @FXML
    private TableColumn<Combo, String> colComboPrice;
    @FXML
    private TableColumn<Combo, String> colComboVipPrice;
    @FXML
    private AnchorPane scrChooseComboEdition;
    @FXML
    private Button btnEditDefinedCombo;
    @FXML
    private Button btnEditCustomCombo;
    @FXML
    private AnchorPane scrEditDefinedCombo;
    @FXML
    private AnchorPane scrEditCustomeCombo;
    @FXML
    private Button btnAddCategoryCustomeCombo1;
    @FXML
    private Button btnContinueCustomCombo1;
    @FXML
    private TextField txtProductId_tabCreate;
    @FXML
    private TextField txtProductName_tabCreate;
    @FXML
    private TextField txtProductPrice_tabCreate;
    @FXML
    private TextField txtProductVIpPrice_tabCreate;
    @FXML
    private TextField txtProductType_tabCreate;
    @FXML
    private Button btnCreateProduct;
    @FXML
    private Tab tabProduct;
    @FXML
    private Tab tabCreateProduct;
    @FXML
    private ImageView imgProduct_tabCreate;
    @FXML
    private Button btnImportImage_tabCreate;

    @FXML
    private TextField txtSearchProduct_tabCreate;
    @FXML
    private Button btnSearch_tabCreate;
    @FXML
    private TableColumn<Product, String> colProductId_tabCreate;
    @FXML
    private TableColumn<Product, String> colProductName_tabCreate;
    @FXML
    private TableColumn<Product, String> colProductType_tabCreate;
    @FXML
    private TableColumn<Product, BigDecimal> colProductPrice_tabCreate;
    @FXML
    private TableColumn<Product, BigDecimal> colProductVipPrice_tabCreate;
    @FXML
    private Tab tabEditProduct;
    @FXML
    private TextField txtSearchProduct_tabEdit;
    @FXML
    private Button btnSearch2;
    @FXML
    private TableColumn<?, ?> colProductId_tabEdit;
    @FXML
    private TableColumn<?, ?> colProductName_tabEdit;
    @FXML
    private TableColumn<?, ?> colProductType_tabEdit;
    @FXML
    private TableColumn<?, ?> colProductPrice_tabEdit;
    @FXML
    private TableColumn<?, ?> colProductVipPrice_tabEdit;
    @FXML
    private TextField txtProductName_tabEdit;
    @FXML
    private TextField txtProductType_tabEdit;
    @FXML
    private ImageView imgProduct_tabEdit;
    @FXML
    private Button btnImporImage_tabEdit;
    @FXML
    private TextField txtProductVipPrice_tabEdit;
    @FXML
    private TextField txtProductPrice_tabEdit;
    @FXML
    private Button btnEditProduct;
    @FXML
    private Button btnDeleteProduct;


    private CafeteriaStorage cafeteriaStorage = new CafeteriaStorage();

    public void displayMenuCards() {
        CafeteriaMenu menu = new CafeteriaMenu();
        List<Product> productsOnMenu = menu.getProducts();
        ObservableList<Product> productDataList = FXCollections.observableArrayList(productsOnMenu);
        ObservableList<Product> comboDataList = FXCollections.observableArrayList();


        for (Product currentProduct : productDataList) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/com/mycompany/masterrules/itemCardProduct.fxml"));
                AnchorPane pane = load.load();
                ItemCardProductController cardController = load.getController();

                cardController.setProductDataToCard(currentProduct);
                cardController.setSelectionListener(this);

                scrollPaneMenuCreatorCombo.getChildren().add(pane);

                /*
                pane.setOnMousePressed(event -> {
                    pane.setStyle("-fx-background-color: lightgray");
                    //pane.setStyle("-fx-background-color: white");
                });
                */
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        List<Product> prueba = new ArrayList<>();
        BigDecimal prueba1 = new BigDecimal("30");
        BigDecimal prueba2 = new BigDecimal("15");
        Combo combo = new Combo("Chepo", prueba, prueba1, prueba2);
        comboDataList.add(combo);
    }

    @FXML
    void setScrCreateComboFinalStep(MouseEvent event) {

        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(false);
        scrCreateDefinedCombo.setVisible(false);
        scrCreateComboFinalStep.setVisible(true);

    }

    @FXML
    void setScrCreateComboStepTwo(MouseEvent event) {
        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(true);
    }

    @FXML
    void setScrCreateCustomeCombo(MouseEvent event) {
        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(false);
        scrCreateCustomeCombo.setVisible(true);

    }

    @FXML
    void setScrCreateDefinedCombo(MouseEvent event) {
        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(false);
        scrCreateDefinedCombo.setVisible(true);

    }

    //private ObservableList<Product> observableProductList = FXCollections.observableArrayList();

    private void configColumns() {
        colProductId_tabCreate.setReorderable(false);
        colProductId_tabCreate.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProductName_tabCreate.setReorderable(false);
        colProductName_tabCreate.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductType_tabCreate.setReorderable(false);
        colProductType_tabCreate.setCellValueFactory(new PropertyValueFactory<>("type"));
        colProductPrice_tabCreate.setReorderable(false);
        colProductPrice_tabCreate.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductVipPrice_tabCreate.setReorderable(false);
        colProductVipPrice_tabCreate.setCellValueFactory(new PropertyValueFactory<>("VIPPrice"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Configurar las columnas de la tabla

        configColumns();
        // Obtener los productos de la base de datos
        ProductDatabase chepobd = new ProductDatabase();
        List<Product> chepo = chepobd.readAll();

        // Crear una lista observable con los productos de la base de datos
        ObservableList<Product> observableProductList = FXCollections.observableArrayList(chepo);

        // Asignar la lista observable a la tabla
        tblFood.setItems(observableProductList);

        // Añadir listener para la selección de productos en la tabla
        tblFood.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> displaySelection());
    }

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();
        try {
            if (source.equals(btnCreateProduct)) {
                String id = txtProductId_tabCreate.getText();
                String name = txtProductName_tabCreate.getText();
                String type = txtProductType_tabCreate.getText();
                BigDecimal price = new BigDecimal(txtProductPrice_tabCreate.getText());
                BigDecimal vipPrice = new BigDecimal(txtProductVIpPrice_tabCreate.getText());

                Product product = new Product(id, name, type, price, vipPrice);
                cafeteriaMenu.addProductToMenu(product);

                //StockInfo stockInfo = new StockInfo(0, 0, 0);
                //cafeteriaStorage.addProduct(product, stockInfo);
                clearTextFields(
                        txtProductId_tabCreate,
                        txtProductName_tabCreate,
                        txtProductType_tabCreate,
                        txtProductPrice_tabCreate,
                        txtProductVIpPrice_tabCreate
                );
                updateProductTable();
                //List<Product> chepo = cafeteriaMenu.getProducts();
                //ObservableList<Product> observableProductList = FXCollections.observableArrayList(chepo);
            }else if (source.equals(btnDeleteProduct)) {
                Product selectedProduct = tblFood.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    cafeteriaMenu.removeProductOnMenu(selectedProduct.getId());
                    //cafeteriaStorage.removeProduct(selectedProduct);
                    updateProductTable();
                }
            } else if (source.equals(btnEditProduct)) {
                Product selectedProduct = tblFood.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    String newName = txtProductName_tabEdit.getText();
                    String newType = txtProductType_tabEdit.getText();
                    BigDecimal newPrice = new BigDecimal(txtProductPrice_tabEdit.getText());
                    BigDecimal newVipPrice = new BigDecimal(txtProductVipPrice_tabEdit.getText());

                    selectedProduct.setName(newName);
                    selectedProduct.setType(newType);
                    selectedProduct.setPrice(newPrice);
                    selectedProduct.setVIPPrice(newVipPrice);

                    cafeteriaMenu.editProduct(selectedProduct);
                    updateProductTable();
                }
            }
        } catch (Exception e) {
            System.err.println("Error al registrar el producto: " + e.getMessage());
        }
    }



    private void updateProductTable() {
        // Obtener la lista actualizada de productos desde el modelo
        List<Product> updatedProducts = cafeteriaMenu.getProducts();

        // Crear una nueva lista observable con los productos actualizados
        ObservableList<Product> observableProductList = FXCollections.observableArrayList(updatedProducts);

        // Asignar la nueva lista observable a la tabla
        tblFood.setItems(observableProductList);
        tblFood.refresh();
    }


    @FXML
    private void displaySelection() {
        // Obtener el producto seleccionado
        Product selectedProduct = tblFood.getSelectionModel().getSelectedItem();

        // Verificar que hay un producto seleccionado
        if (selectedProduct != null) {
            // Establecer los valores en los campos de edición
            txtProductName_tabEdit.setText(selectedProduct.getName());
            txtProductType_tabEdit.setText(selectedProduct.getType());
            txtProductPrice_tabEdit.setText(selectedProduct.getPrice().toString());
            txtProductVipPrice_tabEdit.setText(selectedProduct.getVIPPrice().toString());
        }
    }

    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();  // Limpia el contenido de cada TextField
        }
    }


    @Override
    public void onProductSelected(Product product) {

    }
}
