package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Database.CustomerDatabase;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.cafeteria.CafeteriaMenu;
import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.possystem.POSManager;
import com.mycompany.masterrules.Model.possystem.PaymentDetails;
import com.mycompany.masterrules.Model.possystem.OrderItem;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Controlador de la ventana de Venta
 *
 * @author campv
 */
public class WnSaleController implements Initializable, ProductSelectionListener {

    // ATRIBUTOS
    //-------------------------------------------------------------------------------------------

    private POSManager posManager = POSManager.getInstance();
    private ToggleGroup group;
    private WnSaleController wnSaleSection;
    private int currentCategoryIndex = 0;

    //COMPONENTES DE LA VENTANA QUE SE MUESTRA AL CONTINUAR LA ORDEN
    //-------------------------------------------------------------------------------------------

    @FXML
    private Button btnContinue;
    @FXML
    private Button btnPay;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnNextCategory;
    @FXML
    private Button btnPreviousCategory;
    @FXML
    private Button btnAdd1;
    @FXML
    private Button btnRemove1;

    @FXML
    private Label lblTotal;
    @FXML
    private Label lbCategory;

    @FXML
    private AnchorPane continueOrderWindow;
    @FXML
    private AnchorPane menuWindow;
    @FXML
    private AnchorPane AnchorPaneCategoriesProductsCombo;
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane scrMainSale;
    @FXML
    private AnchorPane scrOrderTable;
    @FXML
    private AnchorPane scrMainSale1;
    @FXML
    private AnchorPane scrCustomCombo1;
    @FXML
    private AnchorPane scrCustomCombo11;

    @FXML
    private TabPane menuCategories;

    @FXML
    private VBox continueOrderOptionsBox;
    @FXML
    private VBox menuOrderOptionsBox;
    @FXML
    private VBox navigationCategory;

    @FXML
    private HBox tableNumberBox;

    @FXML
    private ComboBox<Customer> cboCustomers;

    @FXML
    private ScrollPane menuCardsScroller;
    @FXML
    private ScrollPane menuCardsScroller1;

    @FXML
    private FlowPane productCardsScroller = new FlowPane();
    @FXML
    private FlowPane comboCardsScroller;

    @FXML
    private TextArea txtAdittionalComments;

    @FXML
    private TextField inputClientName;


    @FXML
    private ToggleGroup deliveryMethod;

    @FXML
    private RadioButton paraMostradoMetodo;
    @FXML
    private RadioButton paraLlevarMetodo;
    @FXML
    private RadioButton paraMesaMetodo;

    @FXML
    private TableView<OrderItem> tblOrder;
    @FXML
    private TableColumn<OrderItem, String> colAmount;
    @FXML
    private TableColumn<OrderItem, String> colProduct;
    @FXML
    private TableColumn<OrderItem, String> colPrice; // este si es un String?
    @FXML
    private TableView<?> tblAuxiliarCustomCombo;
    @FXML
    private TableColumn<?, ?> colAmount1;
    @FXML
    private TableColumn<?, ?> colProduct1;
    @FXML
    private TableColumn<?, ?> colPrice1;

    // MÉTODOS
    //-------------------------------------------------------------------------------------------

