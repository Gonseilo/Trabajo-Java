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
    private Contador contador;

    public Insecto(Refugio refugio, Tunel tunel, Contador contador) {
        this.contador = contador;
        this.refugio = refugio;
        this.tunel = tunel;
    }
    
    public void GenerarInsecto(){
        this.barrera = new CyclicBarrier(contador.getNumSoldados());
        
        for (Thread thread : contador.getListaSoldados()){
            if (thread != null){
                thread.interrupt();
            }
        }
        
        for (Thread thread : contador.getListaCrias()){
            if (thread != null){
                thread.interrupt();
            }
        }
    }
    
    public void DefenderInsecto(HormigaSoldado hormigaSoldado){
        System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " saliendo a defender la colonia");
        tunel.Salir(null, hormigaSoldado, this);
        try {
            this.barrera.await();
            System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " comienza a defender la colonia");
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Insecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(Insecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        refugio.setAtaque(false);
        synchronized(refugio.getBloqueo()){
            refugio.getBloqueo().notifyAll();
        }
        System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " volviendo de defender la colonia");
        tunel.Entrar(null, hormigaSoldado, null, this);
    }
}
