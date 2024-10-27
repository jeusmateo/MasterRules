//import com.mycompany.masterrules.Model.*;
//import com.mycompany.masterrules.Database.*;
//import java.math.BigDecimal;
//import java.util.Scanner;
//
//public class DBAdminTest {
//
//    public static void main(String[] args) {
//        Product product = new Product(1, "Laptop", "Laptop", BigDecimal.valueOf(1000.00), new BigDecimal(900.00));
//        System.out.println(DatabaseAdministrator.addProduct(product));
//
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("Operation:\nSelect 1 to add a product\nSelect 2 to update a product\nSelect 3 to delete a product\n4. Read all products\n5. Exit");
//            int operation = scanner.nextInt();
//
//            switch (operation){
//                case 1:
//                    System.out.println("Enter the product ID: ");
//                    int id = scanner.nextInt();
//                    System.out.println("Enter the product name: ");
//                    String name = scanner.next();
//                    System.out.println("Enter the product type: ");
//                    String type = scanner.next();
//                    System.out.println("Enter the product price: ");
//                    BigDecimal price = scanner.nextBigDecimal();
//                    System.out.println("Enter the product VIP price: ");
//                    BigDecimal VIPprice = scanner.nextBigDecimal();
//                    Product product1 = new Product(id, name, type, price, VIPprice);
//                    System.out.println(DatabaseAdministrator.addProduct(product1));
//                    break;
//                case 2:
//                    System.out.println("Enter the product ID: ");
//                    int id1 = scanner.nextInt();
//                    System.out.println("Enter the product name: ");
//                    String name1 = scanner.next();
//                    System.out.println("Enter the product type: ");
//                    String type1 = scanner.next();
//                    System.out.println("Enter the product price: ");
//                    BigDecimal price1 = scanner.nextBigDecimal();
//                    System.out.println("Enter the product VIP price: ");
//                    BigDecimal VIPprice1 = scanner.nextBigDecimal();
//                    Product product2 = new Product(id1, name1, type1, price1, VIPprice1);
//                    System.out.println(DatabaseAdministrator.updateProduct(product2));
//                    break;
//                case 3:
//                    System.out.println("Enter the product ID: ");
//                    int id2 = scanner.nextInt();
//                    System.out.println("Enter the product name: ");
//                    String name2 = scanner.next();
//                    System.out.println("Enter the product type: ");
//                    String type2 = scanner.next();
//                    System.out.println("Enter the product price: ");
//                    BigDecimal price2 = scanner.nextBigDecimal();
//                    System.out.println("Enter the product VIP price: ");
//                    BigDecimal VIPprice2 = scanner.nextBigDecimal();
//                    Product product3 = new Product(id2, name2, type2, price2, VIPprice2);
//                    System.out.println(DatabaseAdministrator.deleteProduct(product3));
//                    break;
//
//                case 4:
//                    System.out.println(DatabaseAdministrator.readAllProducts());
//                    for(Product p: DatabaseAdministrator.readAllProducts()){
//                        System.out.println(p.getID());
//                    }
//                    break;
//            }
//
//
//
////            System.out.println("Enter the product ID: ");
////            int id = scanner.nextInt();
////            System.out.println("Enter the product name: ");
////            String name = scanner.next();
////            System.out.println("Enter the product type: ");
////            String type = scanner.next();
////            System.out.println("Enter the product price: ");
////            BigDecimal price = scanner.nextBigDecimal();
////            System.out.println("Enter the product VIP price: ");
////            BigDecimal VIPprice = scanner.nextBigDecimal();
////
//
//        }
//
//    }
//}
