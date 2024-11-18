package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Database.ProductDBManager;
import com.mycompany.masterrules.Model.cafeteria.CafeteriaMenu;
import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.Product;
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

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

public class WnProductsController implements Initializable {

private CafeteriaMenu cafeteriaMenu = new CafeteriaMenu();

    @FXML
    private Button btnAddCategoryCustomeCombo;

    @FXML
    private Button btnContinueCustomCombo;

    @FXML
    private Button btnContinueDefinedCombo;


    @FXML
    private Button btnCustomCombo;

    @FXML
    private Button btnDefinedCombo;

    @FXML
    private Button btnImportImage;


    @FXML
    private Button buttonConfirmCombo;

    @FXML
    private Button buttonCreateCombo;

    @FXML
    private Button buttonIconSearchFood;


    @FXML
    private FlowPane flowPaneMenuCards;


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
    private Tab tabEditCombo;


    @FXML
    private TableView<?> tableComboProducts;

    @FXML
    private TableColumn<?, ?> tableProductCombo;


    @FXML
    private TextField textFieldName111;


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
    private AnchorPane wnCreateFood;

    @FXML
    private AnchorPane wnEditCombo;


    @FXML
    private AnchorPane wnEditFood;

    @FXML
    private AnchorPane wnFood;
    @FXML
    private TableView<Product> tblFood;
    @FXML
    private TableView<Product> tblFood2;
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
    private TextField txtProductId_tabCreate;
    @FXML
    private TextField txtProductName_tabCreate;
    @FXML
    private TextField txtProductPrice_tabCreate;
    private TextField txtProductVipPrice_tabCreate;
    @FXML
    private TextField txtProductType_tabCreate;
    @FXML
    private Button btnCreateProduct;
    @FXML
    private Tab tabProduct;
    @FXML
    private Tab tabCreateProduct;
    @FXML
    private ImageView imgProduct_tabCreate;
    @FXML
    private Button btnImportImage_tabCreate;

    @FXML
    private TextField txtSearchProduct_tabCreate;
    @FXML
    private Button btnSearch_tabCreate;
    @FXML
    private TableColumn<?, ?> colProductId_tabCreate;
    @FXML
    private TableColumn<?, ?> colProductName_tabCreate;
    @FXML
    private TableColumn<?, ?> colProductType_tabCreate;
    @FXML
    private TableColumn<?, ?> colProductPrice_tabCreate;
    @FXML
    private TableColumn<?, ?> colProductVipPrice_tabCreate;
    @FXML
    private Tab tabEditProduct;
    @FXML
    private TextField txtSearchProduct_tabEdit;
    @FXML
    private Button btnSearch2;
    @FXML
    private TableColumn<?, ?> colProductId_tabEdit;
    @FXML
    private TableColumn<?, ?> colProductName_tabEdit;
    @FXML
    private TableColumn<?, ?> colProductType_tabEdit;
    @FXML
    private TableColumn<?, ?> colProductPrice_tabEdit;
    @FXML
    private TableColumn<?, ?> colProductVipPrice_tabEdit;
    @FXML
    private TextField txtProductName_tabEdit;
    @FXML
    private TextField txtProductType_tabEdit;
    @FXML
    private ImageView imgProduct_tabEdit;
    @FXML
    private Button btnImporImage_tabEdit;
    @FXML
    private TextField txtProductVipPrice_tabEdit;
    @FXML
    private TextField txtProductPrice_tabEdit;



    private void eventAction(ActionEvent event) {
//        Object source = event.getSource(); // Identifica la fuente del evento
//        try {
//            if (source.equals(btnCreateProduct)) {
//                // Si el evento proviene de btnCreateProduct
//
//                String id = txtProductId_tabCreate.getText();
//                String name = txtProductName_tabCreate.getText();
//                String type = txtProductType_tabCreate.getText();
//                BigDecimal price = new BigDecimal(txtProductPrice_tabCreate.getText());
//                BigDecimal vipPrice = new BigDecimal(txtProductVipPrice_tabCreate.getText());
//
//                // Crear el producto
//                Product product = new Product(id, name, type, price, vipPrice);
//
//                // Registrar el producto
//                cafeteriaMenu.registerNewProduct(product);
//            }
//        } catch (Exception e) {
//            System.err.println("Error al registrar el producto: " + e.getMessage());
//        }
    }


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

    @FXML
    private void eventAction(javafx.event.ActionEvent event) {
        Object source = event.getSource(); // Identifica la fuente del evento
        try {
            if (source.equals(btnCreateProduct)) {
                // Si el evento proviene de btnCreateProduct

                String id = txtProductId_tabCreate.getText();
                String name = txtProductName_tabCreate.getText();
                String type = txtProductType_tabCreate.getText();
                BigDecimal price = new BigDecimal(txtProductPrice_tabCreate.getText());
                System.out.println("CHEPO IMPRESION : "+ txtProductVipPrice_tabCreate);
                BigDecimal vipPrice = new BigDecimal(txtProductVipPrice_tabCreate.getText());

                // Crear el producto
                Product product = new Product(id, name, type, price, vipPrice);

                // Registrar el producto
                cafeteriaMenu.registerNewProduct(product);
            }
        } catch (Exception e) {
            System.err.println("Error al registrar el producto: " + e.getMessage());
        }
    }
}
