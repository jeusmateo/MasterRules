package com.mycompany.masterrules.Model.possystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Printer {



    public void imprimirOrder(Order order){
        LocalDateTime now = order.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, hh:mm:ss a");
        String formattedDate = now.format(formatter);
        System.out.println("Fecha: "+formattedDate);
        System.out.println("=====================================");
        System.out.println("Imprimiendo orden: " + order.getId());
        System.out.println("Productos: ");
        for (OrderItem product : order.getPedidoComandaList()) {
            System.out.print("Nombre : "+product.getProductName());
            System.out.println("   Cantidad: "+product.getQuantity());

        }
        System.out.println("Comentarios adicionales: "+order.getComment());
        System.out.println("Entregar en: "+order.getDeliveryMethod());
        System.out.println("=====================================");

    }




    public void imprimirBill(Bill bill) {
        System.out.println("=====================================");
        System.out.println("Imprimiendo factura: ");
        System.out.println("Nombre del empleado: " + bill.getEmployeeName());
        if (bill.getCustomerName() != null) {
            System.out.println("Nombre del cliente: " + bill.getCustomerName());
        } else {
            System.out.println("Nombre del cliente: Público general");
        }

        System.out.println("Productos: ");
        for (OrderItem product : bill.getOrder().getPedidoComandaList()) {
            System.out.print("Nombre : "+product.getProductName());
            System.out.print("   Cantidad: "+product.getQuantity());
            System.out.println("  Precio : "+product.getTotalPrice());

        }
        StringBuilder description = new StringBuilder("");
        switch (bill.getPaymentMethod()) { //todo cambiar por el enum
            case "Efectivo":
                description.append("PARADO CON EFECTIVO: ");
                description.append(String.valueOf(bill.getAmount()));
                description.append("\n");
                description.append("CAMBIO: $"+ bill.getChange());
                description.append("\n");
                break;

            case "PaymentType.CARD":
               description.append("PARADO CON TARJETA: ");
                description.append(String.valueOf(bill.getAmount()));
                description.append("\n").append("REFERENCIA : "+bill.getReference()).append("\n");
                break;

            case "PaymentType.STORE_CREDIT":
                description.append("PARADO CON TARJETA: ");
                description.append(String.valueOf(bill.getAmount()));
                description.append("\n").append("USUARIO : "+bill.getOrder().getCustomerName()).append("\n");
                break;

            default:
                break;
        }

    }
}
