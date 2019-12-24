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
        views++;
    }
       
    store(String storeName, String storeAddress, String storePhoneNo, String storeType, int views , boolean onSite, String ownerID){
       storeID = UUID. randomUUID(). toString();
       this.storeName = storeName;
       this.storeAddress = storeAddress;
       this.storePhoneNo = storePhoneNo;
       this.storeType = storeType;
       this.views = views;
       this.onSite = onSite;
       visible = false;
       createStore(ownerID);
   }
       
    public void createStore(String ownerID){
        try{
           conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
           statObj = conObj.createStatement();
           statObj.executeUpdate("Insert into STORE values (" +storeID +"," +storeName+ "," +storeAddress+ "," +storePhoneNo+ "," +storeType+ "," +views+ "," +onSite+ "," +visible+  " )" );
           statObj.executeUpdate("Insert into Store (OWNERID) values ( "+ownerID+ ")");
           if(admin.acceptStore(storeID, storeName, storeAddress, storePhoneNo,storeType, onSite) == true){
            visible = true;
            statObj.executeUpdate("Insert into STORE (VISIBLE) values ( " +visible+ ")");
        }
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
}
