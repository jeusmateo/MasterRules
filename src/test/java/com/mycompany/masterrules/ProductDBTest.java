package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.ProductDBManager;
import com.mycompany.masterrules.Model.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase de prueba para la clase ProductDB
 *
 * @author Mateo Ortiz
 */
public class ProductDBTest {

    public static void main(String[] args) {
        Product tmpProduct = new Product("1", "Boing de mango", "Bebida", BigDecimal.valueOf(1000), BigDecimal.valueOf(900));

        ProductDBManager productDB = new ProductDBManager();
        assert productDB.save(tmpProduct);

        Product tmpPapas = new Product("2", "Papas", "Snack", BigDecimal.valueOf(1.00), BigDecimal.valueOf(0.90));

        assert productDB.save(tmpPapas);

        // Cambio de precio
        tmpPapas.setPrice(BigDecimal.valueOf(2.00));
        assert productDB.update(tmpPapas);

        // Buscar un producto
        Product searched = productDB.findById("2");
        assert searched != null;

        // Impression de todos los productos
        List<Product> products = productDB.readAll();
        assert products.size() == 2;

        System.out.println("Productos en la base de datos:");
        for (Product p : products) {
            System.out.println(p);
        }

        // Borrado de un producto

        assert productDB.delete(tmpProduct);

        products = productDB.readAll();
        assert products.size() == 1;

        for (Product p : products) {
            System.out.println(p);
        }


    }

}
