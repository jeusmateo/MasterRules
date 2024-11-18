package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Model.cafeteria.CafeteriaManager;
import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.storage.StockInfo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CafeteriaManagerTest {

    static CafeteriaManager cafeteriaManager;

    @BeforeAll
    static void beforeAll() {
        cafeteriaManager = new CafeteriaManager();
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void createProduct() {
        var product1 = new Product("1", "Boing de mango", "Bebida", BigDecimal.valueOf(1000), BigDecimal.valueOf(900));
        assertDoesNotThrow(() -> cafeteriaManager.createProduct(product1, true, new StockInfo(10, 5, 15)));

        // obteniendo el producto
        var product = cafeteriaManager.getMenu().getProducts().get(1);
        assertDoesNotThrow(() -> cafeteriaManager.deleteProduct(product));

        assertTrue(cafeteriaManager.getMenu().getProducts().isEmpty());

    }

    @Test
    void deleteProduct() {
    }

    @Test
    void editProduct() {
    }

    @Test
    void createCombo() {
    }

    @Test
    void deleteCombo() {
    }

    @Test
    void editCombo() {
    }

    @Test
    void editMenu() {
    }

    @Test
    void getMenu() {
    }

    @Test
    void setMenu() {
    }

    @Test
    void getStorage() {
    }

    @Test
    void setStorage() {
    }

    @Test
    void testCreateProduct() {
    }

    @Test
    void testDeleteProduct() {
    }

    @Test
    void testEditProduct() {
    }

    @Test
    void testCreateCombo() {
    }

    @Test
    void testDeleteCombo() {
    }

    @Test
    void testEditCombo() {
    }

    @Test
    void testEditMenu() {
    }

    @Test
    void testGetMenu() {
    }

    @Test
    void testSetMenu() {
    }

    @Test
    void testGetStorage() {
    }

    @Test
    void testSetStorage() {
    }

    @Test
    void testCreateProduct1() {
    }

    @Test
    void testDeleteProduct1() {
    }

    @Test
    void testEditProduct1() {
    }

    @Test
    void testCreateCombo1() {
    }

    @Test
    void testDeleteCombo1() {
    }

    @Test
    void testEditCombo1() {
    }

    @Test
    void testEditMenu1() {
    }

}