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
    private int TiempoComerMin;
    private int TiempoComerMax;
    private int TiempoDescansar;

    public HormigaCria(int numHormiga, char[] ID, String TipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion, Estadisticas estadisticas, Insecto insecto) {
        super(numHormiga, ID, TipoHormiga, almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
        this.TiempoComerMin = 3000;
        this.TiempoComerMax = 5000;
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

    public int getTiempoComerMin() {
        return TiempoComerMin;
    }

    public int getTiempoComerMax() {
        return TiempoComerMax;
    }

    public int getTiempoDescansar() {
        return TiempoDescansar;
    }

    public void setTiempoComerMin(int TiempoComerMin) {
        this.TiempoComerMin = TiempoComerMin;
    }

    public void setTiempoComerMax(int TiempoComerMax) {
        this.TiempoComerMax = TiempoComerMax;
    }

    public void setTiempoDescansar(int TiempoDescansar) {
        this.TiempoDescansar = TiempoDescansar;
    }

    public void run() {
        SetID(GenerarIDCria());
        System.out.println(getID());
        
        tunel.Entrar(null, null, this, insecto);
        if (estadisticas.getInterrumpirInsecto()){
            synchronized(estadisticas.getBloqueoCriasRefugio()){
                estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()+ 1);
                System.out.println("Crias en el refugio: " + estadisticas.getCriasRefugio());
            }
            refugio.Refugiarse(this);
            synchronized(estadisticas.getBloqueoCriasRefugio()){
                estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()- 1);
                System.out.println("Crias en el refugio: " + estadisticas.getCriasRefugio());
            }
        }
        while (true){
            synchronized(estadisticas.getBloqueoCriasZonaComer()){
                estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() + 1);
                System.out.println("Crias en la zona de comer: " + estadisticas.getCriasZonaComer());
            }
            
            zonaComer.Comer(null, null, this, insecto, tunel);
            synchronized(estadisticas.getBloqueoCriasZonaComer()){
                estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() - 1);
                System.out.println("Crias en la zona de comer: " + estadisticas.getCriasZonaComer());
            }
            zonaDescanso.Descansar(null, null, this, insecto, tunel);
        }
    }
}
