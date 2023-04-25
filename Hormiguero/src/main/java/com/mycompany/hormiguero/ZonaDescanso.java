/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class ZonaDescanso {
    private Refugio refugio;

    public ZonaDescanso(Refugio refugio) {
        this.refugio = refugio;
    }
    
    public void Descansar(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto, Tunel tunel){
        String id = null;
        int tiempoDescansar = 0;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getID());
            tiempoDescansar = hormigaObrera.getTiempoDescansar();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getID());
            tiempoDescansar = hormigaSoldado.getTiempoDescansar();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getID());
            tiempoDescansar = hormigaCria.getTiempoDescansar();
        }
        
        try {
            System.out.println("Hormiga " + id + " descansando");
            Thread.sleep(tiempoDescansar);
        } catch (InterruptedException ex) {
            if (hormigaSoldado != null){
                insecto.DefenderInsecto(hormigaSoldado);
            }
            else{
                if (hormigaCria != null){
                    refugio.Refugiarse(hormigaCria);
                }
                else{
                    Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
