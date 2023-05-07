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
public class ZonaInstruccion {
    private Contador contador;

    public ZonaInstruccion(Contador contador) {
        this.contador = contador;
    }
    
    public void Instruir (HormigaSoldado hormigaSoldado, Insecto insecto, Tunel tunel){
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = hormigaSoldado.getTiempoInstruir();
        
        synchronized(contador.getBloqueoInstruyendo()){
            contador.getListaInstruyendo().add(hormigaSoldado.getID());
            contador.actualizarInstruyendo();
        }
        while(tiempoDormido < tiempoFinal){
            try {
                System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " va a instruir durante " + (tiempoFinal - tiempoDormido) + "ms");
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
                System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " se ha interrumpido después de instruir " + tiempoDormido + "ms");
                
                if (insecto.getInterrumpirInsecto()){
                    contador.getListaInstruyendo().remove(hormigaSoldado.getID());
                    contador.actualizarInstruyendo();
                    insecto.DefenderInsecto(hormigaSoldado);
                    contador.getListaInstruyendo().add(hormigaSoldado.getID());
                    contador.actualizarInstruyendo();
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
        synchronized(contador.getBloqueoInstruyendo()){
            contador.getListaInstruyendo().remove(hormigaSoldado.getID());
            contador.actualizarInstruyendo();
        }
    }
}
