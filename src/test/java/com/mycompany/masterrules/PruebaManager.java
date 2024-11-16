//package com.mycompany.masterrules;
//
//import com.mycompany.masterrules.Model.*;
//
//import java.math.BigDecimal;
//
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
//
///**
// * @author alexs
// */
//public class PruebaManager {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws Exception {
//        // TODO code application logic here
//        Product P1 = new Product("1", "hamburguesa", "comida", new BigDecimal("100.00"), new BigDecimal("80.00"));
//        Product P2 = new Product("2", "papas", "comida", new BigDecimal("50.00"), new BigDecimal("30.00"));
//        Product P3 = new Product("3", "coca", "bebida", new BigDecimal("20.00"), new BigDecimal("15.00"));
//
//        //funciona bien el editProduct cuando se llama individualmente el menu(menu2)
//        /*
//        CafeteriaMenu menu2=new CafeteriaMenu("MENU");
//        menu2.addProduct(P1);
//        menu2.addProduct(P2);
//        menu2.addProduct(P3);
//        printMenu(menu2);
//        menu2.editProduct(new Product(1,"hamburguesa con salsa","bebida",new BigDecimal(100.00),new BigDecimal(80.00)));
//        printMenu(menu2);
//        */
//
//        //probar el manager
//        CafeteriaMenu menu1 = new CafeteriaMenu("MENU MRCafe");
//        CafeteriaStorage storage = new CafeteriaStorage();
//
//        CafeteriaManager manager = new CafeteriaManager(menu1, storage);
//        //agregar producto
//        //la excepcion no funciona para queries
//        manager.createProduct(P1, true, 50);
//        manager.createProduct(P2, true, 60);
//        manager.createProduct(P3, false, 70);
//        printMenu(manager.getMenu());
//        printStorage(storage);
////        printProductDB(productQueries);
//
//        //remover producto
//        manager.deleteProductByID(P2.getProductName());
//        //remover producto que no existe
//        //manager.deleteProduct("banderilla");
//
//        printMenu(menu1);
//        printStorage(storage);
////        printProductDB(productQueries);
//
//        //editar producto cuando queremos que se ponga en el inventario si no estab antes
//        //Product modifyProduct=new Product(3,"coca zero","bebida",new BigDecimal(100.00),new BigDecimal(80.00));
//
//
//        //aqui inicia el error
//        Product modifyProduct = new Product("1", "hamburguesa con queso", "bebida", new BigDecimal("100.00"), new BigDecimal("80.00"));
//
//        manager.editProduct(modifyProduct, true, 70);
//
//        //manager.editProduct(modifyProduct, true, 100);//causa error
//
//        printStorage(storage);
////        printProductDB(productQueries);
//        printMenu(manager.getMenu());
//    }
//
//    public static void printMenu(CafeteriaMenu menu) {
//        System.out.print("MENU: ");
//        System.out.print("[");
//        for (Product type : menu.getProducts()) {
//            System.out.print("(" + type.getProductID() + "," + type.getProductName() + ")");
//        }
//        System.out.println("]");
//        System.out.print("\n");
//    }
//
//    public static void printStorage(CafeteriaStorage storage) {
//        System.out.print("Storage:");
//        System.out.print("[");
//        for (String id : storage.getProducts().keySet()) {
//            System.out.print("(" + id + "," + storage.getProducts().get(id) + ")");
//        }
//        System.out.print("]");
//        System.out.println("\n");
//    }
//
////    public static void printProductDB(ProductQueries productQueries) {
////        System.out.print("DataBase:");
////        System.out.print("[");
////        for (int i = 0; i < productQueries.products.size(); i++) {
////            System.out.print("(" + productQueries.products.get(i).getID() + "," + productQueries.products.get(i).getProductName() + ")");
////        }
////        System.out.print("]");
////        System.out.println("\n");
////    }
//
//}
