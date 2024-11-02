package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.ProductDAO;
import com.mycompany.masterrules.Model.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase de prueba para la clase ProductDB
 *
 * @see com.mycompany.masterrules.Database.ProductDAO
 */
public class ProductDBTest {

    public static void main(String[] args) {
        Product tmpProduct = new Product(1, "Boing de mango", "Bebida", BigDecimal.valueOf(1000), BigDecimal.valueOf(900));

        ProductDAO productDB = new ProductDAO();
        assert productDB.save(tmpProduct);

        Product tmpPapas = new Product(2, "Papas", "Snack", BigDecimal.valueOf(1.00), BigDecimal.valueOf(0.90));

        assert productDB.save(tmpPapas);

        // Cambio de precio
        tmpPapas.setPrice(BigDecimal.valueOf(2.00));
        assert productDB.update(tmpPapas);

        // Impression de todos los productos
        List<Product> products = productDB.readAll();
        assert products.size() == 2;

        for (Product p : products) {
            System.out.println(
                    "ID: " + p.getID() + "\n" +
                            "Name: " + p.getProductName() + "\n" +
                            "Type: " + p.getProductType() + "\n" +
                            "Price: " + p.getPrice() + "\n" +
                            "VIP Price: " + p.getVIPprice() + "\n"
            );
        }

        // Borrado de un producto

        assert productDB.delete(tmpProduct);

        products = productDB.readAll();
        assert products.size() == 1;

        for (Product p : products) {
            System.out.println(
                    "ID: " + p.getID() + "\n" +
                            "Name: " + p.getProductName() + "\n" +
                            "Type: " + p.getProductType() + "\n" +
                            "Price: " + p.getPrice() + "\n" +
                            "VIP Price: " + p.getVIPprice() + "\n"
            );
        }


    }

}
