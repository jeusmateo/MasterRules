package com.mycompany.masterrules.Model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// TODO: Clase temporal para que no de errores
@Entity
@Table(name = "Customer")
public class CustomerAccount {
    private long ID;
    private String customerName;
    private String customerPhoneNumber;
    private int loyaltyPoints;
    @Column(name = "storeCredit")
    private double storeCredit;
    private boolean isVIP;
    //private List<DebtsRecord> totalDebt;
    private LoyaltyCard loyaltyCard;
    
    public CustomerAccount(String customerName, String customerPhone){
        this.ID = 0; //generarlo
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhone;
        this.loyaltyPoints = 0;
        this.storeCredit = 0;
        this.isVIP = false;
        //this.totalDebt = new ArrayList<>();
        this.loyaltyCard = new LoyaltyCard(); //Debemos generar su ID

    }

}
