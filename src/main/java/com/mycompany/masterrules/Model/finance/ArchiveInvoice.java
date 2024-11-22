package com.mycompany.masterrules.Model.finance;

import com.mycompany.masterrules.Database.BillDatabase;
import com.mycompany.masterrules.Model.possystem.Bill;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArchiveInvoice{
    private List<Bill> savedBills;

    public ArchiveInvoice(){
        BillDatabase customerDBManager = new BillDatabase();
        savedBills = new ArrayList<>();

    }

    // Rename this method name to match the regular expression '^[a-z][a-zA-Z0-9]*$'.
    public boolean archiveBill(Bill bill) {
        BillDatabase customerDBManager = new BillDatabase();
        return customerDBManager.save(bill);
    }

    public List<Bill> getAllBills() {
        BillDatabase customerDBManager = new BillDatabase();
        savedBills =customerDBManager.readAll();
        return savedBills;
    }

    public List<Bill> getBillsByDateRange(LocalDateTime beginDate, LocalDateTime endDate){
        BillDatabase customerDBManager = new BillDatabase();
        var bills = customerDBManager.readAll();

        return bills.stream()
                .filter(bill -> bill.getOrder().getDate().isAfter(beginDate) && bill.getOrder().getDate().isBefore(endDate))
                .toList();
    }

}
