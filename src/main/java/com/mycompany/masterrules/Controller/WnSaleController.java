package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Database.CustomerDBManager;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.cafeteria.menu.Combo;
import com.mycompany.masterrules.Model.cafeteria.menu.Product;
import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.possystem.Order;
import com.mycompany.masterrules.Model.possystem.PedidoComanda;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
public class WnSaleController implements Initializable {
    //COMPONENTES DE LA VENTANA DE LA CARTILLA DE MENU QUE MUESTRA LOS PRODUCTOS
    //-------------------------------------------------------------------------------------------
    //TODO Retirar esta Order.
    private Order currentTemporalOrder;
    @FXML
    private Button btnContinue;

    @FXML
    private Button btnPay;

    @FXML
    private AnchorPane menuWindow;

    @FXML
    private VBox menuOrderOptionsBox;

    @FXML
    private ScrollPane menuCardsScroller;

    @FXML
    private FlowPane productCardsScroller = new FlowPane();

    @FXML
    private TabPane menuCategories;


    //COMPONENTES DE LA VENTANA QUE SE MUESTRA AL CONTINUAR LA ORDEN
    //-------------------------------------------------------------------------------------------
    @FXML
    private AnchorPane continueOrderWindow;

    @FXML
    private VBox continueOrderOptionsBox;

    @FXML
    private HBox tableNumberBox;

    @FXML
    private TextField inputClientName;
    @FXML
    private ComboBox<Customer> cboCustomers;
    @FXML
    private TextArea txtAdittionalComments;


    //OTROS OBJETOS
    //-------------------------------------------------------------------------------------------


    private WnSaleController wnSaleSection;


    @FXML
    private AnchorPane scrCustomCombo;

    @FXML
    private TableView<PedidoComanda> tblOrder;

    @FXML
    private TableColumn<PedidoComanda, String> colAmount;

    @FXML
    private TableColumn<PedidoComanda, String> colProduct;

    @FXML
    private TableColumn<PedidoComanda, String> colPrice; // este si es un String?

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    @FXML
    private Label lblTotal;

    @FXML
    private Button btnCancel;

    @FXML
    private ToggleGroup deliveryMethod;
    @FXML
    private ScrollPane menuCardsScroller1;
    @FXML
    private FlowPane comboCardsScroller;


    /**
     * Agregar nueva categoria de menu en las pestañas
     */
    public void addMenuCategory() {
        /*
        //Dependiendo de la categoria de menu seleccionada, se cambiaran los productos en pantalla
        newMenuCategory.setOnSelectionChanged(event -> {
            if (newMenuCategory.isSelected()) {
                //nombreTab.setText(newMenuCategory.getText());

            }
        });

        menuCategories.getTabs().add(newMenuCategory);



         */
    }
    
    /*
    public ObservableList<ProductTest> getProductCardData(){
        
    }
    */

    public void displayMenuCards() {
        ObservableList<Product> productDataList = FXCollections.observableArrayList();
        ObservableList<Product> comboDataList = FXCollections.observableArrayList();
        //BORRAR ESTO SOLO ES DE PRUEBA
        Product p1 = new Product("P1", "Burger", "Platillo", new BigDecimal("20"), new BigDecimal("15"));
        Product p2 = new Product("P2", "Fries", "Platillo", new BigDecimal("15"), new BigDecimal("10"));
        Product p3 = new Product("P3", "Soda", "Platillo", new BigDecimal("20"), new BigDecimal("10"));

        productDataList.clear();
        productDataList.add(p1);
        productDataList.add(p2);
        productDataList.add(p3);

        for (Product currentProduct : productDataList) {
            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/com/mycompany/masterrules/itemCardProduct.fxml"));
                AnchorPane pane = load.load();
                ItemCardProductController cardController = load.getController();

                cardController.setProductDataToCard(currentProduct);

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

    /**
     * Mostrar formulario que aparece al continuar la orden
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


    @FXML
    private void handlePayAction(MouseEvent event) {
        try {
            // Cargar la vista de pago desde el archivo FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/masterrules/wnPayment.fxml"));
            Parent paymentView = loader.load();

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

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al cargar la vista de pago: " + e.getMessage());
        }
    }


    /**
     * Inicializar el controllador de la ventana de Venta
     *
     * @param url Ubicación utilizada para resolver rutas relativas para el objeto raíz
     * @param rb  Recursos utilizados para localizar el objeto raíz
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<PedidoComanda> productOrderList = FXCollections.observableArrayList();

        colAmount.setReorderable(false);

        colAmount.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getQuantity())));
        colPrice.setReorderable(false);
        colPrice.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getTotalPrice())));
        colProduct.setReorderable(false);
        colProduct.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProductName()));

        Product p1 = new Product("P1", "Burger", "Platillo", new BigDecimal("20"), new BigDecimal("15"));
        Product p2 = new Product("P2", "Fries", "Platillo", new BigDecimal("15"), new BigDecimal("10"));
        Product p3 = new Product("P3", "Soda", "Platillo", new BigDecimal("20"), new BigDecimal("10"));
        PedidoComanda pI1 = new PedidoComanda(p1);
        PedidoComanda pI2 = new PedidoComanda(p2);
        PedidoComanda pI3 = new PedidoComanda(p3);

        productOrderList.add(pI1);
        productOrderList.add(pI2);
        productOrderList.add(pI3);
        tblOrder.setItems(productOrderList);



        //Hace que la distribución de las cartas se ajusten al tamaño del cuadro donde estan contenidas
        productCardsScroller.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        comboCardsScroller.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        displayMenuCards();
        CustomerDBManager cdbm = new CustomerDBManager();
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


}
