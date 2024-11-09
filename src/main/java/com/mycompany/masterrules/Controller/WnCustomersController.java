package com.mycompany.masterrules.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.Customer;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WnCustomersController implements Initializable{



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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        ObservableList<Customer> customers = FXCollections.observableArrayList();
        customers.add(new Customer("Juan", "123"));
        customers.add(new Customer("Pedro", "456"));
        customers.add(new Customer("Maria", "789"));
        customers.add(new Customer("Jose", "101"));
        customers.add(new Customer("Luis", "112"));

        iDCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        nameCustomerColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhoneNumber"));
        tableViewCustomers.setItems(customers);
    }




}
