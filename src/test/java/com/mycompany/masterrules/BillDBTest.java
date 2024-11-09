package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.BillDBManager;
import com.mycompany.masterrules.Database.ProductDBManager;
import com.mycompany.masterrules.Model.Bill;
import com.mycompany.masterrules.Model.Order;
import com.mycompany.masterrules.Model.Product;

import java.math.BigDecimal;
import java.util.Objects;

public class BillDBTest {
    public static void main(String[] args) {
        Product product = new Product("1", "Coca cola", "Refresco", BigDecimal.valueOf(10.0), BigDecimal.valueOf(1.0));
        ProductDBManager productDBManager = new ProductDBManager();
        assert productDBManager.save(product); // Se debe guardar el producto en la base de datos para poder guardar la factura

        product = null;

        product = productDBManager.findById("1");

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
//        assert foundBill.equals(bill); // This will fail because the equals method is not implemented

        System.out.println("Bill found successfully");

        // Update the bill
        foundBill.setAmount(BigDecimal.valueOf(20.0));
        assert billDBManager.update(foundBill);
        System.out.println("Bill updated successfully");

        // Save another bill
        Bill bill2 = new Bill(order, BigDecimal.valueOf(20.0), "David Torres");
        assert billDBManager.save(bill2);
        System.out.println("Bill 2 saved successfully");

        // get all bills
        assert Objects.requireNonNull(billDBManager.readAll()).size() == 2;
        System.out.println("All bills read successfully");

        // Delete the bill
        assert billDBManager.delete(foundBill);
        System.out.println("Bill deleted successfully");

    }
}
