/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class AlmacenComida {
    private Semaphore semaforo;
    AtomicInteger comida = new AtomicInteger(0);
    private final Object bloqueo = new Object();

    public AlmacenComida(Semaphore semaforo) {
        this.semaforo = semaforo;
        this.comida = comida;
    }
    
    public void DejarComida(HormigaObrera hormigaObrera){
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a dejar comida al almacén");
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está dejando comida en el almacén");
        try {
            Thread.sleep(hormigaObrera.getTiempoDejarComidaAlmacén());
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
        comida.addAndGet(5);
        synchronized(bloqueo){
            bloqueo.notify();
            System.out.println("Comida en el almacén: " + comida);
        }
        semaforo.release();
    }
    
    public void SacarComida(HormigaObrera hormigaObrera){
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a coger comida al almacén");
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized(bloqueo){
            while (comida.get() < 5){
                System.out.println("No hay comida, hormiga " + new String(hormigaObrera.getID()) + " sale del almacén a esperar");
                semaforo.release();
                synchronized(bloqueo){
                    try {
                        bloqueo.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    semaforo.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está cogiendo comida del almacén");
            try {
                Thread.sleep(hormigaObrera.getTiempoCogerComidaAlmacén());
            } catch (InterruptedException ex) {
                Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
            }
            comida.addAndGet(-5);
            System.out.println("Comida en el almacén: " + comida);
        }
        semaforo.release();
    }
}
