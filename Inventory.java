/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.*;
import java.util.ArrayList;

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
        System.out.println("infinity");
    try{ 
        conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
        statObj = conObj.createStatement();
        statObj.executeUpdate("INSERT INTO INVENTORY VALUES ( '"+storeID+ "','" +productID+ "'," +price+ "," +amount+ ")");  
        statObj.close();
        conObj.close();
    }catch(SQLException e){
        e.printStackTrace();
    } 
    
    }
    public void updateInventory(String storeID , String productID , int amount){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            resObj=statObj.executeQuery("update inventory set amount= amount -"+amount+  "where storeID = '"+storeID+ "'and productID ='" +productID+ "'");
            statObj.close();
            conObj.close();            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    public void viewStoreProducts(String storeID){
        products p = new products();
        try{
        conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
        statObj = conObj.createStatement();
        resObj=statObj.executeQuery("(Select * From Inventory where storeID = '" +storeID+"'");
        while(resObj.next()){
            System.out.println(p.getProductName(resObj.getString("productID"))+ "\t   " +p.getProductCategory(resObj.getString("productID"))+ "\t   " +resObj.getFloat("PRICE"));
        }
        statObj.close();
        conObj.close();        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public float getPrice(String productID){
        float price = 0;
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            price = resObj.getFloat("Select price where productID ='" +productID+"'");
            statObj.close();
            conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return price;
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
