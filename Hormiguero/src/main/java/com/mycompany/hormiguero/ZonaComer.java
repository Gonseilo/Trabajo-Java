/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class ZonaComer {
    AtomicInteger comida = new AtomicInteger(0);
    private final Object bloqueo = new Object();
    private Refugio refugio;

    public ZonaComer(Refugio refugio) {
        this.refugio = refugio;
    }
    
    public void Comer (HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto, Tunel tunel){
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
        
        while (comida.get() == 0){
            System.out.println("Hormiga " + id + " esperando comida");
            synchronized(bloqueo){
                try {
                    bloqueo.wait();
                } catch (InterruptedException ex) {
                    if (hormigaSoldado != null){
                        insecto.DefenderInsecto(hormigaSoldado);
                    }
                    else{
                        if (hormigaCria != null){
                            refugio.Refugiarse(hormigaCria);
                            Comer(null, null, hormigaCria, insecto, tunel);
                        }
                        else{
                            Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        comida.addAndGet(-1);
        System.out.println("Hormiga " + id + " comiendo");
        System.out.println("Comida restante en la zona de comer: " + comida);
        try {
            Thread.sleep(tiempoComer);
        } catch (InterruptedException ex) {
            if (hormigaSoldado != null){
                insecto.DefenderInsecto(hormigaSoldado);
            }
            else{
                if (hormigaCria != null){
                    refugio.Refugiarse(hormigaCria);
                    Comer(null, null, hormigaCria, insecto, tunel);
                }
                else{
                    Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void DejarComida (HormigaObrera hormigaObrera){
        try {
            Thread.sleep(hormigaObrera.getTiempoDejarComidaZonaComer());
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaComer.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " dejando comida en la zona de comer");
        comida.addAndGet(5);
        synchronized(bloqueo){
            bloqueo.notify();
        }
        System.out.println("Comida restante en la zona de comer: " + comida);
    }
}
