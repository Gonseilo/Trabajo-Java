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
public class ZonaInstruccion {
    public void Instruir (HormigaSoldado hormigaSoldado){
        Random rand = new Random();
        
        int tiempoMinimo = 2000;
        int tiempoMaximo = 8000;
        int tiempoIntruccion = rand.nextInt((tiempoMinimo - tiempoMaximo)+1)+ tiempoMinimo;
        
        try {
            System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " instruyendo");
            Thread.sleep(tiempoIntruccion);
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
