package com.mycompany.masterrules.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author David Torres
 */
@Entity
public class Bill {
    private String reference;
    private BigDecimal pagadoEnEfectivo;
    private BigDecimal pagadoEnTajeta;
    private BigDecimal pagadoEnCreditoDeTienda;
    private BigDecimal change;
    private String customerName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Nombre del empleado en turno */
    private String employeeName;

    /** Cliente al que se le realiza la factura */
    @Transient
    private Customer customer;

    /** Monto total de la factura */
    private BigDecimal amount;

    /** Orden a la que pertenece la factura */
    @Embedded
    private Order order;





    public BigDecimal getPagadoEnEfectivo() {
        return pagadoEnEfectivo;
    }

    public void setPagadoEnEfectivo(BigDecimal pagadoEnEfectivo) {
        this.pagadoEnEfectivo = pagadoEnEfectivo;
    }

    public BigDecimal getPagadoEnTajeta() {
        return pagadoEnTajeta;
    }

    public void setPagadoEnTajeta(BigDecimal pagadoEnTajeta) {
        this.pagadoEnTajeta = pagadoEnTajeta;
    }

    public BigDecimal getPagadoEnCreditoDeTienda() {
        return pagadoEnCreditoDeTienda;
    }

    public void setPagadoEnCreditoDeTienda(BigDecimal pagadoEnCreditoDeTienda) {
        this.pagadoEnCreditoDeTienda = pagadoEnCreditoDeTienda;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    /**
     * Constructor de la clase Bill
     * @param orderArg Orden a la que pertenece la factura
     * @param amountArg Monto total de la factura
     * @param employeeNameArg Nombre del empleado en turno
     */
    public Bill(Order orderArg, String employeeNameArg) {
        order = orderArg;
        employeeName = employeeNameArg;
    }


    
    public void setReference(String reference) {
        this.reference = reference;
    }
    /**
     * Obtiene el id de la factura
     * @return El id de la factura
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el id de la factura
     * @param id El id de la factura
     */
    public void setId(long id) {
        this.id = id;
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
     * @param customerAccount El cliente al que se le realiza la factura
     */
    public void setCustomerAccount(Customer customerAccount) {
        this.customer = customerAccount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    // Necesario para Hibernate
    protected Bill(){

    }

    /**
     * @param o Objeto a comparar con la factura
     * @return true si el objeto es igual a la factura, de lo contrario false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
//        return id == bill.id && Objects.equals(employeeName, bill.employeeName) && Objects.equals(customer, bill.customer) && Objects.equals(amount, bill.amount) && Objects.equals(order, bill.order);
        return id == bill.id && Objects.equals(employeeName, bill.employeeName) && Objects.equals(customer, bill.customer) && amount.compareTo(bill.amount) == 0 && Objects.equals(order, bill.order);
    }

    /**
     * @return El hash de la factura
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, employeeName, customer, amount, order);
    }

    /**
     * @return La representación en cadena de la factura
     */
    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", customer=" + customer +
                ", amount=" + amount +
                ", order=" + order +
                '}';
    }
}
