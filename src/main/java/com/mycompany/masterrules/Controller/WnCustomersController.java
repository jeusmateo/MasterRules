package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.customers.CustomerManager;

import java.math.BigDecimal;
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

    //Atributos de la clase
    //--------------------------------------------------------------------------------------------

    private CustomerManager customerManager;
    private static final Logger logger = Logger.getLogger(WnCustomersController.class.getName());
    private boolean accessCodeRequested;

    //Componentes de la vista
    //--------------------------------------------------------------------------------------------

    //Campos de texto
    @FXML
    private PasswordField pswFieldAccesCode;
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
    private TextField txtFieldAccessCode;

    //Botones
    @FXML
    private Button btnAcceptCredit;
    @FXML
    private Button btnSaveNewCustomer;
    @FXML
    private Button btnUpdateCustomerAccount;
    @FXML
    private Button btnDeleteCustomer;

    //Paneles
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

    //Tablas y columnas
    @FXML
    private Tab tabNewCustomer;
    @FXML
    private Tab tabCustomerAccount;

    //Etiquetas
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

    //CheckBox
    @FXML
    private CheckBox chkCustomerVip;
    @FXML
    private CheckBox chkNewCustomerVipStatus;
    @FXML
    private CheckBox chkEditCustomerVipStatus;

    //Tablas y columnas
    @FXML
    private TableView<Customer> tblCustomers;
    @FXML
    private TableColumn<Customer, String> colCustomerId;
    @FXML
    private TableColumn<Customer, String> colCustomerName;

    //Métodos de la clase
    //--------------------------------------------------------------------------------------------

    @FXML
    private void searchCustomers() {
        String searchText = txtFieldSearch.getText().toLowerCase();
        List<Customer> filteredCustomers = customerManager.getCustomers().stream()
                .filter(customer -> customer.getCustomerName().toLowerCase().contains(searchText))
                .toList();
        ObservableList<Customer> filteredObservableList = FXCollections.observableArrayList(filteredCustomers);
        tblCustomers.setItems(filteredObservableList);
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
        accessCodeRequested =false;
    }

    @FXML
    private void setScrWarningCredit() {
        if(!accessCodeRequested){
            scrWarningCredit.setVisible(true);
            pswFieldAccesCode.requestFocus();
        }

    }

    @FXML
    private void setButtonAcceptCredit() {

        Customer selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
        //Si logica de el codgio es verdadero el access se vuelve verdadero;
       String password = String.valueOf(pswFieldAccesCode.getText());
       if(selectedCustomer!= null){
           if(selectedCustomer.getCustomerAccount().getAccessCode().equals(password)){
               accessCodeRequested = true;
           }
       }

        scrWarningCredit.setVisible(false);
    }


    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear();
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
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(btnSaveNewCustomer)) {
            handleSaveNewCustomer();
        } else if (evt.equals(btnUpdateCustomerAccount)) {
            handleUpdateCustomerAccount();
        } else if (evt.equals(btnDeleteCustomer)) {
            handleDeleteCustomer();
        }
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerManager.getCustomers());
        setItemsToAllTables(customers);
    }

    private void handleSaveNewCustomer() {
        try {
            registerNewCustomer();
            clearTextFields(txtNewCustomerName, txtNewCustomerPhoneNumber, txtNewCustomerLoyaltyPoints);
            chkNewCustomerVipStatus.setSelected(false);
        }catch (IllegalArgumentException e){
            showAlert("Error", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error registrando un nuevo cliente", e);
            showAlert("Error", "Ocurrió un error al registrar el nuevo cliente. Por favor, inténtalo nuevamente.");
        }
    }

    private void handleUpdateCustomerAccount() {
        try {
            editCustomerInfo();
            clearTextFields(txtEditCustomerStoreCredit, txtEditCustomerLoyaltyPoints);
            chkEditCustomerVipStatus.setSelected(false);
            scrEditCustomerAccount.setVisible(false);
            scrMainViewCustomerAccount.setVisible(true);
            accessCodeRequested = false;
        }catch (IllegalArgumentException e){
            showAlert("Error", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error actualizando la cuenta del cliente", e);
            showAlert("Error", "Ocurrió un problema al actualizar la cuenta del cliente. Por favor, inténtalo de nuevo.");
        }
    }

    private void handleDeleteCustomer() {
        try {
            Customer selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
            if (selectedCustomer == null) {
                showAlert("Advertencia", "Por favor, selecciona un cliente para eliminar.");
                logger.log(Level.WARNING, "No se seleccionó ningún cliente para eliminar.");
                return;
            }
            customerManager.removeCustomer(selectedCustomer.getId());
            clearTextFields(txtEditCustomerStoreCredit, txtEditCustomerLoyaltyPoints);
            chkEditCustomerVipStatus.setSelected(false);
            scrEditCustomerAccount.setVisible(false);
            scrMainViewCustomerAccount.setVisible(true);
        }catch (IllegalArgumentException e){
            showAlert("Error", e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error eliminando cliente", e);
            showAlert("Error", "Ocurrió un problema al eliminar el cliente. Por favor, inténtalo nuevamente.");
        }
    }

    private void registerNewCustomer() {
        String newCustomerName = txtNewCustomerName.getText();
        String newCustomerPhoneNumber = txtNewCustomerPhoneNumber.getText();
        String newCustomerLoyaltyPoints = txtNewCustomerLoyaltyPoints.getText();
        boolean newCustomerVipStatus = chkNewCustomerVipStatus.isSelected();
        String acessCode=txtFieldAccessCode.getText();

        customerManager.registerCustomer(newCustomerName, newCustomerPhoneNumber, newCustomerLoyaltyPoints, newCustomerVipStatus,acessCode);
    }

    private void editCustomerInfo() {
        Customer selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
        if (selectedCustomer == null) {
            showAlert("Error", "Por favor, selecciona un cliente para editar.");
            return;
        }
        String newCustomerName = txtFieldEditCustomerName.getText();
        String newCustomerStoreCreditQuantity=String.valueOf(selectedCustomer.getCustomerAccount().getStoreCredit());;
        if(accessCodeRequested=true){
             newCustomerStoreCreditQuantity = txtEditCustomerStoreCredit.getText();
        }
        String updateCustomerLoyaltyPoints = txtEditCustomerLoyaltyPoints.getText();
        boolean updateCustomerVipStatus = chkEditCustomerVipStatus.isSelected();
        String newCustomerPhoneNumber = txtEditCustomerPhoneNumber.getText();

        if (newCustomerName.isEmpty() || newCustomerStoreCreditQuantity.isEmpty() || updateCustomerLoyaltyPoints.isEmpty() || newCustomerPhoneNumber.isEmpty()) {
            showAlert("Error", "Por favor, llena todos los campos.");
            return;
        }

        selectedCustomer.setCustomerName(newCustomerName);
        selectedCustomer.getCustomerAccount().setStoreCredit(new BigDecimal(newCustomerStoreCreditQuantity));
        selectedCustomer.getCustomerAccount().setLoyaltyPoints(Integer.parseInt(updateCustomerLoyaltyPoints));
        selectedCustomer.getCustomerAccount().setIsVIP(updateCustomerVipStatus);
        selectedCustomer.setCustomerPhoneNumber(newCustomerPhoneNumber);

        customerManager.updateCustomerData(selectedCustomer);
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
        if (!event.getCharacter().matches("[\\p{L} ]")) {
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
        String phoneNumber = txtNewCustomerPhoneNumber.getText();
        if (!event.getCharacter().matches("\\d{0,10}") && !event.getCharacter().equals("\r") || phoneNumber.length() >= 10) {
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

    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            lbCustomerName.setText(customer.getCustomerName());
            lbLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            lbStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));
            chkCustomerVip.setSelected(customer.getCustomerAccount().isIsVIP());
            lbCustomerPhoneNumber.setText(String.valueOf(customer.getCustomerPhoneNumber()));
        } else {
            clearTextFields();
        }
    }

    private void configTextFields() {
        txtNewCustomerName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtNewCustomerPhoneNumber.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtNewCustomerLoyaltyPoints.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtEditCustomerLoyaltyPoints.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtEditCustomerStoreCredit.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtEditCustomerPhoneNumber.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }

    private void configTableColumns() {
        colCustomerId.setReorderable(false);
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        accessCodeRequested =false;
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
            txtEditCustomerLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            txtEditCustomerStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));
            chkEditCustomerVipStatus.setSelected(customer.getCustomerAccount().isIsVIP());
            txtEditCustomerPhoneNumber.setText(String.valueOf(customer.getCustomerPhoneNumber()));
        } else {
            clearTextFields();
        }
    }

    @FXML
    private void displaySelected(javafx.scene.input.MouseEvent event) {
        Customer selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();
        showCustomerDetailsForUpdate(selectedCustomer);
        accessCodeRequested =false;
    }

    public WnCustomersController(CustomerManager cm) {
        this.customerManager = cm;
    }
}