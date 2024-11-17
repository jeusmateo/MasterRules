package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Combo;
import com.mycompany.masterrules.Model.CustomCombo;
import com.mycompany.masterrules.Model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CombosDBManagerTest {

    static CombosDBManager combosDBManager = new CombosDBManager();


    @Test
    void save() {
        // creando un combo normal
        var product1 = new Product("1", "Product 1", "Type 1", BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        var product2 = new Product("2", "Product 2", "Type 2", BigDecimal.valueOf(20), BigDecimal.valueOf(10));
        var productDBManager = new ProductDBManager();
        assertTrue(productDBManager.save(product1));
        assertTrue(productDBManager.save(product2));

        var products = List.of(product1, product2);

        var combo = new Combo("Combo 1", products, BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        assertTrue(combosDBManager.save(combo));

        // creando un combo personalizado
        var quantityPerCategory = new HashMap<String, Integer>();
        quantityPerCategory.put("Postres", 2);

        var postre1 = new Product("3", "Postre 1", "Postres", BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        var postre2 = new Product("4", "Postre 2", "Postres", BigDecimal.valueOf(20), BigDecimal.valueOf(10));

        var customCombo = new CustomCombo(quantityPerCategory);
        customCombo.set;
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void readAll() {
    }
}