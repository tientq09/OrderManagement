/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.OrderList;
import ui.Menu;

public class Main {

    public static void main(String[] args) {
        OrderList orList = new OrderList();
        orList.addProductFromFile();
        orList.addOrderFromFile();
        orList.addCustomerFromFile();
        
        Menu menu = new Menu("Order Management Program.");
        menu.addNewOption("         1-List all Products.");
        menu.addNewOption("         2-List all Customers.");
        menu.addNewOption("         3-Search a Customer based on his/her ID.");
        menu.addNewOption("         4-Add a Customer.");
        menu.addNewOption("         5-Update a Customer.");
        menu.addNewOption("         6-Save Customer to the file, named customers.txt.");
        menu.addNewOption("         7-List all Orders in ascending order of Customer name.");
        menu.addNewOption("         8-List all pending Orders.");
        menu.addNewOption("         9-Add an Order.");
        menu.addNewOption("        10-Update an Order.");
        menu.addNewOption("        11-Save Orders to file, named orders.txt");
        menu.addNewOption("        12-Exit..");
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    orList.printAllProduct();
                    break;
                case 2:
//                    orList.addCustomerFromFile();
                    orList.printAllCustomer();
                    break;
                case 3:
                    orList.searchACustomer();
                    break;
                case 4:
                    orList.addNewCustomer();
                    break;
                case 5:
                    orList.updateCustomer();
                    break;
                case 6:
                    orList.saveCustomerToFile();
                    break;
                case 7:
                    
                    orList.printAllOrdersSortByName();
                    break;
                case 8:
//                    orList.addOrderFromFile();
                    orList.printAllPendingOrders();
                    break;
                case 9:
                    orList.addNewOrders();
                    break;
                case 10:
                     Menu menu1 = new  Menu("UPDATE AN ORDER");
                  menu1.addNewOption("1. Update an Order based on its ID " );
                  menu1.addNewOption("2. Delete an Order based on its ID ");
                  menu1. addNewOption("3. Return ");
                  int choice1;
                    do {                        
                    menu1.printMenu();
                    choice1 = menu1.getChoice();
                    switch(choice1){
                        case 1:
                            orList.updateOrder();
                            break;
                            case 2:
                                orList.deleteOrder();
                                case 3:
                                    break;
                    }
                      
                    } while (choice1 !=3);
                    break;
                case 11:
                    orList.saveOrdersToFile();
                    break;
                case 12:
                    System.out.println("Bye,bye.See you next time.");
                    break;
            }
        } while (choice != 12);
    }
}
