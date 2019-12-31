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
    private int upperLimit;
    private int lowerLimit;
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
    
    products(String productID ,String productName , int upperLimit , int lowerLimit , String category , boolean onlineProduct ){
    this.productID = productID;
    this.productName = productName;
    this.upperLimit = upperLimit;
    this.lowerLimit = lowerLimit;
    this.category = category ;
    this.onlineProduct = onlineProduct;
    }

           
    products(String productName , int upperLimit , int lowerLimit , String category , boolean onlineProduct ){
        productID = UUID. randomUUID(). toString();
        this.productName = productName;
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.category = category ;
        this.onlineProduct = onlineProduct;
        //addProduct(productID , productName , upperLimit , lowerLimit , category , onlineProduct);
       
    }
    
    public void addProduct(products produc){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            statObj.executeUpdate("Insert into PRODUCTS values('"+produc.getProductID()+"','"+produc.getProductName()+"',"+produc.getUpperLimit()+","+produc.getLowerLimit()+",'"+produc.getCategory()+ "','"+produc.isOnlineProduct()+"')");
            statObj.close();
            conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
            e.getMessage();
        }
    }

    public ArrayList<products> getProducts(){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            resObj=statObj.executeQuery("Select * From PRODUCTS");
            while(resObj.next()){
             //   System.out.println(resObj.getString("productID")+ "\t   " + resObj.getString("productname"));
             products pr = new products(resObj.getString("productID") , resObj.getString("productName") , resObj.getInt("priceUpperLimit") , resObj.getInt("priceLowerLimit") , resObj.getString("category") , resObj.getBoolean("onlineproduct"));
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
        statObj.close();
        conObj.close();
    }catch(SQLException e){
            e.printStackTrace();
            }
     
    
}
    public String getProductName(String productID){
        String Name= null;
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            Name= resObj.getString("(Select productName from products where productID = '" +productID+ "')");
            statObj.close();
            conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return Name;
        
    }
    public String getProductCategory(String ProductID){
        String category= null;
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            category= resObj.getString("(Select Category from products where productID ='" +productID+ "')");
            resObj.close();
            conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return category;
        
    }
    public String getProductName() {
        return productName;
    }

    public float getUpperLimit() {
        return upperLimit;
    }

    public float getLowerLimit() {
        return lowerLimit;
    }

    public String getCategory() {
        return category;
    }

    public boolean isOnlineProduct() {
        return onlineProduct;
    }

    }

    

