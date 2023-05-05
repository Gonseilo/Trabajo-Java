/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class Tunel {
    private Semaphore semaforoTunelEntrada;
    private Semaphore semaforoTunelSalida;
    private Contador contador;

    public Tunel(Semaphore semaforoTunelEntrada, Semaphore semaforoTunelSalida, Contador contador) {
        this.semaforoTunelEntrada = semaforoTunelEntrada;
        this.semaforoTunelSalida = semaforoTunelSalida;
        this.contador = contador;
    }
    
    public void Entrar(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto){
        String id = null;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getID());
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getID());
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getID());
        }
        
        try {
            semaforoTunelEntrada.acquire();
            System.out.println("Hormiga " + id + " entrando al hormiguero");
            Thread.sleep(100);
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
            else{
                if (insecto.getInterrumpirInsecto()){
                    insecto.DefenderInsecto(hormigaSoldado);
                }
                else{
                    Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        semaforoTunelEntrada.release();
    }
    
    public void Salir(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, Insecto insecto){
        try {
            semaforoTunelSalida.acquire();
            System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " saliendo del hormiguero");
            Thread.sleep(100);
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
            else{
                if (insecto.getInterrumpirInsecto()){
                    insecto.DefenderInsecto(hormigaSoldado);
                }
                else{
                    Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        semaforoTunelSalida.release();
    }
}
