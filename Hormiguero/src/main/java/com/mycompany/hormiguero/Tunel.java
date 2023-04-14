/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

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

    public Tunel(Semaphore semaforoTunelEntrada, Semaphore semaforoTunelSalida) {
        this.semaforoTunelEntrada = semaforoTunelEntrada;
        this.semaforoTunelSalida = semaforoTunelSalida;
    }
    
    public void Entrar(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado, HormigaCria hormigaCria){
        if (hormigaObrera != null){
            try {
                semaforoTunelEntrada.acquire();
                System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " entrando al hormiguero");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
            semaforoTunelEntrada.release();
        }
        if (hormigaSoldado != null){
            try {
                semaforoTunelEntrada.acquire();
                System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " entrando al hormiguero");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
            semaforoTunelEntrada.release();
        }
        if (hormigaCria != null){
            try {
                semaforoTunelEntrada.acquire();
                System.out.println("Hormiga " + new String(hormigaCria.getID()) + " entrando al hormiguero");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
            semaforoTunelEntrada.release();
        }
    }
    
    public void Salir(HormigaObrera hormigaObrera, HormigaSoldado hormigaSoldado){
        if (hormigaObrera != null){
            try {
                semaforoTunelSalida.acquire();
                System.out.println("Hormiga " + new String(hormigaObrera.getID()) + " saliendo del hormiguero");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
            semaforoTunelSalida.release();
        }
        else{
            try {
                semaforoTunelSalida.acquire();
                System.out.println("Hormiga " + new String(hormigaSoldado.getID()) + " saliendo del hormiguero");
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tunel.class.getName()).log(Level.SEVERE, null, ex);
            }
            semaforoTunelSalida.release();
        }
    }
}
