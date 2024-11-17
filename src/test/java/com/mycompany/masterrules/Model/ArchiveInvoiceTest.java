package com.mycompany.masterrules.Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArchiveInvoiceTest {

    static ArchiveInvoice archiveInvoice = new ArchiveInvoice();

    @Test
    void archiveBill() {
        var order = new Order();
        order.setDate(LocalDateTime.of(2021, 1, 1, 12, 52));
        var bill = new Bill(order, "Chepo");
        assertTrue(archiveInvoice.ArchiveBill(bill));
    }

    @Test
    void getBillsByDateRange() {
        archiveBill();
        var beginDate = LocalDate.of(2021, 1, 1);
        var endDate = LocalDate.of(2021, 1, 2);
        assertFalse(archiveInvoice.getBillsByDateRange(beginDate, endDate).isEmpty());
    }

    @Test
    void getAllBills() {
        archiveBill();
        assertFalse(archiveInvoice.getAllBills().isEmpty());
    }
}