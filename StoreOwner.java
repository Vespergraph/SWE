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
    public void viewProducts(){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            resObj=statObj.executeQuery("Select * From PRODUCTS");
            while(resObj.next()){
                System.out.println(resObj.getString("productID")+ "\t   " + resObj.getString("productname"));
            }           
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void addProduct(){
        viewProducts();
        // add to list;
    
}
    
}
