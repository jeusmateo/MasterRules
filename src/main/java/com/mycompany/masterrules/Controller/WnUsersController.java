package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.UserAccount;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * Controlador de la ventana de Usuarios
 * @author Jimena Garcia
 */

public class WnUsersController implements Initializable {

    @FXML
    private AnchorPane verCliente;

    @FXML
    private AnchorPane verCliente1;
    @FXML
    private TableView<UserAccount> tblUserAccount;
    @FXML
    private TableColumn<UserAccount, String> colUserID;
    @FXML
    private TableColumn<UserAccount, String> colUserName;
    @FXML
    private TableView<UserAccount> tblUserAccount2;
    @FXML
    private TableColumn<UserAccount, String> colUserID2;
    @FXML
    private TableColumn<UserAccount, String> colUserName2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
