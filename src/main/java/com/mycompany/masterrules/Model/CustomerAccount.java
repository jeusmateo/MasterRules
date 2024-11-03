package com.mycompany.masterrules.Model;


import java.util.ArrayList;

import jakarta.persistence.*;

// TODO: Clase temporal para que no de errores
@Table(name = "CUENTAS_CLIENTES")
@Embeddable
public class CustomerAccount {

    private int loyaltyPoints;

    @Column(name = "storeCredit")
    private double storeCredit;

    private boolean isVIP;

    @OneToMany
    private ArrayList<Debt> totalDebt;

    @Embedded
    private LoyaltyCard loyaltyCard;
    
    public CustomerAccount(){
        this.loyaltyPoints = 0;
        this.storeCredit = 0;
        this.isVIP = false;
        this.totalDebt = new ArrayList<>();
        this.loyaltyCard = new LoyaltyCard(); //Debemos generar su ID

    }



    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public double getStoreCredit() {
        return storeCredit;
    }

    public void setStoreCredit(double storeCredit) {
        this.storeCredit = storeCredit;
    }

    public boolean isIsVIP() {
        return isVIP;
    }

    public void setIsVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }

    public LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(LoyaltyCard loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    public ArrayList<Debt> getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(ArrayList<Debt> totalDebt) {
        this.totalDebt = totalDebt;
    }

    public void addDebt(Debt debt){
        this.totalDebt.add(debt);
    }

}
