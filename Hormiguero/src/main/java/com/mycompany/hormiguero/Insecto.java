/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author Ivanl
 */
public class Insecto {
    public void GenerarInsecto(int numSoldados, Thread[] soldado){
        CyclicBarrier barrera = new CyclicBarrier(numSoldados);
        
        for (Thread thread : soldado){
            thread.interrupt();
        }
    }
    
    public void DefenderInsecto(HormigaSoldado hormigaSoldado){
        
    }
}
