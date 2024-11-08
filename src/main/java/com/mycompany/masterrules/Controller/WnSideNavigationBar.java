package com.mycompany.masterrules.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class WnSideNavigationBar {

    @FXML
    private Button btnCashRegisterAuditReportSection;

    @FXML
    private Button btnCustomersSection;

    @FXML
    private Button btnInventorySection;

    @FXML
    private Button btnProductsSection;

    @FXML
    private Button btnReportsSection;

    @FXML
    private Button btnSalesSection;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnUsersSection;

    @FXML
    private Label labelUserName;

    @FXML
    private Label lbllSignOut;

    @FXML
    private AnchorPane scrPane;

    @FXML
    void ProductsSection(ActionEvent event) {

    }

    @FXML
    void cashRegisterAuditReportSection(ActionEvent event) {

    }

    @FXML
    void customerSection(ActionEvent event) {

    }

    @FXML
    void inventorySection(ActionEvent event) {

    }

    @FXML
    void reportSection(ActionEvent event) {

    }

    @FXML
    public void salesSection() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/resources/com/mycompany/masterrules/WnSale.fxml"));
        scrPane.getChildren().setAll(pane);


    }

    @FXML
    void usersSection(ActionEvent event) {

    }

}
