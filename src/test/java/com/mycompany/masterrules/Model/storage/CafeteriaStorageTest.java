package com.mycompany.masterrules.Model.storage;

import com.mycompany.masterrules.Model.cafeteria.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CafeteriaStorageTest {
    static CafeteriaStorage cafeteriaStorage = null;


    @BeforeEach
    void setUp() {
        cafeteriaStorage = new CafeteriaStorage();
    }

    @Test
    void addProduct() {
        var product = new Product("Coca Cola", new BigDecimal(16), BigDecimal.valueOf(10));
        var stockInfo = new StockInfo(10, 5, 20);
        cafeteriaStorage.addProduct(product, stockInfo);
        assertTrue(cafeteriaStorage.isProductStored(product));

    }

    @Test
    void removeProduct() {
    }

    @Test
    void editCurrentStock() {
    }

    @Test
    void editMinStock() {
    }

    @Test
    void editMaxStock() {
    }

    @Test
    void incrementCurrentStock() {
    }

    @Test
    void decrementCurrentStock() {
    }

    @Test
    void isEnoughStock() {
    }

    @Test
    void isProductStored() {
    }

    @Test
    void getProducts() {
    }

    @Test
    void setProducts() {
    }
}