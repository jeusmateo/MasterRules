package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.CustomComboCreator;
import com.mycompany.masterrules.Model.cafeteria.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ComboDatabaseManagerTest {

    static ProductDatabase combosDBManager = new ProductDatabase();

    @BeforeAll
    static void setUp() {
        combosDBManager.readAll().forEach(combo -> combosDBManager.delete(combo));
    }

    @Test
    void save() {
        // creando un combo normal
        var product1 = new Product("1", "Product 1", "Type 1", BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        var product2 = new Product("2", "Product 2", "Type 2", BigDecimal.valueOf(20), BigDecimal.valueOf(10));
        var productDBManager = new ProductDatabase();
        assertTrue(productDBManager.save(product1));
        assertTrue(productDBManager.save(product2));

        var products = new ArrayList<>(List.of(product1, product2));

        var combo = new Combo("Combo 1", products, BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        assertTrue(combosDBManager.save(combo));
    }

    @Test
    void saveCustomCombo(){
        // creando un combo personalizado
        var quantityPerCategory = new HashMap<String, Integer>();
        quantityPerCategory.put("Postres", 2);

        var postre1 = new Product("3", "Postre 1", "Postres", BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        var postre2 = new Product("4", "Postre 2", "Postres", BigDecimal.valueOf(20), BigDecimal.valueOf(10));

        var productDBManager = new ProductDatabase();
        assertTrue(productDBManager.save(postre1));
        assertTrue(productDBManager.save(postre2));

        var customComboCreator = new CustomComboCreator(quantityPerCategory);
        customComboCreator.addProductToCategory("Postres", postre1);
        customComboCreator.addProductToCategory("Postres", postre2);

        var combo = customComboCreator.createCombo("Combo 2", BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        assertTrue(combosDBManager.save(combo));
    }

    @Test
    void update() {
        save();
        var combo = combosDBManager.readAll().get(0);
        combo.setName("Combo 1 Updated");
        assertTrue(combosDBManager.update(combo));
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