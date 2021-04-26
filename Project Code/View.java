/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.databaseGUI;

/**
 *
 * @author Hunter
 */
public class View {
    private String orderDate;
    private String startDate;
    private String returnDate;
    private String totalDays;
    private String vin;
    private String vehicle;
    private String type;
    private String category;
    private String custID;
    private String name;
    private String orderAmount;
    private String rentalBalance;
    
    public View(String orderDate, String startDate, String returnDate, String totalDays, String vin, String vehicle, String type, String category, String custID, String name, String orderAmount, String rentalBalance)
    {
        this.orderDate = orderDate;
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.totalDays = totalDays;
        this.vin = vin;
        this.vehicle = vehicle;
        this.type = type;
        this.category = category;
        this.custID = custID;
        this.name = name;
        this.orderAmount = orderAmount;
        this.rentalBalance = rentalBalance;
        
    }
    
    public String getOrderDate() {
        return orderDate;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public String getReturnDate() {
        return returnDate;
    }
    
    public String getTotalDays() {
        return totalDays;
    }
    
    public String getVin() {
        return vin;
    }
    
    public String getVehicle() {
        return vehicle;
    }
    
    public String getType() {
        return type;
    }
    
    public String getCategory() {
        return category;
    }
    
    public String getCustID() {
        return custID;
    }
    
    public String getName() {
        return name;
    }
    
    public String getOrderAmount() {
        return orderAmount;
    }
    
    public String getRentalBalance() {
        return rentalBalance;
    }
}
