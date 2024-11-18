package com.mycompany.masterrules.Model.customers;

import com.mycompany.masterrules.Database.CustomerDBManager;

import java.math.BigDecimal;
import java.util.List;


public class CustomerManager {
    private final CustomerDBManager customerDBManager = new CustomerDBManager();

    public void removeCustomer(String id) {
        Customer customer = customerDBManager.findById(id);
        customerDBManager.delete(customer);
    }

    public void updateCustomerData(String id, String loyaltyPoints, boolean vipStatus, String storeCredit) {
        Customer customer = customerDBManager.findById(id);
        assert customer != null;
        customer.getCustomerAccount().setLoyaltyPoints(Integer.parseInt(loyaltyPoints));
        customer.getCustomerAccount().setIsVIP(vipStatus);
        customer.getCustomerAccount().setStoreCredit(new BigDecimal(storeCredit));
        customerDBManager.update(customer);
    }



    public void registerCustomer(String name, String phone, String loyaltyPoints, boolean vipStatus){
        if (!name.trim().isEmpty() && !phone.trim().isEmpty()) {
            int loyaltyPointsInt;
            try {
                if (!loyaltyPoints.isEmpty()) {
                    loyaltyPointsInt = Integer.parseInt(loyaltyPoints);
                } else {
                    loyaltyPointsInt = 0;
                }
                Customer customer = new Customer(name, phone, loyaltyPointsInt, vipStatus);
                customerDBManager.save(customer);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Puntos de fidelidad no válidos", e);
            }
        } else {
            throw new IllegalArgumentException("Parametros Incorrectos: Nombre o teléfono vacío");
        }
    }


    public List<Customer> getCustomers() {
        return customerDBManager.readAll();
    }

}
