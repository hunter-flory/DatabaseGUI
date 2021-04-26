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
public class Customer {
    public String custID;
    public String name;
    public String phone;
    
    public Customer(String custID, String name, String phone)
    {
        this.custID = custID;
        this.name = name;
        this.phone = phone;
    }
    
    public String getID()
    {
        return custID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getPhone()
    {
        return phone;
    }
    
    
}


