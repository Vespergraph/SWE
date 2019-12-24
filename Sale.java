/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.*;
import java.util.UUID;

/**
 *
 * @author Ali-Marwan
 */
public class Sale {
    private String saleID;
    private int saleNumbers;
    Connection conObj = null;
    Statement statObj = null;
    ResultSet resObj = null;
    private Observer observer;
    
    Sale(String userID ,String storeID , String productID , int quantity){
        saleID = UUID. randomUUID(). toString();
        newSale(userID , storeID ,productID, quantity);
    }
    
    public void newSale(String userID ,String storeID ,String productID , int quantity){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            statObj.executeUpdate("Insert into SALE Values ( " +saleID+ "," +userID+ "," +productID+ "," +quantity+ "," +storeID+ ")");
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        Inventory i = new Inventory();
        i.updateInventory(storeID, productID, quantity);
    }

    public void countStoreSale(String storeID){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
          // resObj = statObj.executeQuery("select * from Sales");
           saleNumbers = resObj.getInt("count(salesID) from Sale where storeID = " +storeID+ ")");
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
