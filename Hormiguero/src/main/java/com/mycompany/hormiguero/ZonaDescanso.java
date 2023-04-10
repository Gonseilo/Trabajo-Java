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
    public void DescansarObrera(HormigaObrera hormigaObrera){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DescansarSoldado(HormigaSoldado hormigaSoldado){
        try {
            System.out.println("Hormiga " + hormigaSoldado.getID() + " descansando");
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DescansarCria(HormigaCria hormigaCria){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
