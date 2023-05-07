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
    private final Object bloqueo = new Object();
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
                semaforo.acquire();
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
        contador.getListaAlmacen().add(hormigaObrera.getID());
        contador.actualizarAlmacen();
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
        synchronized(bloqueo){
            comida= comida + 5;
            System.out.println("Comida en el almacén: " + comida);
            bloqueo.notify();
        }
        semaforo.release();
        contador.getListaAlmacen().remove(hormigaObrera.getID());
        contador.actualizarAlmacen();
    }
    
    public void SacarComida(HormigaObrera hormigaObrera){
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " quiere entrar a coger comida al almacén");
        while(true){
            try {
                semaforo.acquire();
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
        contador.getListaAlmacen().add(hormigaObrera.getID());
        contador.actualizarAlmacen();
        while (comida < 5) {
            semaforo.release();
            contador.getListaAlmacen().remove(hormigaObrera.getID());
            contador.actualizarAlmacen();
            while(true){
                try {
                    synchronized(bloqueo){
                        bloqueo.wait();
                    }
                    semaforo.acquire();
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
            contador.getListaAlmacen().add(hormigaObrera.getID());
            contador.actualizarAlmacen();
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está cogiendo comida del almacén");
        synchronized(bloqueo){
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
        semaforo.release();
        contador.getListaAlmacen().remove(hormigaObrera.getID());
        contador.actualizarAlmacen();
    }
}
