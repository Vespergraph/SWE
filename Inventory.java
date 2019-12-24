/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.*;

/**
 *
 * @author Ali-Marwan
 */
public class Inventory implements Observable {
    Connection conObj = null;
    Statement statObj = null;
    ResultSet resObj = null;
    private Observer observer;

    Inventory(){ 
    }
    public void addToInventory(String storeID , String productID , float price , int amount){
    try{ 
        conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
        statObj = conObj.createStatement();
        resObj=statObj.executeQuery("INSERT INTO INVENTORY VALUES ( "+storeID+ "," +productID+ "," +price+ "," +amount+ ")");            
    }catch(SQLException e){
        e.printStackTrace();
    } 
    
    }
    public void updateInventory(String storeID , String productID , int amount){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
             resObj=statObj.executeQuery("update inventory set amount= amount -"+amount+  "where storeID = "+storeID+ "and productID =" +productID+ ")");
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
     @Override
    public void registerObserver(Observer newObserver) {
        observer = newObserver;
    }

    @Override
    public void unregisterObserver() {
        observer = null;
    }

    @Override
    public void notifyObservers() {
        observer.update(null);
    }
}
