package com.mycompany.masterrules.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.awt.*;

/**
 * Controlador de la ventana de Reportes
 * @author Jimena Garcia
 */

public class WnReportsController {

    //COMPONENTES DE LA VENTANA DE REPORTES
    //-------------------------------------------------------------------------------------------

    @FXML
    private Button btnAcceptInflow;

    @FXML
    private Button btnAcceptOutflow;

    @FXML
    private Button btnDoInFlowReport;

    @FXML
    private Button btnDoOutFlowReport;

    @FXML
    private AnchorPane scrDoInflowCash;

    @FXML
    private AnchorPane scrDoOutflowReport;

    @FXML
    private AnchorPane scrDoReport;

    @FXML
    private TableView<?> tblInFlowReport;

    @FXML
    private TableView<?> tblOutFlowReport;

    @FXML
    private TextField txtFieldAmountInflow;

    @FXML
    private TextField txtFieldAmountOutflow;

    @FXML
    private TextArea txtOutflowReason;

    @FXML
    private TextArea txtReasonInflow;

    // metodos de la ventana de reportes
    //-------------------------------------------------------------------------------------------


    /**
     * Muestra la ventana de reportes de entrada de efectivo
     */
    public void setBtnDoInFlowReport() {
        scrDoInflowCash.setVisible(true); // Muestra la ventana de reportes de entrada de efectivo
        scrDoReport.setVisible(false); // Oculta la ventana principal de reportes
    }

    /**
     * Muestra la ventana principal de reportes.
     * Sale de la ventana DoInflow.
     * Acepta la entrada de efectivo
     */
    public void setBtnAcceptInflow() {
        scrDoInflowCash.setVisible(false); // Oculta la ventana de reportes de entrada de efectivo
        scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
    }

    /**
     * Muestra la ventana de reportes de salida de efectivo
     */
    public void setBtnDoOutFlowReport() {
        scrDoOutflowReport.setVisible(true); // Muestra la ventana de reportes de salida de efectivo
        scrDoReport.setVisible(false); // Oculta la ventana principal de reportes
    }

    /**
     * Muestra la ventana principal de reportes.
     * Sale de la ventana DoOutflow.
     * Acepta la salida de efectivo
     */
    public void setBtnAcceptOutflow() {
        scrDoOutflowReport.setVisible(false); // Oculta la ventana de reportes de salida de efectivo
        scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
    }

    /**
     * Muestra la ventana principal de reportes. Sale de la ventana DoInflow
     */
    public void exitDoInflow() {
        clearTextFields(txtFieldAmountInflow); // Limpia los campos de texto proporcionados
        txtReasonInflow.clear(); // Limpia el campo de texto de la razón de la entrada de efectivo
        scrDoInflowCash.setVisible(false); // Oculta la ventana de reportes de entrada de efectivo
        scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
    }

    /**
     * Muestra la ventana principal de reportes. Sale de la ventana DoOutflow
     */
    public void exitDoOutflow() {
        clearTextFields(txtFieldAmountOutflow); // Limpia los campos de texto proporcionados
        txtOutflowReason.clear(); // Limpia el campo de texto de la razón de la salida de efectivo
        scrDoOutflowReport.setVisible(false); // Oculta la ventana de reportes de salida de efectivo
        scrDoReport.setVisible(true); // Muestra la ventana principal de reportes
    }

    /**
     * Limpia los campos de texto proporcionados
     * @param textFields Campos de texto a limpiar
     */
    private void clearTextFields(TextField... textFields) {
        // Limpia cada campo de texto proporcionado
        for (TextField textField : textFields) {
            textField.clear();
        }
    }

}