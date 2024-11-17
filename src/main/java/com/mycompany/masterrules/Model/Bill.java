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


    public Bill(Order orderArg, String employeeNameArg) {
        order = orderArg;
        employeeName = employeeNameArg;
    }


    
    public void setReference(String reference) {
        this.reference = reference;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public Order getOrder() {
        return order;
    }


    public void setOrder(Order order) {
        this.order = order;
    }


    public BigDecimal getAmount() {
        return amount;
    }


    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    public String getEmployeeName() {
        return employeeName;
    }


    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }


    public Customer getCustomer() {
        return customer;
    }


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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
//        return id == bill.id && Objects.equals(employeeName, bill.employeeName) && Objects.equals(customer, bill.customer) && Objects.equals(amount, bill.amount) && Objects.equals(order, bill.order);
        return id == bill.id && Objects.equals(employeeName, bill.employeeName) && Objects.equals(customer, bill.customer) && amount.compareTo(bill.amount) == 0 && Objects.equals(order, bill.order);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, employeeName, customer, amount, order);
    }


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
