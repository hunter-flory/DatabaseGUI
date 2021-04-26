/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.databaseGUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Hunter Flory
 */
public class Connector {
    
    
    
    // create customer query
    // on hitting enter, text in fields will be converted to parameters for this function
    // and newCustomer() will be called
    public static void newCustomer(String name, String areaCode, String mid, String last)
    {
        String phoneNumber = "("+areaCode+") "+mid+"-"+last;
        // custID is auto generated so null is provided for field 1 
        String query = "INSERT INTO customer VALUES "
                + "(" + null + ", '" + name + "', '" + phoneNumber + "');";
        
        try 
            {
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();                   
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
    }
    // end customer
    
    
    
    public static ArrayList<Customer> getCustomer()
    {
        ArrayList<Customer> customerList = new ArrayList<>();
        String query = "SELECT * FROM customer";
        try 
            {
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                
                Customer customer;
                ResultSet rs = statement.executeQuery(query);
                
                // code that prints rental balance and start date of each returned row to verify code is working
                while (rs.next())
                {
                    customer = new Customer(rs.getString("CustID"), rs.getString("Name"), rs.getString("Phone"));
                    customerList.add(customer);
                }
                
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
            return customerList;
    }
    
    
    public static int getRate(String type, String category, String rental)
    {
        String query = "SELECT * FROM rate WHERE Type = '"+ type+ "' AND Category = '" + category + "'";
        int rate;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next())
            {
                rate = Integer.parseInt(rs.getString(rental));
                return rate;
            }
        } catch (Exception e){
            System.out.println(e);
        }
            
        System.out.println("Error! Rate not reached");
        return 0;
    }
    
    
    public static void insertRental(String custID, String vin, String startDate, String orderDate, String rentalType, String qty, String returnDate, String paymentDate, String returned, String days)
    {
        Object[] vehicle = Interface.selectedVehicle;
        Object[] customer = Interface.selectedCustomer; 
        String type = vehicle[3].toString();
        String category = vehicle[4].toString();
        
        int rate = getRate(type, category, rentalType);
              
        int convertedDays = Integer.parseInt(days);
        int totalAmount = rate*convertedDays*(Integer.parseInt(qty));
        String totalAmountConverted = totalAmount + "";
        
        if (rentalType.equals("Daily"))
            rentalType = "1";
        if (rentalType.equals("Weekly"))
            rentalType = "7";
        
        String query = "INSERT INTO rental VALUES "
                +"('"+custID + "', '" + vin + "', '" + startDate + "', '" + orderDate + "', '" 
                + rentalType + "', '" + qty + "', '" + returnDate + "', '" + totalAmountConverted + "', '" 
                + paymentDate + "', '" + returned + "')";
        
        try 
            {
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();                   
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
        
    }
    
    
    // create vehicle query
    public static void newVehicle(String vin, String description, String year, String type, String category)
    {
        String query = "INSERT INTO vehicle VALUES "
                +"( '" + vin + "', '" + description + "', '" + year + "', '" + type + "', '" + category + "');";
        
        try 
            {
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();                   
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
        
    }
    // end vehicle
    
    // create rental query
    // User performs search of available rentals with information,
    // User selects vehicle from available rentals,
    // User selects customer from accounts,
    // User selects payment date option,
    // User clicks enter
    // This method is called with parsed string from row clicked in each table
    // this method delimits strings and creates new rental from relevant data
    // db is updated
    
    // CustID, VIN, and Total amount will be found from selected rows
    public static void newRental(String custID, String vin, String startDate, String orderDate, String rentalType, String qty, String returnDate,
    String totalAmount, String paymentDate, String returned)
    {
        String query = "INSERT INTO rental VALUES "+
                "("+custID+ "," + vin + ","+ startDate + "," + orderDate+ "," 
                 +rentalType+ ","+ qty+ ","+returnDate+ ","+totalAmount+ ","+paymentDate+ ","+returned+");";
        
        try 
            {
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                statement.executeUpdate();                   
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
        
    }
                
    
    public static ArrayList<Vehicle> searchRentals(String type, String category, String startDate, String returnDate)
    {
        String query = "SELECT * FROM vehicle JOIN rental WHERE";
        
        query+= " Type = " + type;
        query+= " AND Category = " + category;
        query+= " AND ReturnDate >= " + "'"+startDate+"'";
        ArrayList<Vehicle> vehicleList = new ArrayList<>();
        try 
            {
                
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                
                Vehicle vehicle;
                ResultSet rs = statement.executeQuery(query);
                
                // code that prints rental balance and start date of each returned row to verify code is working
                while (rs.next())
                {
                    vehicle = new Vehicle(rs.getString("VehicleID"), rs.getString("Description"), rs.getString("Year"), rs.getString("Type"), rs.getString("Category"));
                    vehicleList.add(vehicle);
                }
                
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
            return vehicleList;
    }
                
    
    // end rental
    
    
    
    // create returns query
    public static String returnRental(String returnDate, String custName, String vin)
    {
        String query = "SELECT RentalBalance FROM vrentalinfo WHERE " +
                "ReturnDate = '" + returnDate + "' AND CustomerName = '" + custName +
                "' AND VIN = '" + vin + "'";
        
        try 
            {
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);           
                rs.next();
                String balance = rs.getString("RentalBalance");
                return balance;
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
        return "Error! Reached end of function without balance";
    }
    // end returns
    
    // view query
    // balance and start date will be passed in as ASC or DESC
    public static ArrayList<View> getByFilter_rentalView(String custID, String name, String vin, String balance, String startDate, String type, String category)
    {
        
        // this is buggy as shit, i don't know exactly where to put the ands
        // so i'll cut the video to make it work
        String query = "SELECT * FROM vrentalinfo WHERE"; 
        
        if (custID.trim().length() != 0)
            query += " CustomerID = '"+ custID +"' AND";
        if (name.trim().length() != 0)
            query += " CustomerName = '"+ name +"'";
        if (vin.trim().length() != 0)
            query += " VIN = '"+ vin +"'";
        if (type != "--")
            query += "AND Type = '" + type + "'";
        if (category != "--")
            query += "AND Category = '" + category + "'"; 
        if (balance != "--" || startDate != "--")
            query += " ORDER BY ";
        if (balance != "--")
            query +=  " RentalBalance "+balance;
        if (balance != "--" && startDate != "--")
            query += ", ";
        if (startDate != "--")
            query += "StartDate "+startDate;
      ArrayList<View> viewList = new ArrayList<>();
        try 
            {
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                
                View view;
                ResultSet rs = statement.executeQuery(query);
                
                // code that prints rental balance and start date of each returned row to verify code is working
                while (rs.next())
                {
                    view = new View(rs.getString("OrderDate"), rs.getString("StartDate")
                            , rs.getString("ReturnDate"), rs.getString("TotalDays"), rs.getString("VIN"), rs.getString("Vehicle")
                            , rs.getString("Type"), rs.getString("Category"), rs.getString("CustomerID"), rs.getString("CustomerName")
                            , rs.getString("OrderAmount"), rs.getString("RentalBalance") );
                    viewList.add(view);
                }
                
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
            return viewList;
    }
    // end view
    
    public static ArrayList<View> getNoFilter_rentalView()
    {
        
        String query = "SELECT * FROM vrentalinfo";
        ArrayList<View> viewList = new ArrayList<>();
        try 
            {
                // this gets a connection with provided parameters from getConnection method we created
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                
                View view;
                ResultSet rs = statement.executeQuery(query);
                
                // code that prints rental balance and start date of each returned row to verify code is working
                while (rs.next())
                {
                    view = new View(rs.getString("OrderDate"), rs.getString("StartDate")
                            , rs.getString("ReturnDate"), rs.getString("TotalDays"), rs.getString("VIN"), rs.getString("Vehicle")
                            , rs.getString("Type"), rs.getString("Category"), rs.getString("CustomerID"), rs.getString("CustomerName")
                            , rs.getString("OrderAmount"), rs.getString("RentalBalance") );
                    viewList.add(view);
                }
                
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            finally
            {
                // This prints the query 
                System.out.println(query);
            }
            return viewList;
    }
    // end view

    
    
    
      // get connection method, returns null if it doesn't work
    public static Connection getConnection() throws Exception{
        try{
            // unnecesary but i'm following the tutorial
            String driver = "com.mysql.cj.jdbc.Driver";
            // found under connection properties
            String URL = "jdbc:mysql://localhost:3306/project2v3";
            // username and password i set for myself in server
            String username = "hunter";
            String password = "hunter";
            // loads driver, again unnecessary
            Class.forName(driver);
            
            Connection con = DriverManager.getConnection(URL, username, password);
            System.out.println("Connected to " + URL);
            return con;
            
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    // end getConnection()
    
    
    
    public static void main(String args[])
    {
        System.out.println("Getting connection to database");
        try {
            getConnection();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        
        // call loader function
        
            
    }
    
    
}
// end class