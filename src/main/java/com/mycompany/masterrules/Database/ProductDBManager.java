package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

/**
 * Clase que administra la base de datos de productos.
 *
 */
public final class ProductDBManager extends DatabaseManager<Product, String> {

    /**
     * @param id La llave primaria de la entidad
     * @return El producto encontrado, o null si no se encontró
     */
    @Override
    public Product findById(String id) {

        try (Session session = HibernateUtil.getOpenSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }catch (Exception e){
            System.err.println("Error al buscar el producto: " + e);
            return null;
        }
    }

    /**
     * @return Una lista con todos los productos en la base de datos
     */
    @Override
    public List<Product> readAll() {

        try (Session session = HibernateUtil.getOpenSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product", Product.class).list();
            session.getTransaction().commit();
            return products;
        } catch (Exception ex) {
            System.err.println("Error al leer los productos: " + ex);
            return null;
        }
    }
}