    @FXML
    private void handleCategoryNavigation(ActionEvent evt) {
        try {
            if (evt.getSource().equals(btnNextCategory)) {
                if (currentCategoryIndex < categories.size() - 1) {
                    currentCategoryIndex++;
                    displayCategoriesForCustomCombo(currentCategoryIndex); // Actualizar vista
                } else {
                    System.out.println("Ya estás en la última categoría");
                }
            }
            if (evt.getSource().equals(btnPreviousCategory)) {
                if (currentCategoryIndex > 0) {
                    currentCategoryIndex--;
                    displayCategoriesForCustomCombo(currentCategoryIndex); // Actualizar vista
                } else {
                    System.out.println("Ya estás en la primera categoría");
                }
            }

        } catch (Exception e) {
            System.err.println("Error al navegar entre categorías: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayCategory(int index) {
        if (index >= 0 && index < categories.size()) {
            List<Product> selectedCategory = categories.get(index);
            // Lógica para actualizar la vista con los productos de la categoría
            productCardsScroller.getChildren().clear(); // Limpiar productos anteriores
            for (Product product : selectedCategory) {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/com/mycompany/masterrules/itemCardProduct.fxml"));
                    AnchorPane productPane = loader.load();
                    ItemCardProductController controller = loader.getController();
                    controller.setProductDataToCard(product);
                    productCardsScroller.getChildren().add(productPane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Índice de categoría fuera de rango: " + index);
        }
    }

    private List<List<Product>> categories = new ArrayList<>();

    private void initializeCategories() {
        // Crear categorías con productos de prueba
        List<Product> category1 = List.of(
                new Product("P1", "Burger", "Platillo", new BigDecimal("20"), new BigDecimal("15")),
                new Product("P2", "Fries", "Platillo", new BigDecimal("15"), new BigDecimal("10"))
        );
        List<Product> category2 = List.of(
                new Product("P3", "Ice Cream", "Postre", new BigDecimal("25"), new BigDecimal("20")),
                new Product("P4", "Cake", "Postre", new BigDecimal("35"), new BigDecimal("30"))
        );
        List<Product> category3 = List.of(
                new Product("P5", "Coffee", "Bebida", new BigDecimal("10"), new BigDecimal("5")),
                new Product("P6", "Tea", "Bebida", new BigDecimal("15"), new BigDecimal("8"))
        );

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
    }


    public void displayCategoriesForCustomCombo(int categoryIndex) {
        try {
            if (categoryIndex < 0 || categoryIndex >= categories.size()) {
                System.err.println("Índice de categoría fuera de rango: " + categoryIndex);
                return;
            }

            // Cargar el diseño de la vista de categorías
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/mycompany/masterrules/ProductCategoriesCustomCombo.fxml"));
            AnchorPane categoryPane = loader.load();

            // Obtener el controlador de la vista cargada
            ProductCategoriesCustomComboController categoryController = loader.getController();

            // Limpiar la vista actual y agregar la nueva
            AnchorPaneCategoriesProductsCombo.getChildren().clear();
            AnchorPaneCategoriesProductsCombo.getChildren().add(categoryPane);

            // Obtener productos de la categoría actual
            List<Product> selectedCategory = categories.get(categoryIndex);
            ObservableList<Product> categoryProducts = FXCollections.observableArrayList(selectedCategory);

            // Cargar productos dentro de la categoría seleccionada
            for (Product product : categoryProducts) {
                try {
                    FXMLLoader productLoader = new FXMLLoader();
                    productLoader.setLocation(getClass().getResource("/com/mycompany/masterrules/itemCardProduct.fxml"));
                    AnchorPane productPane = productLoader.load();

                    // Configurar datos del producto en la tarjeta
                    ItemCardProductController productController = productLoader.getController();
                    productController.setProductDataToCard(product);

                    // Agregar la tarjeta de producto al contenedor de productos
                    categoryController.getProductContainer().getChildren().add(productPane);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

                productCardsScroller.getChildren().add(pane);

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

        for (Product currentProductCombo : comboDataList) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/com/mycompany/masterrules/itemCardProduct.fxml"));
                AnchorPane pane = load.load();
                ItemCardProductController cardController = load.getController();

                cardController.setProductDataToCard(currentProductCombo);

                comboCardsScroller.getChildren().add(pane);

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
    }

    /*
    public void setupAutoCompleteOnTextfield(TextField textfield){//List<String> clientNames poner como parametro
        ContextMenu nameSuggestionsMenu=new ContextMenu();

        textfield.textProperty().addListener((observable,newInput,oldInput)->{
            if(newInput.isEmpty()){
                nameSuggestionsMenu.hide();
            }
            nameSuggestionsMenu.show(textfield, Side.BOTTOM, 0, 0);
        });

        MenuItem nameSugesstion=new MenuItem("Jose");
        nameSuggestionsMenu.getItems().add(nameSugesstion);

        textfield.setContextMenu(nameSuggestionsMenu);
    }
    */

    @FXML
    public void showOrderForm() {
        continueOrderWindow.setVisible(true);
        continueOrderOptionsBox.setVisible(true);

        menuWindow.setVisible(false);
        menuOrderOptionsBox.setVisible(false);

    }

    /**
     * Mostrar cartilla de menu que muestra los porductos
     */
    @FXML
    public void showMenuWindow() {
        menuWindow.setVisible(true);
        menuOrderOptionsBox.setVisible(true);

        continueOrderWindow.setVisible(false);
        continueOrderOptionsBox.setVisible(false);
    }

    /**
     * Mostrar opcion de número de mesa
     */
    @FXML
    public void showTableNumber() {
        tableNumberBox.setVisible(true);
    }

    /**
     * Ocultar opcion de número de mesa
     */
    @FXML
    public void hideTableNumber() {
        tableNumberBox.setVisible(false);
    }

    private void configOrderInfo(){
        Customer customerInfo=cboCustomers.getValue();
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        String deliveryMethod="";
        if (selected != null) {
            deliveryMethod=selected.getText();
        }
        String comments = txtAdittionalComments.getText();
        //TODO validaciones para null
        posManager.configureOrder(deliveryMethod,comments,customerInfo);
    }


    @FXML
    private void handlePayAction(MouseEvent event) {
        if(group.getSelectedToggle()!=null && posManager.getCurrentOrder().getTotalAmount().compareTo(BigDecimal.ZERO)!=0) {
            try {
                // Cargar la vista de pago desde el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/masterrules/wnPayment.fxml"));
                Parent paymentView = loader.load();
                WnPaymentController paymentController = loader.getController();
                try {
                    paymentController.setOrderData(posManager.getCurrentOrder().calculateTotalAmount(), posManager.getCurrentOrder().getCustomer());
                } catch (Exception e) {
                    System.out.println("Chepipi " + e.getMessage());
                }
                // Crear una nueva escena y un nuevo Stage para la vista de pago
                Scene paymentScene = new Scene(paymentView);
                Stage paymentStage = new Stage();
                paymentStage.setScene(paymentScene);

                // Configurar el Stage como modal para bloquear interacciones en la ventana principal
                paymentStage.initModality(Modality.WINDOW_MODAL);
                paymentStage.initOwner(btnPay.getScene().getWindow());

                // Configurar el Stage sin bordes (sin barra de título)
                paymentStage.initStyle(StageStyle.UNDECORATED);

                // Aplicar un efecto de difuminado a la ventana principal mientras el Stage modal esté abierto
                Stage currentStage = (Stage) btnPay.getScene().getWindow();
                currentStage.getScene().getRoot().setEffect(new javafx.scene.effect.BoxBlur(10, 10, 3));

                // Añadir un evento para restaurar el fondo al cerrar el modal
                paymentStage.setOnHidden(e -> currentStage.getScene().getRoot().setEffect(null));

                // Mostrar el modal
                paymentStage.showAndWait();

                configOrderInfo();

                PaymentDetails paymentResult = paymentController.getPaymentDetails();
                if (paymentResult != null) {
                    System.out.println("Pago realizado:");
                    posManager.sell(paymentResult);

                } else {
                    System.out.println("Pago cancelado.");
                }
            } catch (Exception e) {

                System.err.println("Error al cargar la vista de pago: " + e.getStackTrace());
                e.printStackTrace();
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {

        group = new ToggleGroup();
        paraMostradoMetodo.setToggleGroup(group);
        paraLlevarMetodo.setToggleGroup(group);
        paraMesaMetodo.setToggleGroup(group);

        posManager = POSManager.getInstance();
        System.out.println(posManager.getCurrentUser());

        initializeCategories();
        displayCategoriesForCustomCombo(currentCategoryIndex);
        List<OrderItem> chepo1 = posManager.getCurrentOrder().getPedidoComandaList();
        ObservableList<OrderItem> productOrderList = FXCollections.observableArrayList();
        colAmount.setReorderable(false);

        colAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity())));
        colPrice.setReorderable(false);
        colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalPrice())));
        colProduct.setReorderable(false);
        colProduct.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        tblOrder.setItems(productOrderList);
        tblOrder.refresh();


        //Hace que la distribución de las cartas se ajusten al tamaño del cuadro donde estan contenidas
        productCardsScroller.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        comboCardsScroller.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        displayMenuCards();
        CustomerDatabase cdbm = new CustomerDatabase();
        //TODO CAMBIAR NOMBRE DE TODOS LOS CHEPOS
        List<Customer> chepo = cdbm.readAll();
        ObservableList<Customer> cutomersList = FXCollections.observableArrayList(chepo);
        cboCustomers.setItems(cutomersList);
        //TODO ChepoEmergencia se tuvo que hacer feo uwur
        cboCustomers.setCellFactory(lv -> new ListCell<Customer>() {
            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);
                if (empty || customer == null) {
                    setText(null);
                } else {
                    setText(customer.getCustomerName()); // Mostrar solo el nombre del cliente
                }
            }
        });
        cboCustomers.setButtonCell(new ListCell<Customer>() {
            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);
                if (empty || customer == null) {
                    setText(null);
                } else {
                    setText(customer.getCustomerName());
                }
            }
        });


    }


    @Override
    public void onProductSelected(Product product) {
        posManager.addProductToOrder(product);
        ObservableList<OrderItem> productOrderList = FXCollections.observableArrayList(posManager.getCurrentOrder().getPedidoComandaList());
        tblOrder.setItems(productOrderList);
        tblOrder.refresh();
        lblTotal.setText(String.valueOf(posManager.getCurrentOrder().calculateTotalAmount()));





    }
}