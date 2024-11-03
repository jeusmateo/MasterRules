package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Customer;
import com.mycompany.masterrules.Model.CustomerAccount;
import com.mycompany.masterrules.Model.Product;
import com.mycompany.masterrules.Model.UserAccount;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

import java.util.List;

/**
 * Clase que implementa las operaciones básicas de un DAO (Data Access Object) utilizando Hibernate
 * TODO: Implementar los métodos de la interfaz DAO
 *
 * @param <T> La entidad a ser manipulada
 * @param <K> El tipo de dato de la llave primaria de la entidad
 * @author Mateo Ortiz
 */
public class HibernateDAO<T, K> {

    private HibernateDAO() {
    }

    /**
     * Factory method para obtener un DAO de Productos
     *
     * @return Un DAO de Productos
     */
    public static HibernateDAO<Product, Integer> createProductDAO() {
        return new HibernateDAO<>();
    }


    public static HibernateDAO<UserAccount, String> createUserDAO() {
        return new HibernateDAO<>();
    }

    public static HibernateDAO<Customer, Long> createCustomerDAO() {
        return new HibernateDAO<>();
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
            ex.printStackTrace();
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
    public T findById(K id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Obtiene todas las entidades de un tipo de la base de datos
     *
     * @return Una lista con todas las entidades encontradas
     */
    public List<T> readAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery((Class<T>) Product.class);
        criteria.from((Class<T>) Product.class);
        List<T> data = session.createQuery(criteria).getResultList();
        session.close();
        return data;
    }

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
            ex.printStackTrace();
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
