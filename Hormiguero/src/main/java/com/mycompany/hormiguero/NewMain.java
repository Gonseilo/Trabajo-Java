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
        
        AlmacenComida almacenComida = new AlmacenComida(semaforo);
        Refugio refugio = new Refugio();
        Tunel tunel = new Tunel(semaforoTunelEntrada, semaforoTunelSalida);
        ZonaComer zonaComer = new ZonaComer();
        ZonaDescanso zonaDescanso = new ZonaDescanso();
        ZonaInstruccion zonaInstruccion = new ZonaInstruccion();
        
        char[] ID = new char[6];
        Hormiga hormiga = new Hormiga(0, ID, "", almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
        
        hormiga.GenerarHormigas(almacenComida, refugio, tunel, zonaComer, zonaDescanso, zonaInstruccion);
    }
    
}

/*
Estoy poniendo todo lo refernte al cyclicbarrier en la clase insecto, tengo ya hecho un metodo que crearia el cyclic barrier
con la cantidad de soldados existentes, a este habria que llamarle desde el main supongo, y las soldado tendrian que acceder
a ese barrier.

Para hacer que las hormigas dejen de hacer lo que estén haciendo hay que hacerles un interrupt, y lo que quieres que hagan,
que en este caso será hacer que entren a otro método que estará en insecto y que será el que haga la defensa de la colmena,
habrá que ponerlo en el except de los tryexcept, te dejo el ejemplo que le he pedido al chatgpt para que lo entiendas:

        class MyThread extends Thread {
            private volatile int variable = 0;

            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(2000); // Espera 2 segundos
                        variable++;
                    } catch (InterruptedException e) {
                        System.out.println("Hilo interrumpido");
                        try {
                            Thread.sleep(5000); // Espera 5 segundos
                            variable -= 5;
                        } catch (InterruptedException e1) {
                            // Manejo de excepciones
                        }
                    }
                }
            }

            public int getVariable() {
                return variable;
            }
        }

        public class Main {
            public static void main(String[] args) {
                MyThread hilo = new MyThread();
                hilo.start();

                try {
                    Thread.sleep(3000); // Espera 3 segundos
                    hilo.interrupt(); // Interrumpe el hilo
                } catch (InterruptedException e) {
                    // Manejo de excepciones
                }

                try {
                    hilo.join(); // Espera a que el hilo termine
                } catch (InterruptedException e) {
                    // Manejo de excepciones
                }

                System.out.println("Variable: " + hilo.getVariable()); // Imprime el valor de la variable
            }
        }

Más o menos creo que no va a ser tan difícil, pero no he tenido mucho tiempo para dejar el código preparado
*/