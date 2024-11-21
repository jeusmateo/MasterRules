package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.customers.CustomerManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;



public class WnCustomersController implements Initializable {

    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    private CustomerManager customerManager;
    private static final Logger logger = Logger.getLogger(WnCustomersController.class.getName());


    // Componentes de la vista
    // --------------------------------------------------------------------------------------------
    @FXML
    private Button btnAcceptCredit;
    @FXML
    private Button btnSaveNewCustomer;
    @FXML
    private Button btnUpdateCustomerAccount;
    @FXML
    private Button btnDeleteCustomer;

    @FXML
    private AnchorPane guardarClienteNvo;
    @FXML
    private AnchorPane scrCustomerAccount;
    @FXML
    private AnchorPane scrEditCustomerAccount;
    @FXML
    private AnchorPane scrMainViewCustomerAccount;
    @FXML
    private AnchorPane scrViewInfoCustomer;
    @FXML
    private AnchorPane scrWarningCredit;

    @FXML
    private Tab tabNewCustomer;
    @FXML
    private Tab tabCustomerAccount;

    @FXML
    private Label lbEditCustomerName;
    @FXML
    private Label lbLoyaltyPoints;
    @FXML
    private Label lbCustomerName;
    @FXML
    private Label lbDebt;
    @FXML
    private Label lbStoreCredit;
    @FXML
    private Label lbCustomerPhoneNumber;
    @FXML
    private Label lbCustomerIdAuxiliar;

    @FXML
    private CheckBox chkCustomerVip;
    @FXML
    private CheckBox chkNewCustomerVipStatus;
    @FXML
    private CheckBox chkEditCustomerVipStatus;

    @FXML
    private TextField txtNewCustomerName;
    @FXML
    private TextField txtNewCustomerPhoneNumber;
    @FXML
    private TextField txtNewCustomerLoyaltyPoints;
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private TextField txtEditCustomerStoreCredit;
    @FXML
    private TextField txtEditCustomerLoyaltyPoints;
    @FXML
    private TextField txtEditCustomerPhoneNumber;
    @FXML
    private TextField txtFieldEditCustomerName;


    @FXML
    private TableView<Customer> tblCustomers;
    @FXML
    private TableColumn<Customer, String> colCustomerId;
    @FXML
    private TableColumn<Customer, String> colCustomerName;

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    @FXML
    private void searchCustomers() {
        String searchText = txtFieldSearch.getText().toLowerCase();

        // Filtrar la lista de clientes
        List<Customer> filteredCustomers = customerManager.getCustomers().stream()
                .filter(customer -> customer.getCustomerName().toLowerCase().contains(searchText))
                .toList();

        // Actualizar la tabla con los clientes filtrados
        ObservableList<Customer> filteredObservableList = FXCollections.observableArrayList(filteredCustomers);
        tblCustomers.setItems(filteredObservableList);
    }

    //TODO: ESTO DEBE QUITARSE PORQUE NO SE USA
    public void setBtnShowInformation() {
        scrViewInfoCustomer.setVisible(true);
        scrMainViewCustomerAccount.setVisible(false);

    }

    //TODO: ESTO DEBE QUITARSE PORQUE NO SE USA
    public void setBtnBackViewInfoCustomer() {
        scrMainViewCustomerAccount.setVisible(true);
        scrViewInfoCustomer.setVisible(false);

        clearTextFields(new TextField());
    }

    @FXML
    public void setBtnEditAccount() {
        scrEditCustomerAccount.setVisible(true);
        scrMainViewCustomerAccount.setVisible(false);

    }

    @FXML
    public void setBtnBackEditCustomerAccount() {
        scrMainViewCustomerAccount.setVisible(true);
        scrEditCustomerAccount.setVisible(false);
        clearTextFields(txtEditCustomerStoreCredit, txtEditCustomerLoyaltyPoints);

    }


    // falta que lo de atras no sea editable y se desenfoque
    @FXML
    private void setScrWarningCredit() {
        scrWarningCredit.setVisible(true);
    }

