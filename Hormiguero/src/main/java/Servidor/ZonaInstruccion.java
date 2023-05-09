/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class ZonaInstruccion {
    private Estadisticas estadisticas;

    public ZonaInstruccion(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }
    
    public void Instruir (HormigaSoldado hormigaSoldado, Insecto insecto, Tunel tunel){
        Random rand = new Random();
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = rand.nextInt(hormigaSoldado.getTiempoInstruirMax()-hormigaSoldado.getTiempoInstruirMin()+1)+hormigaSoldado.getTiempoInstruirMin();
        
        synchronized(estadisticas.getBloqueoInstruyendo()){
            estadisticas.getListaInstruyendo().add(hormigaSoldado.getID());
            estadisticas.actualizarInstruyendo();
        }
        synchronized(estadisticas.getBloqueoSoldadosInstruccion()){
            estadisticas.setSoldadosInstruccion(estadisticas.getSoldadosInstruccion() + 1);
        }
        while(tiempoDormido < tiempoFinal){
            try {
                System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaSoldado.getTipoHormiga() + " " + new String(hormigaSoldado.getID()) + " comienza a hacer instrucción.");
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
                
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
                    if (estadisticas.getInterrumpirInsecto()){
                        synchronized(estadisticas.getBloqueoInstruyendo()){
                            estadisticas.getListaInstruyendo().remove(hormigaSoldado.getID());
                            estadisticas.actualizarInstruyendo();
                        }
                        synchronized(estadisticas.getBloqueoSoldadosInstruccion()){
                            estadisticas.setSoldadosInstruccion(estadisticas.getSoldadosInstruccion() - 1);
                        }
                        insecto.DefenderInsecto(hormigaSoldado);
                        synchronized(estadisticas.getBloqueoInstruyendo()){
                            estadisticas.getListaInstruyendo().add(hormigaSoldado.getID());
                            estadisticas.actualizarInstruyendo();
                        }
                        synchronized(estadisticas.getBloqueoSoldadosInstruccion()){
                            estadisticas.setSoldadosInstruccion(estadisticas.getSoldadosInstruccion() + 1);
                        }
                    }
                    else{
                        Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        synchronized(estadisticas.getBloqueoInstruyendo()){
            estadisticas.getListaInstruyendo().remove(hormigaSoldado.getID());
            estadisticas.actualizarInstruyendo();
        }
        synchronized(estadisticas.getBloqueoSoldadosInstruccion()){
            estadisticas.setSoldadosInstruccion(estadisticas.getSoldadosInstruccion() - 1);
        }
    }
}
