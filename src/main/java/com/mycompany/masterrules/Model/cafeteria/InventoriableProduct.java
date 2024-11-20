package com.mycompany.masterrules.Model.cafeteria;

public class InventoriableProduct extends Product{
    private int availableQuantity;
    private int minDesiredInventory;
    private int maxDesiredInventory;


    public boolean isStockAvailable(){
        return availableQuantity > 0;
    }

    public void removeFromInventory(){
        availableQuantity--;
    }
}
