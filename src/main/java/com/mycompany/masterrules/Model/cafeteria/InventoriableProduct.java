package com.mycompany.masterrules.Model.cafeteria;

/**
 * Clase que representa un producto inventariable en la cafetería.
 * Un producto inventariable es un producto que tiene una cantidad disponible y un inventario deseado mínimo y máximo.
 */
public class InventoriableProduct extends Product {
    private int availableQuantity;
    private int minDesiredInventory;
    private int maxDesiredInventory;

    /**
     * Verifica si hay stock disponible del producto.
     *
     * @return true si hay stock disponible, false en caso contrario.
     */
    public boolean isStockAvailable() {
        return availableQuantity > 0;
    }

    /**
     * Elimina una unidad del inventario del producto.
     */
    public void removeFromInventory() {
        availableQuantity--;
    }
}