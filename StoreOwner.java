/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.sql.*;
import java.lang.String;
import java.util.ArrayList;
import java.util.Iterator;



/**
 *
 * @author Ali-Marwan
 */
public class StoreOwner extends Customer implements Observer {
    private String ownerID;
  
    Connection conObj = null;
    Statement statObj = null;
    ResultSet resObj = null;
           
    ArrayList<String> myStores = new ArrayList<String>();
    ArrayList<String> record = new ArrayList<String>();
    Iterator<String> recordIterator = record.iterator();
    public void addToStores(String storeID){
        myStores.add(storeID);
    }
    
    public StoreOwner(String ownerID){
        this.ownerID = ownerID;
    }
    public StoreOwner(){
        
    }
    public void newStore(String storeName, String storeAddress, String storePhoneNo, String storeType,  boolean onSite){
    store sd = new store(storeName,  storeAddress,  storePhoneNo,  storeType,   onSite , ownerID);
    }
    
    public void viewSystemProducts(){
        products p = new products();
        p.viewProducts();
    }
    
    public void addProduct(String storeID , String productID , float price , int amount){
        
        Inventory I = new Inventory();
        I.addToInventory(storeID , productID , price , amount);
      //  record.add("( added " +productID+ "to" +storeID+ ")");
    }


    @Override
    public void update(int views) {
        System.out.println(views);
    }
    

    @Override
    public void update(String soldOut) {
        System.out.println(soldOut);
    }
    private void addColab(String storeID){
        collab collaber = new collab(storeID);
    }
    private void checkHistory(){
        while(recordIterator.hasNext()){
            System.out.println(recordIterator.next());
        }
    }
}
    

