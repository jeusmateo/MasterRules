package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.ComboDatabase;
import com.mycompany.masterrules.Database.ProductDatabase;
import com.mycompany.masterrules.Model.cafeteria.Combo;
import com.mycompany.masterrules.Model.cafeteria.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase de prueba para la clase ProductDatabase
 *
 * @author Mateo Ortiz
 */
public class ProductDBTest {

    @Test
    void general() {
        Product tmpProduct = new Product("1", "Boing de mango", "Bebida", BigDecimal.valueOf(1000), BigDecimal.valueOf(900));

        ProductDatabase productDatabase = new ProductDatabase();
        assert productDatabase.save(tmpProduct);

        Product tmpPapas = new Product("2", "Papas", "Snack", BigDecimal.valueOf(1.00), BigDecimal.valueOf(0.90));

        assert productDatabase.save(tmpPapas);

        // Cambio de precio
        tmpPapas.setPrice(BigDecimal.valueOf(2.00));
        assert productDatabase.update(tmpPapas);

        // Buscar un producto
        Product searched = productDatabase.findById("2");
        assert searched != null;
        assert searched.equals(tmpPapas); // Verifica que la información del producto sea la misma

        // Impression de todos los productos
        List<Product> products = productDatabase.readAll();
        assert Objects.requireNonNull(products).size() == 2;

        System.out.println("Productos en la base de datos:");
        for (Product p : products) {
            System.out.println(p.toString());
        }

        // Borrado de un producto

        assert productDatabase.delete(tmpProduct);

        products = productDatabase.readAll();
        assert Objects.requireNonNull(products).size() == 1;

        for (Product p : products) {
            System.out.println(p.toString());
        }


    }

    @Test
    void readProductOnly() {
        var productDatabase = new ProductDatabase();
        var comboDatabase = new ComboDatabase();
        var combo = new Combo("combo1", new ArrayList<>(), BigDecimal.valueOf(1000), BigDecimal.valueOf(900));
        assert comboDatabase.save(combo);

        var products = productDatabase.readAll();
        for(var p: products) {
            assert !(p instanceof Combo);
        }




    }
}
