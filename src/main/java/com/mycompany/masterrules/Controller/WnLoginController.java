package com.mycompany.masterrules.Controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.users.LoginValidator;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class WnLoginController implements Initializable {

    private final LoginValidator loginValidator = new LoginValidator();

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtFieldUserName;
    @FXML
    private PasswordField txtFieldPassword;
    @FXML
    private Label lbIncorrectCredential;



    @FXML
    private void eventKey(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtFieldUserName)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            } else if (event.getCharacter().equals("\r")) {
                txtFieldPassword.requestFocus();
                event.consume();
            }

        } else  if (evt.equals(txtFieldPassword)) {
            if (event.getCharacter().equals(" ")) {
                event.consume();
            } else if (event.getCharacter().equals("\r")) {
                btnLogin.fire();
                event.consume();

            }
        }
        if(evt.equals(btnLogin) && event.getCharacter().equals("\r")){
                event.consume();
                String user = txtFieldUserName.getText();
                String pass = txtFieldPassword.getText();
                try {
                    if (loginValidator.validateUser(user, pass)) {
                        System.out.println("Usuario y contraseña correctos");
                        loadStage("/com/mycompany/masterrules/wnSideNavigationBar.fxml", event);
                    }
                } catch (Exception e) {
                    System.err.println("Error al validar usuario: " + e);
                    lbIncorrectCredential.setVisible(true);
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }


    }

    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(btnLogin)) {
            String user = txtFieldUserName.getText();
            String pass = txtFieldPassword.getText();
            try {
                if (loginValidator.validateUser(user, pass)) {
                    System.out.println("Usuario y contraseña correctos");
                    loadStage("/com/mycompany/masterrules/wnSideNavigationBar.fxml", event);
                } else {
                    lbIncorrectCredential.setVisible(true);
                }
            } catch (Exception e) {
                System.err.println("Error al validar usuario: " + e);
                lbIncorrectCredential.setVisible(true);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void loadStage(String url, Event event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(url)));
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
