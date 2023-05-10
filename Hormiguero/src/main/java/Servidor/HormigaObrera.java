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
public class HormigaObrera extends Hormiga implements Runnable {
    Random rand = new Random();
    private int tiempoRecolectarComida;
    private int tiempoDejarComidaAlmacénMin;
    private int tiempoDejarComidaAlmacénMax;
    private int tiempoCogerComidaAlmacénMin;
    private int tiempoCogerComidaAlmacénMax;
    private int tiempoIrZonaComerMin;
    private int tiempoIrZonaComerMax;
    private int tiempoDejarComidaZonaComerMin;
    private int tiempoDejarComidaZonaComerMax;
    private int tiempoComer;
    private int tiempoDescansar;
    
    public HormigaObrera(int numHormiga, char[] ID, String tipoHormiga, AlmacenComida almacenComida, Refugio refugio, Tunel tunel, ZonaComer zonaComer, ZonaDescanso zonaDescanso, ZonaInstruccion zonaInstruccion, Estadisticas estadisticas, Insecto insecto) {
        super(numHormiga, ID, tipoHormiga, almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion, estadisticas, insecto);
        this.tiempoRecolectarComida = 4000;
        this.tiempoDejarComidaAlmacénMin = 2000;
        this.tiempoDejarComidaAlmacénMax = 4000;
        this.tiempoCogerComidaAlmacénMin = 1000;
        this.tiempoCogerComidaAlmacénMax = 2000;
        this.tiempoIrZonaComerMin = 1000;
        this.tiempoIrZonaComerMax = 3000;
        this.tiempoDejarComidaZonaComerMin = 1000;
        this.tiempoDejarComidaZonaComerMax = 2000;
        this.tiempoComer = 3000;
        this.tiempoDescansar = 1000;
    }
    
    public char[] GenerarIDObrera(){
        char[] ID = new char[6];
        int NumID = super.getNumHormiga()/5*3 + super.getNumHormiga()%5;
        
        ID[0] = 'H';
        ID[1] = 'O';
        ID[2] = Character.forDigit(NumID/1000, 10);
        ID[3] = Character.forDigit(NumID/100 - (NumID/1000)*10, 10);
        ID[4] = Character.forDigit(NumID/10 - (NumID/100)*10, 10);
        ID[5] = Character.forDigit(NumID - (NumID/10)*10, 10);
        
        return ID;
    }
    
    public void SetID(char[] ID){
        super.setId(ID);
    }

    public int getTiempoRecolectarComida() {
        return tiempoRecolectarComida;
    }

    public int getTiempoComer() {
        return tiempoComer;
    }

    public int getTiempoDescansar() {
        return tiempoDescansar;
    }

    public void setTiempoRecolectarComida(int tiempoRecolectarComida) {
        this.tiempoRecolectarComida = tiempoRecolectarComida;
    }

    public void setTiempoComer(int tiempoComer) {
        this.tiempoComer = tiempoComer;
    }

    public void setTiempoDescansar(int tiempoDescansar) {
        this.tiempoDescansar = tiempoDescansar;
    }

    public void setTiempoDejarComidaAlmacénMin(int tiempoDejarComidaAlmacénMin) {
        this.tiempoDejarComidaAlmacénMin = tiempoDejarComidaAlmacénMin;
    }

    public void setTiempoDejarComidaAlmacénMax(int tiempoDejarComidaAlmacénMax) {
        this.tiempoDejarComidaAlmacénMax = tiempoDejarComidaAlmacénMax;
    }

    public void setTiempoCogerComidaAlmacénMin(int tiempoCogerComidaAlmacénMin) {
        this.tiempoCogerComidaAlmacénMin = tiempoCogerComidaAlmacénMin;
    }

    public void setTiempoCogerComidaAlmacénMax(int tiempoCogerComidaAlmacénMax) {
        this.tiempoCogerComidaAlmacénMax = tiempoCogerComidaAlmacénMax;
    }

    public void setTiempoIrZonaComerMin(int tiempoIrZonaComerMin) {
        this.tiempoIrZonaComerMin = tiempoIrZonaComerMin;
    }

    public void setTiempoIrZonaComerMax(int tiempoIrZonaComerMax) {
        this.tiempoIrZonaComerMax = tiempoIrZonaComerMax;
    }

    public void setTiempoDejarComidaZonaComerMin(int tiempoDejarComidaZonaComerMin) {
        this.tiempoDejarComidaZonaComerMin = tiempoDejarComidaZonaComerMin;
    }

    public void setTiempoDejarComidaZonaComerMax(int tiempoDejarComidaZonaComerMax) {
        this.tiempoDejarComidaZonaComerMax = tiempoDejarComidaZonaComerMax;
    }

    public int getTiempoDejarComidaAlmacénMin() {
        return tiempoDejarComidaAlmacénMin;
    }

    public int getTiempoDejarComidaAlmacénMax() {
        return tiempoDejarComidaAlmacénMax;
    }

    public int getTiempoCogerComidaAlmacénMin() {
        return tiempoCogerComidaAlmacénMin;
    }

    public int getTiempoCogerComidaAlmacénMax() {
        return tiempoCogerComidaAlmacénMax;
    }

    public int getTiempoIrZonaComerMin() {
        return tiempoIrZonaComerMin;
    }

    public int getTiempoIrZonaComerMax() {
        return tiempoIrZonaComerMax;
    }

