package com.mycompany.masterrules.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author David Torres
 */
public class LoyaltyCard {
    private long ID;
    public String accessCode;

    public String getAccessCode(){ return accessCode; }

    public boolean accessCredit(){
        return true;
    }
}
