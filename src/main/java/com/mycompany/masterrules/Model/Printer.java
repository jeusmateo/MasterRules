package com.mycompany.masterrules.Model;

import java.time.format.DateTimeFormatter;

/**
 *
 * @author David Torres
 */
public class Printer {

    //TODO: se debe cambiar a ingles la parte d elos metodos
    /*
    public void imprimirOrder(Order order){
        System.out.println("=====================================");
        System.out.println("Imprimiendo orden: " + order.getId());
        System.out.println("Imprimiendo productos: ");
        for (Product product : order.getProducts()) {
            System.out.println("Nombre : "+product.getProductName());
            System.out.println("Precio: "+product.getPrice());
        }
        
        if(!order.getCombos().isEmpty()){
            System.out.println("COMBOS: ");
            for (Combo combo : order.getCombos()) {
                System.out.println("Nombre : "+combo.getComboName());
                for (Product product : combo.getProducts()) {
                    System.out.println("Nombre : "+product.getProductName());
                    System.out.println("Precio: "+product.getPrice());
                }
            }
        } else {
            System.out.println("No hay combos en la orden");
        }
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("Fecha: "+order.getDate().format(formato));
        System.out.println("Comentarios adicionales: "+order.getComment());
        System.out.println("Entregar en: "+order.getDeliveryMethod());
        System.out.println("=====================================");

    }

     */

    public void imprimirBill(Bill bill) {
        System.out.println("=====================================");
        System.out.println("Imprimiendo factura: ");
        System.out.println("Nombre del empleado: " + bill.getEmployeeName());
        if (bill.getCustomer() != null) {
            System.out.println("Nombre del cliente: " + bill.getCustomer().getCustomerName());
        } else {
            System.out.println("Nombre del cliente: Público general");
        }

        System.out.println("Productos: ");
/*
        for (Product product : bill.getOrder().getProducts()) {
            System.out.println("Nombre : "+product.getProductName());
            System.out.println("Precio: "+product.getPrice());
        }
        System.out.println("Monto: "+bill.getAmount());
        System.out.println("=====================================");
    }

 */
    }
}
