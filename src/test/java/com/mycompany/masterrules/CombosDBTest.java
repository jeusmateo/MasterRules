package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.CombosDBManager;
import com.mycompany.masterrules.Database.ProductDBManager;
import com.mycompany.masterrules.Model.Combo;
import com.mycompany.masterrules.Model.CustomComboTemplate;
import com.mycompany.masterrules.Model.Product;

import java.math.BigDecimal;
import java.util.List;

public class CombosDBTest {
    public static void main(String[] args) {

        Product product1 = new Product("1", "Product 1", "Type 1", BigDecimal.valueOf(10), BigDecimal.valueOf(5));
        Product product2 = new Product("2", "Product 2", "Type 2", BigDecimal.valueOf(20), BigDecimal.valueOf(10));

        // nota: los productos deben estar guardados en la base de datos para que el combo pueda guardarse
        ProductDBManager productDBManager = new ProductDBManager();
        assert productDBManager.save(product1);
        assert productDBManager.save(product2);

        List<Product> products = List.of(product1, product2);

        Combo combo = new Combo("Combo 1", products, null, BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        System.out.println(combo.toString());

        CustomComboTemplate customComboTemplate = new CustomComboTemplate(BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        customComboTemplate.setDefaultProducts(products);

        Combo customCombo = new Combo(products, BigDecimal.valueOf(30), BigDecimal.valueOf(15), "Custom Combo 1");
        System.out.println(customCombo.toString());

        // Guardar el combo en la base de datos
        CombosDBManager combosDBManager = new CombosDBManager();

        assert combosDBManager.save(combo);

        // Leer todos los combos de la base de datos
        List<Combo> combos = combosDBManager.readAll();
        assert combos != null;
        for (Combo c : combos) {
            System.out.println(c.toString());
        }

        // Buscar un combo por su id
        Combo comboFound = combosDBManager.findById("1");
        assert comboFound != null;

        System.out.println(comboFound.toString());

        // Borrar el combo de la base de datos
        assert combosDBManager.delete(comboFound);


    }
}
