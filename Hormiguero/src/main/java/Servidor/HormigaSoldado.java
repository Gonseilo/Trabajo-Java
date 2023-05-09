/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.Random;

/**
 *
 * @author Ivanl
 */
public class HormigaSoldado extends Hormiga implements Runnable {
    Random rand = new Random();
    private int tiempoInstruirMin;
    private int tiempoInstruirMax;
    private int tiempoDescansar;
    private int tiempoComer;
    private int tiempoDefender;

    public HormigaSoldado(int numHormiga, char[] ID, String TipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion, Estadisticas estadisticas, Insecto insecto) {
        super(numHormiga, ID, TipoHormiga, almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
        this.tiempoInstruirMin = 2000;
        this.tiempoInstruirMax = 4000;
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

    public int getTiempoDescansar() {
        return tiempoDescansar;
    }

    public int getTiempoComer() {
        return tiempoComer;
    }

    public int getTiempoDefender() {
        return tiempoDefender;
    }

    public void setTiempoInstruirMin(int tiempoInstruirMin) {
        this.tiempoInstruirMin = tiempoInstruirMin;
    }

    public void setTiempoInstruirMax(int tiempoInstruirMax) {
        this.tiempoInstruirMax = tiempoInstruirMax;
    }

    public int getTiempoInstruirMin() {
        return tiempoInstruirMin;
    }

    public int getTiempoInstruirMax() {
        return tiempoInstruirMax;
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
        tunel.Entrar(null, this, null, insecto);
        while (true){
            for (int i = 0; i < 6; i++){
                zonaInstruccion.Instruir(this, insecto, tunel);
                zonaDescanso.Descansar(null, this, null, insecto, tunel);
            }
            
            zonaComer.Comer(null, this, null, insecto, tunel);
        }
    }
}
