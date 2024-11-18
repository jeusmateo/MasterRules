package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.finanzas.CashFlowReport;

import java.util.List;

public class CashFlowReportDatabase extends Database<Long, CashFlowReport>{

    @Override
    public CashFlowReport findById(Long id) {
        var session = HibernateUtil.getOpenSession();
        try{
            session.beginTransaction();
            var cashFlowReport = session.get(CashFlowReport.class, id);
            session.getTransaction().commit();
            return cashFlowReport;
        }catch (Exception e) {
            if(session.getTransaction()!=null)
                session.getTransaction().rollback();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public List<CashFlowReport> readAll() {
        var session = HibernateUtil.getOpenSession();
        try{
            session.beginTransaction();
            var cashFlowReports = session.createQuery("from CashFlowReport", CashFlowReport.class).list();
            session.getTransaction().commit();
            return cashFlowReports;
        }catch (Exception e) {
            if(session.getTransaction()!=null)
                session.getTransaction().rollback();
            return null;
        }finally {
            session.close();
        }
    }
}
