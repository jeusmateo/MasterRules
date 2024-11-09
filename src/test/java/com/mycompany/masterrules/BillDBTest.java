package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.BillDBManager;
import com.mycompany.masterrules.Model.Bill;
import com.mycompany.masterrules.Model.Order;
import com.mycompany.masterrules.Model.Product;

import java.math.BigDecimal;

public class BillDBTest {
    public static void main(String[] args) {
        Product product = new Product("1", "Coca cola", "Refresco", BigDecimal.valueOf(10.0), BigDecimal.valueOf(1.0));

        Order order = new Order();
        order.addProduct(product);

        Bill bill = new Bill(order, BigDecimal.valueOf(10.0), "David Torres");

        BillDBManager billDBManager = new BillDBManager();

        // Save the bill
        assert billDBManager.save(bill);

        System.out.println("Bill saved successfully");

        // find the bill
        Bill foundBill = billDBManager.findById(bill.getId());
        assert foundBill != null;

        System.out.println("Bill found successfully");



    }
}
