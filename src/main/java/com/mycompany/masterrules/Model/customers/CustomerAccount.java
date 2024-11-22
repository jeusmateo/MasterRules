package com.mycompany.masterrules.Model.customers;

import com.mycompany.masterrules.Model.retailsystem.Debt;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa la cuenta de un cliente en la cafetería.
 * La cuenta del cliente incluye puntos de lealtad, crédito en tienda, estado VIP, deudas y una tarjeta de lealtad.
 */
@Embeddable
public class CustomerAccount {

    private int loyaltyPoints;
    private BigDecimal storeCredit;
    private boolean isVIP;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Debt> totalDebt;
    @Embedded
    private LoyaltyCard loyaltyCard;

    /**
     * Constructor por defecto que inicializa una cuenta de cliente con valores predeterminados.
     */
    public CustomerAccount() {
        this.loyaltyPoints = 0;
        this.storeCredit = BigDecimal.ZERO;
        this.isVIP = false;
        this.totalDebt = new ArrayList<>();
        this.loyaltyCard = new LoyaltyCard(); // Debemos generar su ID
    }

    /**
     * Constructor que inicializa una cuenta de cliente con puntos de lealtad y estado VIP especificados.
     *
     * @param loyaltyPoints Los puntos de lealtad del cliente.
     * @param vipStatus El estado VIP del cliente.
     */
    public CustomerAccount(int loyaltyPoints, boolean vipStatus) {
        this.loyaltyPoints = loyaltyPoints;
        this.storeCredit = BigDecimal.ZERO;
        this.isVIP = vipStatus;
        this.totalDebt = new ArrayList<>();
        this.loyaltyCard = new LoyaltyCard(); // Debemos generar su ID
    }

    /**
     * Acumula un punto de lealtad en la cuenta del cliente.
     */
    public void accumulatePoints() {
        this.loyaltyPoints++;
    }

    /**
     * Verifica si el cliente es VIP.
     *
     * @return true si el cliente es VIP, false en caso contrario.
     */
    public boolean isIsVIP() {
        return isVIP;
    }

    /**
     * Establece el estado VIP del cliente.
     *
     * @param isVIP El nuevo estado VIP del cliente.
     */
    public void setIsVIP(boolean isVIP) {
        this.isVIP = isVIP;
    }

    /**
     * Obtiene la tarjeta de lealtad del cliente.
     *
     * @return La tarjeta de lealtad del cliente.
     */
    public LoyaltyCard getLoyaltyCard() {
        return loyaltyCard;
    }

    /**
     * Establece la tarjeta de lealtad del cliente.
     *
     * @param loyaltyCard La nueva tarjeta de lealtad del cliente.
     */
    public void setLoyaltyCard(LoyaltyCard loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    /**
     * Obtiene la lista de deudas del cliente.
     *
     * @return La lista de deudas del cliente.
     */
    public List<Debt> getTotalDebt() {
        return totalDebt;
    }

    /**
     * Establece la lista de deudas del cliente.
     *
     * @param totalDebt La nueva lista de deudas del cliente.
     */
    public void setTotalDebt(List<Debt> totalDebt) {
        this.totalDebt = totalDebt;
    }

    /**
     * Añade una deuda a la lista de deudas del cliente.
     *
     * @param debt La deuda a añadir.
     */
    public void addDebt(Debt debt) {
        this.totalDebt.add(debt);
    }

    /**
     * Obtiene los puntos de lealtad del cliente.
     *
     * @return Los puntos de lealtad del cliente.
     */
    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    /**
     * Establece los puntos de lealtad del cliente.
     *
     * @param loyaltyPoints Los nuevos puntos de lealtad del cliente.
     */
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    /**
     * Obtiene el crédito en tienda del cliente.
     *
     * @return El crédito en tienda del cliente.
     */
    public BigDecimal getStoreCredit() {
        return storeCredit;
    }

    /**
     * Establece el crédito en tienda del cliente.
     *
     * @param storeCredit El nuevo crédito en tienda del cliente.
     */
    public void setStoreCredit(BigDecimal storeCredit) {
        this.storeCredit = storeCredit;
    }

    /**
     * Devuelve una representación en cadena de la cuenta del cliente.
     *
     * @return Una cadena que representa la cuenta del cliente.
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

    /**
     * Compara esta cuenta de cliente con otro objeto para verificar si son iguales.
     *
     * @param o El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerAccount that = (CustomerAccount) o;
        return loyaltyPoints == that.loyaltyPoints &&
                isVIP == that.isVIP &&
                storeCredit.compareTo(that.storeCredit) == 0 &&
                Objects.equals(totalDebt, that.totalDebt) &&
                Objects.equals(loyaltyCard, that.loyaltyCard);
    }

    /**
     * Calcula el código hash para esta cuenta de cliente.
     *
     * @return El código hash.
     */
    @Override
    public int hashCode() {
        return Objects.hash(loyaltyPoints, storeCredit, isVIP, totalDebt, loyaltyCard);
    }
}