package com.mycompany.masterrules.Model.cafeteria;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ComboCreatorTest {

    static ComboCreator comboCreator;

    @BeforeAll
    static void setUp() {
        var quantityPerCategory = Map.of("snacks", 2, "bebidas", 2);
        comboCreator = new ComboCreator(quantityPerCategory);
    }

    @BeforeEach
    void setUpEach() {
        comboCreator.setProductsToCategory("snacks", new ArrayList<>());
        comboCreator.setProductsToCategory("bebidas", new ArrayList<>());
    }

    @Test
    void addProductToCategory() {
        var product = new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8));
        comboCreator.addProductToCategory("snacks", product);
        assertEquals(1, comboCreator.getProductsPerCategory("snacks").size());
    }

    @Test
    void getAllowedQuantity() {
        assertEquals(2, comboCreator.getAllowedQuantity("snacks"));
    }

    @Test
    void removeProductFromCategory() {
        var product = new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8));
        comboCreator.addProductToCategory("snacks", product);
        comboCreator.removeProductFromCategory("snacks", product);
        assertEquals(0, comboCreator.getProductsPerCategory("snacks").size());
    }

    @Test
    void setProductsToCategory() {
        var products = new ArrayList<Product>();
        products.add(new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        comboCreator.setProductsToCategory("snacks", products);
        assertEquals(1, comboCreator.getProductsPerCategory("snacks").size());
    }

    @Test
    void getProductsPerCategory() {
        var products = new ArrayList<Product>();
        products.add(new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        comboCreator.setProductsToCategory("snacks", products);
        assertEquals(products, comboCreator.getProductsPerCategory("snacks"));
    }


    @Test
    void testGetAllowedQuantity() {
        assertEquals(2, comboCreator.getAllowedQuantity("snacks"));
    }

    @Test
    void getAllProductsPerCategory() {
        var products = new ArrayList<Product>();
        products.add(new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        comboCreator.setProductsToCategory("snacks", products);
        var expected = Map.of("snacks", products, "bebidas", List.of());
        assertEquals(expected, comboCreator.getAllProductsPerCategory());

    }
}