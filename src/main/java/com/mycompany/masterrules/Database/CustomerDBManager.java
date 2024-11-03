package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Clase que administra la base de datos de clientes.
 */
public final class CustomerDBManager extends DatabaseManager<Customer, Long> {

    /**
     * @param id La llave primaria de la entidad
     * @return El cliente con la llave primaria dada, de lo contrario null
     */
    @Override
    public Customer findById(Long id) {
        Session session = HibernateUtil.getOpenSession();
        try {
            return session.get(Customer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
}
