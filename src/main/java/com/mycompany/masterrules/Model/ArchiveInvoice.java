package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Database.BillDBManager;
import com.mycompany.masterrules.Database.CustomerDBManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ArchiveInvoice{

    BillDBManager customerDBManager = new BillDBManager();

    public boolean ArchiveBill(Bill bill) {
        return customerDBManager.save(bill);
    }


    public List<Bill> getAllBills() {
        return customerDBManager.readAll();
    }

    public List<Bill> getBillsByDateRange(LocalDate beginDate, LocalDate endDate){
        var bills = customerDBManager.readAll();

        return bills.stream()
                .filter(bill -> bill.getOrder().getDate().isAfter(beginDate.atStartOfDay()) &&
                        bill.getOrder().getDate().isBefore(endDate.atStartOfDay()))
                .toList();
    }

}
