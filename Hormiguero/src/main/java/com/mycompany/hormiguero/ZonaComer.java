/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class ZonaComer {
    AtomicInteger comida = new AtomicInteger(0);
    private final Object bloqueo = new Object();
    private Refugio refugio;
    private Contador contador;

    public ZonaComer(Refugio refugio, Contador contador) {
        this.refugio = refugio;
        this.contador = contador;
    }
    
    public void Comer (HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto, Tunel tunel){
        String id = null;
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = 0;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getID());
            tiempoFinal = hormigaObrera.getTiempoComer();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getID());
            tiempoFinal = hormigaSoldado.getTiempoComer();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getID());
            tiempoFinal = hormigaCria.getTiempoComer();
        }
        
        while (comida.get() == 0){
            System.out.println("Hormiga " + id + " esperando comida");
            synchronized(bloqueo){
                try {
                    bloqueo.wait();
                } catch (InterruptedException ex) {
                    if (insecto.getInterrumpirInsecto()){
                        if (hormigaSoldado != null){
                            insecto.DefenderInsecto(hormigaSoldado);
                        }
                        else{
                            if (hormigaCria != null){
                                refugio.Refugiarse(hormigaCria);
                                Comer(null, null, hormigaCria, insecto, tunel);
                            }
                            else{
                                Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    if (!contador.getPlay()){
                        synchronized(contador.getBloqueoPausa()){
                            try {
                                contador.getBloqueoPausa().wait();
                            } catch (InterruptedException ex1) {
                                Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                            }
                        }
                    }
                }
            }
        }
        
        comida.addAndGet(-1);
        System.out.println("Comida restante en la zona de comer: " + comida);
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
                        insecto.DefenderInsecto(hormigaSoldado);
                    }
                    else{
                        if (hormigaCria != null){
                            tiempoDormido = tiempoFinal;
                            System.out.println("Hormiga " + id + " se ha interrumpido después de comer " + tiempoDormido + "ms");
                            tiempoDormido = tiempoFinal;
                            refugio.Refugiarse(hormigaCria);
                            Comer(null, null, hormigaCria, insecto, tunel);
                        }
                        else{
                            Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                if (!contador.getPlay()){
                    synchronized(contador.getBloqueoPausa()){
                        try {
                            contador.getBloqueoPausa().wait();
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }
        }
    }
    
    public void DejarComida (HormigaObrera hormigaObrera){
        try {
            Thread.sleep(hormigaObrera.getTiempoDejarComidaZonaComer());
        } catch (InterruptedException ex) {
            if (!contador.getPlay()){
                synchronized(contador.getBloqueoPausa()){
                    try {
                        contador.getBloqueoPausa().wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            }
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " dejando comida en la zona de comer");
        comida.addAndGet(5);
        synchronized(bloqueo){
            bloqueo.notify();
        }
        System.out.println("Comida restante en la zona de comer: " + comida);
    }
}
