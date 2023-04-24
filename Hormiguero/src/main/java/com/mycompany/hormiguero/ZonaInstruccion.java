/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

/**
 *
 * @author Ivanl
 */
public class ZonaInstruccion {
    public void Instruir (HormigaSoldado hormigaSoldado, Insecto insecto, Tunel tunel){
        try {
            System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " instruyendo");
            Thread.sleep(hormigaSoldado.getTiempoInstruir());
        } catch (InterruptedException ex) {
            insecto.DefenderInsecto(hormigaSoldado, tunel);
        }
    }
}
