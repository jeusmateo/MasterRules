package com.mycompany.masterrules.Database;

import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;

import java.util.List;

/**
 * Clase que implementa las operaciones básicas de un DAO (Data Access Object) utilizando Hibernate.
 * <br>
 * Nota: Esta clase no debe ser instanciada directamente, sino que debe ser extendida por una clase que represente una entidad de la base de datos.
 * <br>
 * TODO: Implementar los métodos de la interfaz DAO
 *
 * @param <T> La entidad a ser manipulada
 * @param <K> El tipo de dato de la llave primaria de la entidad
 * @author Mateo Ortiz
 */
abstract class Database<K, T> {

    Database() {
    }

    private static void sqliteExceptionHandler(GenericJDBCException e) {
        var errorCode = e.getSQLException();
    }

    public boolean save(T entity) throws DuplicatePrimaryKeyException {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return true;
        } catch (GenericJDBCException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            sqliteExceptionHandler(e);
            return false;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            session.close();
        }
    }

    public abstract T findById(K id);

    public abstract List<T> readAll();

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
            System.err.println("Error al actualizar la entidad: " + ex);
            return false;
        } finally {
            session.close();
        }
    }

    public boolean delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al eliminar la entidad: " + ex);
            return false;
        } finally {
            session.close();
        }
    }
}
