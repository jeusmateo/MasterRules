package com.mycompany.masterrules.Model.cafeteria;

import com.mycompany.masterrules.Model.cafeteria.menu.CustomComboCreator;
import com.mycompany.masterrules.Model.cafeteria.menu.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CustomComboCreatorTest {

    static CustomComboCreator customComboCreator;

    @BeforeAll
    static void setUp() {
        var quantityPerCategory = Map.of("snacks", 2, "bebidas", 2);
        customComboCreator = new CustomComboCreator(quantityPerCategory);
    }

    @BeforeEach
    void setUpEach() {
        customComboCreator.setProductsToCategory("snacks", new ArrayList<>());
        customComboCreator.setProductsToCategory("bebidas", new ArrayList<>());
    }

    @Test
    void addProductToCategory() {
        var product = new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8));
        customComboCreator.addProductToCategory("snacks", product);
        assertEquals(1, customComboCreator.getProductsPerCategory("snacks").size());
    }

    @Test
    void getAllowedQuantity() {
        assertEquals(2, customComboCreator.getAllowedQuantity("snacks"));
    }

    @Test
    void removeProductFromCategory() {
        var product = new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8));
        customComboCreator.addProductToCategory("snacks", product);
        customComboCreator.removeProductFromCategory("snacks", product);
        assertEquals(0, customComboCreator.getProductsPerCategory("snacks").size());
    }

    @Test
    void setProductsToCategory() {
        var products = new ArrayList<Product>();
        products.add(new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        customComboCreator.setProductsToCategory("snacks", products);
        assertEquals(1, customComboCreator.getProductsPerCategory("snacks").size());
    }

    @Test
    void getProductsPerCategory() {
        var products = new ArrayList<Product>();
        products.add(new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        customComboCreator.setProductsToCategory("snacks", products);
        assertEquals(products, customComboCreator.getProductsPerCategory("snacks"));
    }


    @Test
    void testGetAllowedQuantity() {
        assertEquals(2, customComboCreator.getAllowedQuantity("snacks"));
    }

    @Test
    void getAllProductsPerCategory() {
        var products = new ArrayList<Product>();
        products.add(new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        customComboCreator.setProductsToCategory("snacks", products);
        var expected = Map.of("snacks", products, "bebidas", List.of());
        assertEquals(expected, customComboCreator.getAllProductsPerCategory());

    }

    @Test
    void getAllProducts() {
        var products = new ArrayList<Product>();
        products.add(new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        customComboCreator.setProductsToCategory("snacks", products);
        var products2 = new ArrayList<Product>();
        products2.add(new Product("2", "Coca", "bebidas", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        customComboCreator.setProductsToCategory("bebidas", products2);
        var expected = new ArrayList<Product>();
        expected.addAll(products);
        expected.addAll(products2);
        assertEquals(expected, customComboCreator.getAllProducts());
    }

    @Test
    void createCombo() {
        var products = new ArrayList<Product>();
        products.add(new Product("1", "Papas", "snacks", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        customComboCreator.setProductsToCategory("snacks", products);

        var products2 = new ArrayList<Product>();
        products2.add(new Product("2", "Coca", "bebidas", BigDecimal.valueOf(10), BigDecimal.valueOf(8)));
        customComboCreator.setProductsToCategory("bebidas", products2);

        var combo = customComboCreator.createCombo("Combo 1", BigDecimal.valueOf(20), BigDecimal.valueOf(15));

        var expected = new ArrayList<Product>();
        expected.addAll(products);
        expected.addAll(products2);

        assertEquals("Combo 1", combo.getName());
        assertEquals(expected, combo.getProducts());
        assertEquals(BigDecimal.valueOf(20), combo.getPrice());
        assertEquals(BigDecimal.valueOf(15), combo.getVIPPrice());
    }
}