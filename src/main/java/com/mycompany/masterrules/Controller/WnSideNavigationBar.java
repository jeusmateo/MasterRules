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

    // Definir constantes para las rutas FXML
    private static final String PRODUCTS_VIEW = "/com/mycompany/masterrules/wnProducts.fxml";
    private static final String CASH_REGISTER_AUDIT_REPORT_VIEW = "/com/mycompany/masterrules/wnCashRegisterAuditReport.fxml";
    private static final String CUSTOMERS_VIEW = "/com/mycompany/masterrules/wnCustomers.fxml";
    private static final String INVENTORY_VIEW = "/com/mycompany/masterrules/wnInventory.fxml";
    private static final String REPORTS_VIEW = "/com/mycompany/masterrules/wnReports.fxml";
    private static final String SALES_VIEW = "/com/mycompany/masterrules/wnSale.fxml";
    private static final String USERS_VIEW = "/com/mycompany/masterrules/wnUsers.fxml";



    @FXML
    void ProductsSection(ActionEvent event) throws IOException {
        loadSection(PRODUCTS_VIEW);
    }


    @FXML
    void cashRegisterAuditReportSection(ActionEvent event) throws IOException {
        loadSection(CASH_REGISTER_AUDIT_REPORT_VIEW);

    }

    @FXML
    void customerSection(ActionEvent event) throws IOException {
       loadSection(CUSTOMERS_VIEW);

    }

    @FXML
    void inventorySection(ActionEvent event) throws IOException {
       loadSection(INVENTORY_VIEW);
    }

    @FXML
    void reportSection(ActionEvent event) throws IOException {
       loadSection(REPORTS_VIEW);
    }

    @FXML
    void salesSection(ActionEvent event) throws IOException {
        loadSection(SALES_VIEW);
    }

    @FXML
    void usersSection(ActionEvent event) throws IOException {
       loadSection(USERS_VIEW);
    }

    // Método extraído para cargar y ajustar el panel
    private void loadSection(String fxmlPath) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlPath));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);
    }

    // Método para ajustar el panel cargado al tamaño del contenedor
    private void setPaneToFitScrPane(AnchorPane pane) {
        pane.prefWidthProperty().bind(scrPane.widthProperty());
        pane.prefHeightProperty().bind(scrPane.heightProperty());
    }




}
