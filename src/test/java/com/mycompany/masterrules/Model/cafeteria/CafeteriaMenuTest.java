package com.mycompany.masterrules.Model.cafeteria;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class CafeteriaMenuTest {

    static CafeteriaMenu cafeteriaMenu = new CafeteriaMenu();

    @Test
    void addProductToMenu() {
        var product = new Product("1","1", "1",BigDecimal.ONE, BigDecimal.ONE);
        cafeteriaMenu.addProductToMenu(product);
        assertTrue(cafeteriaMenu.getProducts().contains(product));

        // dos productos del mismo
        var combo = new Combo("Combo 1", List.of(product, product), new BigDecimal(20), new BigDecimal(15));

        // agregar combo
        cafeteriaMenu.addComboToMenu(combo);
        assertTrue(cafeteriaMenu.getCombos().contains(combo));

        //agregar otro combo
        var combo2 = new Combo("Combo 2", List.of(product, product), new BigDecimal(20), new BigDecimal(15));

        // no, borré la bd, el id de combo es generado de forma unica, con su hash

        cafeteriaMenu.addComboToMenu(combo2);
        assertTrue(cafeteriaMenu.getCombos().contains(combo2));

    }

    @Test
    void removeProductOnMenu() {
    }

    @Test
    void editProduct() {
    }

    @Test
    void getProductsByType() {
    }

    @Test
    void addComboToMenu() {
    }

    @Test
    void removeComboOnMenu() {
    }

    @Test
    void editCombo() {
    }

    @Test
    void getTitle() {
    }

    @Test
    void setTitle() {
    }

    @Test
    void getProducts() {
    }

    @Test
    void setProducts() {
    }

    @Test
    void getCombos() {
    }

    @Test
    void setCombos() {
    }
}