package com.mycompany.masterrules.Controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.retailsystem.POSManager;
import com.mycompany.masterrules.Model.users.UserManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class WnLoginController implements Initializable {

    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    private final UserManager userManager = new UserManager();

    // Componentes de la vista
    // --------------------------------------------------------------------------------------------

    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtFieldUserName;

    @FXML
    private PasswordField txtFieldPassword;

    @FXML
    private Label lbIncorrectCredential;

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtFieldUserName)) {
            handleTextInput(event, txtFieldPassword);
        } else if (evt.equals(txtFieldPassword)) {
            handlePasswordInput(event);
        } else if (evt.equals(btnLogin) && isEnterKey(event)) {
            event.consume();
            handleLogin(event);
        }
    }

    private void handleTextInput(KeyEvent event, Control nextField) {
        if (event.getCharacter().equals(" ")) {
            event.consume(); // Evita espacios en el nombre de usuario
        } else if (isEnterKey(event)) {
            nextField.requestFocus();
            event.consume(); // Mueve el foco al siguiente campo
        }
    }

    private void handlePasswordInput(KeyEvent event) {
        if (event.getCharacter().equals(" ")) {
            event.consume(); // Evita espacios en la contraseña
        } else if (isEnterKey(event)) {
            btnLogin.fire(); // Simula un clic en el botón "Iniciar sesión"
            event.consume();
        }
    }

//    private void handleLogin(KeyEvent event) {
//        event.consume();
//        String user = txtFieldUserName.getText();
//        String pass = txtFieldPassword.getText();
//
//        try {
//            if (loginValidator.validateUser(user, pass)) {
//
//                loadStage(event);
//            }
//        } catch (Throwable e) {
//            System.err.println("Error al validar usuario: " + e.getMessage());
//            lbIncorrectCredential.setVisible(true); // Muestra mensaje de error
//        }
//    }

    private boolean isEnterKey(KeyEvent event) {
        return event.getCharacter().equals("\r");
    }

    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(btnLogin)) {
            event.consume();
            handleLogin(event);
        }
    }

    private void handleLogin(Event event) {
        var obtainedUsername = txtFieldUserName.getText();
        var obtainedPassword = txtFieldPassword.getText();
        try {
            var foundUser = userManager.getUser(obtainedUsername, obtainedPassword);
            if(foundUser.isPresent()){
                POSManager.getInstance().setCurrentUser(foundUser.get());
                loadStage(event);
            }
        } catch (Exception e) {
            System.err.println("Error al validar usuario: " + e);
            lbIncorrectCredential.setVisible(true);
        }
    }

    private void loadStage(Event event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/mycompany/masterrules/wnSideNavigationBar.fxml")));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();  // Imprime la traza de error
            System.err.println("Error al cargar la ventana: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setupTextFieldFilters();
    }

    // Configura los filtros para los campos de texto
    private void setupTextFieldFilters() {
        txtFieldUserName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtFieldPassword.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }

}
