/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.UUID;

/**
 *
 * @author Ali-Marwan
 */
public class collab extends StoreOwner {
    private String collabID;
    private String storeID;
    public collab(String storeID){
        collabID = UUID. randomUUID(). toString();
        this.storeID = storeID;

        
    }
    
    public void viewSystemProducts(){
        products p = new products();
        p.viewProducts();
    }
    
    
    public void addProduct(String storeID , String productID , float price , int amount){// do rest of parameters

    Inventory IO = new Inventory();
    IO.addToInventory(storeID , productID , price , amount);
    record.add("( added " +productID+ "to" +storeID+ ")");
}
}
