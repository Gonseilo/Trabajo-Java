/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class Insecto {
    private CyclicBarrier barrera = new CyclicBarrier(1);
    private Refugio refugio;
    private Tunel tunel;
    private Estadisticas estadisticas;
    private Boolean interrumpirInsecto = false;
    private int soldadosBarrera;

    public Insecto(Refugio refugio, Tunel tunel, Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
        this.refugio = refugio;
        this.tunel = tunel;
    }
    
    public void GenerarInsecto(){
        soldadosBarrera = estadisticas.getNumSoldados();
        this.barrera = new CyclicBarrier(soldadosBarrera);
        interrumpirInsecto = true;
        
        for (Thread thread : estadisticas.getListaSoldados()){
            if (thread != null){
                thread.interrupt();
            }
        }
        
        for (Thread thread : estadisticas.getListaCrias()){
            if (thread != null){
                thread.interrupt();
            }
        }
    }
    
    public void DefenderInsecto(HormigaSoldado hormigaSoldado){
        
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = hormigaSoldado.getTiempoInstruir();
        
        System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " saliendo a defender la colonia");
        tunel.Salir(null, hormigaSoldado, this);
        while(true){
            try {
                this.barrera.await();
                
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
                    Logger.getLogger(Insecto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (BrokenBarrierException ex) {
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                    this.barrera = new CyclicBarrier(soldadosBarrera);
                        try {
                            estadisticas.getBloqueoPausa().wait();
                        } catch (InterruptedException ex1) {
                            if (!estadisticas.getPlay()){
                                synchronized(estadisticas.getBloqueoPausa()){
                                    try {
                                        estadisticas.getBloqueoPausa().wait();
                                    } catch (InterruptedException ex2) {
                                        Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex2);
                                    }
                                }
                            }
                            else{
                                Logger.getLogger(Insecto.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }
                }
                else{
                    Logger.getLogger(Insecto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        synchronized(estadisticas.getBloqueoSoldadosDefendiendo()){
            estadisticas.setSoldadosDefendiendo(estadisticas.getSoldadosDefendiendo() + 1);
            System.out.println("Soldados defendiendo: " + estadisticas.getSoldadosDefendiendo());
        }
        interrumpirInsecto = false;
        System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " comienza a defender la colonia");
        synchronized(estadisticas.getBloqueoDefendiendo()){
            estadisticas.getListaDefendiendo().add(hormigaSoldado.getID());
            estadisticas.actualizarDefendiendo();
        }
        while(tiempoDormido < tiempoFinal){    
            try{
                Thread.sleep(20000);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
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
        synchronized(estadisticas.getBloqueoSoldadosDefendiendo()){
            estadisticas.setSoldadosDefendiendo(estadisticas.getSoldadosDefendiendo() - 1);
            System.out.println("Soldados defendiendo: " + estadisticas.getSoldadosDefendiendo());
        }
        synchronized(estadisticas.getBloqueoDefendiendo()){
            estadisticas.getListaDefendiendo().remove(hormigaSoldado.getID());
            estadisticas.actualizarDefendiendo();
        }
        refugio.setAtaque(false);
        synchronized(refugio.getBloqueo()){
            refugio.getBloqueo().notifyAll();
        }
        System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " volviendo de defender la colonia");
        tunel.Entrar(null, hormigaSoldado, null, this);
    }

    public Boolean getInterrumpirInsecto() {
        return interrumpirInsecto;
    }
}
