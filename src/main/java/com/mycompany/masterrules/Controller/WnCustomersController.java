package com.mycompany.masterrules.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.Customer;

import com.mycompany.masterrules.Model.CustomerManager;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WnCustomersController implements Initializable{
    private CustomerManager customerManager;


    @FXML
    private Button btnSaveNewCustomer;

    @FXML
    private Button btnShowInformation;

    @FXML
    private Button btnBackViewInfoCustomer;

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
    private TextArea txtAreaCustomerNote;

    @FXML
    public void setBtnShowInformation() {
        scrViewInfoCustomer.setVisible(true);
        scrMainViewCustomerAccount.setVisible(false);

    }

    @FXML
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
        clearTextFields();

    }

    @FXML
    public void setBtnUpdateCustomerAccount() {
        //setBtnBackEditCustomerAccount();//regresa a la vista principal
    }

    private void clearTextFields(TextField... textFields) {
        for (TextField textField : textFields) {
            textField.clear(); // Limpia cada campo de texto proporcionado
        }
    }


    private void showCustomerDetails(Customer customer){
        if(customer != null){
            lbCustomerName.setText(customer.getCustomerName());

            lbLoyaltyPoints.setText(String.valueOf(customer.getCustomerAccount().getLoyaltyPoints()));
            lbStoreCredit.setText((String.valueOf(customer.getCustomerAccount().getStoreCredit())));

            //txtAreaCustomerNote.setText(customer.getCustomerNote());
            //heckboxCustomerVip.setSelected(customer.isCustomerVip());

        }else{
            clearTextFields();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        customerManager = new CustomerManager();
        List<Customer> chepo = customerManager.getCustomers();
        ObservableList<Customer> customers = FXCollections.observableArrayList(chepo);
        iDCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        nameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        tableViewCustomers.setItems(customers);
        tableViewCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)-> showCustomerDetails(newValue));
    }




}
