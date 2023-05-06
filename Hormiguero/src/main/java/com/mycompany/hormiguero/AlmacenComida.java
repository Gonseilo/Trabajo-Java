/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

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
    int comida = 0;
    private final Object bloqueo = new Object();
    private Contador contador;
    private Boolean algo = true;

    public AlmacenComida(Semaphore semaforo, Contador contador) {
        this.semaforo = semaforo;
        this.comida = comida;
        this.contador = contador;
    }
    
    public void DejarComida(HormigaObrera hormigaObrera){
        System.out.println("--------------------DEJAR---------------Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a dejar comida al almacén");
        try {
            semaforo.acquire();
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
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("--------------------DEJAR---------------Hormiga " + new String(hormigaObrera.getID()) + " está dejando comida en el almacén");
        try {
            Thread.sleep(hormigaObrera.getTiempoDejarComidaAlmacén());
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
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        synchronized(bloqueo){
            comida= comida + 5;
            System.out.println("--------------------DEJAR---------------Comida en el almacén: " + comida);
            bloqueo.notify();
        }
        
        semaforo.release();
    }
    
    public void SacarComida(HormigaObrera hormigaObrera){
        System.out.println("--------------------SACAR---------------Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a coger comida al almacén");
        try {
            semaforo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //synchronized (bloqueo) {
            while (comida < 5) {
                try {
                    semaforo.release();
                    synchronized(bloqueo){
                    bloqueo.wait();
                    }
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    semaforo.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("--------------------SACAR---------------Hormiga " + new String(hormigaObrera.getID()) + " está cogiendo comida del almacén");
            comida = comida - 5;
            System.out.println("--------------------SACAR---------------Comida en el almacén: " + comida);
            
        //}
        try {
            Thread.sleep(hormigaObrera.getTiempoCogerComidaAlmacén());
        } catch (InterruptedException ex) {
            Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        semaforo.release();
    }
        /*
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a coger comida al almacén");
        try {
            semaforo.acquire();
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
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //synchronized(bloqueo){
        while (comida < 5) {
            System.out.println("No hay comida, hormiga " + new String(hormigaObrera.getID()) + " sale del almacén a esperar");
            semaforo.release();
            synchronized (bloqueo) {
                while (comida < 5) {
                    try {
                        bloqueo.wait();

                    } catch (InterruptedException ex) {
                        if (!contador.getPlay()) {
                            synchronized (contador.getBloqueoPausa()) {
                                try {
                                    contador.getBloqueoPausa().wait();
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            }
                        } else {
                            Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            try {
                semaforo.acquire();
            } catch (InterruptedException ex) {
                if (!contador.getPlay()) {
                    synchronized (contador.getBloqueoPausa()) {
                        try {
                            contador.getBloqueoPausa().wait();
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                } else {
                    Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está cogiendo comida del almacén");
        try {
            Thread.sleep(hormigaObrera.getTiempoCogerComidaAlmacén());
        } catch (InterruptedException ex) {
            if (!contador.getPlay()) {
                synchronized (contador.getBloqueoPausa()) {
                    try {
                        contador.getBloqueoPausa().wait();
                    } catch (InterruptedException ex1) {
                        Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                }
            } else {
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        synchronized(bloqueo){
            while(comida < 5){
                try {
                    bloqueo.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(AlmacenComida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            comida = comida - 5;
            System.out.println("Comida en el almacén: " + comida);
        }
        
        
        semaforo.release();

    }*/
}
