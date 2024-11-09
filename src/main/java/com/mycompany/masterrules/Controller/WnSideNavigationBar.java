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
    void ProductsSection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/mycompany/masterrules/wnProducts.fxml"));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);
    }


    @FXML
    void cashRegisterAuditReportSection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/mycompany/masterrules/wnCashRegisterAuditReport.fxml"));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);

    }

    @FXML
    void customerSection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/mycompany/masterrules/wnCustomers.fxml"));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);

    }

    @FXML
    void inventorySection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/mycompany/masterrules/wnInventory.fxml"));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);


    }

    @FXML
    void reportSection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/mycompany/masterrules/wnReports.fxml"));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);

    }

    @FXML
    void salesSection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/mycompany/masterrules/wnSale.fxml"));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);
    }

    @FXML
    void usersSection(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/mycompany/masterrules/wnUsers.fxml"));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);

    }

    private void setPaneToFitScrPane(AnchorPane pane) {
        pane.prefWidthProperty().bind(scrPane.widthProperty());
        pane.prefHeightProperty().bind(scrPane.heightProperty());
    }


}
