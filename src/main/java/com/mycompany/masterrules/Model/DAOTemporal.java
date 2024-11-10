/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

/**
 *
 * @author chepo
 */
public class DAOTemporal {
    public boolean verificador(String name, String password){
        if(name.equals("admin")&& password.equals("admin") || name.equals("")&& password.equals("")){
            return true;
        }
        return false;
    }
}
