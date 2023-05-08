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
public class ZonaComer {
    private final Object bloqueo = new Object();
    private Refugio refugio;
    private Estadisticas estadisticas;

    public ZonaComer(Refugio refugio, Estadisticas estadisticas) {
        this.refugio = refugio;
        this.estadisticas = estadisticas;
    }
    
    public void Comer (HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto, Tunel tunel){
        String id = null;
        char[] idChar = null;
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = 0;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getID());
            idChar = hormigaObrera.getID();
            tiempoFinal = hormigaObrera.getTiempoComer();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getID());
            idChar = hormigaSoldado.getID();
            tiempoFinal = hormigaSoldado.getTiempoComer();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getID());
            idChar = hormigaCria.getID();
            tiempoFinal = hormigaCria.getTiempoComer();
        }
        
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().add(idChar);
            estadisticas.actualizarZonaComer();
        }
        
        while (estadisticas.getComidaZonaComer() == 0){
            System.out.println("Hormiga " + id + " esperando comida");
            synchronized(bloqueo){
                try {
                    bloqueo.wait();
                } catch (InterruptedException ex) {
                    if (insecto.getInterrumpirInsecto()){
                        if (hormigaSoldado != null){
                            synchronized(estadisticas.getBloqueoZonaComer()){
                                estadisticas.getListaZonaComer().remove(idChar);
                                estadisticas.actualizarZonaComer();
                            }
        
                            insecto.DefenderInsecto(hormigaSoldado);
        
                            synchronized(estadisticas.getBloqueoZonaComer()){
                                estadisticas.getListaZonaComer().add(idChar);
                                estadisticas.actualizarZonaComer();
                            }
                        }
                        else{
                            if (hormigaCria != null){
                                synchronized(estadisticas.getBloqueoZonaComer()){
                                    estadisticas.getListaZonaComer().remove(idChar);
                                    estadisticas.actualizarZonaComer();
                                }
                                synchronized(estadisticas.getBloqueoCriasZonaComer()){
                                    estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() - 1);
                                    System.out.println("Crias en la zona de comer: " + estadisticas.getCriasZonaComer());
                                }
                                synchronized(estadisticas.getBloqueoCriasRefugio()){
                                    estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()+ 1);
                                    System.out.println("Crias en el refugio: " + estadisticas.getCriasRefugio());
                                }
                                refugio.Refugiarse(hormigaCria);
                                synchronized(estadisticas.getBloqueoCriasRefugio()){
                                    estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()- 1);
                                    System.out.println("Crias en el refugio: " + estadisticas.getCriasRefugio());
                                }
                                synchronized(estadisticas.getBloqueoCriasZonaComer()){
                                    estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() + 1);
                                    System.out.println("Crias en la zona de comer: " + estadisticas.getCriasZonaComer());
                                }
                                Comer(null, null, hormigaCria, insecto, tunel);
        
                                synchronized(estadisticas.getBloqueoZonaComer()){
                                    estadisticas.getListaZonaComer().add(idChar);
                                    estadisticas.actualizarZonaComer();
                                }
                            }
                            else{
                                Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    if (!estadisticas.getPlay()){
                        synchronized(estadisticas.getBloqueoPausa()){
                            try {
                                estadisticas.getBloqueoPausa().wait();
                            } catch (InterruptedException ex1) {
                                Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }
                }
            }
        }
        
        synchronized(estadisticas.getBloqueoComidaZonaComer()){
            estadisticas.setComidaZonaComer(estadisticas.getComidaZonaComer() - 1);
            estadisticas.actualizarComidaZonaComer();
            System.out.println("Comida restante en la zona de comer: " + estadisticas.getComidaZonaComer());
        }
        while(tiempoDormido < tiempoFinal){
            try {
                if (insecto.getInterrumpirInsecto() && hormigaCria != null){
                    hormigaCria.detenerHilo(hormigaCria.getNumHormiga());
                }
                System.out.println("Hormiga " + id + " va a comer durante " + (tiempoFinal - tiempoDormido) + "ms");
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                if (insecto.getInterrumpirInsecto()){
                    if (hormigaSoldado != null){
                        tiempoDormido = System.currentTimeMillis() - tiempoInicio;
                        System.out.println("Hormiga " + id + " se ha interrumpido después de comer " + tiempoDormido + "ms");
        
                        synchronized(estadisticas.getBloqueoZonaComer()){
                            estadisticas.getListaZonaComer().remove(idChar);
                            estadisticas.actualizarZonaComer();
                        }
        
                        insecto.DefenderInsecto(hormigaSoldado);
        
                        synchronized(estadisticas.getBloqueoZonaComer()){
                            estadisticas.getListaZonaComer().add(idChar);
                            estadisticas.actualizarZonaComer();
                        }
                    }
                    else{
                        if (hormigaCria != null){
                            tiempoDormido = tiempoFinal;
                            System.out.println("Hormiga " + id + " se ha interrumpido después de comer " + tiempoDormido + "ms");
                            tiempoDormido = tiempoFinal;
        
                            synchronized(estadisticas.getBloqueoZonaComer()){
                                estadisticas.getListaZonaComer().remove(idChar);
                                estadisticas.actualizarZonaComer();
                            }
                            synchronized(estadisticas.getBloqueoCriasZonaComer()){
                                estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() - 1);
                                System.out.println("Crias en la zona de comer: " + estadisticas.getCriasZonaComer());
                            }
                            synchronized(estadisticas.getBloqueoCriasRefugio()){
                                estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()+ 1);
                                System.out.println("Crias en el refugio: " + estadisticas.getCriasRefugio());
                            }
                            refugio.Refugiarse(hormigaCria);
                            synchronized(estadisticas.getBloqueoCriasRefugio()){
                                estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()- 1);
                                System.out.println("Crias en el refugio: " + estadisticas.getCriasRefugio());
                            }
                            synchronized(estadisticas.getBloqueoCriasZonaComer()){
                                estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() + 1);
                                System.out.println("Crias en la zona de comer: " + estadisticas.getCriasZonaComer());
                            }
                            Comer(null, null, hormigaCria, insecto, tunel);
        
                            synchronized(estadisticas.getBloqueoZonaComer()){
                                estadisticas.getListaZonaComer().add(idChar);
                                estadisticas.actualizarZonaComer();
                            }
                        }
                        else{
                            Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                        try {
                            estadisticas.getBloqueoPausa().wait();
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }
        }
        
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().remove(idChar);
            estadisticas.actualizarZonaComer();
        }
    }
    
    public void DejarComida (HormigaObrera hormigaObrera){
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().add(hormigaObrera.getID());
            estadisticas.actualizarZonaComer();
        }
        
        try {
            Thread.sleep(hormigaObrera.getTiempoDejarComidaZonaComer());
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
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " dejando comida en la zona de comer");
        
        synchronized(estadisticas.getBloqueoComidaZonaComer()){
            estadisticas.setComidaZonaComer(estadisticas.getComidaZonaComer() + 5);
            estadisticas.actualizarComidaZonaComer();
            System.out.println("Comida restante en la zona de comer: " + estadisticas.getComidaZonaComer());
        }
        
        synchronized(bloqueo){
            bloqueo.notify();
        }
        
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().remove(hormigaObrera.getID());
            estadisticas.actualizarZonaComer();
        }
    }
}
