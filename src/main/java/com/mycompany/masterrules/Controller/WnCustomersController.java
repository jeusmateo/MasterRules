package com.mycompany.masterrules.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.Customer;
import com.mycompany.masterrules.Model.CustomerManager;

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

    private CustomerManager customerManager;

    @FXML
    private Button btnAcceptCredit;

    @FXML
    private PasswordField psswrdFieldAccesstoStoreCredit;

    @FXML
    private Button btnSaveNewCustomer;

    @FXML
    private Button btnBackEditCustomerAccount;

    @FXML
    private Button btnUpdateCustomerAccount;

    @FXML
    private AnchorPane guardarClienteNvo;

    @FXML
    private AnchorPane scrCustomerAccount;

    @FXML
    private AnchorPane scrEditCustomerAccount;

    @FXML
    private AnchorPane scrMainViewCustomerAccount;

    private AnchorPane scrViewInfoCustomer;

    @FXML
    private AnchorPane scrWarningCredit;

    @FXML
    private Tab tabNewCustomer;
    @FXML
    private Tab tabCustomerAccount;
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private TableView<Customer> tableViewCustomers;
    @FXML
    private TableColumn<Customer, String> iDCustomerColumn;
    @FXML
    private TableColumn<Customer, String> nameCustomerColumn;
    @FXML
    private Button btnEditAccount;
    @FXML
    private Label lbLoyaltyPoints;
    @FXML
    private Label lbCustomerName;
    @FXML
    private Label lbDebt;
    @FXML
    private Label lbStoreCredit;
    @FXML
    private CheckBox checkboxCustomerVip;
    @FXML
    private TextField textFieldNewCustomerName;
    @FXML
    private TextField textFieldNewCustomerPhoneNumber;
    @FXML
    private TextField textFieldNewCustomerLoyaltyPoints;

    @FXML
    private TextField txtFieldDebt;
    @FXML
    private CheckBox checkBoxNewCustomerVIPStatus;
    @FXML
    private TableView<Customer> tableViewCustomers2;
    @FXML
    private TableColumn<Customer, String> columnIdCustomer2;
    @FXML
    private TableColumn<Customer, String> columnCustomerName;
    @FXML
    private Label lbCustomerPhoneNumber;
    @FXML
    private TextField textFieldEditCustomerStoreCredit;
    @FXML
    private TextField textFieldEditCustomerLoyaltyPoints;
    @FXML
    private CheckBox checkBoxEditCustomerVipStatus;
    @FXML
    private TableColumn<Customer, String> tableColumnEditCustomerId;
    @FXML
    private TableColumn<Customer, String> tableColumnEditCustomerName;
    @FXML
    private TableView<Customer> tableViewCustomerEdit;
    @FXML
    private Label lbCustomerIdAuxiliar;

    public void setBtnShowInformation() {
        scrViewInfoCustomer.setVisible(true);
        scrMainViewCustomerAccount.setVisible(false);

    }

    public void setBtnBackViewInfoCustomer() {
        scrMainViewCustomerAccount.setVisible(true);
        scrViewInfoCustomer.setVisible(false);

        // esto creo que esta mal usado, debería ser clearTextFields(txtFieldDebt, textFieldEditCustomerStoreCredit, textFieldEditCustomerLoyaltyPoints ) o lgo parecido
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
        clearTextFields(txtFieldDebt, textFieldEditCustomerStoreCredit, textFieldEditCustomerLoyaltyPoints );

    }


    // falta que lo de atras no sea editable y se desenfoque
    @FXML
    private void setScrWarningCredit(){
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



    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();

        if (evt.equals(btnSaveNewCustomer)) {
            try {
                String newCustomerName = textFieldNewCustomerName.getText();
                String newCustomerPhoneNumber = textFieldNewCustomerPhoneNumber.getText();
                String newCustomerLoyaltyPoints = textFieldNewCustomerLoyaltyPoints.getText();
                boolean newCustomerVipStatus = checkBoxNewCustomerVIPStatus.isSelected();
                customerManager.registerCustomer(newCustomerName, newCustomerPhoneNumber, newCustomerLoyaltyPoints, newCustomerVipStatus);
                clearTextFields(textFieldNewCustomerName, textFieldNewCustomerPhoneNumber, textFieldNewCustomerLoyaltyPoints);
                checkBoxNewCustomerVIPStatus.setSelected(false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (evt.equals(btnUpdateCustomerAccount)) {
            String customerId = lbCustomerIdAuxiliar.getText();
            String newCustomerStoreCreditQuantity = textFieldEditCustomerStoreCredit.getText();
            String updateCustomerLoyaltyPoints = textFieldEditCustomerLoyaltyPoints.getText();
            boolean updateCustomerVipStatus = checkBoxEditCustomerVipStatus.isSelected();
            customerManager.updateCustomerData(customerId, updateCustomerLoyaltyPoints, updateCustomerVipStatus, newCustomerStoreCreditQuantity);
            clearTextFields(textFieldEditCustomerStoreCredit, textFieldEditCustomerLoyaltyPoints);
            lbCustomerIdAuxiliar.setText("");
            checkBoxEditCustomerVipStatus.setSelected(false);

        }
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerManager.getCustomers());
        setItemsToAllTables(customers);
    }

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(textFieldNewCustomerName)) {
            if (event.getCharacter().equals("\r")) {
                textFieldNewCustomerPhoneNumber.requestFocus();
                event.consume();
            }
        } else if (evt.equals(textFieldEditCustomerStoreCredit)) {
            if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
                event.consume();
            }
        } else if (evt.equals(textFieldNewCustomerPhoneNumber)) {
            if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
                event.consume();
            } else if (event.getCharacter().equals("\r")) {
                textFieldNewCustomerLoyaltyPoints.requestFocus();
                event.consume();
            }
        } else if (evt.equals(textFieldNewCustomerLoyaltyPoints) || evt.equals(textFieldEditCustomerLoyaltyPoints)) {
            if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
                event.consume();
            } else if (event.getCharacter().equals("\r")) {
                checkBoxNewCustomerVIPStatus.requestFocus();
                event.consume();
            }
        }
    }

    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            lbCustomerName.setText(customer.getCustomerName());

            lbLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            lbStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));
            checkboxCustomerVip.setSelected(customer.getCustomerAccount().isIsVIP());
            lbCustomerPhoneNumber.setText(String.valueOf(customer.getCustomerPhoneNumber()));
            //txtAreaCustomerNote.setText(customer.getCustomerNote());
            //heckboxCustomerVip.setSelected(customer.isCustomerVip());
        } else {
            clearTextFields();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        textFieldNewCustomerName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        textFieldNewCustomerPhoneNumber.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        textFieldNewCustomerLoyaltyPoints.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        textFieldEditCustomerLoyaltyPoints.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        textFieldEditCustomerStoreCredit.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        List<Customer> chepo = customerManager.getCustomers();
        ObservableList<Customer> customers = FXCollections.observableArrayList(chepo);
        iDCustomerColumn.setReorderable(false);
        iDCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameCustomerColumn.setReorderable(false);
        nameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        columnIdCustomer2.setReorderable(false);
        columnIdCustomer2.setCellValueFactory(new PropertyValueFactory<>("ID"));
        columnCustomerName.setReorderable(false);
        columnCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableColumnEditCustomerId.setReorderable(false);
        tableColumnEditCustomerId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tableColumnEditCustomerName.setReorderable(false);
        tableColumnEditCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        setItemsToAllTables(customers);
        tableViewCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCustomerDetails(newValue));
        tableViewCustomerEdit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCustomerDetailsForUpdate(newValue));
    }

    private void setItemsToAllTables(ObservableList<Customer> customers) {
        tableViewCustomers.setItems(customers);
        tableViewCustomers2.setItems(customers);
        tableViewCustomerEdit.setItems(customers);
    }

    private void showCustomerDetailsForUpdate(Customer customer) {
        if (customer != null) {
            //lbCustomerName.setText(customer.getCustomerName());
            lbCustomerIdAuxiliar.setText(customer.getID());
            textFieldEditCustomerLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            textFieldEditCustomerStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));
            checkBoxEditCustomerVipStatus.setSelected(customer.getCustomerAccount().isIsVIP());
            //lbCustomerPhoneNumber.setText(String.valueOf(customer.getCustomerPhoneNumber()));
        } else {
            clearTextFields();
        }
    }
    
    public WnCustomersController(CustomerManager cm){
        this.customerManager=cm;
        
    }



}
