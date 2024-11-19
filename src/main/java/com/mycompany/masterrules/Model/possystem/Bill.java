package com.mycompany.masterrules.Model.possystem;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author David Torres
 */
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String employeeName;
    private BigDecimal amount;
    @Embedded
    private Order order;
    private String reference;
    private BigDecimal pagadoEnEfectivo;
    private BigDecimal pagadoEnTajeta;
    private BigDecimal pagadoEnCreditoDeTienda;
    private BigDecimal change;
    private String customerName;
    private String paymentMethod;
    private LocalDateTime date; //TODO Remove this unused "date" private field.

    public Bill(String employeeName, String customer, BigDecimal amount, String paymentMethod,Order order) {
        this.employeeName = employeeName;
        this.customerName = customer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.date = LocalDateTime.now();
        this.order = order;


    }

    public String getReference() {
        return reference;
    }
    public String getPaymentMethod(){
        return paymentMethod;
    }
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
        return id == bill.id &&
                Objects.equals(reference, bill.reference) &&
                Objects.equals(pagadoEnEfectivo, bill.pagadoEnEfectivo) &&
                Objects.equals(pagadoEnTajeta, bill.pagadoEnTajeta) &&
                Objects.equals(pagadoEnCreditoDeTienda, bill.pagadoEnCreditoDeTienda) &&
                Objects.equals(change, bill.change) &&
                Objects.equals(customerName, bill.customerName) &&
                Objects.equals(paymentMethod, bill.paymentMethod) &&
                Objects.equals(employeeName, bill.employeeName) &&
                Objects.equals(amount, bill.amount) &&
                Objects.equals(order, bill.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, pagadoEnEfectivo, pagadoEnTajeta, pagadoEnCreditoDeTienda, change, customerName, paymentMethod, id, employeeName, amount, order);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "reference='" + reference + '\'' +
                ", pagadoEnEfectivo=" + pagadoEnEfectivo +
                ", pagadoEnTajeta=" + pagadoEnTajeta +
                ", pagadoEnCreditoDeTienda=" + pagadoEnCreditoDeTienda +
                ", change=" + change +
                ", customerName='" + customerName + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", amount=" + amount +
                ", order=" + order +
                '}';
    }
}
