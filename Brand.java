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
public class Brand {
    private String brandID;
    private String brandName;
    private String brandCategory;
    
    Brand(String brandName , String brandCategory){
        brandID = UUID. randomUUID(). toString();
        this.brandName = brandName;
        this.brandCategory = brandCategory;

    }
    
}
