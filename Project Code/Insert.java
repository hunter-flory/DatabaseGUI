/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hunter
 */
import java.sql.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.sql.Connection; 
import java.sql.ResultSet; 
import java.sql.DriverManager; 
import java.sql.Statement; 

public class Insert
{

    // call this function with the full filename as a parameter to open it
    // and scan for database entries
    // THIS IS INCOMPLETE
    public static void readFile(String fileName, String connectionUrl)
    {
        try (Scanner input = new Scanner(new File("src/Database_Entries/"+fileName)))
        {
            String line;
            switch (fileName) // parse each file differently depending on datatype/name
            {
                case "DEPARTMENT.txt":
                    // DEPARTMENT has a name, location, and number in that order. 
                    String name, dept_num, manager_num, date;
                    String insertDept; 
                    
                    while (input.hasNextLine())
                    {
                            input.useDelimiter(", "); 
                            name = input.next(); 
                            input.useDelimiter(", "); 
                            dept_num = input.next(); 
                            input.useDelimiter(", "); 
                            manager_num = input.next(); 
                            date = input.next(); 
                            insertDept = "INSERT INTO DEPARTMENT  VALUES "
                                    + "(" + name + ", " + dept_num + ", " + manager_num + ", " + date + ");";
                            
                            ResultSet resultSet = null; 
                            
                            try (Connection connection = DriverManager.getConnection(connectionUrl))
                            {
                                PreparedStatement prepsInsertProduct = connection.prepareStatement(insertDept, Statement.RETURN_GENERATED_KEYS); 
                                prepsInsertProduct.execute(); 
                                
                                resultSet = prepsInsertProduct.getGeneratedKeys(); 
                                
                                while (resultSet.next())
                                {
                                    System.out.println("Generated + " + resultSet.getString(1));
                                }
                            }
                            
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                    }
                    break;
                case "DEPT_LOCATIONS.txt":
                    // DEPT_LOCATIONS just has locations
                    while (input.hasNextLine())
                    {
                        System.out.println(input.nextLine());
                    }
                    break;
                case "EMPLOYEE.txt":
                    while (input.hasNextLine())
                    {
                        try
                        {
                            input.useDelimiter(", "); 
                            
                        }
                    }
                    break;
                case "PROJECT.txt":
                    while (input.hasNextLine())
                    {
                        try
                        {
                            input.useDelimiter(", "); 
                            
                        }
                    }
                    break;
                case "WORKS_ON.txt":
                    while (input.hasNextLine())
                    {
                        try
                        {
                            input.useDelimiter(", "); 
                            
                        }
                    }
                    break;
                default:
                    System.out.println("File not supported. Use files found in src/Database_Entries");
            }
                
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args)
    {
        //connection is established through netbeans to sql server
        // INSTRUCTIONS: under 'Services', right click 'Databases' and click 'New Connection'
        // Select the driver you are using for the connection (I'm using Connector J 8.0.21 found from MySQL Community Installer)
        // Browse for the file path to the driver jar you downloaded 
        // Use 'localhost' as Host, port as 3306, and the name of the database ('mysql', or whatever you changed it to)
        // The username and passwords I set up for my local server is your lowercase first name for both
        // Test the connection to see if it worked and you're done. 
        // Your connection should now show up under the 'Services' tab
        // You can find 'mydb' under 'Other databases'
        
        
        // TODO: finish readFile function and write prepared statements to insert 
        // data files into tables
        System.out.println("Hello!");
        readFile("DEPARTMENT.txt");
    }
}
