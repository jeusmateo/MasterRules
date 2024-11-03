package com.mycompany.masterrules.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author David Torres
 */
public class LoyaltyCard {
    private long ID;

    public boolean accessCredit(){
        return true;
    }
}
