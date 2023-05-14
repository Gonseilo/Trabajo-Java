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
    private Random rand = new Random();
    private Estadisticas estadisticas;
    private Semaphore semaforo;
    private final Object bloqueoAvisoComida = new Object();
    private final Object bloqueoSemaforoAcquire = new Object();
    private final Object bloqueoSemaforoRelease = new Object();

    public AlmacenComida(Semaphore semaforo, Estadisticas estadisticas) {
        this.semaforo = semaforo;
        this.estadisticas = estadisticas;
    }
    
    public void DejarComida(HormigaObrera hormigaObrera){
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = rand.nextInt(hormigaObrera.getTiempoDejarComidaAlmacénMax()-hormigaObrera.getTiempoDejarComidaAlmacénMin()+1)+hormigaObrera.getTiempoDejarComidaAlmacénMin();
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaObrera.gettipoHormiga() + " " + new String(hormigaObrera.getId()) + " quiere entrar a dejar comida al almacén.");
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
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaObrera.gettipoHormiga() + " " + new String(hormigaObrera.getId()) + " está dejando comida en el almacén.");
        synchronized(estadisticas.getBloqueoAlmacen()){
            estadisticas.getListaAlmacen().add(hormigaObrera.getId());
            estadisticas.actualizarAlmacen();
        }
        while(tiempoDormido < tiempoFinal){
            try {
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
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
        }
        synchronized(bloqueoAvisoComida){
            bloqueoAvisoComida.notify();
        }
        synchronized(bloqueoSemaforoRelease){
            semaforo.release();
        }
        synchronized(estadisticas.getBloqueoAlmacen()){
            estadisticas.getListaAlmacen().remove(hormigaObrera.getId());
            estadisticas.actualizarAlmacen();
        }
    }
    
    public void SacarComida(HormigaObrera hormigaObrera){
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = rand.nextInt(hormigaObrera.getTiempoCogerComidaAlmacénMax()-hormigaObrera.getTiempoCogerComidaAlmacénMin()+1)+hormigaObrera.getTiempoCogerComidaAlmacénMin();
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaObrera.gettipoHormiga() + " " + new String(hormigaObrera.getId()) + " quiere entrar a coger comida del almacén.");
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
            estadisticas.getListaAlmacen().add(hormigaObrera.getId());
            estadisticas.actualizarAlmacen();
        }
        while (estadisticas.getComidaAlmacen() < 5) {
            synchronized(bloqueoSemaforoRelease){
                semaforo.release();
            }
            synchronized(estadisticas.getBloqueoAlmacen()){
                estadisticas.getListaAlmacen().remove(hormigaObrera.getId());
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
                estadisticas.getListaAlmacen().add(hormigaObrera.getId());
                estadisticas.actualizarAlmacen();
            }
        }
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaObrera.gettipoHormiga() + " " + new String(hormigaObrera.getId()) + " está cogiendo comida del almacén.");
        synchronized(estadisticas.getBloqueoComidaAlmacen()){
            estadisticas.setComidaAlmacen(estadisticas.getComidaAlmacen() - 5);
            estadisticas.actualizarComidaAlmacen();
        }
        while(tiempoDormido < tiempoFinal){
            try {
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
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
            estadisticas.getListaAlmacen().remove(hormigaObrera.getId());
            estadisticas.actualizarAlmacen();
        }
    }
}
