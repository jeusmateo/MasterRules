package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 *
 * @autor: David Torres
 *
 * Clase que representa a un cliente en MasterRules
 */
@Entity
public class Customer {

    /**
     * Atributos de la clase
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String ID;
    private String customerName;
    private String customerPhoneNumber;
    @Embedded
    private CustomerAccount customerAccount;




    /**
     * Constructor de la clase
     *
     * @param customerName        Nombre del cliente
     * @param customerPhoneNumber Numero de telefono del cliente
     */
    //TODO: cual es el constructor bueno? o son dos?
    public Customer(String customerName, String customerPhoneNumber) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccount = new CustomerAccount();
    }
    public Customer(String customerName, String customerPhoneNumber, int loyaltyPoints, boolean vipStatus) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccount = new CustomerAccount(loyaltyPoints, vipStatus);
    }
    /**
     * @return Retorna el ID del cliente
     */
    public String getID() {
        return ID;
    }

    /**
     * Recibe el ID del cliente y lo asigna a la variable ID
     *
     * @param ID Recibe el ID del cliente
     */
    public void setID(String ID) {
        this.ID = ID;
    }

    /**
     * @return Retorna el nombre del cliente
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Recibe el nombre del cliente y lo asigna a la variable customerName
     *
     * @param customerName Recibe el nombre del cliente
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return Retorna el numero de telefono del cliente
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * Recibe el numero de telefono del cliente y lo asigna a la variable customerPhoneNumber
     *
     * @param customerPhoneNumber Recibe el numero de telefono del cliente
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * @return Retorna la cuenta del cliente
     */
    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    /**
     * Recibe la cuenta del cliente y la asigna a la variable customerAccount
     *
     * @param customerAccount Recibe la cuenta del cliente
     */
    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(ID, customer.ID) && Objects.equals(customerName, customer.customerName) && Objects.equals(customerPhoneNumber, customer.customerPhoneNumber) && Objects.equals(customerAccount, customer.customerAccount);
    }


    /*
     * Constructor por defecto para JPA
     **/
    protected Customer() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, customerName, customerPhoneNumber, customerAccount);
    }

    /**
     * NOTA: Este constructor no se usa en el programa, es solo para pruebas
     *
     * @return Retorna una cadena con la información del cliente
     */
    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                ", customerAccount=" + customerAccount +
                '}';
    }
}
