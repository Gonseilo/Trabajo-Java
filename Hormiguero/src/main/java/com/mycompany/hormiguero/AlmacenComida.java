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
public class AlmacenComida {
    private Semaphore semaforo;
    int comida = 0;
    private final Object avisoComida = new Object();
    private final Object bloqueo1 = new Object();
    private final Object bloqueo2 = new Object();
    private final Object bloqueo3 = new Object();
    private final Object bloqueo4 = new Object();
    private final Object bloqueo5 = new Object();
    private final Object bloqueo6 = new Object();
    private final Object bloqueo7 = new Object();
    private final Object bloqueo8 = new Object();
    private final Object bloqueo9 = new Object();
    private final Object bloqueo10 = new Object();
    private final Object bloqueo11 = new Object();
    private final Object bloqueo12 = new Object();
    private Contador contador;
    

    public AlmacenComida(Semaphore semaforo, Contador contador) {
        this.semaforo = semaforo;
        this.comida = comida;
        this.contador = contador;
    }
    
    public void DejarComida(HormigaObrera hormigaObrera){
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a dejar comida al almacén");
        while(true){
            try {
                synchronized(bloqueo1){
                    semaforo.acquire();
                }
                break;
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
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está dejando comida en el almacén");
        synchronized(bloqueo2){
            contador.getListaAlmacen().add(hormigaObrera.getID());
            contador.actualizarAlmacen();
        }
        while(true){
            try {
                Thread.sleep(hormigaObrera.getTiempoDejarComidaAlmacén());
                break;
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
        }
        synchronized(bloqueo12){
            comida= comida + 5;
            System.out.println("Comida en el almacén: " + comida);
            
            
        }
        synchronized(avisoComida){
            avisoComida.notify();
        }
       
        synchronized(bloqueo3){
            semaforo.release();
        }
        synchronized(bloqueo4){
            contador.getListaAlmacen().remove(hormigaObrera.getID());
            contador.actualizarAlmacen();
        }
    }
    
    public void SacarComida(HormigaObrera hormigaObrera){
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a coger comida al almacén");
        while(true){
            try {
                synchronized(bloqueo5){
                    semaforo.acquire();
                }
                break;
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
        }
        synchronized(bloqueo6){
            contador.getListaAlmacen().add(hormigaObrera.getID());
            contador.actualizarAlmacen();
        }
        while (comida < 5) {
            synchronized(bloqueo7){
                semaforo.release();
                contador.getListaAlmacen().remove(hormigaObrera.getID());
                contador.actualizarAlmacen();
            }
            
            while(true){
                try {
                    synchronized(avisoComida){
                        avisoComida.wait();
                    }
                    synchronized(bloqueo8){
                        semaforo.acquire();
                    }
                    break;
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
            }
            synchronized(bloqueo9){
                contador.getListaAlmacen().add(hormigaObrera.getID());
                contador.actualizarAlmacen();
            }
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está cogiendo comida del almacén");
        synchronized(bloqueo10){
            comida = comida - 5;
            System.out.println("Comida en el almacén: " + comida);
        }
        while(true){
            try {
                Thread.sleep(hormigaObrera.getTiempoCogerComidaAlmacén());
                break;
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
        }
        synchronized(bloqueo11){
            semaforo.release();
            contador.getListaAlmacen().remove(hormigaObrera.getID());
            contador.actualizarAlmacen();
        }
        
    }
}
