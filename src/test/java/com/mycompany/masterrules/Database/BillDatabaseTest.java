package com.mycompany.masterrules.Database;

import com.mycompany.masterrules.Model.cafeteria.Product;
import com.mycompany.masterrules.Model.possystem.Bill;
import com.mycompany.masterrules.Model.possystem.Order;
import com.mycompany.masterrules.Model.possystem.PedidoComanda;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BillDatabaseTest {
    static final BillDatabase BILL_DATABASE_MANAGER = new BillDatabase();


    @Test
    void saveBill() {
        var Product = new Product("1", "Coca cola", "Refresco", BigDecimal.valueOf(10.0), BigDecimal.valueOf(1.0));
        var productDBManager = new ProductDatabase();
        // Save the product
        assertTrue(productDBManager.save(Product));
        var order = new Order();
        var orderItem = new PedidoComanda(Product);
        order.addProductToOrderItemList(orderItem);
        var bill = new Bill(order, "David Torres");
        assertTrue(BILL_DATABASE_MANAGER.save(bill));
        System.out.println("Bill saved successfully");
    }

    @Test
    void findBill() {
        var Bill = BILL_DATABASE_MANAGER.findById(1L);
        assertNotNull(Bill);
        System.out.println("Bill found successfully");
    }

    @Test
    void updateBill() {
        var Bill = BILL_DATABASE_MANAGER.findById(1L);
        assertNotNull(Bill);
        Bill.setAmount(BigDecimal.valueOf(20.0));
        assertTrue(BILL_DATABASE_MANAGER.update(Bill));
        System.out.println("Bill updated successfully");
    }

    @Test
    void getAllBills() {
        var Bills = BILL_DATABASE_MANAGER.readAll();
        assertNotNull(Bills);
        System.out.println("All bills read successfully");
    }

    @Test
    void deleteBill() {
        var Bill = BILL_DATABASE_MANAGER.findById(1L);
        assertNotNull(Bill);
        assertTrue(BILL_DATABASE_MANAGER.delete(Bill));
        System.out.println("Bill deleted successfully");
    }
}
