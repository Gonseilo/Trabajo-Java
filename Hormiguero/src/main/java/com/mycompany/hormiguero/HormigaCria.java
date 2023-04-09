/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

/**
 *
 * @author Ivanl
 */
public class HormigaCria extends Hormiga {
    public char[] GenerarIDCria(){
        char[] ID = new char[6];
        int NumID = super.getNumHormiga()/5;
        
        ID[0] = 'H';
        ID[1] = 'C';
        ID[2] = (char) (NumID/1000);
        ID[3] = (char) (NumID/100 - (NumID/1000)*10);
        ID[4] = (char) (NumID/10 - (NumID/100)*10 - (NumID/1000)*100);
        ID[5] = (char) (NumID - (NumID/10)*10 - (NumID/100)*100 - (NumID/1000)*1000);
        
        return ID;
    }
    
    public void SetID(){
        super.setID(GenerarIDCria());
    }
    
    
}
