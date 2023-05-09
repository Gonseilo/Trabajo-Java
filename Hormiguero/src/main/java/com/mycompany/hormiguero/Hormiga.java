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
    
    protected AlmacenComida almacenComida;
    protected Refugio refugio;
    protected Tunel tunel;
    protected ZonaComer zonaComer;
    protected ZonaDescanso zonaDescanso;
    protected ZonaInstruccion zonaInstruccion;
    protected Estadisticas estadisticas;
    protected Insecto insecto;
    
    Thread[] hilos = new Thread[10000];
    
    public Hormiga(int numHormiga, char[] ID, String TipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion, Estadisticas estadisticas, Insecto insecto){
        this.numHormiga = numHormiga;
        this.ID = ID;
        this.TipoHormiga = TipoHormiga;
        this.almacenComida = almacenComida;
        this.refugio = refugio;
        this.tunel = tunel;
        this.zonaComer = zonaComer;
        this.zonaDescanso = zonaDescanso;
        this.zonaInstruccion = zonaInstruccion;
        this.estadisticas = estadisticas;
        this.insecto = insecto;
    }
    
    public void GenerarHormigas(AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion){  Thread[] hilos = new Thread[10000];
        Random rand = new Random();
        
        int tiempoMinimo = 80;
        int tiempoMaximo = 350;
        
        for (int i=0; i < 10000; i++){
            int tiempoGeneracionHormigas = rand.nextInt((tiempoMaximo - tiempoMinimo +1)+ tiempoMinimo);
            estadisticas.setNumHormigas(i+1);
            
            if (i % 5 <= 2){
                Runnable runnable = new HormigaObrera(i, ID, "Obrera", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
                hilos[i] = new Thread(runnable);
                hilos[i].start();
                estadisticas.setNumObreras(estadisticas.getNumObreras()+1);
                System.out.println("Obrera " + i);
            }
            else{
                if (i % 5 == 3){
                    Runnable runnable = new HormigaSoldado(i, ID, "Soldado", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
                    hilos[i] = new Thread(runnable);
                    hilos[i].start();
                    estadisticas.getListaSoldados()[estadisticas.getNumSoldados()] = hilos[i];
                    estadisticas.setNumSoldados(estadisticas.getNumSoldados()+1);
                    System.out.println("Soldado " + i);
                }
                else{
                    Runnable runnable = new HormigaCria(i, ID, "Cría", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
                    hilos[i] = new Thread(runnable);
                    hilos[i].start();
                    estadisticas.getListaCrias()[estadisticas.getNumCrias()] = hilos[i];
                    estadisticas.setNumCrias(estadisticas.getNumCrias()+1);
                    System.out.println("Cría " + i);
                }
            }
            estadisticas.setListaHormigas(hilos);
            try {
                Thread.sleep(tiempoGeneracionHormigas);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            while(!estadisticas.getPlay()){
                try {
                    Thread.sleep(tiempoGeneracionHormigas);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        for (int i=0; i < 10000; i++){
            try {
                hilos[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void detenerHilo (int numHormiga){
        hilos[numHormiga].interrupt();
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
}
