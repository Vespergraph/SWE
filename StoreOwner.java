/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.sql.*;
import java.lang.String;


/**
 *
 * @author Ali-Marwan
 */
public class StoreOwner extends Customer {
    private String ownerID;
  
    Connection conObj = null;
    Statement statObj = null;
    ResultSet resObj = null;
        
    public StoreOwner(String ownerID){     
    this.ownerID = ownerID;
    }   
    
    
    
    public void newStore(String storeID, String storeName, String storeAddress, String storePhoneNo, String storeType, int views , boolean onSite){
    store sd = new store( storeID ,  storeName,  storeAddress,  storePhoneNo,  storeType,  views ,  onSite , ownerID);
    }

    public void addProduct(String storeID , String productID , float price , int amount){
        products products = new products();
        products.viewProducts();
        try{
             conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
             statObj = conObj.createStatement();
             resObj=statObj.executeQuery("INSERT INTO INVENTORY VALUES ( "+storeID+ "," +productID+ "," +price+ "," +amount+ ")");            
    }catch(SQLException e){
        e.printStackTrace();
    }
    
}
    
}
