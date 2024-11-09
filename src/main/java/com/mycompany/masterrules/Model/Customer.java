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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String customerName;
    private String customerPhoneNumber;
    @Embedded
    private CustomerAccount customerAccount;

    /*
     * Constructor por defecto para JPA
     **/
    protected Customer() {
    }


    /**
     * Constructor de la clase
     * @param customerName Nombre del cliente
     * @param customerPhoneNumber Numero de telefono del cliente
     */

    public Customer(String customerName, String customerPhoneNumber) {
        //Falta ver como generarlothis.ID = Random.nextLong(1000)+1;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccount = new CustomerAccount();
    }

    /**
     * @return Retorna el ID del cliente
     */
    public long getID() {
        return ID;
    }

    /**
     * Recibe el ID del cliente y lo asigna a la variable ID
     *
     * @param ID Recibe el ID del cliente
     */
    public void setID(long ID) {
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
        return ID == customer.ID && Objects.equals(customerName, customer.customerName) && Objects.equals(customerPhoneNumber, customer.customerPhoneNumber) && Objects.equals(customerAccount, customer.customerAccount);
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
