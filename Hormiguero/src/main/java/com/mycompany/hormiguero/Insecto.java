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
    private CyclicBarrier barrera;
    
    public void GenerarInsecto(int numSoldados, Thread[] soldado){
        barrera = new CyclicBarrier(numSoldados);
        
        for (Thread thread : soldado){
            if (thread != null){
                thread.interrupt();
            }
        }
    }
    
    public void DefenderInsecto(HormigaSoldado hormigaSoldado, Tunel tunel){
        System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " saliendo a defender la colonia");
        tunel.Salir(null, hormigaSoldado, this, tunel);
        try {
            barrera.await();
            barrera.reset();
            Thread.sleep(20000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Insecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BrokenBarrierException ex) {
            Logger.getLogger(Insecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " volviendo de defender la colonia");
        tunel.Entrar(null, hormigaSoldado, null, this, tunel);
    }
}
