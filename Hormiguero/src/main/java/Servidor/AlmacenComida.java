/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

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
    private final Object bloqueoAvisoComida = new Object();
    private final Object bloqueoSemaforoAcquire = new Object();
    private final Object bloqueoSemaforoRelease = new Object();
    private Estadisticas estadisticas;
    private Random rand = new Random();

    public AlmacenComida(Semaphore semaforo, Estadisticas estadisticas) {
        this.semaforo = semaforo;
        this.estadisticas = estadisticas;
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
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                        try {
                            estadisticas.getBloqueoPausa().wait();
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
        synchronized(estadisticas.getBloqueoAlmacen()){
            estadisticas.getListaAlmacen().add(hormigaObrera.getID());
            estadisticas.actualizarAlmacen();
        }
        while(true){
            try {
                Thread.sleep(rand.nextInt(hormigaObrera.getTiempoDejarComidaAlmacénMax()-hormigaObrera.getTiempoDejarComidaAlmacénMin()+1)+hormigaObrera.getTiempoDejarComidaAlmacénMin());
                break;
            } catch (InterruptedException ex) {
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                        try {
                            estadisticas.getBloqueoPausa().wait();
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
        
        synchronized(estadisticas.getBloqueoComidaAlmacen()){
            estadisticas.setComidaAlmacen(estadisticas.getComidaAlmacen() + 5);
            estadisticas.actualizarComidaAlmacen();
            System.out.println("Comida en el almacén: " + estadisticas.getComidaAlmacen());
        }
        synchronized(bloqueoAvisoComida){
            bloqueoAvisoComida.notify();
        }
        synchronized(bloqueoSemaforoRelease){
            semaforo.release();
        }
        synchronized(estadisticas.getBloqueoAlmacen()){
            estadisticas.getListaAlmacen().remove(hormigaObrera.getID());
            estadisticas.actualizarAlmacen();
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
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                        try {
                            estadisticas.getBloqueoPausa().wait();
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
        synchronized(estadisticas.getBloqueoAlmacen()){
            estadisticas.getListaAlmacen().add(hormigaObrera.getID());
            estadisticas.actualizarAlmacen();
        }
        while (estadisticas.getComidaAlmacen() < 5) {
            synchronized(bloqueoSemaforoRelease){
                semaforo.release();
            }
            synchronized(estadisticas.getBloqueoAlmacen()){
                estadisticas.getListaAlmacen().remove(hormigaObrera.getID());
                estadisticas.actualizarAlmacen();
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
                    if (!estadisticas.getPlay()){
                        synchronized(estadisticas.getBloqueoPausa()){
                            try {
                                estadisticas.getBloqueoPausa().wait();
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
            synchronized(estadisticas.getBloqueoAlmacen()){
                estadisticas.getListaAlmacen().add(hormigaObrera.getID());
                estadisticas.actualizarAlmacen();
            }
        }
        System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " está cogiendo comida del almacén");
        synchronized(estadisticas.getBloqueoComidaAlmacen()){
            estadisticas.setComidaAlmacen(estadisticas.getComidaAlmacen() - 5);
            estadisticas.actualizarComidaAlmacen();
            System.out.println("Comida en el almacén: " + estadisticas.getComidaAlmacen());
        }
        while(true){
            try {
                Thread.sleep(rand.nextInt(hormigaObrera.getTiempoCogerComidaAlmacénMax()-hormigaObrera.getTiempoCogerComidaAlmacénMin()+1)+hormigaObrera.getTiempoCogerComidaAlmacénMin());
                break;
            } catch (InterruptedException ex) {
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                        try {
                            estadisticas.getBloqueoPausa().wait();
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
        synchronized(estadisticas.getBloqueoAlmacen()){
            estadisticas.getListaAlmacen().remove(hormigaObrera.getID());
            estadisticas.actualizarAlmacen();
        }
    }
}
