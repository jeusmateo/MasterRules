package com.mycompany.masterrules.Database;


import com.mycompany.masterrules.Model.Bill;
import org.hibernate.Session;

import java.util.List;

public final class BillDBManager extends DatabaseManager<Bill, Long>{

    /**
     * @param id La llave primaria de la entidad
     * @return El producto encontrado, o null si no se encontró
     */
    @Override
    public Bill findById(Long id) {
        try(Session session = HibernateUtil.getOpenSession()){
            session.beginTransaction();
            Bill bill = session.get(Bill.class, id);
            session.getTransaction().commit();
            return bill;
        }
        catch (Exception e){
            System.err.println("Error al buscar la factura: " + e);
            return null;
        }
    }

    /**
     * @return Una lista con todas las facturas en la base de datos
     */
    @Override
    public List<Bill> readAll() {
        try(Session session = HibernateUtil.getOpenSession()){
            session.beginTransaction();
            List<Bill> bills = session.createQuery("from Bill", Bill.class).list();
            session.getTransaction().commit();
            return bills;
        }
        catch (Exception e){
            System.err.println("Error al leer las facturas: " + e);
            return null;
        }
    }
}
