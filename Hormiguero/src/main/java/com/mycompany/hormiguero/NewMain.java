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
        
        Contador contador = new Contador();
        AlmacenComida almacenComida = new AlmacenComida(semaforo, contador);
        Refugio refugio = new Refugio(contador);
        ZonaComer zonaComer = new ZonaComer(refugio, contador);
        ZonaDescanso zonaDescanso = new ZonaDescanso(refugio, contador);
        ZonaInstruccion zonaInstruccion = new ZonaInstruccion(contador);
        Tunel tunel = new Tunel(semaforoTunelEntrada, semaforoTunelSalida, contador);
        Insecto insecto = new Insecto(refugio, tunel, contador);
        
        Interfaz interfaz = new Interfaz(refugio, insecto, contador);
        interfaz.setVisible(true);
        
        char[] ID = new char[6];
        Hormiga hormiga = new Hormiga(0, ID, "", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, contador, insecto);
        
        hormiga.GenerarHormigas(almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
    }
    
}