package com.mycompany.masterrules.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.mycompany.masterrules.Model.DAOTemporal;

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

    private DAOTemporal chepo = new DAOTemporal();

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
        if(evt.equals(btnLogin)){
            if(event.getCharacter().equals("\r")){
                event.consume();
                String user = txtFieldUserName.getText();
                String pass = txtFieldPassword.getText();
                if (this.chepo.verificador(user, pass)) {
                    System.out.println("Usuario y contraseña correctos");
                    loadStage("/com/mycompany/masterrules/wnSideNavigationBar.fxml", event);
                }
            }

        }

    }

    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(btnLogin)) {
            String user = txtFieldUserName.getText();
            String pass = txtFieldPassword.getText();
            if (this.chepo.verificador(user, pass)) {
                System.out.println("Usuario y contraseña correctos");
                loadStage("/com/mycompany/masterrules/wnSideNavigationBar.fxml", event);
            } else {
                lbIncorrectCredential.setVisible(true);
            }

        }
    }

    private void loadStage(String url, Event event) {
        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(url));
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
        txtFieldUserName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtFieldPassword.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }
}
