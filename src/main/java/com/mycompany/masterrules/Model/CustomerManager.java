package com.mycompany.masterrules.Model;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author IGNITER
 */
public class CustomerManager {
    private ArrayList<Customer> customers;

    public CustomerManager(){
        customers= new ArrayList();
    }
    public void addCustomer(String name, String phone){
        
    }

    public void removeCustomer(long ID){
        //Logica para remover cliente
    }

    public void setVIP(long ID){
        //Logica para setar cliente como VIP
        //cliente.setVIP(true);
    }

    public void registerCustomer(String name, String phone){
        Customer customer = new Customer(name, phone);
        customers.add(customer);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
    
    
}
