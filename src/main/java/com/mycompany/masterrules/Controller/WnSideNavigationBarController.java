package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.customers.CustomerManager;
import com.mycompany.masterrules.Model.possystem.POSManager;
import com.mycompany.masterrules.Model.users.UserAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WnSideNavigationBarController implements Initializable {

    //Atributos de la clase
    // --------------------------------------------------------------------------------------------

    // Constantes para las rutas FXML
    private static final String PRODUCTS_VIEW = "/com/mycompany/masterrules/wnProducts.fxml";
    private static final String CASH_REGISTER_AUDIT_REPORT_VIEW = "/com/mycompany/masterrules/wnCashRegisterAuditReport.fxml";
    private static final String CUSTOMERS_VIEW = "/com/mycompany/masterrules/wnCustomers.fxml";
    private static final String INVENTORY_VIEW = "/com/mycompany/masterrules/wnInventory.fxml";
    private static final String REPORTS_VIEW = "/com/mycompany/masterrules/wnReports.fxml";
    private static final String SALES_VIEW = "/com/mycompany/masterrules/wnSale.fxml";
    private static final String USERS_VIEW = "/com/mycompany/masterrules/wnUsers.fxml";

    // Variables
    private String activeView = null;
    private UserAccount currentUser = null; //TODO: verificar que esto este bien implementado

    // Componentes de la vista
    // --------------------------------------------------------------------------------------------

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

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadSection(SALES_VIEW);
            activeView = SALES_VIEW;
        } catch (IOException e) {
            e.printStackTrace();
        }
        POSManager posManager = POSManager.getInstance();
        setCustomerName(posManager.getCurrentUser());
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
    void customerSection(ActionEvent event) {
        if (!CUSTOMERS_VIEW.equals(activeView)) {
            try {

                CustomerManager prueba = new CustomerManager();

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
        AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlPath)));
        scrPane.getChildren().setAll(pane);
        setPaneToFitScrPane(pane);
    }

    private void setPaneToFitScrPane(AnchorPane pane) {
        pane.prefWidthProperty().bind(scrPane.widthProperty());
        pane.prefHeightProperty().bind(scrPane.heightProperty());
    }

    @FXML
    private void btnOnClickSelected(MouseEvent event) {
        resetButtonStyles();
        Button btn = (Button) event.getSource();
        btn.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
    }

    private void resetButtonStyles() {
        btnSalesSection.setStyle("");
        btnProductsSection.setStyle("");
        btnCustomersSection.setStyle("");
        btnInventorySection.setStyle("");
        btnReportsSection.setStyle("");
        btnUsersSection.setStyle("");
        btnCashRegisterAuditReportSection.setStyle("");
    }

    @FXML
    private void signOut(ActionEvent event) {
        try {

            //TODO: implementar esto del login pero ya funciona el boton
            clearUserSession();

            // Cargar la vista de Login
            StackPane loginView = FXMLLoader.load(getClass().getResource("/com/mycompany/masterrules/wnLogin.fxml"));

            // Limpiar el contenedor de la vista actual y mostrar la vista de Login
            scrPane.getChildren().setAll(loginView);



            // Si la ventana wnSideBar es un Stage separado, puedes cerrarla
            Stage currentStage = (Stage) scrPane.getScene().getWindow();
            currentStage.close(); // Cierra la ventana actual

            // Si deseas abrir una nueva ventana con el login, puedes hacerlo así:
            Stage loginStage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/masterrules/wnLogin.fxml"));
            Scene loginScene = new Scene(loader.load());
            loginStage.setScene(loginScene);
            loginStage.show();

        } catch (IOException e) {
            // Manejo de errores, por ejemplo, mostrar un mensaje o hacer un log
            e.printStackTrace();
            showErrorDialog("Error al cerrar sesión", "Hubo un problema al intentar cerrar sesión.");
        }
    }

    private void clearUserSession() {
        // Aquí limpias los datos de sesión. Ejemplo:
        // - Eliminar cookies, tokens, objetos de usuario, etc.
        // Si tienes un sistema de autenticación, como un token o un usuario en memoria, lo borrarías aquí.

        // Ejemplo:
        // user = null;
        // authToken = null;

        // O si estás usando un gestor de sesiones:
        // sessionManager.clear();
    }


    public void setCustomerName(UserAccount userAccount) {
        if (userAccount != null) {
            labelUserName.setText(userAccount.getFullEmployeeName());
        } else {
            labelUserName.setText("Nombre no disponible");
        }
    }


    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
