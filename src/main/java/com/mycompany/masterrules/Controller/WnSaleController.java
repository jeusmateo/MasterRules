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





    public void displayMenuCards() {
        CafeteriaMenu menu = new CafeteriaMenu();
        List<Product> productsOnMenu = menu.getProducts();
        ObservableList<Product> productDataList = FXCollections.observableArrayList(productsOnMenu);

        for (Product currentProduct : productDataList) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/com/mycompany/masterrules/itemCardProduct.fxml"));
                AnchorPane pane = load.load();
                ItemCardProductController cardController = load.getController();

                cardController.setProductDataToCard(currentProduct);
                cardController.setSelectionListener(this);

                productCardsScroller.getChildren().add(pane);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        List<Combo> combosOnMenu = new ArrayList<>();
        combosOnMenu = menu.getCombos();

        ObservableList<Product> comboDataList = FXCollections.observableArrayList(combosOnMenu);
        for (Product currentProductCombo : comboDataList) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/com/mycompany/masterrules/itemCardProduct.fxml"));
                AnchorPane pane = load.load();
                ItemCardProductController cardController = load.getController();

                cardController.setProductDataToCard(currentProductCombo);
                cardController.setSelectionListener(this);

                comboCardsScroller.getChildren().add(pane);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    @FXML
    public void hideTxtFieldClientName(MouseEvent event) {
        inputClientName.setVisible(false);
    }


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
//TODO FALTA EL DEL CLIENTRES
    private void configOrderInfo() {
        Customer customerInfo = cboCustomers.getValue();
       // if(customerInfo == null) {
            System.out.println("Customer Info: " + customerInfo);
       // }
        RadioButton selected = (RadioButton) group.getSelectedToggle();
        String deliveryMethod = "";
        if (selected != null) {
            deliveryMethod = selected.getText();
        }
        String comments = txtAdittionalComments.getText();
        //TODO validaciones para null
        lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount()));
        posManager.configureOrder(deliveryMethod, comments, customerInfo);
    }


    @FXML
    private void handlePayAction(MouseEvent event) {
        if (group.getSelectedToggle() != null && posManager.getCurrentOrder().getTotalAmount().compareTo(BigDecimal.ZERO) != 0) {
            try {
                configOrderInfo();

                // Cargar la vista de pago desde el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/masterrules/wnPayment.fxml"));
                Parent paymentView = loader.load();
                WnPaymentController paymentController = loader.getController();
                try {
                    if(posManager.getCurrentOrder().getCustomer()==null){
                        System.out.println(posManager.getCurrentOrder().getCustomer());
                    }
                    paymentController.setOrderData(posManager.getCurrentOrder().getTotalAmount(), posManager.getCurrentOrder().getCustomer());
                    System.out.println("Cuesto :"+posManager.getCurrentOrder().getTotalAmount());
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


                PaymentDetails paymentResult = paymentController.getPaymentDetails();
                if (paymentResult != null) {
                    System.out.println("Pago realizado:");
                    posManager.sell(paymentResult);
                    showMenuWindow();
                    updateOrderInfo();

                } else {
                    System.out.println("Pago cancelado.");
                }
            } catch (Exception e) {

                System.err.println("Error al cargar la vista de pago: " + e.getStackTrace());
                e.printStackTrace();
            }
        }
    }

    private void initializeTableOrder() {
        ObservableList<OrderItem> productOrderList = FXCollections.observableArrayList(posManager.getCurrentOrder().getPedidoComandaList());
        colAmount.setReorderable(false);
        colAmount.setResizable(false);
        colAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity())));
        colPrice.setReorderable(false);
        colPrice.setResizable(false);
        colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalPrice())));
        colProduct.setReorderable(false);
        colProduct.setResizable(false);
        colProduct.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));
        tblOrder.setItems(productOrderList);
        tblOrder.refresh();

        tblOrder.setOnMouseClicked(event -> {
            if (event.getClickCount() > 1) {
                event.consume();
            }
        });
        tblOrder.getSelectionModel().getSelectedItem();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO este configura
        group = new ToggleGroup();
        paraMostradoMetodo.setToggleGroup(group);
        paraLlevarMetodo.setToggleGroup(group);
        paraMesaMetodo.setToggleGroup(group);

        posManager = POSManager.getInstance();
        System.out.println(posManager.getCurrentUser());



        initializeTableOrder();

        cboCustomers.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {

                posManager.getCurrentOrder().setCustomer(cboCustomers.getValue());
                Customer test = cboCustomers.getValue();
                System.out.println("Soy "+ test.getCustomerName()+ "y soy ");
                if(test.getCustomerAccount().isIsVIP()){
                    System.out.println("vip");
                }
                updateOrderInfo();
                //TODO chepo arregla esto
                if(posManager.getCurrentOrder().getCustomer()!=null && posManager.getCurrentOrder().getCustomer().getCustomerAccount().isIsVIP()){
                    colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalVipPrice())));
                }else{
                    colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalPrice())));
                    updateOrderInfo();
                }
                lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount()));
            }
        });

               //

        //Hace que la distribución de las cartas se ajusten al tamaño del cuadro donde estan contenidas
        productCardsScroller.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        comboCardsScroller.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        displayMenuCards();
        CustomerDatabase cdbm = new CustomerDatabase();
        //TODO CAMBIAR NOMBRE DE TODOS LOS CHEPOS
        List<Customer> chepo = cdbm.readAll(); // Lee todos los datos
        ObservableList<Customer> customersList = FXCollections.observableArrayList(chepo);
        cboCustomers.setItems(customersList);

// Personaliza cómo se muestran las celdas desplegables del ComboBox
        cboCustomers.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);
                if (empty || customer == null) {
                    setText(null);
                } else {
                    setText(customer.getCustomerName()); // Muestra el nombre del cliente
                }
            }
        });

// Personaliza la celda visible del ComboBox (cuando no está desplegado)
        cboCustomers.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);
                if (empty || customer == null) {
                    setText("Seleccione un cliente"); // Texto por defecto cuando no hay selección
                } else {
                    setText(customer.getCustomerName()); // Muestra el nombre del cliente seleccionado
                }
            }
        });
        lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount()));

    }


    @FXML
    void removeProductToOrden(ActionEvent event) {
        try {
            Product selectedProduct = tblOrder.getSelectionModel().getSelectedItem().getProduct();
            posManager.removeProductFromOrder(selectedProduct);
            updateOrderInfo();
        } catch (Exception e) {
            //todo algo debe tener yo creo
        }

    }

    private void updateOrderInfo(){
        ObservableList<OrderItem> productOrderList = FXCollections.observableArrayList(posManager.getCurrentOrder().getPedidoComandaList());
        tblOrder.setItems(productOrderList);
        tblOrder.refresh();
        lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount()));
    }

    @FXML
    void cancelOrder(ActionEvent event) {
        posManager.cancelOrder();
        updateOrderInfo();
    }

    @Override
    public void onProductSelected(Product product) {
        posManager.addProductToOrder(product);
        updateOrderInfo();
    }
}