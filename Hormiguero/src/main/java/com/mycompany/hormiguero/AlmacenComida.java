/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class AlmacenComida {
    private Semaphore semaforo;
    int comida;

    public AlmacenComida(Semaphore semaforo) {
        this.semaforo = semaforo;
        this.comida = 0;
    }
    
    public void DejarComida(HormigaObrera hormigaObrera){
        Random rand = new Random();
        
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a dejar comida al almacén");
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está dejando comida en el almacén");
        try {
            Thread.sleep(rand.nextInt(1000, 2000));
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
        comida = comida + 5;
        synchronized(semaforo){
            notify();
        }
        System.out.println("Comida en el almacén: " + comida);
        semaforo.release();
    }
    
    public void SacarComida(HormigaObrera hormigaObrera){
        Random rand = new Random();
        
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a coger comida al almacén");
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        while (comida < 5){
            System.out.println("No hay comida, hormiga " + new String(hormigaObrera.getID()) + " sale del almacén a esperar");
            semaforo.release();
            synchronized(semaforo){
                try {
                    wait();
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
            Thread.sleep(rand.nextInt(1000, 2000));
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
        comida = comida - 5;
        System.out.println("Comida en el almacén: " + comida);
        synchronized(semaforo){
            notify();
        }
        semaforo.release();
    }
}
