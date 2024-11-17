package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Database.CustomerDBManager;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author David Torres Clase que se encarga de gestionar los clientes
 */
public class CustomerManager {
    CustomerDBManager customerDBManager = new CustomerDBManager();

    public void removeCustomer(String id) {
        Customer customer = customerDBManager.findById(id);
        customerDBManager.delete(customer);
    }

    public void updateCustomerData(String id, String loyaltyPoints, boolean vipStatus, String storeCredit) {
        Customer customer = customerDBManager.findById(id);
        customer.getCustomerAccount().setLoyaltyPoints(Integer.parseInt(loyaltyPoints));
        customer.getCustomerAccount().setIsVIP(vipStatus);
        customer.getCustomerAccount().setStoreCredit(new BigDecimal(storeCredit));
        customerDBManager.update(customer);
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
            customerDBManager.save(customer);
        } else {
            throw new Exception("Parametros Incorrectos");
        }

    }

    public List<Customer> getCustomers() {
        return customerDBManager.readAll();
    }

}
