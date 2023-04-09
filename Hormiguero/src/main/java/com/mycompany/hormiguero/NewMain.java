/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hormiguero;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bonba
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread[] hilos = new Thread[10000];
        for (int i=0; i < 10000; i++){
            Runnable runnable = new Hormiga(i);
            hilos[i] = new Thread(runnable);
            hilos[i].start();
            //Hormiga nueva = new Hormiga(i);
        }
        for (int i=0; i < 10000; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
