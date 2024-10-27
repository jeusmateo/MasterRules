/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author IGNITER
 */
public class Printer {
    public void imprimir(Order order){
        System.out.println("=====================================");
        System.out.println("Imprimiendo orden: " + order.getId());
        System.out.println("Imprimiendo productos: ");
        for (Product product : order.getProducts()) {
            System.out.println("Nombre : "+product.getProductName());
            System.out.println("Precio: "+product.getPrice());
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Fecha: "+order.getDate().format(formato));
        System.out.println("=====================================");

    }
}
