/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

/**
 *
 * @author Ivanl
 */
public class Hormiga implements Runnable {
    private int numHormiga;
    private char[] ID;
    
    public Hormiga(int numHormiga){
        this.numHormiga = numHormiga;
    }
    
    public void ClasificarHormiga(int numHormiga){
        if (numHormiga % 5 <= 2){
            System.out.println("Obrera " + numHormiga);
        }
        else{
            if (numHormiga % 5 == 3){
                System.out.println("Soldado " + numHormiga);
            }
            else{
                HormigaCria DaIgual = new HormigaCria();
                DaIgual.SetID();
                System.out.println("Cría " + numHormiga + " " + getID());
            }
        }
    }
    
    public void run(){
        ClasificarHormiga(this.numHormiga);
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
}
