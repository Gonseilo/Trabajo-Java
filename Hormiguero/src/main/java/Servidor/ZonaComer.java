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
public class ZonaComer {
    private final Object bloqueo = new Object();
    private Refugio refugio;
    private Estadisticas estadisticas;
    Random rand = new Random();

    public ZonaComer(Refugio refugio, Estadisticas estadisticas) {
        this.refugio = refugio;
        this.estadisticas = estadisticas;
    }
    
    public void Comer (HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto, Tunel tunel){
        String id = null;
        char[] idChar = null;
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = 0;
        String tipoHormiga = null;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getId());
            idChar = hormigaObrera.getId();
            tiempoFinal = hormigaObrera.getTiempoComer();
            tipoHormiga = hormigaObrera.gettipoHormiga();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getId());
            idChar = hormigaSoldado.getId();
            tiempoFinal = hormigaSoldado.getTiempoComer();
            tipoHormiga = hormigaSoldado.gettipoHormiga();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getId());
            idChar = hormigaCria.getId();
            tiempoFinal = rand.nextInt(hormigaCria.getTiempoComerMax()-hormigaCria.getTiempoComerMin()+1)+hormigaCria.getTiempoComerMin();
            tipoHormiga = hormigaCria.gettipoHormiga();
        }
        
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().add(idChar);
            estadisticas.actualizarZonaComer();
        }
        
        while(true){
            while (estadisticas.getComidaZonaComer() <= 0){
                System.out.println(estadisticas.calcularFecha() + "La hormiga " + tipoHormiga + " " + id + " está esperando a que haya comida en la zona de comer.");
                    try {
                        synchronized(bloqueo){
                            bloqueo.wait();
                        }
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
                                    synchronized(estadisticas.getBloqueoZonaComer()){
                                        estadisticas.getListaZonaComer().remove(idChar);
                                        estadisticas.actualizarZonaComer();
                                    }

                                    insecto.DefenderInsecto(hormigaSoldado);

                                    synchronized(estadisticas.getBloqueoZonaComer()){
                                        estadisticas.getListaZonaComer().add(idChar);
                                        estadisticas.actualizarZonaComer();
                                    }
                                }
                                else{
                                    if (hormigaCria != null){
                                        synchronized(estadisticas.getBloqueoZonaComer()){
                                            estadisticas.getListaZonaComer().remove(idChar);
                                            estadisticas.actualizarZonaComer();
                                        }
                                        synchronized(estadisticas.getBloqueoCriasZonaComer()){
                                            estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() - 1);
                                        }
                                        synchronized(estadisticas.getBloqueoCriasRefugio()){
                                            estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()+ 1);
                                        }
                                        refugio.Refugiarse(hormigaCria);
                                        synchronized(estadisticas.getBloqueoCriasRefugio()){
                                            estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()- 1);
                                        }
                                        synchronized(estadisticas.getBloqueoCriasZonaComer()){
                                            estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() + 1);
                                        }
                                        Comer(null, null, hormigaCria, insecto, tunel);

                                        synchronized(estadisticas.getBloqueoZonaComer()){
                                            estadisticas.getListaZonaComer().add(idChar);
                                            estadisticas.actualizarZonaComer();
                                        }
                                    }
                                    else{
                                        Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                            else{
                                Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
            }
            
            synchronized(estadisticas.getBloqueoComidaZonaComer()){
                if(estadisticas.getComidaZonaComer() > 0){
                    estadisticas.setComidaZonaComer(estadisticas.getComidaZonaComer() - 1);
                    estadisticas.actualizarComidaZonaComer();
                    break;
                }
            }
        }
        while(tiempoDormido < tiempoFinal){
            try {
                if (estadisticas.getInterrumpirInsecto() && hormigaCria != null){
                    hormigaCria.interrumpirHilo(hormigaCria.getNumHormiga());
                }
                System.out.println(estadisticas.calcularFecha() + "La hormiga " + tipoHormiga + " " + id + " empieza a comer.");
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
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
                            tiempoDormido = System.currentTimeMillis() - tiempoInicio;

                            synchronized(estadisticas.getBloqueoZonaComer()){
                                estadisticas.getListaZonaComer().remove(idChar);
                                estadisticas.actualizarZonaComer();
                            }

                            insecto.DefenderInsecto(hormigaSoldado);

                            synchronized(estadisticas.getBloqueoZonaComer()){
                                estadisticas.getListaZonaComer().add(idChar);
                                estadisticas.actualizarZonaComer();
                            }
                        }
                        else{
                            if (hormigaCria != null){
                                tiempoDormido = tiempoFinal;

                                synchronized(estadisticas.getBloqueoZonaComer()){
                                    estadisticas.getListaZonaComer().remove(idChar);
                                    estadisticas.actualizarZonaComer();
                                }
                                synchronized(estadisticas.getBloqueoCriasZonaComer()){
                                    estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() - 1);
                                }
                                synchronized(estadisticas.getBloqueoCriasRefugio()){
                                    estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()+ 1);
                                }
                                refugio.Refugiarse(hormigaCria);
                                synchronized(estadisticas.getBloqueoCriasRefugio()){
                                    estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()- 1);
                                }
                                synchronized(estadisticas.getBloqueoCriasZonaComer()){
                                    estadisticas.setCriasZonaComer(estadisticas.getCriasZonaComer() + 1);
                                }
                                Comer(null, null, hormigaCria, insecto, tunel);

                                synchronized(estadisticas.getBloqueoZonaComer()){
                                    estadisticas.getListaZonaComer().add(idChar);
                                    estadisticas.actualizarZonaComer();
                                }
                            }
                            else{
                                Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    else{
                        Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().remove(idChar);
            estadisticas.actualizarZonaComer();
        }
    }
    
    public void DejarComida (HormigaObrera hormigaObrera){
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = rand.nextInt(hormigaObrera.getTiempoDejarComidaZonaComerMax()-hormigaObrera.getTiempoDejarComidaZonaComerMin()+1)+hormigaObrera.getTiempoDejarComidaZonaComerMin();
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().add(hormigaObrera.getId());
            estadisticas.actualizarZonaComer();
        }
        
        while(tiempoDormido < tiempoFinal){
            try {
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                        try {
                            estadisticas.getBloqueoPausa().wait();
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }
        }
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaObrera.gettipoHormiga() + " " + new String(hormigaObrera.getId()) + " ha dejado comida en la zona de comer.");
        
        synchronized(estadisticas.getBloqueoComidaZonaComer()){
            estadisticas.setComidaZonaComer(estadisticas.getComidaZonaComer() + 5);
            estadisticas.actualizarComidaZonaComer();
        }
        
        synchronized(bloqueo){
            bloqueo.notify();
            bloqueo.notify();
            bloqueo.notify();
            bloqueo.notify();
            bloqueo.notify();
        }
        
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().remove(hormigaObrera.getId());
            estadisticas.actualizarZonaComer();
        }
    }
}
