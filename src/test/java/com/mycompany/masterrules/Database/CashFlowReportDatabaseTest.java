package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.finanzas.CashFlowReport;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CashFlowReportDatabaseTest {

    static CashFlowReportDatabase cashFlowReportDatabase = new CashFlowReportDatabase();

    @Test
    void save() {
        var cashFlowReport = new CashFlowReport("reason",new BigDecimal(100));
        assertTrue(cashFlowReportDatabase.save(cashFlowReport));
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