package com.mycompany.masterrules.Model;

import java.util.HashMap;

// TODO: arreglar el ADT de esta clase
public class ComboTemplate {
    private HashMap<String, Integer> comboRequirements;//HashMap<ProductType, quantity>

    public void setComboRequirements(HashMap<String, Integer> comboRequirements) {
        this.comboRequirements = comboRequirements;
    }

    public HashMap<String, Integer> getComboRequirements() {
        return comboRequirements;
    }


    /*
     * 
     
    public boolean verifyCombo(Order order) {//no se si necesitamos el parametro Order, probablemente se haga la verificacion antes de que si quiera se crea la Order
        
        //tengo duda de cuando se va a emplear este. Puede ser que cuando oprimimos que queremos ese combo, la vista le pide que escoja del menu, x producto de cierto tipo
        //y se va ciclando hasta que cumpla el requisito, sin embargo ¿quien es el que se esta encargando de contar los productos y guardarlos?
        //¿primero se seleccionan todos los productos y  se incluyen en el combo al final?¿o se van agregando los productos uno por uno al combo?
        //tambien hay que ver que debe mostrar la vista cuando se selecciona el combo personlaizable: ¿acaso mostrara la vista del menu normal con una label que le diga "seleccionar x bedidas"?
        //¿o mostrara un vista en especial donde se tenga solo las categorias que necesita?
        
    }
    */
    
}
