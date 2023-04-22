/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class Hormiga {
    private int numHormiga;
    private char[] ID;
    private String TipoHormiga;
    private Thread[] soldado = new Thread[2000];
    
    protected AlmacenComida almacenComida;
    protected Refugio refugio;
    protected Tunel tunel;
    protected ZonaComer zonaComer;
    protected ZonaDescanso zonaDescanso;
    protected ZonaInstruccion zonaInstruccion;
    
    public Hormiga(int numHormiga, char[] ID, String TipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion){
        this.numHormiga = numHormiga;
        this.ID = ID;
        this.TipoHormiga = TipoHormiga;
        this.almacenComida = almacenComida;
        this.refugio = refugio;
        this.tunel = tunel;
        this.zonaComer = zonaComer;
        this.zonaDescanso = zonaDescanso;
        this.zonaInstruccion = zonaInstruccion;
    }
    
    public void GenerarHormigas(AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion){
        Thread[] hilos = new Thread[10000];
        Random rand = new Random();
        
        int tiempoMinimo = 800;
        int tiempoMaximo = 3500;
        int tiempoGeneracionHormigas = rand.nextInt((tiempoMaximo - tiempoMinimo +1)+ tiempoMinimo);
        
        for (int i=0; i < 10000; i++){
            if (i % 5 <= 2){
                Runnable runnable = new HormigaObrera(i, ID, "Obrera", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
                hilos[i] = new Thread(runnable);
                hilos[i].start();
                System.out.println("Obrera " + i);
            }
            else{
                if (i % 5 == 3){
                    Runnable runnable = new HormigaSoldado(i, ID, "Soldado", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
                    hilos[i] = new Thread(runnable);
                    hilos[i].start();
                    soldado[(i+2)/5] = hilos[i];
                    System.out.println("Soldado " + i);
                }
                else{
                    Runnable runnable = new HormigaCria(i, ID, "Cría", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
                    hilos[i] = new Thread(runnable);
                    hilos[i].start();
                    System.out.println("Cría " + i);
                }
            }
            try {
                Thread.sleep(tiempoGeneracionHormigas);
            } catch (InterruptedException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (int i=0; i < 10000; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void interrumpirSoldados (){
        for (Thread thread : soldado){
            thread.interrupt();
        }
    }

    public int getNumHormiga() {
        return numHormiga;
    }

    public void setID(char[] ID) {
        this.ID = ID;
    }

    public char[] getID() {
        return ID;
    }

    public void setNumHormiga(int numHormiga) {
        this.numHormiga = numHormiga;
    }

    public void setTipoHormiga(String TipoHormiga) {
        this.TipoHormiga = TipoHormiga;
    }

    public String getTipoHormiga() {
        return TipoHormiga;
    }

    public Thread[] getSoldado() {
        return soldado;
    }

    public void setSoldado(Thread[] soldado) {
        this.soldado = soldado;
    }
}
