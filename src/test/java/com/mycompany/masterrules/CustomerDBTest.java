package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.CustomerDBManager;
import com.mycompany.masterrules.Model.Customer;

import java.util.List;

public class CustomerDBTest {
    public static void main(String[] args) {
        Customer customer = new Customer("David", "1234567890");

        // Guardar un cliente con una cuenta
        System.out.println("Guardando un cliente con una cuenta");
        CustomerDBManager customerDBManager = new CustomerDBManager();
        assert customerDBManager.save(customer);

        // Obtener todos los clientes
        System.out.println("Obteniendo todos los clientes");
        List<Customer> customers = customerDBManager.readAll();
        assert customers.size() == 1;
        for (Customer c : customers) {
            System.out.println(c.toString());
        }

        // Actualizar un cliente
        System.out.println("Actualizando un cliente");
        customer.setCustomerName("David Torres");
        assert customerDBManager.update(customer);


        // Obtener un cliente por su id
        System.out.println("Obteniendo un cliente por su id");
        Customer customerById = customerDBManager.findById(customer.getID());
        assert customerById != null;

        // Eliminar un cliente
        System.out.println("Eliminando un cliente");
        assert customerDBManager.delete(customer);


    }
}
