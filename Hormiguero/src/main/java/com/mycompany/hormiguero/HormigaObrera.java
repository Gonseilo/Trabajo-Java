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
    public HormigaObrera(int numHormiga, char[] ID, String TipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion) {
        super(numHormiga, ID, TipoHormiga, almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
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
    
    public void run() {
        Random rand = new Random();
        
        SetID(GenerarIDObrera());
        System.out.println(getID());
        tunel.Entrar(this, null, null);
        while (true){
            if (super.getNumHormiga()%2 == 0){
                for (int i = 0; i < 10; i++){
                    //almacenComida.SacarComida(this);
                    try {
                        Thread.sleep(rand.nextInt(1000, 3000));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HormigaObrera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            else{
                for (int i = 0; i < 10; i++){
                    tunel.Salir(this, null);
                    System.out.println("Hormiga " + new String(getID()) + " cogiendo comida");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(HormigaObrera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    tunel.Entrar(this, null, null);
                    almacenComida.DejarComida(this);
                }
            }
            zonaComer.Comer(this, null, null);
            zonaDescanso.Descansar(this, null, null);
        }
    }
}