    @FXML
    private void setButtonAcceptCredit() {
        scrWarningCredit.setVisible(false);
    }


    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear(); // Limpia cada campo de texto proporcionado
        }
    }


    // checar si customer es nullo
    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(btnSaveNewCustomer)) {
            try {
                registerNewCustomer();
                clearTextFields(txtNewCustomerName, txtNewCustomerPhoneNumber, txtNewCustomerLoyaltyPoints);
                chkNewCustomerVipStatus.setSelected(false);
            } catch (Exception e) {
                // esto decia que se hiciera con sonar lint, se cambio *System.out.println(e.getMessage());* al logger
                logger.log(Level.SEVERE, "Error registering new customer", e);
            }
        } else if (evt.equals(btnUpdateCustomerAccount)) {
            editCustomerInfo();
            clearTextFields(txtEditCustomerStoreCredit, txtEditCustomerLoyaltyPoints);
            lbCustomerIdAuxiliar.setText("");
            chkEditCustomerVipStatus.setSelected(false);
            scrEditCustomerAccount.setVisible(false);
            scrMainViewCustomerAccount.setVisible(true);

        }
        else if (evt.equals(btnDeleteCustomer)) { // Nuevo bloque para eliminar clientes
            try {
                String customerIdToDelete = lbCustomerIdAuxiliar.getText(); // Obtén el ID del cliente desde la etiqueta auxiliar
                if (customerIdToDelete == null || customerIdToDelete.isEmpty()) {
                    logger.log(Level.WARNING, "No se seleccionó ningún cliente para eliminar.");
                    return;
                }
                customerManager.removeCustomer(customerIdToDelete); // Elimina al cliente usando el ID
                clearTextFields(txtEditCustomerStoreCredit, txtEditCustomerLoyaltyPoints);
                lbCustomerIdAuxiliar.setText("");
                chkEditCustomerVipStatus.setSelected(false);
                scrEditCustomerAccount.setVisible(false);
                scrMainViewCustomerAccount.setVisible(true);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error deleting customer", e);
            }
        }
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerManager.getCustomers());
        setItemsToAllTables(customers);
    }
    // TODO: Recordatorio chepil; Aqui algo esta mal. Aqui no de este metodo, en la clase de actualizar informacion de cliente. Clase de actualizar que no es clase es metodo. UWU
    private void registerNewCustomer() throws Exception {
        String newCustomerName = txtNewCustomerName.getText();
        String newCustomerPhoneNumber = txtNewCustomerPhoneNumber.getText();
        String newCustomerLoyaltyPoints = txtNewCustomerLoyaltyPoints.getText();
        boolean newCustomerVipStatus = chkNewCustomerVipStatus.isSelected();
        customerManager.registerCustomer(newCustomerName, newCustomerPhoneNumber, newCustomerLoyaltyPoints, newCustomerVipStatus);

    }

    // TODO: REFACTORIZAR LEGIBILIDAD POR QUE ESA MADRE DE ULTIMO TA LARGA
    private void editCustomerInfo() {
        String customerId = lbCustomerIdAuxiliar.getText();
        String newCustomerName = txtFieldEditCustomerName.getText();
        String newCustomerStoreCreditQuantity = txtEditCustomerStoreCredit.getText();
        String updateCustomerLoyaltyPoints = txtEditCustomerLoyaltyPoints.getText();
        boolean updateCustomerVipStatus = chkEditCustomerVipStatus.isSelected();
        String newCustomerPhoneNumber = txtEditCustomerPhoneNumber.getText();
        customerManager.updateCustomerData( newCustomerName,customerId, updateCustomerLoyaltyPoints, updateCustomerVipStatus, newCustomerStoreCreditQuantity, newCustomerPhoneNumber);
    }


    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(txtNewCustomerName)) {
            handleNewCustomerName(event);
        } else if (evt.equals(txtEditCustomerStoreCredit)) {
            handleEditCustomerStoreCredit(event);
        } else if (evt.equals(txtNewCustomerPhoneNumber) || evt.equals(txtEditCustomerPhoneNumber)) {
            handleNewCustomerPhoneNumber(event);
        } else if (evt.equals(txtNewCustomerLoyaltyPoints) || evt.equals(txtEditCustomerLoyaltyPoints)) {
            handleLoyaltyPoints(event);
        }
    }

    private void handleNewCustomerName(KeyEvent event) {
        if ( !event.getCharacter().matches("[\\p{L} ]")) {
            event.consume();
        } else if (event.getCharacter().equals("\r")) {
            txtNewCustomerPhoneNumber.requestFocus();
            event.consume();
        }
    }

    private void handleEditCustomerStoreCredit(KeyEvent event) {
        if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
            event.consume();
        }
    }

    private void handleNewCustomerPhoneNumber(KeyEvent event) {
        if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
            event.consume();
        } else if (event.getCharacter().equals("\r")) {
            txtNewCustomerLoyaltyPoints.requestFocus();
            event.consume();
        }
    }

    private void handleLoyaltyPoints(KeyEvent event) {
        if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
            event.consume();
        } else if (event.getCharacter().equals("\r")) {
            chkNewCustomerVipStatus.requestFocus();
            event.consume();
        }
    }
// hasta aqui es lo q se factorizo

    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            lbCustomerName.setText(customer.getCustomerName());

            lbLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            lbStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));
            chkCustomerVip.setSelected(customer.getCustomerAccount().isIsVIP());
            lbCustomerPhoneNumber.setText(String.valueOf(customer.getCustomerPhoneNumber()));
            //checkboxCustomerVip.setSelected(customer.isCustomerVip());
        } else {
            clearTextFields();
        }
    }


    private void configTextFields(){
        txtNewCustomerName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtNewCustomerPhoneNumber.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtNewCustomerLoyaltyPoints.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtEditCustomerLoyaltyPoints.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtEditCustomerStoreCredit.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtEditCustomerPhoneNumber.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }

    private void configTableColumns() {
        colCustomerId.setReorderable(false);

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colCustomerName.setReorderable(false);
        colCustomerName.setResizable(false);
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }



    private void configTableSelection() {
        tblCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCustomerDetails(newValue)
        );
        tblCustomers.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCustomerDetailsForUpdate(newValue)
        );
    }

    private void loadCustomerData() {
        List<Customer> customerList = customerManager.getCustomers();
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerList);
        setItemsToAllTables(customers);
    }

    // TODO: Recordatorio chepil; aqui algo esta mal. Aqui no de este metodo, en la clase de actualizar informacion de cliente. Clase de actualizar que no es clase es metodo. UWU
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        configTextFields();
        configTableColumns();
        configTableSelection();
        loadCustomerData();
        txtFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> searchCustomers());
    }

    private void setItemsToAllTables(ObservableList<Customer> customers) {
        tblCustomers.setItems(customers);


    }

    private void showCustomerDetailsForUpdate(Customer customer) {
        if (customer != null) {

            lbEditCustomerName.setText(customer.getCustomerName());
            txtFieldEditCustomerName.setText(customer.getCustomerName());
            //lbCustomerIdAuxiliar.setText(customer.getID());
            txtEditCustomerLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            txtEditCustomerStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));
            chkEditCustomerVipStatus.setSelected(customer.getCustomerAccount().isIsVIP());
            txtEditCustomerPhoneNumber.setText(String.valueOf(customer.getCustomerPhoneNumber()));
        } else {
            clearTextFields();
        }
    }

    public WnCustomersController(CustomerManager cm) {
        this.customerManager = cm;

    }





}
