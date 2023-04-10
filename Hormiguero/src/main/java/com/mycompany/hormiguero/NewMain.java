/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hormiguero;

/**
 *
 * @author bonba
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char[] ID = new char[6];
        Hormiga hormiga = new Hormiga(0, ID, "");
        
        hormiga.GenerarHormigas();
    }
    
}
