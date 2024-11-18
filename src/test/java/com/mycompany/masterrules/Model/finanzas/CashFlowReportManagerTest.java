package com.mycompany.masterrules.Model.finanzas;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CashFlowReportManagerTest {

    static CashFlowReportManager cashFlowReportManager = new CashFlowReportManager();

    @Test
    void getCashOutFlowReports() {
        assertNotNull(cashFlowReportManager.getCashOutFlowReports());
    }

    @Test
    void setCashOutFlowReports() {
        var cashOutFlowReports = new ArrayList<CashFlowReport>();
        cashOutFlowReports.add(new CashFlowReport("reason", BigDecimal.valueOf(100)));
        cashFlowReportManager.setCashOutFlowReports(cashOutFlowReports);
        assertEquals(cashOutFlowReports, cashFlowReportManager.getCashOutFlowReports());
    }

    @Test
    void addNewCashOutFlowReport() {
        var cashOutFlowReport = new CashFlowReport("out", BigDecimal.valueOf(100));
        cashFlowReportManager.addNewCashOutFlowReport(cashOutFlowReport);
        assertTrue(cashFlowReportManager.getCashOutFlowReports().contains(cashOutFlowReport));
    }

    @Test
    void addNewCashInFlowReport() {
        var cashInFlowReport = new CashFlowReport("in", BigDecimal.valueOf(100));
        cashFlowReportManager.addNewCashInFlowReport(cashInFlowReport);
        assertTrue(cashFlowReportManager.getCashInFlowReports().contains(cashInFlowReport));
    }

    @Test
    void getCashInFlowReports() {
    }

    @Test
    void setCashInFlowReports() {
    }
}