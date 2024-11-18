/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model.storage;


public class StockInfo {
    private int currentStock;
    private int minStock;
    private int maxStock;

    public StockInfo() {
        this.currentStock = 0;
        this.minStock = 0;
        this.maxStock = 0;
    }

    public StockInfo(int currentStock, int minStock, int maxStock) {
        this.currentStock = currentStock;
        this.minStock = minStock;
        this.maxStock = maxStock;
    }

    public int getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(int currentStock) {
        this.currentStock = currentStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

}
