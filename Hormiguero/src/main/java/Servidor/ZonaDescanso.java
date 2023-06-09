/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class ZonaDescanso {
    private Refugio refugio;
    private Estadisticas estadisticas;

    public ZonaDescanso(Refugio refugio, Estadisticas estadisticas) {
        this.refugio = refugio;
        this.estadisticas = estadisticas;
    }
    
    public void Descansar(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria, Insecto insecto, Tunel tunel){
        String id = null;
        char[] idChar = null;
        long tiempoInicio = System.currentTimeMillis();
        long tiempoDormido = 0;
        int tiempoFinal = 0;
        String tipoHormiga = null;
        
        if (hormigaObrera != null){
            id = new String(hormigaObrera.getId());
            idChar = hormigaObrera.getId();
            tiempoFinal = hormigaObrera.getTiempoDescansar();
            tipoHormiga = hormigaObrera.gettipoHormiga();
        }
        if (hormigaSoldado != null){
            id = new String(hormigaSoldado.getId());
            idChar = hormigaSoldado.getId();
            tiempoFinal = hormigaSoldado.getTiempoDescansar();
            tipoHormiga = hormigaSoldado.gettipoHormiga();
        }
        if (hormigaCria != null){
            id = new String(hormigaCria.getId());
            idChar = hormigaCria.getId();
            tiempoFinal = hormigaCria.getTiempoDescansar();
            tipoHormiga = hormigaCria.gettipoHormiga();
        }
        
        synchronized(estadisticas.getBloqueoDescansando()){
            estadisticas.getListaDescansando().add(idChar);
            estadisticas.actualizarDescansando();
        }
        
        while(tiempoDormido < tiempoFinal){
            try {
                if (estadisticas.getInterrumpirInsecto() && hormigaCria != null){
                    hormigaCria.interrumpirHilo(hormigaCria.getNumHormiga());
                }
                System.out.println(estadisticas.calcularFecha() + "La hormiga " + tipoHormiga + " " + id + " se pone a descansar.");
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

                            synchronized(estadisticas.getBloqueoDescansando()){
                                estadisticas.getListaDescansando().remove(idChar);
                                estadisticas.actualizarDescansando();
                            }

                            insecto.DefenderInsecto(hormigaSoldado);

                            synchronized(estadisticas.getBloqueoDescansando()){
                                estadisticas.getListaDescansando().add(idChar);
                                estadisticas.actualizarDescansando();
                            }
                        }
                        else{
                            if (hormigaCria != null){
                                tiempoDormido = tiempoFinal;

                                synchronized(estadisticas.getBloqueoDescansando()){
                                    estadisticas.getListaDescansando().remove(idChar);
                                    estadisticas.actualizarDescansando();
                                }
                                synchronized(estadisticas.getBloqueoCriasRefugio()){
                                    estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()+ 1);
                                }
                                refugio.Refugiarse(hormigaCria);
                                synchronized(estadisticas.getBloqueoCriasRefugio()){
                                    estadisticas.setCriasRefugio(estadisticas.getCriasRefugio()- 1);
                                }

                                synchronized(estadisticas.getBloqueoDescansando()){
                                    estadisticas.getListaDescansando().add(idChar);
                                    estadisticas.actualizarDescansando();
                                }
                            }
                            else{
                                Logger.getLogger(ZonaDescanso.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    else{
                        Logger.getLogger(ZonaInstruccion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        
        synchronized(estadisticas.getBloqueoDescansando()){
            estadisticas.getListaDescansando().remove(idChar);
            estadisticas.actualizarDescansando();
        }
    }
}
