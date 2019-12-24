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
   // private float price = 0;
    private String category;
    //private int quantity = 0;
    private boolean onlineProduct;
 //   private boolean productVisibility;
    Connection conObj = null;
    Statement statObj = null;
    ResultSet resObj = null;
    ArrayList<products> prodList = new ArrayList<>();
    
    public String getProductID() {
        return productID;
    }   
    
    products(){
        
    }
    
    products(String productID ,String productName , float upperLimit , float lowerLimit , String category , boolean onlineProduct ){
    this.productID = productID;
    this.productName = productName;
    this.upperLimit = upperLimit;
    this.lowerLimit = lowerLimit;
    this.category = category ;
    this.onlineProduct = onlineProduct;
    }

           
    products(String productName , float upperLimit , float lowerLimit , String category , boolean onlineProduct ){
        productID = UUID. randomUUID(). toString();
        this.productName = productName;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.category = category ;
        this.onlineProduct = onlineProduct;
        //addProduct(productID , productName , upperLimit , lowerLimit , category , onlineProduct);
       
    }
    
    public void addProduct(products product){
        String productName = product.productName;
        float upperLimit = product.upperLimit;
        float lowerLimit = product.lowerLimit;
        String category = product.category;
        boolean onlineProduct = product.onlineProduct;
        try{
              conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
              statObj = conObj.createStatement();
              statObj.executeUpdate("Insert into PRODUCTS (PRODUCTID , PRODUCTNAME , PRICEUPPERLIMIT, PRICELOWERLIMIT , CATEGORY , ONLINEPRODUCT) values "
                      + "(" +productID+ "," +productName+ "," +upperLimit+ "," +lowerLimit+ ","  +category+ ","  +onlineProduct+ ")");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<products> getProducts(){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            resObj=statObj.executeQuery("Select * From PRODUCTS");
            while(resObj.next()){
             //   System.out.println(resObj.getString("productID")+ "\t   " + resObj.getString("productname"));
             products pr = new products(resObj.getString("productID") , resObj.getString("productName") , resObj.getFloat("priceUpperLimit") , resObj.getFloat("priceLowerLimit") , resObj.getString("category") , resObj.getBoolean("onlineproduct"));
             prodList.add(pr);
            }           
        }catch(SQLException e){
            e.printStackTrace();
        }
        return prodList;
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

}
