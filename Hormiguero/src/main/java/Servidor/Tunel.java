/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class Tunel {
    private Semaphore semaforoTunelEntrada;
    private Semaphore semaforoTunelSalida;
    private Estadisticas estadisticas;
    private Refugio refugio;

    public Tunel(Semaphore semaforoTunelEntrada, Semaphore semaforoTunelSalida, Estadisticas estadisticas, Refugio refugio) {
        this.semaforoTunelEntrada = semaforoTunelEntrada;
        this.semaforoTunelSalida = semaforoTunelSalida;
        this.estadisticas = estadisticas;
        this.refugio = refugio;
    }
    
    public void Entrar(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto){
        String id = null;
        String tipoHormiga = null;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getId());
            tipoHormiga = hormigaObrera.gettipoHormiga();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getId());
            tipoHormiga = hormigaSoldado.gettipoHormiga();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getId());
            tipoHormiga = hormigaCria.gettipoHormiga();
        }
        
        try {
            semaforoTunelEntrada.acquire();
            System.out.println(estadisticas.calcularFecha() + "La hormiga " + tipoHormiga + " " + id + " está entrando al hormiguero.");
            Thread.sleep(100);
        } catch (InterruptedException ex) {
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
                if (estadisticas.getInterrumpirInsecto()){
                    if (hormigaSoldado != null){
                        insecto.DefenderInsecto(hormigaSoldado);
                    }
                    else{
                        if (hormigaCria != null){
                            refugio.Refugiarse(hormigaCria);
                        }
                    }
                }
                else{
                    Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        semaforoTunelEntrada.release();
    }
    
    public void Salir(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, Insecto insecto){
        String id = null;
        String tipoHormiga = null;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getId());
            tipoHormiga = hormigaObrera.gettipoHormiga();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getId());
            tipoHormiga = hormigaSoldado.gettipoHormiga();
        }
        
        try {
            semaforoTunelSalida.acquire();
            System.out.println(estadisticas.calcularFecha() + "La hormiga " + tipoHormiga + " " + id + " está saliendo del hormiguero.");
            Thread.sleep(100);
        } catch (InterruptedException ex) {
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
                if (estadisticas.getInterrumpirInsecto()){
                    insecto.DefenderInsecto(hormigaSoldado);
                }
                else{
                    Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        semaforoTunelSalida.release();
    }
}
