package com.mycompany.masterrules.Database;


import com.mycompany.masterrules.Model.Bill;
import org.hibernate.Session;

import java.util.List;

/**
 * Clase que administra la base de datos de facturas.
 */
public final class BillDBManager extends DatabaseManager<Bill, Long>{

    /**
     * @param id La llave primaria de la entidad
     * @return El producto encontrado, o null si no se encontró
     */
    @Override
    public Bill findById(Long id) {
        Session session = HibernateUtil.getOpenSession();
        try{
            session.beginTransaction();
            Bill bill = session.get(Bill.class, id);
            session.getTransaction().commit();
            return bill;
        }
        catch (Exception e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar la factura: " + e);
            return null;
        }
        finally {
            session.close();
        }
    }

    /**
     * @return Una lista con todas las facturas en la base de datos
     */
    @Override
    public List<Bill> readAll() {
        Session session = HibernateUtil.getOpenSession();
        try{
            session.beginTransaction();
            List<Bill> bills = session.createQuery("from Bill", Bill.class).list();
            session.getTransaction().commit();
            return bills;
        }
        catch (Exception e){
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al leer las facturas: " + e);
            return null;
        }
    }
}
