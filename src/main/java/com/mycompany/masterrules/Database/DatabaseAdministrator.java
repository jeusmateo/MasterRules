package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

import java.util.Collection;
import java.util.List;

public class DatabaseAdministrator {

    public static boolean add(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public static boolean updateProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public static boolean deleteProduct(Product product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            ex.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    public static List<Product> readAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
            criteria.from(Product.class);
            List<Product> products = session.createQuery(criteria).getResultList();
            return products;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

}
