package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.ComboDatabase;
import com.mycompany.masterrules.Database.ProductDatabase;
import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class CombosDBTest {
    @Test
    void general() {
        Product product1 = new Product("1", "Product 1", "Type 1", BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        Product product2 = new Product("2", "Product 2", "Type 2", BigDecimal.valueOf(20), BigDecimal.valueOf(10));

        // nota: los productos deben estar guardados en la base de datos para que el combo pueda guardarse
        ProductDatabase productDBManager = new ProductDatabase();
        assert productDBManager.save(product1);
        assert productDBManager.save(product2);

        List<Product> products = List.of(product1, product2);

        Combo combo = new Combo("Combo 1", products, BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        System.out.println(combo.toString());

        ComboDatabase combosDBManager = new ComboDatabase();

        assert combosDBManager.save(combo);

        // Leer todos los combos de la base de datos
        List<Combo> combos = combosDBManager.readAll();
        assert combos != null;
        for (Combo c : combos) {
            System.out.println(c.toString());
        }

        // Buscar un combo por su id
        Combo comboFound = combosDBManager.findById(combo.getId());
        assert comboFound != null;

        System.out.println(comboFound.toString());

        // Borrar el combo de la base de datos
//        assert combosDBManager.delete(comboFound);


    }
}
