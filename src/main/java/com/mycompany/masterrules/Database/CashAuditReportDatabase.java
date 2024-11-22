package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.finance.CashAuditReport;

import java.util.List;

public class CashAuditReportDatabase extends Database<Long, CashAuditReport> {

    @Override
    public CashAuditReport findById(Long id) {
        var session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            var result = session.get(CashAuditReport.class, id);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public List<CashAuditReport> readAll() {
        var session = HibernateUtil.getOpenSession();
        try {
            session.beginTransaction();
            var result = session.createQuery("from CashAuditReport", CashAuditReport.class).list();
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) session.getTransaction().rollback();
            return List.of();
        } finally {
            session.close();
        }
    }
}
