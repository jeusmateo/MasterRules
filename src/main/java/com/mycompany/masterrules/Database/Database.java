package com.mycompany.masterrules.Database;

import org.hibernate.Session;

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

    /**
     * Constructor protegido para evitar que esta clase sea instanciada directamente
     */
    Database() {
    }

    /**
     * Guarda una entidad en la base de datos
     *
     * @param entity La entidad a ser guardada
     * @return true si la entidad fue guardada exitosamente, false en caso contrario
     */
    public boolean save(T entity) {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al guardar la entidad: " + ex);
            return false;
        } finally {
            session.close();
        }
    }

    /**
     * Busca una entidad en la base de datos por su llave primaria
     *
     * @param id La llave primaria de la entidad
     * @return La entidad encontrada, o null si no se encontró
     */
    public abstract T findById(K id);

    /**
     * Obtiene todas las entidades de un tipo de la base de datos
     *
     * @return Una lista con todas las entidades encontradas
     */
    public abstract List<T> readAll();

    /**
     * Actualiza una entidad en la base de datos
     *
     * @param entity La entidad a ser actualizada
     * @return true si la entidad fue actualizada exitosamente, false en caso contrario
     */
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

    /**
     * Elimina una entidad de la base de datos
     *
     * @param entity La entidad a ser eliminada
     * @return true si la entidad fue eliminada exitosamente, false en caso contrario
     */
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
