package com.mycompany.masterrules.Model.possystem;

import com.mycompany.masterrules.Model.customers.Customer;
import com.mycompany.masterrules.Model.cafeteria.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Embeddable
public class Order {
    @Column(name = "order_id")
    private long id; //Chepo necesidad: Este es el numero de la comanda
    @ManyToOne
    private Customer customer; //Chepo necesidad: el nombre del cliente a quien entregar, Tomando en cuenta, esto deberia ser solo un nombre??? <- Nota muy
    // @ManyToMany(fetch = FetchType.EAGER)
    @Column(name = "order_employee_name")
    private String employeeName; //TODO debe ser un nombre mas claro que diga que es el empleado de turno quien toma la comanda
    private String numeroDeMesa; //TODO CAMBIAR NOMBRE A INGLES
    private String comment;
    private String deliveryMethod; //Chepo necesidad: esto aun falta por checar
    private LocalDateTime date; //Chepo necesidad: TODO NO DEBERIA SER DATE SINO QUE LA FECHA DE ENVIADO A COCINA.
    private BigDecimal totalAmount; //Chepo necesidad: TODO ESTO DEBERIA SER EL SUBTOTAL
    @OneToMany
    private List<PedidoComanda> pedidoComandaList; //TODO siento que no es del todo legible al utilizar algo como products en un objeto que no es producto


    public Order() {
        pedidoComandaList = new ArrayList<>();
    }


    /*
        public void calculateTotalAmount(){
            BigDecimal total = new BigDecimal(0);
            for(Product product: products){
                total=total.add(product.getPrice());
            }
            for(Combo combo: combos){
                total = total.add(combo.getPrice());
            }
            totalAmount = total;
        }

     */
    //TODO no funciona, si deberiamos checar que sean iguales y acceder al index del array y editarlo
    public void addProductToOrderItemList(PedidoComanda newPedidoComanda) {
        for(PedidoComanda pedidoComanda : pedidoComandaList){
            if(newPedidoComanda.getProduct().equals(pedidoComanda.getProduct())){
                pedidoComanda.addQuantity();
            }else{
                pedidoComandaList.add(newPedidoComanda);
            }
        }
    }

    public void setEmployeeName(String employeeName){
        this.employeeName = employeeName;
    }
    public void removeProduct(Product product) {
        pedidoComandaList.remove(product);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDateNow(){
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", employeeName='" + employeeName + '\'' +
                ", numeroDeMesa='" + numeroDeMesa + '\'' +
                ", comment='" + comment + '\'' +
                ", deliveryMethod='" + deliveryMethod + '\'' +
                ", date=" + date +
                ", totalAmount=" + totalAmount +
                ", products=" + pedidoComandaList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Objects.equals(customer, order.customer) &&
                Objects.equals(employeeName, order.employeeName) &&
                Objects.equals(numeroDeMesa, order.numeroDeMesa) &&
                Objects.equals(comment, order.comment) &&
                Objects.equals(deliveryMethod, order.deliveryMethod) &&
                Objects.equals(date, order.date) &&
                Objects.equals(totalAmount, order.totalAmount) &&
                Objects.equals(pedidoComandaList, order.pedidoComandaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, employeeName, numeroDeMesa, comment, deliveryMethod, date, totalAmount, pedidoComandaList);
    }
}
