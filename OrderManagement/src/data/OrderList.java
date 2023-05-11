/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.Validation;

public class OrderList {

    private List<Customer> listCustomer = new ArrayList();
    private List<Product> listProduct = new ArrayList();
    private List<Order> listOrder = new ArrayList();

    public void addProductFromFile() {
        listProduct.clear();
        try {
            FileReader fr = new FileReader("products.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                String id = txt[0];
                String name = txt[1];
                String unit = txt[2];
                String origin = txt[3];
                double price = Double.parseDouble(txt[4]);
                listProduct.add(new Product(id, name, unit, origin, price));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from products.txt fail!");
          
        }
    }

    
    public void addCustomerFromFile() {
        listCustomer.clear();
        try {
            FileReader fr = new FileReader("customers.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                String id = txt[0];
                String name = txt[1];
                String address = txt[2];
                String phone = txt[3];
                listCustomer.add(new Customer(id, name, address, phone));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from customers.txt fail!");
        }
    }

    
    public void addOrderFromFile() {
        listOrder.clear();
        try {
            FileReader fr = new FileReader("orders.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(",");
                String orderID = txt[0];
                String customerID = txt[1];
                String productID = txt[2];
                int quantity = Integer.parseInt(txt[3]);
                String date = txt[4];
                boolean status = Boolean.parseBoolean(txt[5]);
                listOrder.add(new Order(orderID, customerID, productID, quantity, date, status));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from orders.txt fail!");
        }
    }

    public void printAllProduct() {
        if (listProduct.isEmpty()) {
            System.out.println("List product is empty.Nothing to print.");
        } else {
            int stt = 1;
            System.out.println("LIST ALL PRODUCT:");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            System.out.println("| STT |  ID |          NAME           |          UNIT           |          ORIGIN         |  PRICE   |");
            System.out.println("-----------------------------------------------------------------------------------------------------");
            for (int i = 0; i < listProduct.size(); i++) {
                System.out.print("|" + stt++ + "    ");
                listProduct.get(i).showInfor();
            }
            System.out.println("-----------------------------------------------------------------------------------------------------");
            
        }
    }

    public void printAllCustomer() {
        if (listCustomer.isEmpty()) {
            System.out.println("List customer is empty.Nothing to print.");
        } else {
            int stt = 1;
            System.out.println("LIST ALL CUSTOMER:");
            System.out.println("------------------------------------------------------------------");
            System.out.println("| STT |  ID |        NAME        |    ADDRESS    |     PHONE     |");
            System.out.println("------------------------------------------------------------------");
            for (int i = 0; i < listCustomer.size(); i++) {
                System.out.print("|" + stt++ + "    ");
                listCustomer.get(i).showInfor();
            }
            System.out.println("------------------------------------------------------------------");
        }
    }

    public void searchACustomer() {
        String id = Validation.regexString("Enter ID(Cxxx): ", "Wrong format(x is digit).", "^[C|c]\\d{3}$");
        int index = getIndexCustomer(id);
        if (index == -1) {
            System.out.println("This customer does not exist!");
        } else {
            System.out.println("HERE IS CUSTOMER'S INFORMATION SEARCH BY CODE: " + id);
            System.out.println("------------------------------------------------------------");
            System.out.println("|  ID |        NAME        |    ADDRESS    |     PHONE     |");
            System.out.println("------------------------------------------------------------");
            listCustomer.get(index).showInfor();
            System.out.println("------------------------------------------------------------");
        }
    }

    public int getIndexCustomer(String inputID) {
        for (int i = 0; i < listCustomer.size(); i++) {
            if (listCustomer.get(i).getId().equalsIgnoreCase(inputID)) {
                return i;
            }
        }
        return -1;
    }

    public void addNewCustomer() {
        String id, name, address, phone, confirm;
        int index;
        do {
            do {
                id = Validation.regexString("Enter ID Customer(Cxxx): ", "Wrong format(x is digit).", "^[C|c]\\d{3}$").toUpperCase();
                index = getIndexCustomer(id);
                if (index >= 0) {
                    System.out.println("Duplicated ID.Input again!");
                }
            } while (index >= 0);
            name = Validation.getString("Enter Name Customer: ", "Not blank or empty!").toUpperCase();
            address = Validation.getString("Enter Address Customer: ", "Not blank or empty.").toUpperCase();
            phone = Validation.regexString("Enter Phone Customer: ", "Phone must be from 10 to 12 digit.", "^[0-9]{10,12}$");
            listCustomer.add(new Customer(id, name, address, phone));
            saveCustomerToFile();
            confirm = Validation.regexString("Do you want to continues(y/n): ", "Just y or n.", "^[Y|y|N|n]$");
        } while (confirm.equalsIgnoreCase("Y"));
    }

    public void updateCustomer() {
        String id = Validation.regexString("Enter ID(Cxxx) Updating: ", "Wrong format(x is digit).", "^[C|c]\\d{3}$");
        int index = getIndexCustomer(id);
        if (index == -1) {
            System.out.println("Customer does not exist!");
        } else {
            System.out.println("HERE IS OLD CUSTOMER'S INFORMATION:");
            System.out.println("------------------------------------------------------------");
            System.out.println("|  ID |        NAME        |    ADDRESS    |     PHONE     |");
            System.out.println("------------------------------------------------------------");
            listCustomer.get(index).showInfor();
            System.out.println("------------------------------------------------------------");

            String newPhone = "";
            String newName = Validation.updateString("Enter New Name: ", listCustomer.get(index).getName());
            String newAddress = Validation.updateString("Enter New Address: ", listCustomer.get(index).getAddress());
            String confirm = Validation.regexString("Do you want to change phone number(y/n): ", "Just y or n.", "^[Y|y|N|n]$");
            if (confirm.equalsIgnoreCase("y")) {
                newPhone = Validation.regexString("Enter New Phone: ", "Not blank or empty.", "^[0-9]{10,12}$");
                listCustomer.get(index).setPhone(newPhone);
            }
            listCustomer.get(index).setName(newName);
            listCustomer.get(index).setAddress(newAddress);
           
            saveCustomerToFile();
        }
    }

    public void saveCustomerToFile() {
        boolean check = true;
        try {
            FileWriter fw = new FileWriter("customers.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listCustomer.size(); i++) {
                bw.write(listCustomer.get(i).toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fail!");
            check = false;
        }
        if (check) {
            System.out.println("Successfully..");
        }
    }
     public void printAllOrdersSortByName() {
        if (listOrder.isEmpty()) {
            System.out.println("List order empty.Nothing to print.");
        } else {
            //  (Order o1, Order o2) -> getNameCustomerByID(o1.getCustomerID()).compareTo(getNameCustomerByID(o2.getCustomerID())
           for(Order o: listOrder) {
               o.customerName = getNameCustomerByID(o.getCustomerID());
           }
           Collections.sort(listOrder, new Comparator<Order>() {
               @Override
               public int compare(Order o1, Order o2) {
                   return o1.customerName.compareToIgnoreCase(o2.customerName);
               }
           });
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("| ORDER ID |CUSTOMER ID|    CUSTOMER NAME   |PRODUCT ID| QUANTITY |      DATE     |  STATUS  |");
            System.out.println("---------------------------------------------------------------------------------------------");
            for (int i = 0; i < listOrder.size(); i++) {
                System.out.printf("|%-10s|%-11s|%-20s|%-10s|%-10d|%-15s|%-10s|\n", listOrder.get(i).getOrderID(), listOrder.get(i).getCustomerID(), getNameCustomerByID(listOrder.get(i).getCustomerID())
                        , listOrder.get(i).getProductID(),
                        listOrder.get(i).getQuantity(), listOrder.get(i).getDate(), listOrder.get(i).isStatus());
            }
            System.out.println("---------------------------------------------------------------------------------------------");
        }
    }
//PRINT PENDING ORDER 
    public void printAllPendingOrders() {

       if (listOrder.isEmpty()) {
            System.out.println("List order empty.Nothing to print.");
        } else {

            System.out.println("-------------------------------------------------------------------------");
            System.out.println("| ORDER ID |CUSTOMER ID|PRODUCT ID| QUANTITY |      DATE     |  STATUS  |");
            System.out.println("-------------------------------------------------------------------------");

              for (Order o :listOrder) {
                  if (o.isStatus()==false) {
                      o.showInfor();
                  }
           }
            }
            System.out.println("-------------------------------------------------------------------------");
        }
   
    
    
    
    public String getNameCustomerByID(String id) {
        String name = "";
        for (int i = 0; i < listCustomer.size(); i++) {
            if (listCustomer.get(i).getId().equalsIgnoreCase(id)) {
                name = listCustomer.get(i).getName();
            }
        }
        return name;
    }

    public void addNewOrders() {
        String orderID, customerID, productID, date, confirm;
        int quantity, index;
        boolean status;
        do {
            do {
                orderID = Validation.regexString("Enter Order ID(Dxxx): ", "Wrong.Input again!", "^[D|d]\\d{3}$");
                index = getIndexOrderById(orderID);
                if (index >= 0) {
                    System.out.println("Duplicated ID.Input again!");
                }
            } while (index >= 0);
            printAllCustomer();
            int choiceCustomer = Validation.getAnInteger("Enter your choice[1.." + listCustomer.size() + "]: ", "Wrong.Input again!", 1, listCustomer.size());
            printAllProduct();
            int choiceProduct = Validation.getAnInteger("Enter your choice[1.." + listProduct.size() + "]: ", "Wrong.Input again!", 1, listProduct.size());
            quantity = Validation.getAnInteger("Enter Quantity: ", "More than 0.Input again!", 0);
            date = Validation.getDay("Enter Date(dd/MM/yyyy): ", "Wrong.Input again!");
            status = false;
            listOrder.add(new Order(orderID, listCustomer.get(choiceCustomer - 1).getId(), listProduct.get(choiceProduct - 1).getId(), quantity, date, status));
            saveOrdersToFile();
            confirm = Validation.regexString("Do you want to continues(y/n): ", "Just y or n.", "^[Y|y|n|N]$");
        } while (confirm.equalsIgnoreCase("Y"));
    }

    public int getIndexOrderById(String id) {
        for (int i = 0; i < listOrder.size(); i++) {
            if (listOrder.get(i).getOrderID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void updateOrder() {
        String id = Validation.regexString("Enter ID(Dxxx) Updating: ", "Wrong.Input again!", "^[D|d]\\d{3}$");
        int index = getIndexOrderById(id);
        if (index == -1) {
            System.out.println("Order does not exist.");
        } else {
            System.out.println("HERE IS OLD INFORMATION: ");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("| ORDER ID |CUSTOMER ID|PRODUCT ID| QUANTITY |      DATE     |  STATUS  |");
            System.out.println("-------------------------------------------------------------------------");
            listOrder.get(index).showInfor();
            System.out.println("-------------------------------------------------------------------------");
            int choiceCustomerID, choiceProductID;
            String confirmCusID = Validation.regexString("Do you want to change CustomerID(y/n): ", "Just y or n", "^[N|n|Y|y]$");
            if (confirmCusID.equalsIgnoreCase("Y")) {
                printAllCustomer();
                choiceCustomerID = Validation.getAnInteger("Enter your choice[1.." + listCustomer.size() + "]: ", "Wrong.Input again!", 1, listCustomer.size());
                listOrder.get(index).setCustomerID(listCustomer.get(choiceCustomerID - 1).getId());
            }
            String confirmProID = Validation.regexString("Do you want to change ProductID(y/n): ", "Just y or n", "^[N|n|Y|y]$");
            if (confirmProID.equalsIgnoreCase("Y")) {
                printAllProduct();
                choiceProductID = Validation.getAnInteger("Enter your choice[1.." + listProduct.size() + "]: ", "Wrong.Input again!", 1, listProduct.size());
                listOrder.get(index).setProductID(listProduct.get(choiceProductID - 1).getId());
            }
            int newQuantity = Validation.updateAnInteger("Enter New Quantity: ", 0, listOrder.get(index).getQuantity());
            listOrder.get(index).setQuantity(newQuantity);

            String confirmDate = Validation.regexString("Do you want to change Date(y/n): ", "Just y or n", "^[N|n|Y|y]$");
            if (confirmDate.equalsIgnoreCase("Y")) {
                String newDate = Validation.getDay("Enter New Date(dd/MM/yyyy): ", "Wrong.Input again!");
                listOrder.get(index).setDate(newDate);
            }

            String confirmStatus = Validation.regexString("Do you want to change status(y/n): ", "Just y or n", "^[N|n|Y|y]$");
            if (confirmStatus.equalsIgnoreCase("Y")) {
                if (listOrder.get(index).isStatus() == true) {
                    listOrder.get(index).setStatus(false);
                } else {
                    listOrder.get(index).setStatus(true);
                }
            }
                saveOrdersToFile();

        }
  
    }

    public void deleteOrder() {
        String id = Validation.regexString("Enter ID(Dxxx) Deleting: ", "Wrong.Input again!", "^[D|d]\\d{3}$");
        int index = getIndexOrderById(id);
        if (index == -1) {
            System.out.println("Order does not exist.");
        } else {
            String confirm = Validation.regexString("Are you sure deleting(y/n): ", "Just y or n.", "^[Y|y|n|N]$");
            if (confirm.equalsIgnoreCase("Y")) {
                listOrder.remove(index);
                    saveOrdersToFile();
            } else {
                System.out.println("You cancel. Fail.");
            }

        }
   
    }

    public void saveOrdersToFile() {
        boolean check = true;
        try {
            FileWriter fw = new FileWriter("orders.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listOrder.size(); i++) {
                bw.write(listOrder.get(i).toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fail!!!");
            check = false;
        }
        if (check) {
            System.out.println("Successfully!!!!");
        }
    }
}
