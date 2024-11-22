package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.finance.CashFlow;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CashFlowDatabaseTest {

    static CashFlowDatabase cashFlowDatabase = new CashFlowDatabase();

    @Test
    void save() {
        var cashFlowReport = new CashFlow("reason",new BigDecimal(100));
        assertTrue(cashFlowDatabase.save(cashFlowReport));
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