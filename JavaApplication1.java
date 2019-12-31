/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;




/**
 *
 * @author Ali-Marwan
 */
public class JavaApplication1 {

    public static void main(String[] args) {
        admin admin = new admin("12345");
        Customer c1 = new Customer("marwan2","12345","manial", "23565", "323@gmail.com" , "2354162121");
        Customer c2 = new Customer();
        admin.addProduct("name1", 5, 20, "clothess", true);
        c1.login("marwan2","12345");
        StoreOwner s1 = c1.becomeStoreOwner(c1.getUserID());
        s1.newStore("x1", "212street", "23542", "clothes", false);
        s1.viewSystemProducts();
        ArrayList<String> products = new ArrayList() ;
        s1.addProduct("95524e70-7a6a-4102-9478-1347b0a7cca7", "e3d03b30-faa0-4e32-9f4e-ca8ffd738973", 10, 4);
        products.add("e3d03b30-faa0-4e32-9f4e-ca8ffd738973");
        products.add("e3d03b30-faa0-4e32-9f4e-ca8ffd738973");
        c1.buyProduct(products, "95524e70-7a6a-4102-9478-1347b0a7cca7", 2);
       

    }
    
}
