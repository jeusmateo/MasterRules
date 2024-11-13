package com.mycompany.masterrules.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WnProductsController implements Initializable {

    @FXML
    private Button btnAddCategoryCustomeCombo;

    @FXML
    private Button btnContinueCustomCombo;

    @FXML
    private Button btnContinueDefinedCombo;

    @FXML
    private Button btnCreateFood;

    @FXML
    private Button btnCustomCombo;

    @FXML
    private Button btnDefinedCombo;

    @FXML
    private Button btnImportImage;

    @FXML
    private Button btnImportImageFood;

    @FXML
    private Button buttonConfirmCombo;

    @FXML
    private Button buttonCreateCombo;

    @FXML
    private Button buttonIconSearchFood;

    @FXML
    private Button buttonSearchFood;

    @FXML
    private FlowPane flowPaneMenuCards;

    @FXML
    private ImageView imageViewFood;

    @FXML
    private ImageView imgCombo;

    @FXML
    private AnchorPane scrCreateComboFinalStep;

    @FXML
    private AnchorPane scrCreateComboStart;

    @FXML
    private AnchorPane scrCreateComboStepTwo;

    @FXML
    private AnchorPane scrCreateCustomeCombo;

    @FXML
    private AnchorPane scrCreateDefinedCombo;

    @FXML
    private ScrollPane scrollPaneMenu;

    @FXML
    private Tab tabCombos;

    @FXML
    private Tab tabCreateCombo;

    @FXML
    private Tab tabCreateDrinks;

    @FXML
    private Tab tabCreateFood;

    @FXML
    private Tab tabDrinks;

    @FXML
    private Tab tabEditCombo;

    @FXML
    private Tab tabEditDrinks;

    @FXML
    private Tab tabEditFood;

    @FXML
    private Tab tabFood;

    @FXML
    private TableColumn<?, ?> tableColumnFoodID;

    @FXML
    private TableColumn<?, ?> tableColumnFoodName;

    @FXML
    private TableColumn<?, ?> tableColumnFoodPrice;

    @FXML
    private TableColumn<?, ?> tableColumnFoodType;

    @FXML
    private TableColumn<?, ?> tableColumnFoodVIPPrice;

    @FXML
    private TableView<?> tableComboProducts;

    @FXML
    private TableView<?> tableFood;

    @FXML
    private TableColumn<?, ?> tableProductCombo;

    @FXML
    private TextField textFieldIDFood;

    @FXML
    private TextField textFieldName1;

    @FXML
    private TextField textFieldName111;

    @FXML
    private TextField textFieldNameDrinks;

    @FXML
    private TextField textFieldNameFood;

    @FXML
    private TextField textFieldPriceFood;

    @FXML
    private TextField textFieldSearchFood;

    @FXML
    private TextField textFieldType;

    @FXML
    private TextField textFieldVIPPriceFood;

    @FXML
    private TextField txtFieldIDCombo;

    @FXML
    private TextField txtFieldNameCombo;

    @FXML
    private TextField txtFieldPriceCombo;

    @FXML
    private TextField txtFieldVIPPriceCombo;

    @FXML
    private AnchorPane wnCreateComboStepOne;

    @FXML
    private AnchorPane wnCreateDrinks;

    @FXML
    private AnchorPane wnCreateFood;

    @FXML
    private AnchorPane wnEditCombo;

    @FXML
    private AnchorPane wnEditDrinks;

    @FXML
    private AnchorPane wnEditFood;

    @FXML
    private AnchorPane wnFood;

    @FXML
    void setScrCreateComboFinalStep(MouseEvent event) {

        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(false);
        scrCreateDefinedCombo.setVisible(false);
        scrCreateComboFinalStep.setVisible(true);

    }

    @FXML
    void setScrCreateComboStepTwo(MouseEvent event) {
            scrCreateComboStart.setVisible(false);
            scrCreateComboStepTwo.setVisible(true);
    }

    @FXML
    void setScrCreateCustomeCombo(MouseEvent event) {
        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(false);
        scrCreateCustomeCombo.setVisible(true);


    }

    @FXML
    void setScrCreateDefinedCombo(MouseEvent event) {
        scrCreateComboStart.setVisible(false);
        scrCreateComboStepTwo.setVisible(false);
        scrCreateDefinedCombo.setVisible(true);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
