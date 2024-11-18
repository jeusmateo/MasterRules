package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.customers.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author David Torres
 */

public class Bill2 {
    private String reference;
    private BigDecimal pagadoEnEfectivo;
    private BigDecimal pagadoEnTajeta;
    private BigDecimal pagadoEnCreditoDeTienda;
    private BigDecimal change;
    private String customerName;
    private String paymentMethod;
    private long id;

    /**
     * Nombre del empleado en turno
     */
    private String employeeName;

    /**
     * Cliente al que se le realiza la factura
     */

    private String customer;

    /**
     * Monto total de la factura
     */
    private BigDecimal amount;

    /**
     * Orden a la que pertenece la factura
     */

    public Bill2(String employeeName, String customer, BigDecimal amount, String paymentMethod) {
        this.employeeName = employeeName;
        this.customerName = customer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;

    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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


    public Bill2(Order orderArg, String employeeNameArg) {

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
    protected Bill2() {

    }


}
