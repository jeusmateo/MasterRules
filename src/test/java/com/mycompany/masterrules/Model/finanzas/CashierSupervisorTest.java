package com.mycompany.masterrules.Model.finanzas;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CashierSupervisorTest {

    static CashierSupervisor cashierSupervisor = new CashierSupervisor();

    @Test
    void getCashOutFlowReports() {
        assertNotNull(cashierSupervisor.getCashFlows());
    }

    @Test
    void setCashOutFlowReports() {
        var cashOutFlowReports = new ArrayList<CashFlow>();
        cashOutFlowReports.add(new CashFlow("reason", BigDecimal.valueOf(100)));
        cashierSupervisor.setCashFlows(cashOutFlowReports);
        assertEquals(cashOutFlowReports, cashierSupervisor.getCashFlows());
    }

    @Test
    void addNewCashOutFlow() {
        var cashOutFlowReport = new CashFlow("out", BigDecimal.valueOf(100));
        cashierSupervisor.addNewCashOutFlow(cashOutFlowReport);
        assertTrue(cashierSupervisor.getCashFlows().contains(cashOutFlowReport));
    }

    @Test
    void addNewCashInFlow() {
        var cashInFlowReport = new CashFlow("in", BigDecimal.valueOf(100));
        cashierSupervisor.addNewCashInFlow(cashInFlowReport);
        assertTrue(cashierSupervisor.getCashInFlows().contains(cashInFlowReport));
    }

    @Test
    void getCashInFlowReports() {
    }

    @Test
    void setCashInFlowReports() {
    }
}