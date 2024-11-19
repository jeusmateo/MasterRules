package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.finanzas.CashRegisterAuditReport;

import java.util.List;

public class AuditReportDatabase extends Database<Long, CashRegisterAuditReport> {

    @Override
    public CashRegisterAuditReport findById(Long id) {
        var session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            var result = session.get(CashRegisterAuditReport.class, id);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if(session.getTransaction()!=null) session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<CashRegisterAuditReport> readAll() {
        var session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            var result = session.createQuery("from CashRegisterAuditReport", CashRegisterAuditReport.class).list();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if(session.getTransaction()!=null) session.getTransaction().rollback();
            return List.of();
        } finally {
            session.close();
        }
    }
}
