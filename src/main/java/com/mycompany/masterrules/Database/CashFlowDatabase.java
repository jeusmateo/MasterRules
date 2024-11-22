package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.finance.CashFlow;

import java.util.List;

public class CashFlowDatabase extends Database<Long, CashFlow>{

    @Override
    public CashFlow findById(Long id) {
        var session = HibernateUtil.getOpenSession();
        try{
            session.beginTransaction();
            var cashFlowReport = session.get(CashFlow.class, id);
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
    public List<CashFlow> readAll() {
        var session = HibernateUtil.getOpenSession();
        try{
            session.beginTransaction();
            var cashFlowReports = session.createQuery("from CashFlow", CashFlow.class).list();
            session.getTransaction().commit();
            return cashFlowReports;
        }catch (Exception e) {
            if(session.getTransaction()!=null)
                session.getTransaction().rollback();
            return List.of();
        }finally {
            session.close();
        }
    }
}
