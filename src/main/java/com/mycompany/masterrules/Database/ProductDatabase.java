package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.cafeteria.Product;
import org.hibernate.Session;

import java.util.List;

/**
 * Clase que administra la base de datos de productos.
 *
 */
public final class ProductDatabase extends Database<String, Product> {

    /**
     * @param id La llave primaria de la entidad
     * @return El producto encontrado, o null si no se encontró
     */
    @Override
    public Product findById(String id) {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            session.getTransaction().commit();
            return product;
        }catch (Exception ex){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el producto: " + ex);
            return null;
        }
        finally {
            session.close();
        }
    }

    public List<Product> findByName(String name) {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product where name = :name", Product.class)
                    .setParameter("name", name)
                    .list();
            session.getTransaction().commit();
            return products;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el producto: " + ex);
            return List.of();
        }
        finally {
            session.close();
        }
    }

    public List<Product> findByType(String type){
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product where type = :type", Product.class)
                    .setParameter("type", type)
                    .list();
            session.getTransaction().commit();
            return products;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el producto: " + ex);
            return List.of();
        }
        finally {
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
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product", Product.class).list();
            session.getTransaction().commit();
            return products;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al leer los productos: " + ex);
            return List.of();
        }
        finally {
            session.close();
        }
    }
}
