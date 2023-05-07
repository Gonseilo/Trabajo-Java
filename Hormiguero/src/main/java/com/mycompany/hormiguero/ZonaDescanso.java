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
    private Refugio refugio;
    private Contador contador;

    public ZonaDescanso(Refugio refugio, Contador contador) {
        this.refugio = refugio;
        this.contador = contador;
    }
    
    public void Descansar(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto, Tunel tunel){
        String id = null;
        char[] idChar = null;
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = 0;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getID());
            idChar = hormigaObrera.getID();
            tiempoFinal = hormigaObrera.getTiempoDescansar();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getID());
            idChar = hormigaSoldado.getID();
            tiempoFinal = hormigaSoldado.getTiempoDescansar();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getID());
            idChar = hormigaCria.getID();
            tiempoFinal = hormigaCria.getTiempoDescansar();
        }
        
        synchronized(contador.getBloqueoDescansando()){
            contador.getListaDescansando().add(idChar);
            contador.actualizarDescansando();
        }
        
        while(tiempoDormido < tiempoFinal){
            try {
                if (insecto.getInterrumpirInsecto() && hormigaCria != null){
                    hormigaCria.detenerHilo(hormigaCria.getNumHormiga());
                }
                System.out.println("Hormiga " + id + " va a descansar durante " + (tiempoFinal - tiempoDormido) + "ms");
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                if (insecto.getInterrumpirInsecto()){
                    if (hormigaSoldado != null){
                        tiempoDormido = System.currentTimeMillis() - tiempoInicio;
                        System.out.println("Hormiga " + id + " se ha interrumpido después de descansar " + tiempoDormido + "ms");
        
                        synchronized(contador.getBloqueoDescansando()){
                            contador.getListaDescansando().remove(idChar);
                            contador.actualizarDescansando();
                        }
        
                        insecto.DefenderInsecto(hormigaSoldado);
        
                        synchronized(contador.getBloqueoDescansando()){
                            contador.getListaDescansando().add(idChar);
                            contador.actualizarDescansando();
                        }
                    }
                    else{
                        if (hormigaCria != null){
                            tiempoDormido = tiempoFinal;
                            System.out.println("Hormiga " + id + " se ha interrumpido después de descansar " + tiempoDormido + "ms");
        
                            synchronized(contador.getBloqueoDescansando()){
                                contador.getListaDescansando().remove(idChar);
                                contador.actualizarDescansando();
                            }
                            
                            refugio.Refugiarse(hormigaCria);
        
                            synchronized(contador.getBloqueoDescansando()){
                                contador.getListaDescansando().add(idChar);
                                contador.actualizarDescansando();
                            }
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
        
        synchronized(contador.getBloqueoDescansando()){
            contador.getListaDescansando().remove(idChar);
            contador.actualizarDescansando();
        }
    }
}
