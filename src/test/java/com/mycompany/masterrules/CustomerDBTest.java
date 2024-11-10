package com.mycompany.masterrules;

import com.mycompany.masterrules.Database.CustomerDBManager;
import com.mycompany.masterrules.Model.Customer;
import com.mycompany.masterrules.Model.Debt;
import com.mycompany.masterrules.Model.Order;
import com.mycompany.masterrules.Model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CustomerDBTest {
    public static void main(String[] args) {
        Customer customer = new Customer("1", "David", "1234567890");

        // Guardar un cliente con una cuenta
        System.out.println("Guardando un cliente con una cuenta");
        CustomerDBManager customerDBManager = new CustomerDBManager();
        assert customerDBManager.save(customer);


        // Obtener todos los clientes
        System.out.println("Obteniendo todos los clientes");
        List<Customer> customers = customerDBManager.readAll();
        assert Objects.requireNonNull(customers).size() == 1;
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

        // Añadir deuda a un cliente
        System.out.println("Añadiendo deuda a un cliente");
        Product newProduct = new Product("111", "Producto 1", "Descripcion 1", BigDecimal.valueOf(100), BigDecimal.valueOf(200));
        Order newOrder = new Order();
        newOrder.addProduct(newProduct);
        Debt newDebt = new Debt(newOrder, BigDecimal.valueOf(1000));
        customer.getCustomerAccount().addDebt(newDebt);
        assert customerDBManager.update(customer);

        // Obtiene al cliente actualizado
        System.out.println("Obteniendo al cliente actualizado");
        Customer updatedCustomer = customerDBManager.findById(customer.getID());
        assert updatedCustomer != null;
        System.out.println(updatedCustomer.toString());

        // Eliminar un cliente
        System.out.println("Eliminando un cliente");
        assert customerDBManager.delete(customer);

    }
}
