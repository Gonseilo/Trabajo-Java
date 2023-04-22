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
public class ZonaInstruccion {
    public void Instruir (HormigaSoldado hormigaSoldado){
        try {
            System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " instruyendo");
            Thread.sleep(hormigaSoldado.getTiempoInstruir());
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
