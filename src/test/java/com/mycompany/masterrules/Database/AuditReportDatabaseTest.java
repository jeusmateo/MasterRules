package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.finanzas.CashAuditReport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class AuditReportDatabaseTest {

    static AuditReportDatabase auditReportDatabase = new AuditReportDatabase();

    @Test
    void save() {
        var cashRegisterAuditReport = new CashAuditReport(BigDecimal.valueOf(1000));
        cashRegisterAuditReport.configCashRegisterAuditReport();

        //configurando el reporte de auditoria
        cashRegisterAuditReport.calculateCashRevenue();
        auditReportDatabase.save(cashRegisterAuditReport);
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void findById() {
    }

    @Test
    void readAll() {
    }
}