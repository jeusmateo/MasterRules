/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.masterrules.Model;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 *
 * @author chepo
 */
public class Test {

    public static void main(String[] args) {
        try {
            CashRegisterAuditReportManager crarm = new CashRegisterAuditReportManager();
            CafeteriaManager cm = new CafeteriaManager();
            UserAccount ua = new UserAccount("admin", "admin");
            POSManager pos = new POSManager(crarm, cm, ua);

            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("Menú interactivo:");
                System.out.println("1. Crear comidas");
                System.out.println("2. Crear clientes");
                System.out.println("3. Vender");
                System.out.println("4. Salir");
                System.out.print("Seleccione una opción: ");
                int option = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (option) {
                    case 1:
                        System.out.print("Ingrese el ID del producto: ");
                        int productId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese el nombre del producto: ");
                        String productName = scanner.nextLine();
                        System.out.print("Ingrese el tipo de producto: ");
                        String productType = scanner.nextLine();
                        System.out.print("Ingrese el costo: ");
                        BigDecimal cost = scanner.nextBigDecimal();
                        System.out.print("Ingrese el precio: ");
                        BigDecimal price = scanner.nextBigDecimal();

                        pos.getCafeteriaManager().createProduct(productId, productName, productType, cost, price);
                        System.out.println("Producto creado con éxito.");
                        break;

                    case 2:
                        System.out.print("Ingrese el nombre del cliente: ");
                        String customerName = scanner.nextLine();
                        System.out.print("Ingrese el teléfono del cliente: ");
                        String customerPhone = scanner.nextLine();

                        pos.createNewCustomer(customerName, customerPhone);
                        System.out.println("Cliente creado con éxito.");
                        break;

                    case 3:
                        System.out.print("Ingrese el ID del producto a vender: ");
                        int productToSellId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese el tipo del producto a vender: ");
                        String productToSellType = scanner.nextLine();

                        pos.addProductToOrder(pos.buscarProducto(productToSellId, productToSellType));

                        System.out.print("Ingrese el tipo de orden (e.g., 'Para llevar' o 'Mesa #'): ");
                        String orderType = scanner.nextLine();
                        System.out.print("Ingrese notas especiales para la orden: ");
                        String orderNotes = scanner.nextLine();

                        pos.configureOrder(orderType, orderNotes);
                        pos.sell();
                        System.out.println("Venta realizada con éxito.");
                        break;

                    case 4:
                        exit = true;
                        System.out.println("Saliendo del programa.");
                        break;

                    default:
                        System.out.println("Opción no válida, intente de nuevo.");
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
