package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

/**
 *
 * @autor: David Torres
 *
 * Clase que representa a un cliente en MasterRules
 */
@Entity
@Table(name = "CLIENTES")
public class Customer {

    /**
     * Atributos de la clase
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ClienteID")
    private long ID;

    @Column(name = "Nombre")
    private String customerName;

    @Column(name = "Telefono")
    private String customerPhoneNumber;

    private CustomerAccount customerAccount;

    /*
     * Constructor por defecto para JPA
     **/
    public Customer() {
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

}
