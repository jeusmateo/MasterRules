package com.mycompany.masterrules.Model;

import com.mycompany.masterrules.Database.CustomerDBManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * @author David Torres Clase que se encarga de gestionar los clientes
 */
public class CustomerManager {

    private List<Customer> customers;

    public CustomerManager() {
        CustomerDBManager customerDBManager = new CustomerDBManager();
        customers =  customerDBManager.readAll();
    }


    public void removeCustomer(String id) {
        CustomerDBManager customerDBManager = new CustomerDBManager();
        Customer customer = customerDBManager.findById(id);
        customerDBManager.delete(customer);
    }



    public void updateCustomerData(String id, String loyaltyPoints, boolean vipStatus, String storeCredit) {
        CustomerDBManager customerDBManager = new CustomerDBManager();
        Customer customer = customerDBManager.findById(id);
        customer.getCustomerAccount().setLoyaltyPoints(Integer.parseInt(loyaltyPoints));
        customer.getCustomerAccount().setIsVIP(vipStatus);
        customer.getCustomerAccount().setStoreCredit(new BigDecimal(storeCredit));
        customerDBManager.update(customer);


    }


    // TODO: siento que no solo registra, tmb pone los puntos, no se deberia poner en un metodo aparte? pero a lo mejor toy loquita
    public void registerCustomer(String name, String phone, String loyaltyPoints, boolean vipStatus) throws Exception {

        if (!name.trim().isEmpty() && !phone.trim().isEmpty()) {
            int loyaltyPointsInt;
            if (!loyaltyPoints.isEmpty()) {
                loyaltyPointsInt = Integer.parseInt(loyaltyPoints);
            } else {
                loyaltyPointsInt = 0;
            }
            Customer customer = new Customer(generateRandomId(), name, phone, loyaltyPointsInt, vipStatus);
            CustomerDBManager customerDBManager = new CustomerDBManager();
            customerDBManager.save(customer);
        } else {
            throw new Exception("Parametros Incorrectos");
        }

    }

    public List<Customer> getCustomers() {
        CustomerDBManager customerDBManager = new CustomerDBManager();
        customers = customerDBManager.readAll();
        return customers;
    }


    private boolean isIdUnique(String ID) {
        for (Customer customer : customers) {
            if (customer.getID().equals(ID)) {
                return false;
            }
        }
        return true;
    }

    public String generateRandomId() {
        Random random = new Random();
        long idNumber = Math.abs(random.nextLong());
        String idString = String.valueOf(idNumber);
        if (isIdUnique(idString)) {
            return idString;
        }
        return generateRandomId();
    }

}
