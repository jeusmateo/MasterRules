package com.mycompany.masterrules.Controller;

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

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controlador para la ventana de inicio de sesión.
 * Gestiona la autenticación de usuarios y la navegación a la siguiente ventana.
 */
public class WnLoginController implements Initializable {

    // Atributos de la clase
    // --------------------------------------------------------------------------------------------

    /**
     * Gestor de usuarios para manejar las operaciones de autenticación.
     */
    private final UserManager userManager = new UserManager();

    // Componentes de la vista
    // --------------------------------------------------------------------------------------------

    /**
     * Botón de inicio de sesión.
     */
    @FXML
    private Button btnLogin;

    /**
     * Campo de texto para el nombre de usuario.
     */
    @FXML
    private TextField txtFieldUserName;

    /**
     * Campo de texto para la contraseña.
     */
    @FXML
    private PasswordField txtFieldPassword;

    /**
     * Etiqueta para mostrar mensaje de error.
     */
    @FXML
    private Label lbIncorrectCredential;

    // Métodos de la clase
    // --------------------------------------------------------------------------------------------

    /**
     * Maneja los eventos de teclado en los campos de texto.
     *
     * @param event El evento de teclado.
     */
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

    /**
     * Maneja la entrada de texto en el campo de nombre de usuario.
     *
     * @param event El evento de teclado.
     * @param nextField El siguiente campo a enfocar.
     */
    private void handleTextInput(KeyEvent event, Control nextField) {
        if (event.getCharacter().equals(" ")) {
            event.consume(); // Evita espacios en el nombre de usuario
        } else if (isEnterKey(event)) {
            nextField.requestFocus();
            event.consume(); // Mueve el foco al siguiente campo
        }
    }

    /**
     * Maneja la entrada de texto en el campo de contraseña.
     *
     * @param event El evento de teclado.
     */
    private void handlePasswordInput(KeyEvent event) {
        if (event.getCharacter().equals(" ")) {
            event.consume(); // Evita espacios en la contraseña
        } else if (isEnterKey(event)) {
            btnLogin.fire(); // Simula un clic en el botón "Iniciar sesión"
            event.consume();
        }
    }

    /**
     * Verifica si la tecla presionada es la tecla Enter.
     *
     * @param event El evento de teclado.
     * @return true si la tecla presionada es Enter, false en caso contrario.
     */
    private boolean isEnterKey(KeyEvent event) {
        return event.getCharacter().equals("\r");
    }

    /**
     * Maneja las acciones de los botones en la interfaz de usuario.
     *
     * @param event El evento de acción.
     */
    @FXML
    private void eventAction(ActionEvent event) {
        Object evt = event.getSource();
        if (evt.equals(btnLogin)) {
            event.consume();
            handleLogin(event);
        }
    }

    /**
     * Maneja el proceso de inicio de sesión.
     *
     * @param event El evento de acción.
     */
    private void handleLogin(Event event) {
        var obtainedUsername = txtFieldUserName.getText();
        var obtainedPassword = txtFieldPassword.getText();
        try {
            var foundUser = userManager.getUser(obtainedUsername, obtainedPassword);
            if (foundUser.isPresent()) {
                POSManager.getInstance().setCurrentUser(foundUser.get());
                loadStage(event);
            }
        } catch (Exception e) {
            System.err.println("Error al validar usuario: " + e);
            lbIncorrectCredential.setVisible(true);
        }
    }

    /**
     * Carga la siguiente ventana después de un inicio de sesión exitoso.
     *
     * @param event El evento de acción.
     */
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

    /**
     * Inicializa el controlador después de que su raíz haya sido procesada.
     *
     * @param arg0 La ubicación utilizada para resolver rutas relativas para el objeto raíz, o null si no se conoce la ubicación.
     * @param arg1 Los recursos utilizados para localizar el objeto raíz, o null si no se han localizado recursos.
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setupTextFieldFilters();
    }

    /**
     * Configura los filtros para los campos de texto.
     */
    private void setupTextFieldFilters() {
        txtFieldUserName.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
        txtFieldPassword.addEventFilter(KeyEvent.KEY_TYPED, this::eventKey);
    }

}