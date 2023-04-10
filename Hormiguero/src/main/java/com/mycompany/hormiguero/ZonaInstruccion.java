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
        
        try {
            System.out.println("Hormiga " + hormigaSoldado.getID() + " instruyendo");
            Thread.sleep(rand.nextInt(2000, 8000));
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
