package com.mycompany.masterrules.Model.customers;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

// TODO ELIMINAR ESTA CLASE SUBIRLA COMO ATRIBUTO DE LA CUENTA
@Embeddable
public class LoyaltyCard {
    @Column(name = "LoayltyCardID")
    private long ID;
    public String accessCode; // Make accessCode a static final constant or non-public and provide accessors if needed.



    public LoyaltyCard() {
        this.accessCode = "";
    }
    public String getAccessCode() {
        return accessCode;
    }

    public boolean accessCredit() {
        return true;
    }
}
