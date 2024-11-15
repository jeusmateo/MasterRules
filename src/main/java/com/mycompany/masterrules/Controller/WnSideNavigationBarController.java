package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.CustomerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WnSideNavigationBarController implements Initializable {

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

    // Variable para almacenar la vista activa
    private String activeView = null;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializar por defecto maximizado

//        scrPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
//            if (newScene != null) {
//                newScene.windowProperty().addListener((windowObservable, oldWindow, newWindow) -> {
//                    if (newWindow != null) {
//                        Stage stage = (Stage) newWindow;
//                        stage.setMaximized(true);  // Maximiza la ventana
//                        stage.setResizable(true);  // Permitir redimensionamiento
//                    }
//                });
//            }
//        });
        try {
            loadSection(SALES_VIEW);
            activeView = SALES_VIEW;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ProductsSection(ActionEvent event) throws IOException {
        if (!PRODUCTS_VIEW.equals(activeView)) {
            loadSection(PRODUCTS_VIEW);
            activeView = PRODUCTS_VIEW;
        }
    }

    @FXML
    void cashRegisterAuditReportSection(ActionEvent event) throws IOException {
        if (!CASH_REGISTER_AUDIT_REPORT_VIEW.equals(activeView)) {
            loadSection(CASH_REGISTER_AUDIT_REPORT_VIEW);
            activeView = CASH_REGISTER_AUDIT_REPORT_VIEW;
        }
    }

    @FXML
    void customerSection(ActionEvent event) throws IOException, Exception {
        if (!CUSTOMERS_VIEW.equals(activeView)) {
            try {
                System.out.println("Chepo");
                CustomerManager prueba = new CustomerManager();
                prueba.registerCustomer("Juan", "123", "", true);
                prueba.registerCustomer("Pedro", "456", "0", true);
                prueba.registerCustomer("Maria", "789", "", false);
                prueba.registerCustomer("Jose", "101", "0", false);
                prueba.registerCustomer("Luis", "112", "100", false);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(CUSTOMERS_VIEW));

                loader.setController(new WnCustomersController(prueba));
                AnchorPane pane = loader.load();
                scrPane.getChildren().setAll(pane);
                setPaneToFitScrPane(pane);
                activeView = CUSTOMERS_VIEW;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void inventorySection(ActionEvent event) throws IOException {
        if (!INVENTORY_VIEW.equals(activeView)) {
            loadSection(INVENTORY_VIEW);
            activeView = INVENTORY_VIEW;
        }
    }

    @FXML
    void reportSection(ActionEvent event) throws IOException {
        if (!REPORTS_VIEW.equals(activeView)) {
            loadSection(REPORTS_VIEW);
            activeView = REPORTS_VIEW;
        }
    }

    @FXML
    void salesSection(ActionEvent event) throws IOException {
        if (!SALES_VIEW.equals(activeView)) {
            loadSection(SALES_VIEW);
            activeView = SALES_VIEW;
        }
    }

    @FXML
    void usersSection(ActionEvent event) throws IOException {
        if (!USERS_VIEW.equals(activeView)) {
            loadSection(USERS_VIEW);
            activeView = USERS_VIEW;
        }
    }

    private void loadSection(String fxmlPath) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlPath));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);
    }

    private void setPaneToFitScrPane(AnchorPane pane) {
        pane.prefWidthProperty().bind(scrPane.widthProperty());
        pane.prefHeightProperty().bind(scrPane.heightProperty());
    }
}
