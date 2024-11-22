package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.cafeteria.CafeteriaMenu;
import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.storage.CafeteriaStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;

public class WnProductsController implements Initializable, ProductSelectionListener {

    private CafeteriaMenu cafeteriaMenu = new CafeteriaMenu();
    private ObservableList<Product> selectedProductsForCombo;
    private FilteredList<Product> filteredProductList;
    private FilteredList<Combo> filteredComboList;
    private String selectedImagePath;

    @FXML
    private TableView<Product> tblSelectedProductsForCombo;
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
    private Button btnImportComboImageCreate;


    @FXML
    private Button btnConfirmCombo;

    @FXML
    private Button btnProcessCreateCombo;

    @FXML
    private Button buttonIconSearchFood;

    @FXML
    private Button btnCreateCombo;

    @FXML
    private Button btnEditCombo;

    @FXML
    private Button btnImportComboImageEdit;

    @FXML
    private Button btnDeleteCombo;


    @FXML
    private FlowPane flowPaneMenuCards = new FlowPane();


    @FXML
    private ImageView imgComboCreate;

    @FXML
    private ImageView imgComboEdit;

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
    private TextField txtFieldNameCombo;

    @FXML
    private TextField txtFieldPriceCombo;

    @FXML
    private TextField txtFieldVIPPriceCombo;

    @FXML
    private TextField txtFieldEditComboName;

    @FXML
    private TextField txtFieldEditComboVipPrice;

    @FXML
    private TextField txtFieldEditComboPrice;

    @FXML
    private AnchorPane scrCreateComboStepOne;


    @FXML
    private AnchorPane scrCreateFood;

    @FXML
    private AnchorPane scrEditCombo;


    @FXML
    private AnchorPane scrEditFood;

    @FXML
    private AnchorPane scrFood;
    @FXML
    private TableView<Product> tblFood;
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
    private TextField txtSearchProduct;
    @FXML
    private TextField txtSearchCombo;
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
    private TextField txtProductName_tabEdit;
    @FXML
    private TextField txtProductType_tabEdit;
    @FXML
    private ImageView imgProduct_tabEdit;

    @FXML
    private Button btnImportImage_tabEdit;
    @FXML
    private TextField txtProductVipPrice_tabEdit;
    @FXML
    private TextField txtProductPrice_tabEdit;
    @FXML
    private Button btnEditProduct;
    @FXML
    private Button btnDeleteProduct;

    @FXML
    private AnchorPane scrComboTable;
    @FXML
    private AnchorPane scrMenuCards;


    private CafeteriaStorage cafeteriaStorage = new CafeteriaStorage();
    @FXML
    private TableColumn<Product, String> colProductSelectedName;
    @FXML
    private TableColumn<Product, BigDecimal> colProductSelectedPrice;
    @FXML
    private TableColumn<Product, BigDecimal> colProductSelectedVipPrice;
    @FXML
    private Button btnRemoveProductOnCombo;


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

