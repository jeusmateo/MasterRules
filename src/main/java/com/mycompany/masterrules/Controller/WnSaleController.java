package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Database.CustomerDatabase;
import com.mycompany.masterrules.Model.cafeteria.CafeteriaMenu;
import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.retailsystem.OrderItem;
import com.mycompany.masterrules.Model.retailsystem.POSManager;
import com.mycompany.masterrules.Model.retailsystem.PaymentDetails;
import com.mycompany.masterrules.Model.users.Permission;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Controlador de la ventana de Venta
 *
 * @author campv
 */
public class WnSaleController implements Initializable, ProductSelectionListener {

    // ATRIBUTOS
    //-------------------------------------------------------------------------------------------
    private Customer customerSelected;
    private POSManager posManager = POSManager.getInstance();
    private ToggleGroup group; // TODO: RENOMBRAR ESTO PERO NO PUDE PQ NO SUPE QUE RAYOS ERA

    //COMPONENTES DE LA VENTANA QUE SE MUESTRA AL CONTINUAR LA ORDEN
    //-------------------------------------------------------------------------------------------

    @FXML
    private Button btnContinue;
    @FXML
    private Button btnPay;


    @FXML
    private TextField txtFieldTableNumber;

    @FXML
    private Label lblTotal;

    @FXML
    private AnchorPane continueOrderWindow;
    @FXML
    private AnchorPane menuWindow;
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane scrMainSale;
    @FXML
    private AnchorPane scrOrderTable;
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
    private TextArea txtAdditionalComments;

    @FXML
    private TextField inputClientName;


    @FXML
    private ToggleGroup deliveryMethod;

    @FXML
    private RadioButton deliveryMethodCounter;
    @FXML
    private RadioButton deliveryMethodTakeAway;
    @FXML
    private RadioButton paraMesaMetodo; // TODO RENOMBRAR ESTO

    @FXML
    private TableView<OrderItem> tblOrder;
    @FXML
    private TableColumn<OrderItem, String> colAmount;
    @FXML
    private TableColumn<OrderItem, String> colProduct;
    @FXML
    private TableColumn<OrderItem, String> colPrice; // este si es un String?


    // MÉTODOS
    //-------------------------------------------------------------------------------------------


    public void displayProductMenuCards() {
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
    }

    public void displayComboMenuCards() {
        CafeteriaMenu menu = new CafeteriaMenu();
        List<Combo> combosOnMenu = menu.getCombos();
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

        String customerName = "";
        if (customerInfo != null) {
            customerName = customerInfo.getCustomerName();
        } else {
            customerName = inputClientName.getText();
        }

        RadioButton selectedDeliveryMethod = (RadioButton) group.getSelectedToggle();
        String deliveryMethod = "";
        if (selectedDeliveryMethod != null) {
            deliveryMethod = selectedDeliveryMethod.getText();
            if (selectedDeliveryMethod.equals(paraMesaMetodo)) {
                deliveryMethod += " - Mesa: " + txtFieldTableNumber.getText();
            }
        }
        String comments = txtAdditionalComments.getText();
        //TODO validaciones para null
        lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount(customerSelected)));
        posManager.configureOrder(deliveryMethod, comments, customerName);
    }


    @FXML
    private void handlePayAction(MouseEvent event) {

        var currentUser = posManager.getCurrentUser();
        if (!currentUser.hasPermission(Permission.MAKE_SALE)) {
            showAlert("Error", "No tienes permisos para realizar ventas");
        }

        if (group.getSelectedToggle() != null && posManager.getCurrentOrder().getTotalAmount(customerSelected).compareTo(BigDecimal.ZERO) != 0) {
            try {
                configOrderInfo();

                // Cargar la vista de pago desde el archivo FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/masterrules/wnPayment.fxml"));
                Parent paymentView = loader.load();
                WnPaymentController paymentController = loader.getController();

                paymentController.setOrderData(posManager.getCurrentOrder().getTotalAmount(customerSelected), customerSelected);

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
                    posManager.sell(paymentResult, cboCustomers.getValue());
                    showMenuWindow();
                    updateOrderInfo();
                    inputClientName.setVisible(true);
                    cboCustomers.setValue(null);

                } else {
                    System.out.println("Pago cancelado.");
                }
            } catch (IllegalArgumentException e) {
                showAlert("Error", e.getMessage());
            } catch (Exception e) {

                System.err.println("Error al cargar la vista de pago: " + e.getMessage());
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
        group = new ToggleGroup();
        configTextFields();
        deliveryMethodCounter.setToggleGroup(group);
        deliveryMethodTakeAway.setToggleGroup(group);
        paraMesaMetodo.setToggleGroup(group);

        posManager = POSManager.getInstance();
        System.out.println(posManager.getCurrentUser());

        initializeTableOrder();
        lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount(customerSelected)));

        cboCustomers.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                customerSelected = cboCustomers.getValue();
                Customer test = cboCustomers.getValue();
                System.out.println("Soy " + test.getCustomerName() + " y soy ");
                if (test.getCustomerAccount().isIsVIP()) {
                    System.out.println("vip");
                }
                updateOrderInfo();
                if (customerSelected != null && customerSelected.getCustomerAccount().isIsVIP()) {
                    colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalVipPrice())));
                } else {
                    colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalPrice())));
                    updateOrderInfo();
                }
                lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount(customerSelected)));
            }
        });

        productCardsScroller.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        comboCardsScroller.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        displayProductMenuCards();
        displayComboMenuCards();
        CustomerDatabase cdbm = new CustomerDatabase();
        List<Customer> chepo = cdbm.readAll();
        ObservableList<Customer> customersList = FXCollections.observableArrayList(chepo);
        cboCustomers.setItems(customersList);

        cboCustomers.setCellFactory(lv -> new ListCell<>() {
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

        cboCustomers.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Customer customer, boolean empty) {
                super.updateItem(customer, empty);
                if (empty || customer == null) {
                    setText("Seleccione un cliente");
                } else {
                    setText(customer.getCustomerName());
                }
            }
        });
        lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount(customerSelected)));
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

    private void updateOrderInfo() {
        ObservableList<OrderItem> productOrderList = FXCollections.observableArrayList(posManager.getCurrentOrder().getPedidoComandaList());
        tblOrder.setItems(productOrderList);
        tblOrder.refresh();
        lblTotal.setText(String.valueOf(posManager.getCurrentOrder().getTotalAmount(customerSelected)));
    }


    @Override
    public void onProductSelected(Product product) {
        posManager.addProductToOrder(product);
        updateOrderInfo();
    }

    private void configTextFields() {
        txtFieldTableNumber.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(txtFieldTableNumber)) {
            handleTextFieldTableNumber(event);
        }
    }

    private void handleTextFieldTableNumber(KeyEvent event) {
        if (!event.getCharacter().matches("\\d")) {
            event.consume();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}