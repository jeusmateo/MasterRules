package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Product;
import org.hibernate.Session;

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
        Session session = HibernateUtil.getOpenSession();

        try {
            return session.get(Product.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * @return Una lista con todos los productos en la base de datos
     */
    @Override
    public List<Product> readAll() {
        Session session = HibernateUtil.getOpenSession();

        try {
            return session.createQuery("from Product", Product.class).list();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
