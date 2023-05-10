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
        //Creación del fichero log
        FileOutputStream salida = null;
        try {
            salida = new FileOutputStream("evolucionColonia.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintStream ps = new PrintStream(salida);
        System.setOut(ps);
        
        //Declaración de los semáforos
        Semaphore semaforoAlmacen = new Semaphore(10);//Capacidad del almacén
        Semaphore semaforoTunelEntrada = new Semaphore(1);//Capacidad del túnel de entrada
        Semaphore semaforoTunelSalida = new Semaphore(2);//Capacidad del túnel de salida
        
        //Declaración de los objetos de las clases
        Estadisticas estadisticas = new Estadisticas();
        AlmacenComida almacenComida = new AlmacenComida(semaforoAlmacen, estadisticas);
        Refugio refugio = new Refugio(estadisticas);
        ZonaComer zonaComer = new ZonaComer(refugio, estadisticas);
        ZonaDescanso zonaDescanso = new ZonaDescanso(refugio, estadisticas);
        ZonaInstruccion zonaInstruccion = new ZonaInstruccion(estadisticas);
        Tunel tunel = new Tunel(semaforoTunelEntrada, semaforoTunelSalida, estadisticas, refugio);
        Insecto insecto = new Insecto(refugio, tunel, estadisticas);
        InterfazServidor interfazServidor = new InterfazServidor(refugio, insecto, estadisticas);
        
        estadisticas.setInterfazServidor(interfazServidor);//Objeto interfazServidor paasado como parámetro a la clase estadísticas
        estadisticas.desactivarBotonInsecto();//Desactiva el botón de generar insecto
        interfazServidor.setVisible(true);//Hacer visible la interfaz
        
        //Lanzamiento del hilo del socket del servidor
        Runnable runnable = new SocketServidor(estadisticas, insecto);
        Thread thread = new Thread(runnable);
        thread.start();
        
        //Declara el objeto hormiga
        char[] id = new char[6];
        Hormiga hormiga = new Hormiga(0, id, "", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
        
        //Comienza la generación de hormigas
        hormiga.generarHormigas(almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
    }
}