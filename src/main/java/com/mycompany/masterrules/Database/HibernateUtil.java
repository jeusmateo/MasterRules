package com.mycompany.masterrules.Database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase de utilidad para obtener la sesión de Hibernate.
 */
final class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Carga la configuración de hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al crear el SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * @return La sesión de Hibernate.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }


    /**
     * @return Una nueva sesión de Hibernate.
     */
    public static Session getOpenSession() {
        return sessionFactory.openSession();
    }

}
