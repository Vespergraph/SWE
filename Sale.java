/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;

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
    Sale(){
        
    }
    Sale(String userID ,String storeID , ArrayList products , int quantity){
        saleID = UUID. randomUUID(). toString();
        newSale(userID , storeID ,products, quantity);
    }
    
    public void newSale(String userID ,String storeID ,ArrayList p, int quantity){
        float price = 0;
        float total = 0;
        double sale = 0;
        String productID = "0";
        Inventory i = new Inventory();
        Iterator<String> iterator = p.iterator();
        while(iterator.hasNext()){
        try{
            productID = iterator.next();
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            statObj.executeUpdate("Insert into SALE (saleid , userid , productid , quantity , storeid) Values ( '" +saleID+ "','" +userID+ "','" +productID+ "'," +quantity+ ",'" +storeID+ "')");
            total = total + i.getPrice(resObj.getString("(Select productID where saleID =" + saleID+ "from Sale)"));
            statObj.close();
            conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
       sale = saleCheck(p,userID,storeID);
        total = (float) (total * sale);
        System.out.println(total);
        i.updateInventory(storeID, productID, quantity);
    }
    }

    public void countStoreSale(String storeID){
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
          // resObj = statObj.executeQuery("select * from Sales");
           saleNumbers = resObj.getInt("count(salesID) from Sale where storeID = " +storeID+ ")");
            resObj.close();
            conObj.close();           
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
    
    public int maxSales(String productID){
        int max = 0;
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            max = resObj.getInt("Select max(S) Select'" +productID+ "', sum(quantity) as S from SALE");
            resObj.close();
            conObj.close();            
        }catch(SQLException e){
            e.printStackTrace();
        }
        return max;
    }
    public int minSales(String productID){
        int min = 0;
         try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            min = resObj.getInt("Select min(S) Select'" +productID+ "', sum(quantity) as S from SALE");
            resObj.close();
            conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return min;       
    }
    public int sumOfSales(String productID){
        int sum = 0;
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            sum = resObj.getInt("Select sum(S) Select'" +productID+ "', quantity as S from SALE");
            resObj.close();
            conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return sum;
    }
    public double saleCheck(ArrayList p, String userID , String storeID){
       double sale = 0;
       if (twoProducts(p)== true){
           sale = sale + 0.1;
       }
       if(buyerIsOwner(userID ,storeID) == true){
           sale = sale + 0.15;
       }
       if(firstTime(userID)==true){
           sale = sale + 0.5;
       }
       
       return sale;
    }
    public boolean twoProducts(ArrayList p){
        Iterator<String> it = p.iterator();
        for(int i = 0 ; i < p.size() ; i++){
            while(it.hasNext()){
                if(p.get(i) == it.next()){
                return true;}
        }
        
        }
    return false;
}
    public boolean buyerIsOwner(String userID, String storeID){
        store s = new store();
        if(userID == s.myOwner(storeID)){
            return true;
        }else
        return false;
    }
    public boolean firstTime(String userID){
        int i=0;
        try{
            conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
            statObj = conObj.createStatement();
            i = resObj.getInt("(count(*) from sale where userID='"+userID+ "')");
            resObj.close();
            conObj.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }if(i==0){
            return true;
        }else
        return false;
    }
   
}
