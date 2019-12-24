/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author Ali-Marwan
 */
public interface Observable {
    public void registerObserver(Observer o);
    public void unregisterObserver();
    public void notifyObservers();
    
}
