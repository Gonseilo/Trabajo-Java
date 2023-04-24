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
public class HormigaCria extends Hormiga implements Runnable {
    Random rand = new Random();
    private int TiempoComer;
    private int TiempoDescansar;

    public HormigaCria(int numHormiga, char[] ID, String TipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion, Contador contador) {
        super(numHormiga, ID, TipoHormiga, almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, contador);
        this.TiempoComer = rand.nextInt(2001)+3000;
        this.TiempoDescansar = 4000;
    }
    
    public char[] GenerarIDCria(){
        char[] ID = new char[6];
        int NumID = super.getNumHormiga()/5;
        
        ID[0] = 'H';
        ID[1] = 'C';
        ID[2] = Character.forDigit(NumID/1000, 10);
        ID[3] = Character.forDigit(NumID/100 - (NumID/1000)*10, 10);
        ID[4] = Character.forDigit(NumID/10 - (NumID/100)*10, 10);
        ID[5] = Character.forDigit(NumID - (NumID/10)*10, 10);
        
        return ID;
    }
    
    public void SetID(char[] ID){
        super.setID(ID);
    }

    public int getTiempoComer() {
        return TiempoComer;
    }

    public int getTiempoDescansar() {
        return TiempoDescansar;
    }

    public void setTiempoComer(int TiempoComer) {
        this.TiempoComer = TiempoComer;
    }

    public void setTiempoDescansar(int TiempoDescansar) {
        this.TiempoDescansar = TiempoDescansar;
    }

    public void run() {
        SetID(GenerarIDCria());
        System.out.println(getID());
        tunel.Entrar(null, null, this);
        while (true){
            zonaComer.Comer(null, null, this);
            zonaDescanso.Descansar(null, null, this);
        }
    }
}
