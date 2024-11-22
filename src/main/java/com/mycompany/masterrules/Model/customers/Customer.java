package com.mycompany.masterrules.Model.customers;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Clase que representa un cliente en la cafetería.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String customerPhoneNumber;
    private String customerName;
    @Embedded
    private CustomerAccount customerAccount;

    /**
     * Constructor que inicializa un cliente con su nombre, número de teléfono, puntos de lealtad y estado VIP.
     *
     * @param customerName El nombre del cliente.
     * @param customerPhoneNumber El número de teléfono del cliente.
     * @param loyaltyPoints Los puntos de lealtad del cliente.
     * @param vipStatus El estado VIP del cliente.
     */
    public Customer(String customerName, String customerPhoneNumber, int loyaltyPoints, boolean vipStatus) {
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAccount = new CustomerAccount(loyaltyPoints, vipStatus);
    }

    /**
     * Obtiene el ID del cliente.
     *
     * @return El ID del cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el ID del cliente.
     *
     * @param id El nuevo ID del cliente.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param customerName El nuevo nombre del cliente.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     *
     * @return El número de teléfono del cliente.
     */
    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    /**
     * Establece el número de teléfono del cliente.
     *
     * @param customerPhoneNumber El nuevo número de teléfono del cliente.
     */
    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    /**
     * Obtiene la cuenta del cliente.
     *
     * @return La cuenta del cliente.
     */
    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    /**
     * Establece la cuenta del cliente.
     *
     * @param customerAccount La nueva cuenta del cliente.
     */
    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }

    /**
     * Constructor protegido sin argumentos requerido por JPA.
     */
    protected Customer() {}

    /**
     * Compara este cliente con otro objeto para verificar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(customerPhoneNumber, customer.customerPhoneNumber) &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(customerAccount, customer.customerAccount);
    }

    /**
     * Calcula el código hash para este cliente.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, customerPhoneNumber, customerName, customerAccount);
    }
}