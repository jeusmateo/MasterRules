package com.mycompany.masterrules.Model.retailsystem;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Printer {



    public void imprimirOrder(Order order){
        String fileName= "order.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            LocalDateTime now = order.getDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, hh:mm:ss a");
            String formattedDate = now.format(formatter);
            writer.write("Fecha: "+formattedDate);
            writer.write("=====================================");
            writer.write("Imprimiendo orden: " + order.getId());
            writer.write("Productos: ");
            for (OrderItem product : order.getPedidoComandaList()) {
                writer.write("Nombre : "+product.getProductName());
                writer.write("   Cantidad: "+product.getQuantity());

            }
            writer.write("Comentarios adicionales: "+order.getComment());
            writer.write("Para: "+order.getCustomerName());
            writer.write("Entregar en: "+order.getDeliveryMethod());
            writer.write("=====================================");
        }catch (IOException e) {
            e.printStackTrace();
        }





    }




    public void imprimirBill(Bill bill) {
        String fileName = "bill.txt"; // Nombre del archivo donde se guardará la factura
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("=====================================\n");
            writer.write("Imprimiendo factura :" +bill.getId() + "\n");
            writer.write("Nombre del empleado: " + bill.getEmployeeName() + "\n");
            if (bill.getCustomerName() != null) {
                writer.write("Nombre del cliente: " + bill.getCustomerName() + "\n");
            } else {
                writer.write("Nombre del cliente: Público general\n");
            }

            writer.write("Productos:\n");
            for (OrderItem product : bill.getOrder().getPedidoComandaList()) {
                writer.write("Nombre: " + product.getProductName() +
                        "   Cantidad: " + product.getQuantity() +
                        "  Precio: " + product.getTotalPrice() + "\n");
            }

            StringBuilder description = new StringBuilder();
            switch (bill.getPaymentMethod()) {
                case PaymentMethod.CASH:
                    description.append("PAGADO CON EFECTIVO: ");
                    description.append(bill.getAmount()).append("\n");
                    description.append("CAMBIO: $").append(bill.getChange()).append("\n");
                    break;

                case PaymentMethod.CARD:
                    description.append("PAGADO CON TARJETA: ");
                    description.append(bill.getAmount()).append("\n");
                    description.append("REFERENCIA: ").append(bill.getPaymentReferenceNumber()).append("\n");
                    break;

                case PaymentMethod.STORE_CREDIT:
                    description.append("PAGADO CON CRÉDITO DE LA TIENDA: ");
                    description.append(bill.getAmount()).append("\n");
                    description.append("USUARIO: ").append(bill.getOrder().getCustomerName()).append("\n");
                    break;

                default:
                    break;
            }
            writer.write(description.toString());
            writer.write("\n=====================================\n");
            writer.flush();
            System.out.println("Factura guardada en: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
