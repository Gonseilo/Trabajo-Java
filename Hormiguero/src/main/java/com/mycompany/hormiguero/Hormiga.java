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
    
    public AlmacenComida almacenComida = new AlmacenComida();
    public Refugio refugio = new Refugio();
    public Tunel tunel = new Tunel();
    public ZonaComer zonaComer = new ZonaComer();
    public ZonaDescanso zonaDescanso = new ZonaDescanso();
    public ZonaInstruccion zonaInstruccion = new ZonaInstruccion();
    
    public Hormiga(int numHormiga, char[] ID, String TipoHormiga){
        this.numHormiga = numHormiga;
        this.ID = ID;
        this.TipoHormiga = TipoHormiga;
    }
    
    public void GenerarHormigas(){
        Thread[] hilos = new Thread[10000];
        Random rand = new Random();
        
        for (int i=0; i < 10000; i++){
            if (i % 5 <= 2){
                Runnable runnable = new HormigaObrera(i, ID, "Obrera");
                hilos[i] = new Thread(runnable);
                hilos[i].start();
                System.out.println("Obrera " + i);
            }
            else{
                if (i % 5 == 3){
                    Runnable runnable = new HormigaSoldado(i, ID, "Soldado");
                    hilos[i] = new Thread(runnable);
                    hilos[i].start();
                    System.out.println("Soldado " + i);
                }
                else{
                    Runnable runnable = new HormigaCria(i, ID, "Cría");
                    hilos[i] = new Thread(runnable);
                    hilos[i].start();
                    System.out.println("Cría " + i);
                }
            }
            try {
                Thread.sleep(rand.nextInt(800, 3500));
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
