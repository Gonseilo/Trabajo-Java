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
        Random rand = new Random();
        
        int tiempoComerHormiga = 3000;
        
        int tiempoMaximo = 5000;
        int tiempoMinimo = 3000;
        int tiempoComerCria = rand.nextInt((tiempoMaximo - tiempoMinimo)+1)+ tiempoMinimo;
        
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
                Thread.sleep(tiempoComerCria);
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                Thread.sleep(tiempoComerHormiga);
            } catch (InterruptedException ex) {
                Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public synchronized void DejarComida (HormigaObrera hormigaObrera){
        Random rand = new Random();
        int tiempoMinimo = 1000;
        int tiempoMaximo = 2000;
        int tiempoDejarComida = rand.nextInt((tiempoMaximo - tiempoMinimo)+1) + tiempoMinimo;
        try {
            Thread.sleep(rand.nextInt(tiempoDejarComida));
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hormiga" + new String(hormigaObrera.getID()) + " dejando comida en la zona de comer");
        comida = comida + 5;
        notify();
        System.out.println("Comida restante en la zona de comer: " + comida);
    }
}
