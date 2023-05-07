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
    private final Object bloqueoAvisoComida = new Object();
    private final Object bloqueoSemaforoAcquire = new Object();
    private final Object bloqueoSemaforoRelease = new Object();
    private Contador contador;
    

    public AlmacenComida(Semaphore semaforo, Contador contador) {
        this.semaforo = semaforo;
        this.contador = contador;
    }
    
    public void DejarComida(HormigaObrera hormigaObrera){
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a dejar comida al almacén");
        while(true){
            try {
                synchronized(bloqueoSemaforoAcquire){
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
        synchronized(contador.getBloqueoAlmacen()){
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
        
        synchronized(contador.getBloqueoComidaAlmacen()){
            contador.setComidaAlmacen(contador.getComidaAlmacen() + 5);
            contador.actualizarComidaAlmacen();
            System.out.println("Comida en el almacén: " + contador.getComidaAlmacen());
        }
        
        synchronized(bloqueoAvisoComida){
            bloqueoAvisoComida.notify();
        }
       
        synchronized(bloqueoSemaforoRelease){
            semaforo.release();
        }
        
        synchronized(contador.getBloqueoAlmacen()){
            contador.getListaAlmacen().remove(hormigaObrera.getID());
            contador.actualizarAlmacen();
        }
    }
    
    public void SacarComida(HormigaObrera hormigaObrera){
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a coger comida al almacén");
        while(true){
            try {
                synchronized(bloqueoSemaforoAcquire){
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
        synchronized(contador.getBloqueoAlmacen()){
            contador.getListaAlmacen().add(hormigaObrera.getID());
            contador.actualizarAlmacen();
        }
        while (contador.getComidaAlmacen() < 5) {
            synchronized(bloqueoSemaforoRelease){
                semaforo.release();
            }
            synchronized(contador.getBloqueoAlmacen()){
                contador.getListaAlmacen().remove(hormigaObrera.getID());
                contador.actualizarAlmacen();
            }
            
            while(true){
                try {
                    synchronized(bloqueoAvisoComida){
                        bloqueoAvisoComida.wait();
                    }
                    synchronized(bloqueoSemaforoAcquire){
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
            synchronized(contador.getBloqueoAlmacen()){
                contador.getListaAlmacen().add(hormigaObrera.getID());
                contador.actualizarAlmacen();
            }
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está cogiendo comida del almacén");
        synchronized(contador.getBloqueoComidaAlmacen()){
            contador.setComidaAlmacen(contador.getComidaAlmacen() - 5);
            contador.actualizarComidaAlmacen();
            System.out.println("Comida en el almacén: " + contador.getComidaAlmacen());
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
        synchronized(bloqueoSemaforoRelease){
            semaforo.release();
        }
        synchronized(contador.getBloqueoAlmacen()){
            contador.getListaAlmacen().remove(hormigaObrera.getID());
            contador.actualizarAlmacen();
        }
        
    }
}
