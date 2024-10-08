package com.mycompany.masterrules.Model;
import java.util.HashMap;


public class ComboTemplate {
    private HashMap<ProductType, quantity> comboRequirements;

    public void setComboRequirements(HashMap<ProductType, quantity> comboRequirements) {
        this.comboRequirements = comboRequirements;
    }

    public HashMap<ProductType, quantity> getComboRequirements() {
        return comboRequirements;
    }

    public boolean verifyCombo(Order order) {
        
    }
}
