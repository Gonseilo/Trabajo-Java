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
    public int comida = 0;
    
public synchronized void Comer (HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria){
        int tiempoComer = 0;
        String id = null;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getID());
            tiempoComer = hormigaObrera.getTiempoComer();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getID());
            tiempoComer = hormigaSoldado.getTiempoComer();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getID());
            tiempoComer = hormigaCria.getTiempoComer();
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
                Thread.sleep(tiempoComer);
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                Thread.sleep(tiempoComer);
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public synchronized void DejarComida (HormigaObrera hormigaObrera){
        try {
            Thread.sleep(hormigaObrera.getTiempoDejarComidaZonaComer());
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hormiga" + new String(hormigaObrera.getID()) + " dejando comida en la zona de comer");
        comida = comida + 5;
        notify();
        System.out.println("Comida restante en la zona de comer: " + comida);
    }
}
