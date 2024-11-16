package com.mycompany.masterrules.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author David Torres
 */
@Embeddable
public class LoyaltyCard {
    @Column(name = "LoayltyCardID")
    private long ID;
    public String accessCode;

    public String getAccessCode(){ return accessCode; }

    public boolean accessCredit(){
        return true;
    }
}
