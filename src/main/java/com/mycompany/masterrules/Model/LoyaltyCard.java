package com.mycompany.masterrules.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 *
 * @author David Torres
 */
@Embeddable
public class LoyaltyCard {
    @Column(name = "LoyaltyCardID")
    private long ID;

    public boolean accessCredit(){
        return true;
    }
}
