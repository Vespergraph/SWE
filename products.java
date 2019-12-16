/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Ali-Marwan
 */
public class products{
    private String productID;
    private String productName;
    private float upperLimit;
    private float lowerLimit;
    private float price;
    private String category;
    private int quantity;
    private boolean onlineProduct;
 //   private boolean productVisibility;
    Connection conObj = null;
    Statement statObj = null;
    ResultSet resObj = null;
    products(String productID, String productName , float upperLimit , float lowerLimit , String category , boolean onlineProduct ){
        this.productID = productID;
        this.productName = productName;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.category = category ;
        this.onlineProduct = onlineProduct;
        
        
    }
    public void addProduct(String productID, String productName, float upperLimit , float lowerLimit  , String category ,  boolean onlineProduct){
        try{
              conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
              statObj = conObj.createStatement();
              statObj.executeUpdate("Insert into PRODUCTS (PRODUCTID , PRODUCTNAME , PRICEUPPERLIMIT, PRICELOWERLIMIT , CATEGORY , ONLINEPRODUCT) values "
                      + "(" +productID+ "," +productName+ "," +upperLimit+ "," +lowerLimit+ ","  +category+ ","  +onlineProduct+ ")");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    
}
