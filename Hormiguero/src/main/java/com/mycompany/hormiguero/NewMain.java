/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hormiguero;

import java.util.concurrent.Semaphore;

/**
 *
 * @author bonba
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore semaforo = new Semaphore(10);
        Semaphore semaforoTunelEntrada = new Semaphore(1);
        Semaphore semaforoTunelSalida = new Semaphore(2);
        
        AlmacenComida almacenComida = new AlmacenComida(semaforo);
        Refugio refugio = new Refugio();
        Tunel tunel = new Tunel(semaforoTunelEntrada, semaforoTunelSalida);
        ZonaComer zonaComer = new ZonaComer();
        ZonaDescanso zonaDescanso = new ZonaDescanso();
        ZonaInstruccion zonaInstruccion = new ZonaInstruccion();
        
        char[] ID = new char[6];
        Hormiga hormiga = new Hormiga(0, ID, "", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
        
        hormiga.GenerarHormigas(almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
    }
    
}
