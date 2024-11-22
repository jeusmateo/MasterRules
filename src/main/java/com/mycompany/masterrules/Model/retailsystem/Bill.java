package com.mycompany.masterrules.Model.retailsystem;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

/**
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
    private String paymentReferenceNumber;
    private BigDecimal paidInCash;
    private BigDecimal paidWithCard;
    private BigDecimal paidWithStoreCredit;
    private BigDecimal change;
    private String customerName;
    private PaymentMethod paymentMethod;

    public Bill(String employeeName, String customer, BigDecimal amount, PaymentMethod paymentMethod, Order order) {
        this.employeeName = employeeName;
        this.customerName = customer;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    public Bill(Order orderArg, String employeeNameArg) {
        order = orderArg;
        employeeName = employeeNameArg;
    }

    // Necesario para Hibernate
    protected Bill() {

    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentReferenceNumber() {
        return paymentReferenceNumber;
    }

    public void setPaymentReferenceNumber(String reference) {
        this.paymentReferenceNumber = reference;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public BigDecimal getPaidInCash() {
        return paidInCash;
    }

    public void setPaidInCash(BigDecimal pagadoEnEfectivo) {
        this.paidInCash = pagadoEnEfectivo;
    }

    public BigDecimal getPaidWithCard() {
        return paidWithCard;
    }

    public void setPaidWithCard(BigDecimal pagadoEnTajeta) {
        this.paidWithCard = pagadoEnTajeta;
    }

    public BigDecimal getPaidWithStoreCredit() {
        return paidWithStoreCredit;
    }

    public void setPaidWithStoreCredit(BigDecimal pagadoEnCreditoDeTienda) {
        this.paidWithStoreCredit = pagadoEnCreditoDeTienda;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id &&
                Objects.equals(paymentReferenceNumber, bill.paymentReferenceNumber) &&
                Objects.equals(paidInCash, bill.paidInCash) &&
                Objects.equals(paidWithCard, bill.paidWithCard) &&
                Objects.equals(paidWithStoreCredit, bill.paidWithStoreCredit) &&
                Objects.equals(change, bill.change) &&
                Objects.equals(customerName, bill.customerName) &&
                Objects.equals(paymentMethod, bill.paymentMethod) &&
                Objects.equals(employeeName, bill.employeeName) &&
                Objects.equals(amount, bill.amount) &&
                Objects.equals(order, bill.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentReferenceNumber, paidInCash, paidWithCard, paidWithStoreCredit, change, customerName, paymentMethod, id, employeeName, amount, order);
    }

    @Override
    public String toString() {
        return "Bill{" +
                "reference='" + paymentReferenceNumber + '\'' +
                ", pagadoEnEfectivo=" + paidInCash +
                ", pagadoEnTajeta=" + paidWithCard +
                ", pagadoEnCreditoDeTienda=" + paidWithStoreCredit +
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
