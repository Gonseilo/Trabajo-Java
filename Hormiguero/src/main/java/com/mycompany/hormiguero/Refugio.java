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
public class Refugio {
    private boolean ataque;
    private final Object bloqueo = new Object();

    public Refugio() {
        this.ataque = false;
    }
    
    public void Refugiarse (HormigaCria hormigaCria){
        synchronized(bloqueo){
            System.out.println("Hormiga " + new String(hormigaCria.getID()) + " entra al refugio");
            try {
                bloqueo.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Refugio.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Hormiga " + new String(hormigaCria.getID()) + " sale del refugio");
        }
    }

    public void setAtaque(boolean ataque) {
        this.ataque = ataque;
    }

    public boolean isAtaque() {
        return ataque;
    }

    public Object getBloqueo() {
        return bloqueo;
    }
}
