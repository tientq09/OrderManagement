/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

public class Order {
    private String orderID;
    private String customerID;
    public String customerName;
    private String productID;
    private int quantity;
    private String date;
    private boolean status;

    public Order(String orderID, String customerID, String productID, int quantity, String date, boolean status) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.productID = productID;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return orderID + "," + customerID + "," + productID + "," + quantity + "," + date + "," + status;
    }
    
    public void showInfor(){
        System.out.printf("|%-10s|%-11s|%-10s|%-10d|%-15s|%-10s|\n",orderID, customerID, productID, quantity, date, status);
    }
}
