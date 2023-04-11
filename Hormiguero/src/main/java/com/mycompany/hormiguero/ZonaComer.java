/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class ZonaComer {
    public int comida = 3;
    
    public synchronized void ComerObrera (HormigaObrera hormigaObrera){
        while (comida == 0){
            System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " esperando comida");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        comida--;
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " comiendo");
        System.out.println("Comida restante = " + comida);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void ComerSoldado (HormigaSoldado hormigaSoldado){
        while (comida == 0){
            System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " esperando comida");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        comida--;
        System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " comiendo");
        System.out.println("Comida restante = " + comida);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void ComerCria (HormigaCria hormigaCria){
        Random rand = new Random();
        
        while (comida == 0){
            System.out.println("Hormiga " + new String(hormigaCria.getID()) + " esperando comida");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        comida--;
        System.out.println("Hormiga " + new String(hormigaCria.getID()) + " comiendo");
        System.out.println("Comida restante = " + comida);
        try {
            Thread.sleep(rand.nextInt(3000, 5000));
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