    public int getTiempoDejarComidaZonaComerMin() {
        return tiempoDejarComidaZonaComerMin;
    }

    public int getTiempoDejarComidaZonaComerMax() {
        return tiempoDejarComidaZonaComerMax;
    }
    
    public void run() {
        SetID(GenerarIDObrera());
        System.out.println(estadisticas.calcularFecha() + "Se ha generado una hormiga " + super.gettipoHormiga() + " con id " + new String(super.getId()) + ".");
        synchronized(estadisticas.getBloqueoObrerasExterior()){
            estadisticas.setObrerasExterior(estadisticas.getObrerasExterior() + 1);
        }
        tunel.Entrar(this, null, null, insecto);
        
        synchronized(estadisticas.getBloqueoObrerasExterior()){
            estadisticas.setObrerasExterior(estadisticas.getObrerasExterior() - 1);
        }
        synchronized (estadisticas.getBloqueoObrerasInterior()) {
            estadisticas.setObrerasInterior(estadisticas.getObrerasInterior() + 1);
        }
        while (true){
            if (super.getNumHormiga()%2 == 0){
                for (int i = 0; i < 10; i++){
                    long tiempoInicioIrZonaComer = System.currentTimeMillis();
                    long tiempoDormidoIrZonaComer = 0;
                    int tiempoFinalIrZonaComer = rand.nextInt(tiempoIrZonaComerMax-tiempoIrZonaComerMin+1)+tiempoIrZonaComerMin;
                    almacenComida.SacarComida(this);
                    synchronized(estadisticas.getBloqueoLlevandoComida()){
                        estadisticas.getListaLlevandoComida().add(getId());
                        estadisticas.actualizarLlevandoComida();
                    }
                    while(tiempoDormidoIrZonaComer < tiempoFinalIrZonaComer){
                        try {
                            Thread.sleep(tiempoFinalIrZonaComer - tiempoDormidoIrZonaComer);
                            tiempoDormidoIrZonaComer = System.currentTimeMillis() - tiempoInicioIrZonaComer;
                        } catch (InterruptedException ex) {
                            tiempoDormidoIrZonaComer = System.currentTimeMillis() - tiempoInicioIrZonaComer;
                            if (!estadisticas.getPlay()){
                                synchronized(estadisticas.getBloqueoPausa()){
                                    try {
                                        estadisticas.getBloqueoPausa().wait();
                                    } catch (InterruptedException ex1) {
                                        Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                                    }
                                }
                            }
                            else{
                                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    synchronized(estadisticas.getBloqueoLlevandoComida()){
                        estadisticas.getListaLlevandoComida().remove(getId());
                        estadisticas.actualizarLlevandoComida();
                    }
                    zonaComer.DejarComida(this);
                }
            }
            else{
                for (int i = 0; i < 10; i++){
                    long tiempoInicioRecolectarComida = System.currentTimeMillis();
                    long tiempoDormidoRecolectarComida = 0;
                    int tiempoFinalRecolectarComida = tiempoRecolectarComida;
                    tunel.Salir(this, null, insecto);
                    System.out.println(estadisticas.calcularFecha() + "La hormiga " + super.gettipoHormiga() + " " + new String(super.getId()) + " está buscando comida en el exterior del hormiguero.");
                    synchronized(estadisticas.getBloqueoBuscandoComida()){
                        estadisticas.getListaBuscandoComida().add(getId());
                        estadisticas.actualizarBuscandoComida();
                    }
                    synchronized (estadisticas.getBloqueoObrerasInterior()) {
                        estadisticas.setObrerasInterior(estadisticas.getObrerasInterior() - 1);
                    }
                    synchronized(estadisticas.getBloqueoObrerasExterior()){
                        estadisticas.setObrerasExterior(estadisticas.getObrerasExterior() + 1);
                    }
                    
                    while(tiempoDormidoRecolectarComida < tiempoFinalRecolectarComida){
                        try {
                            Thread.sleep(tiempoFinalRecolectarComida - tiempoDormidoRecolectarComida);
                            tiempoDormidoRecolectarComida = System.currentTimeMillis() - tiempoInicioRecolectarComida;
                        } catch (InterruptedException ex) {
                            tiempoDormidoRecolectarComida = System.currentTimeMillis() - tiempoInicioRecolectarComida;
                            if (!estadisticas.getPlay()){
                                synchronized(estadisticas.getBloqueoPausa()){
                                    try {
                                        estadisticas.getBloqueoPausa().wait();
                                    } catch (InterruptedException ex1) {
                                        Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                                    }
                                }
                            }
                            else{
                                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                    }
                    
                    synchronized(estadisticas.getBloqueoBuscandoComida()){
                        estadisticas.getListaBuscandoComida().remove(getId());
                        estadisticas.actualizarBuscandoComida();
                    }
                    tunel.Entrar(this, null, null, insecto);
                    synchronized(estadisticas.getBloqueoObrerasExterior()){
                        estadisticas.setObrerasExterior(estadisticas.getObrerasExterior() - 1);
                    }
                    synchronized (estadisticas.getBloqueoObrerasInterior()) {
                        estadisticas.setObrerasInterior(estadisticas.getObrerasInterior() + 1);
                    }
                    almacenComida.DejarComida(this);
                }
            }
            zonaComer.Comer(this, null, null, insecto, tunel);
            zonaDescanso.Descansar(this, null, null, insecto, tunel);
        }
    }
}
