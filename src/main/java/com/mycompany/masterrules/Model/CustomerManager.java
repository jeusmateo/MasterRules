/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;
import java.util.List;
/**
 *
 * @author IGNITER
 */
public class CustomerManager {
    private List<CustomerAccount> customers;

    public void addCustomer(CustomerAccount customer){
        customers.add(customer);
    }

    public void removeCustomer(long ID){
        //Logica para remover cliente
    }

    public void setVIP(long ID){
        //Logica para setar cliente como VIP
        //cliente.setVIP(true);
    }

    public void registerCustomer(String name, String phone){
        customers.add(new CustomerAccount(name, phone));
    }
}
