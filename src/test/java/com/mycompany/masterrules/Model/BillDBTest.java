package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Database.BillDBManager;
import com.mycompany.masterrules.Database.ProductDBManager;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillDBTest {
    static final BillDBManager billDBManager = new BillDBManager();


    @Test
    void saveBill() {
        var Product = new Product("1", "Coca cola", "Refresco", BigDecimal.valueOf(10.0), BigDecimal.valueOf(1.0));
        var productDBManager = new ProductDBManager();
        // Save the product
        assertTrue(productDBManager.save(Product));
        var order = new Order();
        order.addProduct(Product, 1);
        var bill = new Bill(order, "David Torres");
        assertTrue(billDBManager.save(bill));
        System.out.println("Bill saved successfully");
    }

    @Test
    void findBill() {
        var Bill = billDBManager.findById(1L);
        assertNotNull(Bill);
        System.out.println("Bill found successfully");
    }

    @Test
    void updateBill() {
        var Bill = billDBManager.findById(1L);
        assertNotNull(Bill);
        Bill.setAmount(BigDecimal.valueOf(20.0));
        assertTrue(billDBManager.update(Bill));
        System.out.println("Bill updated successfully");
    }

    @Test
    void getAllBills() {
        var Bills = billDBManager.readAll();
        assertNotNull(Bills);
        System.out.println("All bills read successfully");
    }

    @Test
    void deleteBill() {
        var Bill = billDBManager.findById(1L);
        assertNotNull(Bill);
        assertTrue(billDBManager.delete(Bill));
        System.out.println("Bill deleted successfully");
    }
}
