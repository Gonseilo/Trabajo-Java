/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hormiguero;

import java.util.Random;
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
        ZonaComer zonaComer = new ZonaComer(refugio);
        ZonaDescanso zonaDescanso = new ZonaDescanso(refugio);
        ZonaInstruccion zonaInstruccion = new ZonaInstruccion();
        Contador contador = new Contador();
        Tunel tunel = new Tunel(semaforoTunelEntrada, semaforoTunelSalida);
        Insecto insecto = new Insecto(refugio, tunel, contador);
        
        Interfaz interfaz = new Interfaz(refugio, insecto, contador);
        interfaz.setVisible(true);
        
        char[] ID = new char[6];
        Hormiga hormiga = new Hormiga(0, ID, "", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, contador, insecto);
        
        hormiga.GenerarHormigas(almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
    }
    
}