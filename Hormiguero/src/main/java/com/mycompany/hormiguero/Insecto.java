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
    public void GenerarInsecto(int numHormigas){
        CyclicBarrier barrera = new CyclicBarrier((numHormigas+2)/5);
    }
    
    public void DefenderInsecto(HormigaSoldado hormigaSoldado){
        
    }
}
