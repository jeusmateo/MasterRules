package com.mycompany.masterrules.Model;

import java.math.BigDecimal;

/**
 *
 * @author David Torres
 */
public class Bill {
    /** Nombre del empleado en turno */
    private String employeeName;
    /** Cliente al que se le realiza la factura */
    private Customer customer;
    /** Monto total de la factura */
    private BigDecimal amount;
    /** Orden a la que pertenece la factura */
    private Order order;

    /**
     * Constructor de la clase Bill
     * @param orderArg Orden a la que pertenece la factura
     * @param amountArg Monto total de la factura
     * @param employeeNameArg Nombre del empleado en turno
     */

    public Bill(Order orderArg, BigDecimal amountArg, String employeeNameArg) {
        order = orderArg;
        amount = amountArg;
        employeeName = employeeNameArg;
    }

    /**
     * Obtiene la orden a la que pertenece la factura
     * @return La orden a la que pertenece la factura
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Establece la orden a la que pertenece la factura
     * @param order La orden a la que pertenece la factura
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Obtiene el monto total de la factura
     * @return El monto total de la factura
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Establece el monto total de la factura
     * @param amount El monto total de la factura
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Obtiene el nombre del empleado en turno
     * @return El nombre del empleado en turno
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * Establece el nombre del empleado en turno
     * @param employeeName El nombre del empleado en turno
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    /**
     * Obtiene el cliente al que se le realiza la factura
     * @return El cliente al que se le realiza la factura
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Establece el cliente al que se le realiza la factura
     * @param customer El cliente al que se le realiza la factura
     */
    public void setCustomerAccount(Customer customerAccount) {
        this.customer = customerAccount;
    }

}
