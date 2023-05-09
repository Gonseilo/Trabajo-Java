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
            id = new String(hormigaObrera.getID());
            idChar = hormigaObrera.getID();
            tiempoFinal = hormigaObrera.getTiempoComer();
            tipoHormiga = hormigaObrera.getTipoHormiga();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getID());
            idChar = hormigaSoldado.getID();
            tiempoFinal = hormigaSoldado.getTiempoComer();
            tipoHormiga = hormigaSoldado.getTipoHormiga();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getID());
            idChar = hormigaCria.getID();
            tiempoFinal = rand.nextInt(hormigaCria.getTiempoComerMax()-hormigaCria.getTiempoComerMin()+1)+hormigaCria.getTiempoComerMin();
            tipoHormiga = hormigaCria.getTipoHormiga();
        }
        
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().add(idChar);
            estadisticas.actualizarZonaComer();
        }
        
        while (estadisticas.getComidaZonaComer() == 0){
            System.out.println(estadisticas.calcularFecha() + "La hormiga " + tipoHormiga + " " + id + " está esperando a que haya comida en la zona de comer.");
            synchronized(bloqueo){
                try {
                    bloqueo.wait();
                } catch (InterruptedException ex) {
                    if (!estadisticas.getPlay()){
                        synchronized(estadisticas.getBloqueoPausa()){
                            try {
                                estadisticas.getBloqueoPausa().wait();
                            } catch (InterruptedException ex1) {
                                System.out.println("10******************************************");
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
                                    System.out.println("11******************************************");
                                    Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                        else{
                            System.out.println("12******************************************" + id);
                            Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        
        synchronized(estadisticas.getBloqueoComidaZonaComer()){
            estadisticas.setComidaZonaComer(estadisticas.getComidaZonaComer() - 1);
            estadisticas.actualizarComidaZonaComer();
        }
        while(tiempoDormido < tiempoFinal){
            try {
                if (estadisticas.getInterrumpirInsecto() && hormigaCria != null){
                    hormigaCria.detenerHilo(hormigaCria.getNumHormiga());
                }
                System.out.println(estadisticas.calcularFecha() + "La hormiga " + tipoHormiga + " " + id + " empieza a comer.");
                Thread.sleep(tiempoFinal - tiempoDormido);
                tiempoDormido = System.currentTimeMillis() - tiempoInicio;
            } catch (InterruptedException ex) {
                if (!estadisticas.getPlay()){
                    synchronized(estadisticas.getBloqueoPausa()){
                        try {
                            estadisticas.getBloqueoPausa().wait();
                        } catch (InterruptedException ex1) {
                            System.out.println("13******************************************");
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
                                System.out.println("14******************************************");
                                Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    else{
                        System.out.println("15******************************************");
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
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().add(hormigaObrera.getID());
            estadisticas.actualizarZonaComer();
        }
        
        try {
            Thread.sleep(rand.nextInt(hormigaObrera.getTiempoDejarComidaZonaComerMax()-hormigaObrera.getTiempoDejarComidaZonaComerMin()+1)+hormigaObrera.getTiempoDejarComidaZonaComerMin());
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
        }
        System.out.println(estadisticas.calcularFecha() + "La hormiga " + hormigaObrera.getTipoHormiga() + " " + new String(hormigaObrera.getID()) + " ha dejado comida en la zona de comer.");
        
        synchronized(estadisticas.getBloqueoComidaZonaComer()){
            estadisticas.setComidaZonaComer(estadisticas.getComidaZonaComer() + 5);
            estadisticas.actualizarComidaZonaComer();
        }
        
        synchronized(bloqueo){
            bloqueo.notifyAll();
        }
        
        synchronized(estadisticas.getBloqueoZonaComer()){
            estadisticas.getListaZonaComer().remove(hormigaObrera.getID());
            estadisticas.actualizarZonaComer();
        }
    }
}