                flowPaneMenuCards.getChildren().add(pane);

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
//
    }

    @FXML
    void setScrCreateComboFinalStep(MouseEvent event) {
        scrComboTable.setVisible(false);
        scrMenuCards.setVisible(false);

        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(false);
        scrCreateDefinedCombo.setVisible(true);
        scrCreateComboFinalStep.setVisible(true);
        btnContinueDefinedCombo.setVisible(false);

    }

    @FXML
    void setBtnBackToScrMenuCard() {
        scrCreateComboFinalStep.setVisible(false);
        scrComboTable.setVisible(false);
        scrMenuCards.setVisible(true);
        btnContinueDefinedCombo.setVisible(true);
    }

    @FXML
    private void btnBackToCreateCombo() {
        scrCreateComboStart.setVisible(true);
        scrComboTable.setVisible(true);

        scrCreateComboStepTwo.setVisible(false);
        scrCreateDefinedCombo.setVisible(false);
        tabEditCombo.setDisable(false);

        clearTextFields(txtFieldNameCombo, txtFieldPriceCombo, txtFieldVIPPriceCombo);
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
        scrComboTable.setVisible(false);
        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(false);
        scrCreateDefinedCombo.setVisible(true);
        tabEditCombo.setDisable(true);
    }

    //private ObservableList<Product> observableProductList = FXCollections.observableArrayList();

    private void configColumns() {
        //

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

    private void configTableColumnsCombo() {
        colComboID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colComboID.setReorderable(false);
        colComboID.setResizable(false);
        colComboName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colComboName.setReorderable(false);
        colComboName.setResizable(false);
        colComboPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colComboPrice.setReorderable(false);
        colComboVipPrice.setCellValueFactory(new PropertyValueFactory<>("VIPPrice"));
        colComboVipPrice.setReorderable(false);
    }

    public String copyImageToResources(File chosenImage) {
        try {
            // Ruta del directorio destino dentro de los recursos
            String resourcesPath = "src/main/resources/productImages";
            File directory = new File(resourcesPath);

            // Crear el directorio si no existe
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Generar un nombre único para la imagen
            String imageName = System.currentTimeMillis() + "_" + chosenImage.getName();
            File destinationFile = new File(directory, imageName);

            // Copiar la imagen al directorio destino
            Files.copy(chosenImage.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Retornar el path relativo para almacenamiento
            return "productImages/" + imageName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void importProductImage(ImageView productImage) {
        FileChooser fileChooser = new FileChooser();
        // Establecer filtro para imágenes
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Abrir archivo de imagen", "*.png", "*.jpg", "*.jpeg")
        );

        // Mostrar el cuadro de diálogo de selección de archivo
        File chosenImage = fileChooser.showOpenDialog(null);
        if (chosenImage != null) {
            // Copiar la imagen a resources/productImages y obtener el path relativo
            String relativePath = copyImageToResources(chosenImage);
            if (relativePath != null) {
                selectedImagePath = relativePath;

                // Establecer la imagen en el ImageView
                productImage.setImage(new javafx.scene.image.Image(chosenImage.toURI().toString()));
            } else {
                System.err.println("Error al copiar la imagen.");
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Combo> combosList = cafeteriaMenu.getCombos();
        ObservableList<Combo> comboDataList = FXCollections.observableArrayList(combosList);
        configTableColumnsCombo();
        tblCombos.setItems(comboDataList);

        selectedProductsForCombo = FXCollections.observableArrayList();
        tblSelectedProductsForCombo.setItems(selectedProductsForCombo);

        colProductSelectedName.setReorderable(false);
        colProductSelectedName.setResizable(false);
        colProductSelectedName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colProductSelectedPrice.setReorderable(false);
        colProductSelectedPrice.setResizable(false);
        colProductSelectedPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductSelectedVipPrice.setReorderable(false);
        colProductSelectedVipPrice.setResizable(false);
        colProductSelectedVipPrice.setCellValueFactory(new PropertyValueFactory<>("VIPPrice"));

        // Configurar las columnas de la tabla
        displayMenuCards();
        configColumns();
        // Obtener los productos de la base de datos
        List<Product> productsForTable = cafeteriaMenu.getProducts();

        // Crear una lista observable con los productos de la base de datos
        ObservableList<Product> observableProductList = FXCollections.observableArrayList(productsForTable);

        // Asignar la lista observable a la tabla
        tblFood.setItems(observableProductList);
        flowPaneMenuCards.prefWidthProperty().bind(scrollPaneMenuCreatorCombo.widthProperty());
        // Añadir listener para la selección de productos en la tabla
        tblFood.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> displayProductSelection());
        tblCombos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> displayComboSelection());
        setupSearchFilters();
    }

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource();
        try {
            if (source.equals(btnCreateProduct)) {
                handleCreateProduct();
            } else if (source.equals(btnDeleteProduct)) {
                handleDeleteProduct();
            } else if (source.equals(btnEditProduct)) {
                handleEditProduct();
            } else if (source.equals(btnImportImage_tabCreate)) {
                importProductImage(imgProduct_tabCreate);
            } else if (source.equals(btnImportImage_tabEdit)) {
                importProductImage(imgProduct_tabEdit);
            } else if (source.equals(btnImportComboImageEdit)) {
                importProductImage(imgComboEdit);
            } else if (source.equals(btnImportComboImageCreate)) {
                importProductImage(imgComboCreate);
            } else if (source.equals(btnCreateCombo)) {
                handleCreateCombo();
            } else if (source.equals(btnDeleteCombo)) {
                handleDeleteCombo();
            } else if (source.equals(btnEditCombo)) {
                handleEditCombo();
            }
        } catch (Exception e) {
            System.err.println("Error al registrar el producto: " + e.getMessage());
        }
    }

    private void handleCreateProduct() {
        String id = txtProductId_tabCreate.getText();
        String name = txtProductName_tabCreate.getText();
        String type = txtProductType_tabCreate.getText();
        BigDecimal price = new BigDecimal(txtProductPrice_tabCreate.getText());
        BigDecimal vipPrice = new BigDecimal(txtProductVIpPrice_tabCreate.getText());

        if (selectedImagePath == null || selectedImagePath.isEmpty()) {
            System.err.println("No se ha seleccionado una imagen para el producto.");
            return;
        }

        Product product = new Product(id, name, type, price, vipPrice);
        product.setProductImage(selectedImagePath);
        cafeteriaMenu.addProductToMenu(product);

        clearTextFields(
                txtProductId_tabCreate,
                txtProductName_tabCreate,
                txtProductType_tabCreate,
                txtProductPrice_tabCreate,
                txtProductVIpPrice_tabCreate
        );

        selectedImagePath = null;
        imgProduct_tabCreate.setImage(null);
        updateProductTable();
        showAlert("Producto registrado", "El producto ha sido registrado correctamente.");
    }

    private void handleDeleteProduct() {
        Product selectedProduct = tblFood.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            cafeteriaMenu.removeProductOnMenu(selectedProduct);
            updateProductTable();
            showAlert("Producto eliminado", "El producto ha sido eliminado correctamente.");
        }
    }

    private void handleEditProduct() {
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
            selectedProduct.setProductImage(selectedImagePath);

            cafeteriaMenu.editProduct(selectedProduct);
            updateProductTable();
            clearTextFields(txtProductName_tabEdit, txtProductType_tabEdit, txtProductPrice_tabEdit, txtProductVipPrice_tabEdit);
            showAlert("Producto editado", "El producto ha sido editado correctamente.");
        }
    }

    private void handleCreateCombo() {
        String comboName = txtFieldNameCombo.getText();
        String comboVipPrice = txtFieldVIPPriceCombo.getText();
        String comboPrice = txtFieldPriceCombo.getText();

        if (selectedImagePath == null || selectedImagePath.isEmpty()) {
            System.err.println("No se ha seleccionado una imagen para el producto.");
            return;
        }

        Combo newCombo = new Combo(comboName, selectedProductsForCombo, new BigDecimal(comboPrice), new BigDecimal(comboVipPrice));
        newCombo.setProductImage(selectedImagePath);
        cafeteriaMenu.addComboToMenu(newCombo);
        btnBackToCreateCombo();
        updateProductTable();
        updateComboTable();
        selectedProductsForCombo.clear();
        scrCreateComboFinalStep.setVisible(false);
        scrComboTable.setVisible(true);
        scrMenuCards.setVisible(true);
        btnContinueDefinedCombo.setVisible(true);
        imgComboCreate.setImage(null);

        showAlert("Combo registrado", "El combo ha sido registrado correctamente.");
    }

    private void handleDeleteCombo() {
        Combo selectedCombo = tblCombos.getSelectionModel().getSelectedItem();
        if (selectedCombo != null) {
            cafeteriaMenu.removeComboOnMenu(selectedCombo);
            showAlert("Combo eliminado", "El combo ha sido eliminado correctamente.");
            updateProductTable();
            updateComboTable(); // Asegúrate de actualizar la tabla después de eliminar un combo
            showAlert("Combo eliminado", "El combo ha sido eliminado correctamente.");
        }
    }

    private void handleEditCombo() {
        Combo selectedCombo = tblCombos.getSelectionModel().getSelectedItem();
        if (selectedCombo != null) {
            String newName = txtFieldEditComboName.getText();
            BigDecimal newPrice = new BigDecimal(txtFieldEditComboPrice.getText());
            BigDecimal newVipPrice = new BigDecimal(txtFieldEditComboVipPrice.getText());

            selectedCombo.setName(newName);
            selectedCombo.setPrice(newPrice);
            selectedCombo.setVIPPrice(newVipPrice);
            selectedCombo.setProducts(selectedProductsForCombo);
            selectedCombo.setProductImage(selectedImagePath);

            cafeteriaMenu.editCombo(selectedCombo);
            updateProductTable();
            updateComboTable(); // Asegúrate de actualizar la tabla después de editar un combo

            clearTextFields(txtFieldEditComboName, txtFieldEditComboPrice, txtFieldEditComboVipPrice);
            showAlert("Combo editado", "El combo ha sido editado correctamente.");
        }
    }

    private void updateComboTable() {
        // Obtener la lista actualizada de combos desde el modelo
        List<Combo> updatedCombos = cafeteriaMenu.getCombos();

        // Crear una nueva lista observable con los combos actualizados
        ObservableList<Combo> observableComboList = FXCollections.observableArrayList(updatedCombos);

        // Asignar la nueva lista observable a la tabla
        tblCombos.setItems(observableComboList);
        tblCombos.refresh();
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
    void removeProductToCombo(ActionEvent event) {
        try {
            Product selectedProduct = tblSelectedProductsForCombo.getSelectionModel().getSelectedItem();
            selectedProductsForCombo.remove(selectedProduct);
            updateProductTable();
        } catch (Exception e) {
            //todo algo debe tener yo creo
        }

    }

    private void setupSearchFilters() {
        // Productos
        ObservableList<Product> productList = FXCollections.observableArrayList(cafeteriaMenu.getProducts());
        filteredProductList = new FilteredList<>(productList, p -> true);

        txtSearchProduct.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredProductList.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostrar todos si no hay filtro
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return product.getName().toLowerCase().contains(lowerCaseFilter) ||
                        product.getType().toLowerCase().contains(lowerCaseFilter);
            });
            tblFood.setItems(filteredProductList);
        });

        // Combos
        ObservableList<Combo> comboList = FXCollections.observableArrayList(cafeteriaMenu.getCombos());
        filteredComboList = new FilteredList<>(comboList, c -> true);

        txtSearchCombo.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredComboList.setPredicate(combo -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // Mostrar todos si no hay filtro
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return combo.getName().toLowerCase().contains(lowerCaseFilter);
            });
            tblCombos.setItems(filteredComboList);
        });
    }

    private void displayComboSelection() {
        Combo selectedCombo = tblCombos.getSelectionModel().getSelectedItem();

        if (selectedCombo != null) {
            txtFieldEditComboName.setText(selectedCombo.getName());
            txtFieldEditComboPrice.setText(selectedCombo.getPrice().toString());
            txtFieldEditComboVipPrice.setText(selectedCombo.getVIPPrice().toString());
            String imagePath = selectedCombo.getProductImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                File imageFile = new File("src/main/resources/" + imagePath);
                if (imageFile.exists()) {
                    imgComboEdit.setImage(new javafx.scene.image.Image(imageFile.toURI().toString()));
                } else {
                    System.err.println("La imagen no existe: " + imagePath);
                }
            }
        }


    }

    private void displayProductSelection() {
        Product selectedProduct = tblFood.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            txtProductName_tabEdit.setText(selectedProduct.getName());
            txtProductType_tabEdit.setText(selectedProduct.getType());
            txtProductPrice_tabEdit.setText(selectedProduct.getPrice().toString());
            txtProductVipPrice_tabEdit.setText(selectedProduct.getVIPPrice().toString());

            // Mostrar la imagen desde el path almacenado
            String imagePath = selectedProduct.getProductImage();
            if (imagePath != null && !imagePath.isEmpty()) {
                File imageFile = new File("src/main/resources/" + imagePath);
                if (imageFile.exists()) {
                    imgProduct_tabEdit.setImage(new javafx.scene.image.Image(imageFile.toURI().toString()));
                } else {
                    System.err.println("La imagen no existe: " + imagePath);
                }
            }
        }
    }

    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();  // Limpia el contenido de cada TextField
        }
    }

    @Override
    public void onProductSelected(Product product) {
        selectedProductsForCombo.add(product);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
