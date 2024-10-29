package com.mycompany.masterrules.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controlador de la ventana de Venta
 * @author campv
 */
public class WnSaleController implements Initializable{
    //COMPONENTES DE LA VENTANA DE LA CARTILLA DE MENU QUE MUESTRA LOS PRODUCTOS
    //-------------------------------------------------------------------------------------------
    @FXML
    private AnchorPane menuWindow;
    
    @FXML
    private VBox menuOrderOptionsBox;
    
    @FXML
    private ScrollPane scrollPaneMenu;
    
    @FXML
    private FlowPane flowPaneMenuCards=new FlowPane();

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
    //private ObservableList<ProductTest> cardDataList=FXCollections.observableArrayList();
    public static int cont=0;//borrar depues, solo es de prueba
    
    
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
    
    /*
    public void displayMenuCards(){
        //BORRAR ESTO SOLO ES DE PRUEBA
        ProductTest p1=new ProductTest("Burger",20.0);
        ProductTest p2=new ProductTest("Fries",20.0);
        ProductTest p3=new ProductTest("Soda",20.0);
        
        cardDataList.clear();
        cardDataList.add(p1);
        cardDataList.add(p2);
        cardDataList.add(p3);
        
        for(int currentProduct = 0; currentProduct < cardDataList.size();currentProduct++){
            try{
                FXMLLoader load=new FXMLLoader();
                load.setLocation(getClass().getResource("cardProduct.fxml"));
                AnchorPane pane=load.load();
                CardProductController cardController=load.getController();
                
                cardController.setProductDataToCard(cardDataList.get(currentProduct));
                
                flowPaneMenuCards.getChildren().add(pane);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }
    }
    */
    
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
        flowPaneMenuCards.prefWidthProperty().bind(scrollPaneMenu.widthProperty());
        
        //displayMenuCards();
    }
}
