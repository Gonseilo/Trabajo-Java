/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.Random;

/**
 *
 * @author Ivanl
 */
public class HormigaSoldado extends Hormiga implements Runnable {
    Random rand = new Random();
    private int tiempoInstruir = 0;
    private int tiempoDescansar = 0;
    private int tiempoComer = 0;
    private int tiempoDefender = 0;

    public HormigaSoldado(int numHormiga, char[] ID, String TipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion, Contador contador) {
        super(numHormiga, ID, TipoHormiga, almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, contador);
        this.tiempoInstruir = rand.nextInt(6001)+2000;
        this.tiempoDescansar = 2000;
        this.tiempoComer = 3000;
        this.tiempoDefender = 20000;
    }
    
    public char[] GenerarIDSoldado(){
        char[] ID = new char[6];
        int NumID = super.getNumHormiga()/5;
        
        ID[0] = 'H';
        ID[1] = 'S';
        ID[2] = Character.forDigit(NumID/1000, 10);
        ID[3] = Character.forDigit(NumID/100 - (NumID/1000)*10, 10);
        ID[4] = Character.forDigit(NumID/10 - (NumID/100)*10, 10);
        ID[5] = Character.forDigit(NumID - (NumID/10)*10, 10);
        
        return ID;
    }
    
    public void SetID(char[] ID){
        super.setID(ID);
    }

    public int getTiempoInstruir() {
        return tiempoInstruir;
    }

    public int getTiempoDescansar() {
        return tiempoDescansar;
    }

    public int getTiempoComer() {
        return tiempoComer;
    }

    public int getTiempoDefender() {
        return tiempoDefender;
    }

    public void setTiempoInstruir(int tiempoInstruir) {
        this.tiempoInstruir = tiempoInstruir;
    }

    public void setTiempoDescansar(int tiempoDescansar) {
        this.tiempoDescansar = tiempoDescansar;
    }

    public void setTiempoComer(int tiempoComer) {
        this.tiempoComer = tiempoComer;
    }

    public void setTiempoDefender(int tiempoDefender) {
        this.tiempoDefender = tiempoDefender;
    }
    
    public void run() {
        SetID(GenerarIDSoldado());
        System.out.println(getID());
        tunel.Entrar(null, this, null);
        while (true){
            for (int i = 0; i < 6; i++){
                zonaInstruccion.Instruir(this);
                zonaDescanso.Descansar(null, this, null);
            }
            
            zonaComer.Comer(null, this, null);
        }
    }
}
