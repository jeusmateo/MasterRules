package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.CustomerDBManager;
import com.mycompany.masterrules.Model.Customer;
import com.mycompany.masterrules.Model.CustomerAccount;

import java.util.List;

public class CustomerDBTest {
    public static void main(String[] args) {
        Customer customer = new Customer("David", "1234567890");

        // Guardar un cliente con una cuenta
        CustomerDBManager customerDBManager = new CustomerDBManager();
        assert customerDBManager.save(customer);

        // Obtener todos los clientes
        List<Customer> customers = customerDBManager.readAll();
        assert customers.size() == 1;
        for (Customer c : customers) {
            System.out.println(c.toString());
        }

        // Actualizar un cliente
        customer.setCustomerName("David Torres");
        assert customerDBManager.update(customer);
//        assert customerDBManager.findById("1234567890").getCustomerName().equals("David Torres");





    }
}
