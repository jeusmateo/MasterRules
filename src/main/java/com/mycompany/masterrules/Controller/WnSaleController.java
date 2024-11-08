package com.mycompany.masterrules.Controller;

import com.mycompany.masterrules.Model.Product;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controlador de la ventana de Venta
 * @author campv
 */
public class WnSaleController implements Initializable{
    //COMPONENTES DE LA VENTANA DE LA CARTILLA DE MENU QUE MUESTRA LOS PRODUCTOS
    //-------------------------------------------------------------------------------------------
    @FXML
    private Button btnContinue;

    @FXML
    private AnchorPane menuWindow;
    
    @FXML
    private VBox menuOrderOptionsBox;
    
    @FXML
    private ScrollPane menuCardsScroller;
    
    @FXML
    private FlowPane menuCards=new FlowPane();

    @FXML
    private TabPane menuCategories;
    
    
    //COMPONENTES DE LA VENTANA QUE SE MUESTRA AL CONTINUAR LA ORDEN
    //-------------------------------------------------------------------------------------------
    @FXML
    private AnchorPane continueOrderWindow;
    
    @FXML
    private VBox continueOrderOptionsBox;
    
    @FXML
    private HBox tableNumberBox;
    
    @FXML
    private TextField inputClientName;
    
    
    //OTROS OBJETOS
    //-------------------------------------------------------------------------------------------
    private ObservableList<Product> cardDataList=FXCollections.observableArrayList();
    public static int cont=0;//borrar depues, solo es de prueba
    private WnSaleController wnSaleSection;
    private Stage stage;
    private WnSideNavigationBar wnSideNavigationBar;


    /**
     * Agregar nueva categoria de menu en las pestañas
     */
    public void addMenuCategory(){
        Tab newMenuCategory=new Tab("TabP"+cont);
        cont++;//ELIMINAR ESTO DESPUES
        
        //Dependiendo de la categoria de menu seleccionada, se cambiaran los productos en pantalla
        newMenuCategory.setOnSelectionChanged(event -> {
        if (newMenuCategory.isSelected()) {
            //nombreTab.setText(newMenuCategory.getText());
            
        }
        });
        
        menuCategories.getTabs().add(newMenuCategory);
        
    }
    
    /*
    public ObservableList<ProductTest> getProductCardData(){
        
    }
    */
    
    public void displayMenuCards(){
        //BORRAR ESTO SOLO ES DE PRUEBA
        Product p1=new Product("P1","Burger","Platillo",new BigDecimal("20"),new BigDecimal("15"));
        Product p2=new Product("P2","Fries","Platillo",new BigDecimal("15"),new BigDecimal("10"));
        Product p3=new Product("P3","Soda","Platillo",new BigDecimal("20"),new BigDecimal("10"));
        
        cardDataList.clear();
        cardDataList.add(p1);
        cardDataList.add(p2);
        cardDataList.add(p3);
        
        for(int currentProduct = 0; currentProduct < cardDataList.size();currentProduct++){
            try{
                FXMLLoader load=new FXMLLoader();
                load.setLocation(getClass().getResource("itemCardProduct.fxml"));
                AnchorPane pane=load.load();
                ItemCardProductController cardController=load.getController();
                
                cardController.setProductDataToCard(cardDataList.get(currentProduct));
                
                menuCards.getChildren().add(pane);
                
                /*
                pane.setOnMousePressed(event -> {
                    pane.setStyle("-fx-background-color: lightgray");
                    //pane.setStyle("-fx-background-color: white");
                });
                */
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }
    
    /*
    public void setupAutoCompleteOnTextfield(TextField textfield){//List<String> clientNames poner como parametro
        ContextMenu nameSuggestionsMenu=new ContextMenu();
        
        textfield.textProperty().addListener((observable,newInput,oldInput)->{
            if(newInput.isEmpty()){
                nameSuggestionsMenu.hide();
            }
            nameSuggestionsMenu.show(textfield, Side.BOTTOM, 0, 0);
        });
        
        MenuItem nameSugesstion=new MenuItem("Jose");
        nameSuggestionsMenu.getItems().add(nameSugesstion);
        
        textfield.setContextMenu(nameSuggestionsMenu);
    }
    */
    
    /**
     * Mostrar formulario que aparece al continuar la orden
     */
    public void showOrderForm(){
        continueOrderWindow.setVisible(true);
        continueOrderOptionsBox.setVisible(true);
        
        menuWindow.setVisible(false);
        menuOrderOptionsBox.setVisible(false);
        
    }
    
    /**
     * Mostrar cartilla de menu que muestra los porductos
     */
    public void showMenuWindow(){
        menuWindow.setVisible(true);
        menuOrderOptionsBox.setVisible(true);
        
        continueOrderWindow.setVisible(false);
        continueOrderOptionsBox.setVisible(false);
    }
    
    /**
     * Mostrar opcion de número de mesa
     */
    public void showTableNumber(){
        tableNumberBox.setVisible(true);
    }
    
    /**
     * Ocultar opcion de número de mesa
     */
    public void hideTableNumber(){
        tableNumberBox.setVisible(false);
    }
    
    
    /**
     * Inicializar el controllador de la ventana de Venta
     * @param url Ubicación utilizada para resolver rutas relativas para el objeto raíz
     * @param rb Recursos utilizados para localizar el objeto raíz
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Hace que la distribución de las cartas se ajusten al tamaño del cuadro donde estan contenidas
        menuCards.prefWidthProperty().bind(menuCardsScroller.widthProperty());
        
        displayMenuCards();
    }




}
