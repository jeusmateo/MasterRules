package com.mycompany.masterrules.Database;

import org.hibernate.Session;

import java.util.List;

public class HibernateDAO<T, K> implements DAO<T, K> {

    @Override
    public boolean save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(entity);
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

    @Override
    public T findById(K id) {
        return null;
    }

    @Override
    public List<T> readAll() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            CriteriaBuilder builder = session.getCriteriaBuilder();
//            CriteriaQuery<T> criteria = builder.createQuery(T.class);
//            criteria.from(T.class);
//            List<T> products = session.createQuery(criteria).getResultList();
//            return products;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return null;
//        } finally {
//            session.close();
//        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean update(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.merge(entity);
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

    @Override
    public boolean delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(entity);
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
}
