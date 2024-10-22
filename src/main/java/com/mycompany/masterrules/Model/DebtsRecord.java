/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.util.List;

/**
 *
 * @author IGNITER
 */
public class DebtsRecord {
    private List<Debt> debts;


    public void addDebt(Debt debt){
        debts.add(debt);
    }

    public void removeDebt(Debt debt){
        debts.remove(debt);
    }


}
