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
    private Estadisticas estadisticas;

    public Refugio(Estadisticas estadisticas) {
        this.ataque = false;
        this.estadisticas = estadisticas;
    }
    
    public void Refugiarse (HormigaCria hormigaCria){
        System.out.println("Hormiga " + new String(hormigaCria.getID()) + " entra al refugio");
        
        synchronized(estadisticas.getBloqueoRefugio()){
            estadisticas.getListaRefugio().add(hormigaCria.getID());
            estadisticas.actualizarRefugio();
        }
        
        while(true){
            try {
                synchronized(bloqueo){
                    bloqueo.wait();
                }
                break;
            } catch (InterruptedException ex) {
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                        try {
                            estadisticas.getBloqueoPausa().wait();
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
                else{
                    Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        System.out.println("Hormiga " + new String(hormigaCria.getID()) + " sale del refugio");
        
        synchronized(estadisticas.getBloqueoRefugio()){
            estadisticas.getListaRefugio().remove(hormigaCria.getID());
            estadisticas.actualizarRefugio();
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
