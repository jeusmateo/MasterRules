package com.mycompany.masterrules.Model.finanzas;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

class CashRegisterAuditReportManagerTest {

//    static CashRegisterAuditReportManager cashRegisterAuditReportManager = new CashRegisterAuditReportManager();

    @Test
    void getCurrentCashRegisterAuditReport() {
    }

    @Test
    void removeCashRegisterAuditReport() {
    }

    @Test
    void generateEndOfDaySalesReport() {

    }

    @Test
    void endCashRegisterAuditReport() {
    }

    @Test
    void simulateCashRegisterAuditReport() {

        var cashRegisterAuditReport = new CashRegisterAuditReportManager();
        cashRegisterAuditReport.generateEndOfDaySalesReport();
        cashRegisterAuditReport.endCashRegisterAuditReport();
        ;
    }
}