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
public class Vehicle {
    private String vin;
    private String description;
    private String year;
    private String type; 
    private String category;
    
    public Vehicle(String vin, String description, String year, String type, String category)
    {
        this.vin = vin;
        this.description = description;
        this.year = year;
        this.type = type;
        this.category = category;
    }
    
    public String getVin()
    {
        return vin;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public String getYear()
    {
        return year;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getCategory()
    {
        return category;
    }
    
}


