package com.mycompany.masterrules.Model;
import java.util.ArrayList;
/**
 *
 * @author David Torres
 * Clase que se encarga de gestionar los clientes
 */
public class CustomerManager {
    private ArrayList<Customer> customers;

    public CustomerManager(){
        customers= new ArrayList();
        customers.add(new Customer("Juan", "123"));
        customers.add(new Customer("Pedro", "456"));
        customers.add(new Customer("Maria", "789"));
        customers.add(new Customer("Jose", "101"));
        customers.add(new Customer("Luis", "112"));
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
