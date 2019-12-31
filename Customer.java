/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;
import java.sql.Connection;
/**
 *
 * @author Ali-Marwan
 */
public class Customer{
    private String userID;
    private String userName;
    private String userPassword ;
    private String userPhoneNo;
    private String userAddress;
    private String userEmail ;
    private String userPaymentDetails;
    private boolean ownsStore = false;
    
    Connection conObj = null;
    Statement statObj = null;
    ResultSet resObj = null;
    
    public Customer(){
        
    }
    
    public Customer(String userName ,String userPassword, 
    String userAddress, String userPhoneNo, String userEmail, String userPaymentDetails){
        userID = UUID. randomUUID(). toString();
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userPhoneNo = userPhoneNo;
        this.userAddress = userAddress;
        this.userPaymentDetails = userPaymentDetails;
        this.userEmail = userEmail; 
        register(userID ,userName,userPassword ,  userAddress,  userPhoneNo ,  userEmail ,  userPaymentDetails);
    }
    
    public void register(String userID ,String userName, String userPassword , String userAddress, String userPhoneNo , String userEmail , String userPaymentDetails){
        boolean storeOwner= false;
        try{
            
             conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
             statObj = conObj.createStatement();
            statObj.executeUpdate("INSERT INTO CUSTOMER"+ " VALUES('"+userID+"','"+userName+"','"+userPassword+"','"+userAddress+"','"+userPhoneNo+"','"+userPaymentDetails+ "','"+userEmail+"'," +storeOwner+ ")");
            statObj.close();
            conObj.close();
        }catch(SQLException e){
            e.printStackTrace(); 
            e.getSQLState();
                     }  

    

        
    }
    
    public void login(String userName , String userPassword){
            if(userMatches(userName,userPassword) != true){
                System.out.println("wrong password");
            }
    }
    public boolean userMatches(String userID, String userPassword){
        try{
             conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
             statObj = conObj.createStatement();
             resObj=statObj.executeQuery("Select * From CUSTOMER");
             while(resObj.next()){
                 if(userID.matches(resObj.getString("userName")) && userPassword.matches(resObj.getString("USERPASSWORD"))){
                     return true;
                 }
             }
            statObj.close();
            conObj.close();
             
    }catch(SQLException e){
        e.printStackTrace();
    }        
                return false;
 
}
    
    public StoreOwner becomeStoreOwner(String StoreOwnerID){
        StoreOwner so = new StoreOwner(StoreOwnerID);
        ownsStore = true;
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            statObj.executeUpdate("Update Customer set isStoreOwner = true where userID ='"+StoreOwnerID+"'");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return so;
    }
    public void viewStore(String StoreID){
        store s = new store(StoreID);
        
    }
    
    public void buyProduct(ArrayList products, String storeID, int quantity){
        Sale sale = new Sale(userID ,storeID, products, quantity);
    }
    public String getUserID(){
        return userID;
    }
}

