package com.mycompany.masterrules.Model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;
import org.hibernate.sql.results.graph.Fetch;

// TODO: Clase temporal para que no de errores (antiguo)
@Embeddable
public class CustomerAccount {

    private int loyaltyPoints;
    private BigDecimal storeCredit;
    private boolean isVIP;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Debt> totalDebt;
    @Embedded // TODO: Revisar si es necesario (antiguo)
    private LoyaltyCard loyaltyCard;


    //TODO: DOS CONSTRUCTORES?

    public CustomerAccount(){
        this.loyaltyPoints = 0;
        this.storeCredit = BigDecimal.ZERO;
        this.isVIP = false;
        this.totalDebt = new ArrayList<>();
        this.loyaltyCard = new LoyaltyCard(); //Debemos generar su ID

    }
     public CustomerAccount(int loyaltyPoints, boolean vipStatus){
        this.loyaltyPoints = loyaltyPoints;
        this.storeCredit = BigDecimal.ZERO;
        this.isVIP = vipStatus;
        this.totalDebt = new ArrayList<>();
        this.loyaltyCard = new LoyaltyCard(); //Debemos generar su ID

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

    public List<Debt> getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(ArrayList<Debt> totalDebt) {
        this.totalDebt = totalDebt;
    }

    public void addDebt(Debt debt){
        this.totalDebt.add(debt);
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public BigDecimal getStoreCredit() {
        return storeCredit;
    }

    public void setStoreCredit(BigDecimal storeCredit) {
        this.storeCredit = storeCredit;
    }


    /*

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAccount that = (CustomerAccount) o;
        //TODO Corregir
        //return loyaltyPoints == that.loyaltyPoints && Double.compare(storeCredit, that.storeCredit) == 0 && isVIP == that.isVIP && Objects.equals(totalDebt, that.totalDebt);
    }
*/

    /**
     * NOTA: Este constructor no se usa en el programa, es solo para pruebas
     *
     * @return Retorna una cadena con la información de la cuenta del cliente
     */
    @Override
    public String toString() {
        return "CustomerAccount{" +
                "loyaltyPoints=" + loyaltyPoints +
                ", storeCredit=" + storeCredit +
                ", isVIP=" + isVIP +
                ", totalDebt=" + totalDebt +
                ", loyaltyCard=" + loyaltyCard +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(loyaltyPoints, storeCredit, isVIP, totalDebt);
    }
}
