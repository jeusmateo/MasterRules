package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.storage.StockInfo;

import java.util.List;


public class StorageDatabase extends Database<Product,StockInfo> {

    @Override
    public StockInfo findById(Product id) {
        var session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            var stockInfo = session.get(StockInfo.class, id);
            session.getTransaction().commit();
            return stockInfo;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al buscar el stock: " + ex);
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<StockInfo> readAll() {
        var session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            var stockInfo = session.createQuery("from StockInfo", StockInfo.class).list();
            session.getTransaction().commit();
            return stockInfo;
        } catch (Exception ex) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            System.err.println("Error al leer los stocks: " + ex);
            return null;
        } finally {
            session.close();
        }
    }
}