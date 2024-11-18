package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.model.cafeteria.CafeteriaManager;
import com.mycompany.masterrules.model.cafeteria.Combo;
import com.mycompany.masterrules.model.cafeteria.Product;
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

    private CafeteriaManager cafeteriaManager;
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



    //Tabla para combos
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
    private TableView<?> tableComboProducts;

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
    private TableView<Product> tblFood;
    @FXML
    private TableColumn<Product, String> colFoodID;
    @FXML
    private TableColumn<Product, String> colFoodName;
    @FXML
    private TableColumn<Product, String> colFoodCategory;
    @FXML
    private TableColumn<Product, String> colFoodPrice;
    @FXML
    private TableColumn<Product, String> colFoodVipPrice;
    @FXML
    private TableView<Product> tblFood2;
    @FXML
    private TableColumn<Product, String> colFoodID2;
    @FXML
    private TableColumn<Product, String> colFoodName2;
    @FXML
    private TableColumn<Product, String> colFoodCategory2;
    @FXML
    private TableView<Product> tblBeverages;
    @FXML
    private TableColumn<Product, String> colBeveragesID;
    @FXML
    private TableColumn<Product, String> colBeveragesName;
    @FXML
    private TableColumn<Product, String> colBeveragesCategory;
    @FXML
    private TableColumn<Product, String> colBeveragesPrice;
    @FXML
    private TableColumn<Product, String> colBeveragesVipPrice;
    @FXML
    private TableView<Product> tblBeverages2;
    @FXML
    private TableColumn<Product, String> colBeveragesID2;
    @FXML
    private TableColumn<Product, String> colBeveragesName2;
    @FXML
    private TableColumn<Product, String> colBeveragesCategory2;
    @FXML
    private TableView<Combo> tblCombos;
    @FXML
    private TableColumn<Combo, String> colComboID;
    @FXML
    private TableColumn<Combo, String> colComboName;
    @FXML
    private TableColumn<Combo, String> colComboPrice;
    @FXML
    private TableColumn<Combo, String> colComboVipPrice;
    @FXML
    private AnchorPane scrChooseComboEdition;
    @FXML
    private Button btnEditDefinedCombo;
    @FXML
    private Button btnEditCustomCombo;
    @FXML
    private AnchorPane scrEditDefinedCombo;
    @FXML
    private AnchorPane scrEditCustomeCombo;
    @FXML
    private Button btnAddCategoryCustomeCombo1;
    @FXML
    private Button btnContinueCustomCombo1;

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
        /*
        ObservableList<Product> cashOutReports = FXCollections.observableArrayList(cashRegisterAuditReportManager.getCurrentCashRegisterAuditReport().getCashOutFlowReports());
        TableColumnCashOutReason.setReorderable(false);
        TableColumnCashOutReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        TableColumnCashOutDate.setReorderable(false);
        TableColumnCashOutDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumnCashOutQuantity.setReorderable(false);
        TableColumnCashOutQuantity.setCellValueFactory(new PropertyValueFactory<>("cashAmount"));
        tableViewlOutFlowReport.setItems(cashOutReports);
         */
    }
}
