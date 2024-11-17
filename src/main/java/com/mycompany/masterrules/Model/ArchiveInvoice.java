package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Database.CustomerDBManager;

import java.math.BigDecimal;

public class ArchiveInvoice{

    CustomerDBManager customerDBManager = new CustomerDBManager();

    public void ArchiveBill(String id) {
        Customer customer = customerDBManager.findById(id);
        customerDBManager.delete(customer);
    }

}
