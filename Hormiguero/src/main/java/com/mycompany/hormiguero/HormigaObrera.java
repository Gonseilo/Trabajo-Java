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
public class HormigaObrera extends Hormiga implements Runnable {
    Random rand = new Random();
    private int tiempoRecolectarComida = 0;
    private int tiempoDejarComidaAlmacén = 0;
    private int tiempoCogerComidaAlmacén = 0;
    private int tiempoIrZonaComer = 0;
    private int tiempoDejarComidaZonaComer = 0;
    private int tiempoComer = 0;
    private int tiempoDescansar = 0;
    
    public HormigaObrera(int numHormiga, char[] ID, String TipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion, Contador contador, Insecto insecto) {
        super(numHormiga, ID, TipoHormiga, almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, contador, insecto);
        this.tiempoRecolectarComida = 4000;
        this.tiempoDejarComidaAlmacén = rand.nextInt(2001)+2000;
        this.tiempoCogerComidaAlmacén = rand.nextInt(1001)+1000;
        this.tiempoIrZonaComer = rand.nextInt(2001)+1000;
        this.tiempoDejarComidaZonaComer = rand.nextInt(1001)+1000;
        this.tiempoComer = 3000;
        this.tiempoDescansar = 1000;
    }
    
    public char[] GenerarIDObrera(){
        char[] ID = new char[6];
        int NumID = super.getNumHormiga()/5*3 + super.getNumHormiga()%5;
        
        ID[0] = 'H';
        ID[1] = 'O';
        ID[2] = Character.forDigit(NumID/1000, 10);
        ID[3] = Character.forDigit(NumID/100 - (NumID/1000)*10, 10);
        ID[4] = Character.forDigit(NumID/10 - (NumID/100)*10, 10);
        ID[5] = Character.forDigit(NumID - (NumID/10)*10, 10);
        
        return ID;
    }
    
    public void SetID(char[] ID){
        super.setID(ID);
    }

    public int getTiempoRecolectarComida() {
        return tiempoRecolectarComida;
    }

    public int getTiempoDejarComidaAlmacén() {
        return tiempoDejarComidaAlmacén;
    }

    public int getTiempoCogerComidaAlmacén() {
        return tiempoCogerComidaAlmacén;
    }

    public int getTiempoIrZonaComer() {
        return tiempoIrZonaComer;
    }

    public int getTiempoDejarComidaZonaComer() {
        return tiempoDejarComidaZonaComer;
    }

    public int getTiempoComer() {
        return tiempoComer;
    }

    public int getTiempoDescansar() {
        return tiempoDescansar;
    }

    public void setTiempoRecolectarComida(int tiempoRecolectarComida) {
        this.tiempoRecolectarComida = tiempoRecolectarComida;
    }

    public void setTiempoDejarComidaAlmacén(int tiempoDejarComidaAlmacén) {
        this.tiempoDejarComidaAlmacén = tiempoDejarComidaAlmacén;
    }

    public void setTiempoCogerComidaAlmacén(int tiempoCogerComidaAlmacén) {
        this.tiempoCogerComidaAlmacén = tiempoCogerComidaAlmacén;
    }

    public void setTiempoIrZonaComer(int tiempoIrZonaComer) {
        this.tiempoIrZonaComer = tiempoIrZonaComer;
    }

    public void setTiempoDejarComidaZonaComer(int tiempoDejarComidaZonaComer) {
        this.tiempoDejarComidaZonaComer = tiempoDejarComidaZonaComer;
    }

    public void setTiempoComer(int tiempoComer) {
        this.tiempoComer = tiempoComer;
    }

    public void setTiempoDescansar(int tiempoDescansar) {
        this.tiempoDescansar = tiempoDescansar;
    }
    
    public void run() {
        SetID(GenerarIDObrera());
        System.out.println(getID());
        tunel.Entrar(this, null, null, insecto);
        while (true){
            if (super.getNumHormiga()%2 == 0){
                for (int i = 0; i < 10; i++){
                    almacenComida.SacarComida(this);
                    try {
                        Thread.sleep(tiempoIrZonaComer);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HormigaObrera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    zonaComer.DejarComida(this);
                }
            }
            else{
                for (int i = 0; i < 10; i++){
                    tunel.Salir(this, null, insecto);
                    System.out.println("Hormiga " + new String(getID()) + " cogiendo comida");
                    try {
                        Thread.sleep(tiempoRecolectarComida);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HormigaObrera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    tunel.Entrar(this, null, null, insecto);
                    almacenComida.DejarComida(this);
                }
            }
            zonaComer.Comer(this, null, null, insecto, tunel);
            zonaDescanso.Descansar(this, null, null, insecto, tunel);
        }
    }
}
