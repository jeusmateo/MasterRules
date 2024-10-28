package com.mycompany.masterrules.Database;

import org.hibernate.Session;

public class DBAdminGeneric {
    /**
     *
     */

    public static <T> boolean saveToDatabase(T t) {
        Session session = HibernateUtil.openSession();
        try {
            session.beginTransaction();
            session.persist(t);
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

    public static <T> T searchInDatabase(int id) {
        return null;
    }

    public static <T> boolean update(T t) {
        Session session = HibernateUtil.openSession();
        try {
            session.beginTransaction();
            session.update(t);
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

    public static <T> boolean deleteInDatabase(T t) {
        Session session = HibernateUtil.openSession();
        try {
            session.beginTransaction();
            session.delete(t);
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
