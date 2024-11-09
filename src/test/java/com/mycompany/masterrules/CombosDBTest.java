package com.mycompany.masterrules;

import com.mycompany.masterrules.Model.Combo;
import com.mycompany.masterrules.Model.CustomComboTemplate;
import com.mycompany.masterrules.Model.Product;

import java.math.BigDecimal;
import java.util.List;

public class CombosDBTest {
    public static void main(String[] args) {

        Product product1 = new Product("1", "Product 1", "Type 1", BigDecimal.valueOf(10), BigDecimal.valueOf(5));

        Product product2 = new Product("2", "Product 2", "Type 2", BigDecimal.valueOf(20), BigDecimal.valueOf(10));

        List<Product> products = List.of(product1, product2);

        Combo combo = new Combo("Combo 1", products, null, BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        System.out.println(combo.toString());

        CustomComboTemplate customComboTemplate = new CustomComboTemplate(BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        customComboTemplate.setDefaultProducts(products);

        Combo customCombo = new Combo(products, customComboTemplate, BigDecimal.valueOf(30), BigDecimal.valueOf(15));
        System.out.println(customCombo.toString());

    }
}
