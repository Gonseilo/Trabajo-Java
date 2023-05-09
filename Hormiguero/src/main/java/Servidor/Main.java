/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bonba
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("evolucionColonia.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        
        Semaphore semaforo = new Semaphore(10);
        Semaphore semaforoTunelEntrada = new Semaphore(1);
        Semaphore semaforoTunelSalida = new Semaphore(2);
        
        Estadisticas estadisticas = new Estadisticas();
        AlmacenComida almacenComida = new AlmacenComida(semaforo, estadisticas);
        Refugio refugio = new Refugio(estadisticas);
        ZonaComer zonaComer = new ZonaComer(refugio, estadisticas);
        ZonaDescanso zonaDescanso = new ZonaDescanso(refugio, estadisticas);
        ZonaInstruccion zonaInstruccion = new ZonaInstruccion(estadisticas);
        Tunel tunel = new Tunel(semaforoTunelEntrada, semaforoTunelSalida, estadisticas, refugio);
        Insecto insecto = new Insecto(refugio, tunel, estadisticas);
        
       
        InterfazServidor interfazServidor = new InterfazServidor(refugio, insecto, estadisticas);
        estadisticas.setInterfazServidor(interfazServidor);
        estadisticas.desactivarBotonInsecto();
        interfazServidor.setVisible(true);
        
        Runnable runnable = new SocketServidor(estadisticas, insecto);
        Thread thread = new Thread(runnable);
        thread.start();
        
        char[] ID = new char[6];
        Hormiga hormiga = new Hormiga(0, ID, "", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
        
        hormiga.GenerarHormigas(almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
    }
}