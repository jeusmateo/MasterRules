package com.mycompany.masterrules.Model;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author David Torres
 * Clase que se encarga de gestionar los clientes
 */
public class CustomerManager {
    private ArrayList<Customer> customers;

    public CustomerManager(){
        try{
        customers= new ArrayList();
        
        }catch(Exception e){
            
        }
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

    public void updateCustomerData(String id, String loyaltyPoints, boolean vipStatus, String storeCredit){
        
        for (Customer customer : customers) {
            if (customer.getID().equals(id) ) {
                int loyaltyPointsInt = Integer.valueOf(loyaltyPoints);
                double storeCreditDouble = Double.valueOf(storeCredit);
                customer.getCustomerAccount().setLoyaltyPoints(loyaltyPointsInt);
                customer.getCustomerAccount().setStoreCredit(storeCreditDouble);
                customer.getCustomerAccount().setIsVIP(vipStatus);
                
            }
        }
    }

    public void registerCustomer(String name, String phone, String loyaltyPoints, boolean vipStatus) throws Exception{
        if(!name.trim().isEmpty()&&!phone.trim().isEmpty()){
            int loyaltyPointsInt;
        if(!loyaltyPoints.equals("")){
            loyaltyPointsInt= Integer.valueOf(loyaltyPoints);
        }else{
            loyaltyPointsInt=0;
        }
        Customer customer = new Customer(generateRandomId(),name, phone,loyaltyPointsInt, vipStatus);
        customers.add(customer);
        }else{
            throw new Exception("Parametros Incorrectos");
        }
        
    }
    
    
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }
   
    private  boolean isIdUnique(String ID) {
        for (Customer customer : customers) {
            if (customer.getID().equals(ID) ) {
                return false;
            }
        }
        return true;
    }
    
    public String generateRandomId() {
        Random random = new Random();
        long idNumber= Math.abs(random.nextLong()); 
        String idString = String.valueOf(idNumber);
        if(isIdUnique(idString)){
            return idString;
        }
        return generateRandomId();
    } 
    
}
