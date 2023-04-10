/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

/**
 *
 * @author Ivanl
 */
public class HormigaCria extends Hormiga implements Runnable {

    public HormigaCria(int numHormiga, char[] ID, String TipoHormiga) {
        super(numHormiga, ID, TipoHormiga);
    }
    
    public char[] GenerarIDCria(){
        char[] ID = new char[6];
        int NumID = super.getNumHormiga()/5;
        
        ID[0] = 'H';
        ID[1] = 'C';
        ID[2] = Character.forDigit(NumID/1000, 10);
        ID[3] = Character.forDigit(NumID/100 - (NumID/1000)*10, 10);
        ID[4] = Character.forDigit(NumID/10 - (NumID/100)*10 - (NumID/1000)*100, 10);
        ID[5] = Character.forDigit(NumID - (NumID/10)*10 - (NumID/100)*100 - (NumID/1000)*1000, 10);
        
        return ID;
    }
    
    public void SetID(char[] ID){
        super.setID(ID);
    }

    public void run() {
        SetID(GenerarIDCria());
        System.out.println(getID());
    }
}
