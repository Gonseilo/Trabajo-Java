/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class Refugio {
    private final Object bloqueo = new Object();
    private Estadisticas estadisticas;

    public Refugio(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }
    
    public void Refugiarse (HormigaCria hormigaCria){
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaCria.gettipoHormiga() + " " + new String(hormigaCria.getId()) + " ha entrado al refugio.");
        
        synchronized(estadisticas.getBloqueoRefugio()){
            estadisticas.getListaRefugio().add(hormigaCria.getId());
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
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaCria.gettipoHormiga() + " " + new String(hormigaCria.getId()) + " ha salido del refugio.");
        
        synchronized(estadisticas.getBloqueoRefugio()){
            estadisticas.getListaRefugio().remove(hormigaCria.getId());
            estadisticas.actualizarRefugio();
        }
    }

    public Object getBloqueo() {
        return bloqueo;
    }
}
