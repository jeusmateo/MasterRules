package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.Customer;
import com.mycompany.masterrules.Model.CustomerManager;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;

public class WnCustomersController implements Initializable {

    private CustomerManager customerManager;

    @FXML
    private Button btnAcceptCredit;

   // @FXML
    //private PasswordField psswrdFieldAccesstoStoreCredit;

    @FXML
    private Button btnSaveNewCustomer;

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
    @FXML
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
    private CheckBox chkCustomerVip;
    @FXML
    private CheckBox chkNewCustomerVipStatus;

    @FXML
    private TextField txtNewCustomerName;
    @FXML
    private TextField txtNewCustomerPhoneNumber;
    @FXML
    private TextField txtNewCustomerLoyaltyPoints;

    @FXML
    private TextField txtEditCustomerStoreCredit;
    @FXML
    private TextField txtEditCustomerLoyaltyPoints;
    @FXML
    private CheckBox chkEditCustomerVipStatus;

    @FXML
    private TableView<Customer> tblCustomers;
    @FXML
    private TableColumn<Customer, String> colCustomerId;
    @FXML
    private TableColumn<Customer, String> colCustomerName;
    @FXML
    private TableColumn<Customer, String> tableColumnEditCustomerId;
    @FXML
    private TableColumn<Customer, String> tableColumnEditCustomerName;
    @FXML
    private TableView<Customer> tableViewCustomerEdit;

    @FXML
    private TableView<Customer> tblCustomers2;
    @FXML
    private TableColumn<Customer, String> colCustomerId2;
    
    @FXML
    private TableColumn<Customer, String> colCustomerName2;

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
                registerNewCustomer();
                clearTextFields(txtNewCustomerName, txtNewCustomerPhoneNumber, txtNewCustomerLoyaltyPoints);
                chkNewCustomerVipStatus.setSelected(false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (evt.equals(btnUpdateCustomerAccount)) {
            editCustomerInfo();
            clearTextFields(txtEditCustomerStoreCredit, txtEditCustomerLoyaltyPoints);
            lbCustomerIdAuxiliar.setText("");
            chkEditCustomerVipStatus.setSelected(false);
            scrEditCustomerAccount.setVisible(false);
            scrMainViewCustomerAccount.setVisible(true);

        }
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerManager.getCustomers());
        setItemsToAllTables(customers);
    }

    private void registerNewCustomer() throws Exception {
        String newCustomerName = txtNewCustomerName.getText();
        String newCustomerPhoneNumber = txtNewCustomerPhoneNumber.getText();
        String newCustomerLoyaltyPoints = txtNewCustomerLoyaltyPoints.getText();
        boolean newCustomerVipStatus = chkNewCustomerVipStatus.isSelected();
        customerManager.registerCustomer(newCustomerName, newCustomerPhoneNumber, newCustomerLoyaltyPoints, newCustomerVipStatus);

    }

    private void editCustomerInfo() {
        String customerId = lbCustomerIdAuxiliar.getText();
        String newCustomerStoreCreditQuantity = txtEditCustomerStoreCredit.getText();
        String updateCustomerLoyaltyPoints = txtEditCustomerLoyaltyPoints.getText();
        boolean updateCustomerVipStatus = chkEditCustomerVipStatus.isSelected();
        customerManager.updateCustomerData(customerId, updateCustomerLoyaltyPoints, updateCustomerVipStatus, newCustomerStoreCreditQuantity);
    }

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();
        if (evt.equals(txtNewCustomerName)) {
            if (event.getCharacter().equals("\r")) {
                txtNewCustomerPhoneNumber.requestFocus();
                event.consume();
            }
        } else if (evt.equals(txtEditCustomerStoreCredit)) {
            if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
                event.consume();
            }
        } else if (evt.equals(txtNewCustomerPhoneNumber)) {
            if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
                event.consume();
            } else if (event.getCharacter().equals("\r")) {
                txtNewCustomerLoyaltyPoints.requestFocus();
                event.consume();
            }
        } else if (evt.equals(txtNewCustomerLoyaltyPoints) || evt.equals(txtEditCustomerLoyaltyPoints)) {
            if (!event.getCharacter().matches("\\d") && !event.getCharacter().equals("\r")) {
                event.consume();
            } else if (event.getCharacter().equals("\r")) {
                chkNewCustomerVipStatus.requestFocus();
                event.consume();
            }
        }
    }

    private void showCustomerDetails(Customer customer) {
        if (customer != null) {
            lbCustomerName.setText(customer.getCustomerName());

            lbLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            lbStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));
            chkCustomerVip.setSelected(customer.getCustomerAccount().isIsVIP());
            lbCustomerPhoneNumber.setText(String.valueOf(customer.getCustomerPhoneNumber()));
            //txtAreaCustomerNote.setText(customer.getCustomerNote());
            //heckboxCustomerVip.setSelected(customer.isCustomerVip());
        } else {
            clearTextFields();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        txtNewCustomerName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtNewCustomerPhoneNumber.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtNewCustomerLoyaltyPoints.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtEditCustomerLoyaltyPoints.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtEditCustomerStoreCredit.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        List<Customer> chepo = customerManager.getCustomers();
        ObservableList<Customer> customers = FXCollections.observableArrayList(chepo);
        colCustomerId.setReorderable(false);
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colCustomerName.setReorderable(false);
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colCustomerId2.setReorderable(false);
        colCustomerId2.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colCustomerName2.setReorderable(false);
        colCustomerName2.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableColumnEditCustomerId.setReorderable(false);
        tableColumnEditCustomerId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tableColumnEditCustomerName.setReorderable(false);
        tableColumnEditCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        setItemsToAllTables(customers);
        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCustomerDetails(newValue));
        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCustomerDetailsForUpdate(newValue));
        tableViewCustomerEdit.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showCustomerDetailsForUpdate(newValue));
    }

    private void setItemsToAllTables(ObservableList<Customer> customers) {
        tblCustomers.setItems(customers);
        tblCustomers2.setItems(customers);
        tableViewCustomerEdit.setItems(customers);
    }

    private void showCustomerDetailsForUpdate(Customer customer) {
        if (customer != null) {
            //lbCustomerName.setText(customer.getCustomerName());
            lbCustomerIdAuxiliar.setText(customer.getID());
            txtEditCustomerLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            txtEditCustomerStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));
            chkEditCustomerVipStatus.setSelected(customer.getCustomerAccount().isIsVIP());
            //lbCustomerPhoneNumber.setText(String.valueOf(customer.getCustomerPhoneNumber()));
        } else {
            clearTextFields();
        }
    }

    public WnCustomersController(CustomerManager cm) {
        this.customerManager = cm;

    }



}
