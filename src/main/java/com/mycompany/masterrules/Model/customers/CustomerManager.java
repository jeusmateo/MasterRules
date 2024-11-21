package com.mycompany.masterrules.Model.customers;

import com.mycompany.masterrules.Database.CustomerDatabase;
import com.mycompany.masterrules.Model.users.UserAccount;

import java.math.BigDecimal;
import java.util.List;


public class CustomerManager {
    private final CustomerDatabase customerDatabase = new CustomerDatabase();

    public void removeCustomer(String id) {
        Customer customer = customerDatabase.findById(id);
        customerDatabase.delete(customer);
    }

    public void updateCustomerData(Customer customer) {
        var foundUser = customerDatabase.findById(customer.getId());

        if (foundUser == null) {
            System.out.println("No se encontró el cliente");
            return;
        }
        customerDatabase.update(customer);
    }


    public void registerCustomer(String name, String phone, String loyaltyPoints, boolean vipStatus) {
        if (!name.trim().isEmpty() && !phone.trim().isEmpty()) {
            int loyaltyPointsInt;
            try {
                if (!loyaltyPoints.isEmpty()) {
                    loyaltyPointsInt = Integer.parseInt(loyaltyPoints);
                } else {
                    loyaltyPointsInt = 0;
                }
                Customer customer = new Customer(name, phone, loyaltyPointsInt, vipStatus);
                customerDatabase.save(customer);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Puntos de fidelidad no válidos", e);
            }
        } else {
            throw new IllegalArgumentException("Parametros Incorrectos: Nombre o teléfono vacío");
        }
    }

    public Customer findCustomerById(String id) {
        return customerDatabase.findById(id);
    }

    public List<Customer> getCustomers() {
        return customerDatabase.readAll();
    }

}
