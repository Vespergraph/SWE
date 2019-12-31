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
public class store implements Observable {
    private String storeID;
    private String storeName;
    private String storeAddress;
    private String storePhoneNo;
    private String storeType;
    private int views ;
    private boolean onSite;
    private boolean visible = false;
    private Observer observer;
    Connection conObj = null;
    Statement statObj = null;
    ResultSet resObj = null;
    admin admin = new admin();
       
       
    public store(){
        
    }
    public store(String StoreID){
        views++;
        Inventory storeInv = new Inventory();
        storeInv.viewStoreProducts(StoreID);
    }
       
    store(String storeName, String storeAddress, String storePhoneNo, String storeType,  boolean onSite, String ownerID){
       storeID = UUID. randomUUID(). toString();
       this.storeName = storeName;
       this.storeAddress = storeAddress;
       this.storePhoneNo = storePhoneNo;
       this.storeType = storeType;
       views = 0;
       this.onSite = onSite;
       visible = false;
       createStore(storeName , storeAddress , storePhoneNo , storeType , onSite ,ownerID);
   }
       
    public void createStore(String storeName, String storeAddress, String storePhoneNo, String storeType,  boolean onSite, String ownerID){
        
        boolean visible = false;
        boolean onsite = false;
        int views = 0;
        try{
           conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
           statObj = conObj.createStatement();
           statObj.executeUpdate("INSERT INTO STORE " + "values ('"+storeID+"','"+storeName+"','"+storeAddress+"','"+storePhoneNo+"',"+views+ "," +onsite+ "," +visible+ ",'" +ownerID+ "','"+storeType+ "')" ); 
           if(admin.acceptStore(storeID, storeName, storeAddress, storePhoneNo,storeType, onSite) == true){
            visible = true;
            statObj.executeUpdate("Insert into STORE (VISIBLE) values ( '" +visible+ "')");
        }
        statObj.close();
        conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        addToOwnerList();
    }
    public void addToOwnerList(){
        StoreOwner o = new StoreOwner();
        o.addToStores(storeID);
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
        observer.update(views);
    }
    public String myOwner(String storeID){
        String owner = null;
        try{
           conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
           statObj = conObj.createStatement();
           owner = resObj.getString("select OwnerID from store where storeID = '" +storeID+ "')");
           resObj.close();
           conObj.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return owner;
    }
}
