/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.Random;
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
    private int soldadosBarrera;

    public Insecto(Refugio refugio, Tunel tunel, Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
        this.refugio = refugio;
        this.tunel = tunel;
    }
    
    public void GenerarInsecto(){
        estadisticas.setInterrumpirInsecto(true);
        soldadosBarrera = estadisticas.getNumSoldados();
        this.barrera = new CyclicBarrier(soldadosBarrera);
        
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
        Random rand = new Random();
        
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = hormigaSoldado.getTiempoDefender();
        
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaSoldado.getTipoHormiga() + " " + new String(hormigaSoldado.getID()) + " está preparada para salir a defender el hormiguero.");
        tunel.Salir(null, hormigaSoldado, this);
        while(true){
            try {
                this.barrera.await();
                break;
            } catch (InterruptedException ex) {
                this.barrera = new CyclicBarrier(soldadosBarrera);
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
                this.barrera = new CyclicBarrier(soldadosBarrera);
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
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
        }
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaSoldado.getTipoHormiga() + " " + new String(hormigaSoldado.getID()) + " está defendiendo el hormiguero.");
        synchronized(estadisticas.getBloqueoDefendiendo()){
            estadisticas.getListaDefendiendo().add(hormigaSoldado.getID());
            estadisticas.actualizarDefendiendo();
        }
        while(tiempoDormido < tiempoFinal){    
            try{
                Thread.sleep(tiempoFinal-tiempoDormido);
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
                    Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        synchronized(estadisticas.getBloqueoSoldadosDefendiendo()){
            estadisticas.setSoldadosDefendiendo(estadisticas.getSoldadosDefendiendo() - 1);
        }
        synchronized(estadisticas.getBloqueoDefendiendo()){
            estadisticas.getListaDefendiendo().remove(hormigaSoldado.getID());
            estadisticas.actualizarDefendiendo();
        }
        estadisticas.setInterrumpirInsecto(false);
        estadisticas.activarBotonInsecto();
        synchronized(refugio.getBloqueo()){
            refugio.getBloqueo().notifyAll();
        }
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaSoldado.getTipoHormiga() + " " + new String(hormigaSoldado.getID()) + " ha defendido con éxito el hormiguero.");
        tunel.Entrar(null, hormigaSoldado, null, this);
    }
}
