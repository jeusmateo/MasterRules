package com.mycompany.masterrules.Model;
import java.util.List;


public class Order {

    private CustomerAccount customer;
    private List<Product> products;
    private List<Combo> combos;
    private String comment;
    private String deliveryMethod;
    private String Fecha;

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void addCombo(Combo combo) {
        combos.add(combo);
    }

    public void removeCombo(Combo combo) {
        combos.remove(combo);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public CustomerAccount getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Combo> getCombos() {
        return combos;
    }

    public String getComment() {
        return comment;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setCustomer(CustomerAccount customer) {
        this.customer = customer;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCombos(List<Combo> combos) {
        this.combos = combos;
    }

}

