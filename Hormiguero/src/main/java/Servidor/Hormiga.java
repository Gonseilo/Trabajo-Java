/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class Hormiga {
    private int numHormiga;
    private char[] id;
    private String tipoHormiga;
    protected AlmacenComida almacenComida;
    protected Refugio refugio;
    protected Tunel tunel;
    protected ZonaComer zonaComer;
    protected ZonaDescanso zonaDescanso;
    protected ZonaInstruccion zonaInstruccion;
    protected Estadisticas estadisticas;
    protected Insecto insecto;
    
    public Hormiga(int numHormiga, char[] id, String tipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion, Estadisticas estadisticas, Insecto insecto){
        this.numHormiga = numHormiga;
        this.id = id;
        this.tipoHormiga = tipoHormiga;
        this.almacenComida = almacenComida;
        this.refugio = refugio;
        this.tunel = tunel;
        this.zonaComer = zonaComer;
        this.zonaDescanso = zonaDescanso;
        this.zonaInstruccion = zonaInstruccion;
        this.estadisticas = estadisticas;
        this.insecto = insecto;
    }
    
    //Método utilizado para generar las 10.000 hormigas
    public void generarHormigas(AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion){  Thread[] hilos = new Thread[10000];
        Random rand = new Random();
        int tiempoMinimo = 800;
        int tiempoMaximo = 3500;
        
        //Bucle que por cada iteración crea una hormiga
        for (int i=0; i < 10000; i++){
            int tiempoGeneracionHormigas = rand.nextInt((tiempoMaximo - tiempoMinimo +1)+ tiempoMinimo);
            
            //Cada 5 hormigas asigna las 3 primeras como obreras
            if (i % 5 <= 2){
                Runnable runnable = new HormigaObrera(i, id, "Obrera", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
                estadisticas.getListaHormigas()[i] = new Thread(runnable);
                estadisticas.getListaHormigas()[i].start();
                estadisticas.setNumObreras(estadisticas.getNumObreras()+1);
            }
            else{
                //Cada 5 hormigas asigna la penúltima como soldado
                if (i % 5 == 3){
                    Runnable runnable = new HormigaSoldado(i, id, "Soldado", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
                    estadisticas.getListaHormigas()[i] = new Thread(runnable);
                    estadisticas.getListaHormigas()[i].start();
                    estadisticas.getListaSoldados()[estadisticas.getNumSoldados()] = estadisticas.getListaHormigas()[i];
                    estadisticas.setNumSoldados(estadisticas.getNumSoldados()+1);
                }
                //Cada 5 hormigas asigna la última como cría
                else{
                    Runnable runnable = new HormigaCria(i, id, "Cría", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
                    estadisticas.getListaHormigas()[i] = new Thread(runnable);
                    estadisticas.getListaHormigas()[i].start();
                    estadisticas.getListaCrias()[estadisticas.getNumCrias()] = estadisticas.getListaHormigas()[i];
                    estadisticas.setNumCrias(estadisticas.getNumCrias()+1);
                }
            }
            
            //Cuando se crea la primera soldado activa el botón de generar insecto
            if(i==3){
                estadisticas.activarBotonInsecto();
            }
            
            //Espera el tiempo de generación de hormigas
            try {
                Thread.sleep(tiempoGeneracionHormigas);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Si el programa está pausado sigue esperando hastq que se reanude
            while(!estadisticas.getPlay()){
                try {
                    Thread.sleep(tiempoGeneracionHormigas);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    //Método usado para interrumpir cualquier hormiga generada
    public void interrumpirHilo (int numHormiga){
        estadisticas.getListaHormigas()[numHormiga].interrupt();
    }

    public int getNumHormiga() {
        return numHormiga;
    }

    public void setId(char[] id) {
        this.id = id;
    }

    public char[] getId() {
        return id;
    }

    public void setNumHormiga(int numHormiga) {
        this.numHormiga = numHormiga;
    }

    public void settipoHormiga(String tipoHormiga) {
        this.tipoHormiga = tipoHormiga;
    }

    public String gettipoHormiga() {
        return tipoHormiga;
    }
}
