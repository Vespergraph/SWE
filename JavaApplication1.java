/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;


import java.sql.*;

/**
 *
 * @author Ali-Marwan
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Connection conObj = null;
       Statement statObj = null;
       ResultSet resObj = null;
       try{ 
          conObj = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDataBase", "am", "am");
          statObj = conObj.createStatement();
         // statObj.executeUpdate("Insert into UNTITLED(ID,Name) values (22 , 'mar')");                                       
          resObj = statObj.executeQuery("Select * from AM.UNTITLED");
          while(resObj.next()){
              int id = resObj.getInt("ID");
              String Name = resObj.getString("Name");
              System.out.print(id);
              System.out.print(Name);
              
              
          }
       }catch(SQLException e){
        e.printStackTrace();
       }
       
       
    }
    
}
