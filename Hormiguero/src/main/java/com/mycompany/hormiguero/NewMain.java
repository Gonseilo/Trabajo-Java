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
        AlmacenComida almacenComida = new AlmacenComida();
        Refugio refugio = new Refugio();
        Tunel tunel = new Tunel();
        ZonaComer zonaComer = new ZonaComer();
        ZonaDescanso zonaDescanso = new ZonaDescanso();
        ZonaInstruccion zonaInstruccion = new ZonaInstruccion();
        
        char[] ID = new char[6];
        Hormiga hormiga = new Hormiga(0, ID, "", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
        
        hormiga.GenerarHormigas(almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
    }
    
}
