/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.*;
/**
 *
 * @author Ali-Marwan
 */
public class admin {
    private String adminID;
    private String adminPassword;
    Scanner input = new Scanner(System.in);
    public boolean acceptStore(String storeID, String storeName, String storeAddress, String storePhoneNo, String storeType, boolean onSite){
        System.out.println("if you accept the store enter Y ");
        System.out.println("if you don't enter N");
        String command = input.nextLine();
        if(command == "Y"){
            return true;
        }else return false;
    }
    public void addProduct(String productID ,String productName, float upperLimit , float lowerLimit, String category , boolean onlineProduct){
        products product = new products(productID , productName , upperLimit , lowerLimit , category , onlineProduct);
        product.addToList(product);
    }
    
    
}
