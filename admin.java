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
    public admin(){
        
    }
    public admin(String adminPassword){
         adminID = UUID. randomUUID(). toString();
         this.adminPassword= adminPassword;
    }
    Scanner input = new Scanner(System.in);
    public boolean acceptStore(String storeID, String storeName, String storeAddress, String storePhoneNo, String storeType, boolean onSite){
        System.out.println("if you accept the store enter Y ");
        System.out.println("if you don't enter N");
        String command = input.nextLine();
        if(command == "Y"){
            return true;
        }else return false;
    }
    public void addProduct(String productName, int upperLimit , int lowerLimit, String category , boolean onlineProduct){
        products produc = new products(productName, upperLimit ,lowerLimit , category, onlineProduct);
        produc.addProduct(produc);
    }
    
    public void addBrand (String brandName , String brandCategory){
        Brand br = new Brand(brandName , brandCategory);
    }
    
    enum Operation {
        SUM,
        MAX,
        MIN;
        //AVG;
    }
    public float getProductStat(String prodID, Operation op) {
        if (op == Operation.MAX) {
            return getProductMaxStat(prodID);
        }else if(op == Operation.MIN){
            return getProductMinStat(prodID);
        }else if(op == Operation.SUM){
            return getProductSumStat(prodID);
        }/*else if(op == Operation.AVG){
            return getProductAvgStat(prodID);
        }*/
        return 0;
    }
    
    public int getProductMaxStat(String productID) {
        Sale sale = new Sale();
        return sale.maxSales(productID);
        
    }
    
    public int getProductMinStat(String productID){
        Sale sale = new Sale();
        return sale.minSales(productID);
    }
    public float getProductSumStat(String productID){
        Sale sale = new Sale();
        return sale.sumOfSales(productID);
    }
    public float getProductAvgStat (String productID){
        return 0;
    }
}
