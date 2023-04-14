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
    
public synchronized void Comer (HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria){
        Random rand = new Random();
        String id = null;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getID());
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getID());
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getID());
        }
        
        while (comida == 0){
            System.out.println("Hormiga " + id + " esperando comida");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        comida--;
        System.out.println("Hormiga " + id + " comiendo");
        System.out.println("Comida restante en la zona de comer: " + comida);
        if (hormigaCria != null){
            try {
                Thread.sleep(rand.nextInt(3000, 5000));
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public synchronized void DejarComida (HormigaObrera hormigaObrera){
        Random rand = new Random();
        
        try {
            Thread.sleep(rand.nextInt(1000, 2000));
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hormiga" + new String(hormigaObrera.getID()) + " dejando comida en la zona de comer");
        comida = comida + 5;
        System.out.println("Comida restante en la zona de comer: " + comida);
    }
}
