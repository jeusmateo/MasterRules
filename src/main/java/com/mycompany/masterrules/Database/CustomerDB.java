package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.customers.Customer;
import org.hibernate.Session;

import java.util.List;

/**
 * Clase que administra la base de datos de clientes.
 */
public final class CustomerDB extends Database<String, Customer> {

    /**
     * @param id La llave primaria de la entidad
     * @return El cliente con la llave primaria dada, de lo contrario null
     */
    @Override
    public Customer findById(String id) {
        Session session = HibernateUtil.getOpenSession();
        try {
            return session.get(Customer.class, id);
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el cliente con id: " + id);
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * @param name El nombre del cliente a buscar
     * @return Una lista con los clientes que tengan el nombre dado, de lo contrario regresa una lista vacía
     */
    public List<Customer> findByName(String name) {
        Session session = HibernateUtil.getOpenSession();
        try {
            return session.createQuery("from Customer where customerName = :name", Customer.class)
                    .setParameter("name", name)
                    .list();
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el cliente con nombre: " + name);
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * @return Todos los clientes en la base de datos
     */
    @Override
    public List<Customer> readAll() {
        Session session = HibernateUtil.getOpenSession();
        try {
            return session.createQuery("from Customer", Customer.class).list();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al leer todos los clientes");
            return null;
        } finally {
            session.close();
        }
    }
}
