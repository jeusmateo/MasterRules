package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.cafeteria.Combo;
import org.hibernate.Session;

import java.util.List;

public final class CombosDBManager extends DatabaseManager<Combo, String> {
    /**
     *
     * @param id La llave primaria de la entidad
     * @return La entidad Combo con la llave primaria dada
     */
    @Override
    public Combo findById(String id) {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            Combo combo = session.get(Combo.class, id);
            session.getTransaction().commit();
            return combo;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el combo con id: " + id);
            return null;
        } finally {
            session.close();
        }
    }

    /**
     * @return Una lista con todas las entidades Combo
     */
    @Override
    public List<Combo> readAll() {
        Session session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            List<Combo> combos = session.createQuery("from Combo",Combo.class).getResultList();
            session.getTransaction().commit();
            return combos;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar todos los combos");
            return null;
        } finally {
            session.close();
        }
    }
}
