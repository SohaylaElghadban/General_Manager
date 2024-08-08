package org.example;//package org.example;
//
//import java.sql.*;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchMethodException {
//        /*try {
//            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_managment", "intern", "Intern@20038");
//            ItemManager itemManager = new ItemManager(connection);
//            StoreManager storeManager = new StoreManager(connection);
//
//            Scanner scanner = new Scanner(System.in);
//
//            System.out.println("Enter operation (insertItem, findItem, findItemsByName, insertStore, findStore): ");
//            String operation = scanner.nextLine().toLowerCase();
//
//            switch (operation) {
//                case "insertitem":
//                    System.out.println("Enter item code: ");
//                    String itemCode = scanner.nextLine();
//                    System.out.println("Enter item name: ");
//                    String itemName = scanner.nextLine();
//                    itemManager.insertItem(itemCode, itemName);
//                    break;
//
//                case "finditem":
//                    System.out.println("Enter item code: ");
//                    String findItemCode = scanner.nextLine();
//                    item item = itemManager.findItem(findItemCode);
//                    if (item != null) {
//                        System.out.println("Item found: " + item.getName());
//                    } else {
//                        System.out.println("Item not found.");
//                    }
//                    break;
//
//                case "finditemsbyname":
//                    System.out.println("Enter item name: ");
//                    String searchName = scanner.nextLine();
//                    itemManager.findItemsByNameContains(searchName).forEach(i -> {
//                        System.out.println("Item found: " + i.getItemCode() + i.getName());
//                    });
//                    break;
//
//                case "insertstore":
//                    System.out.println("Enter store code: ");
//                    String storeCode = scanner.nextLine();
//                    System.out.println("Enter store name: ");
//                    String storeName = scanner.nextLine();
//                    storeManager.insertStore(storeCode, storeName);
//                    break;
//
//                case "findstore":
//                    System.out.println("Enter store code: ");
//                    String findStoreCode = scanner.nextLine();
//                    Store store = storeManager.findStore(findStoreCode);
//                    if (store != null) {
//                        System.out.println("Store found: " + store.getName() );
//                    } else {
//                        System.out.println("Store not found.");
//                    }
//                    break;
//
//                default:
//                    System.out.println("Invalid operation.");
//                    break;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }*/
//
//        java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_managment", "intern", "Intern@20038");
//            generalItemManager<Store> manager = new generalItemManager<>(connection);
//
//            item newEmployee = new item(11,"1C2","Sohayla");
//            manager.insert(newEmployee);
//
//            item query = new item();
//            query.setName_("John Doe");
//            List<Store> employees = manager.select(query);
//
//            for (Store emp : employees) {
//                System.out.println(emp);
//            }
//
//        }
//}
//
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stock_managment", "intern", "Intern@20038");

            generalItemManager<item> manager = new generalItemManager(connection);
            Random rand = new Random();
            int Id = rand.nextInt(1000);
            System.out.println("Enter code: ");
            String code = sc.next();
            System.out.println("Enter name: ");
            String name = sc.next();
            item newItem = new item(Id, code,name);
            manager.insert(newItem);

            item query = new item();
            query.setID(Id);
            List<item> items = manager.select(query);

            for (item item : items) {
                System.out.println(item.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

