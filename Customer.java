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
    }
    
    public void register(){
        try{
             conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
             statObj = conObj.createStatement();
             statObj.executeUpdate("Insert into Customer values( " +userID+ "," +userName+ "," + userPassword+ "," +userAddress+ "," +userPhoneNo+ "," +userPaymentDetails+ "," +userEmail+ ")");
                     
        }catch(SQLException e){
            e.printStackTrace();         
                     }
        
    }
    
    public void login(String userID , String userPassword){
            if(userMatch(userID,userPassword) != true){
                System.out.println("bad");
            }
    }
    public boolean userMatch(String userID, String userPassword){
        try{
             conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
             statObj = conObj.createStatement();
             resObj=statObj.executeQuery("Select * From CUSTOMER");
             while(resObj.next()){
                 if(userID.matches(resObj.getString("USERID")) && userPassword.matches(resObj.getString("USERPASSWORD"))){
                     return true;
                 }
             }
             
    }catch(SQLException e){
        e.printStackTrace();
    }        
                return false;
 
}
    public void viewStore(){
        store s = new store();
    }
    
    public void buyProduct(String productID, String storeID, int quantity){
        Sale sale = new Sale(userID ,storeID, productID , quantity);
    }
}

