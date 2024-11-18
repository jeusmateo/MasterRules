package com.mycompany.masterrules.Model.customers;

import com.mycompany.masterrules.Database.CustomerDatabase;

import java.math.BigDecimal;
import java.util.List;


public class CustomerManager {
    private final CustomerDatabase customerDatabaseManager = new CustomerDatabase();

    public void removeCustomer(String id) {
        Customer customer = customerDatabaseManager.findById(id);
        customerDatabaseManager.delete(customer);
    }

    public void updateCustomerData(String id, String loyaltyPoints, boolean vipStatus, String storeCredit) {
        Customer customer = customerDatabaseManager.findById(id);
        assert customer != null;
        customer.getCustomerAccount().setLoyaltyPoints(Integer.parseInt(loyaltyPoints));
        customer.getCustomerAccount().setIsVIP(vipStatus);
        customer.getCustomerAccount().setStoreCredit(new BigDecimal(storeCredit));
        customerDatabaseManager.update(customer);
    }



    public void registerCustomer(String name, String phone, String loyaltyPoints, boolean vipStatus) throws Exception {

        if (!name.trim().isEmpty() && !phone.trim().isEmpty()) {
            int loyaltyPointsInt;
            if (!loyaltyPoints.isEmpty()) {
                loyaltyPointsInt = Integer.parseInt(loyaltyPoints);
            } else {
                loyaltyPointsInt = 0;
            }
            Customer customer = new Customer(name, phone, loyaltyPointsInt, vipStatus);
            customerDatabaseManager.save(customer);
        } else {
            throw new Exception("Parametros Incorrectos");
        }

    }

    public List<Customer> getCustomers() {
        return customerDatabaseManager.readAll();
    }

}
