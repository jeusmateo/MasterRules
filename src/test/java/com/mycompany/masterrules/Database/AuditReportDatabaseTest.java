package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.finanzas.CashRegisterAuditReport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AuditReportDatabaseTest {

    static AuditReportDatabase auditReportDatabase = new AuditReportDatabase();

    @Test
    void save() {
        var cashRegisterAuditReport = new CashRegisterAuditReport(BigDecimal.valueOf(1000));
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