/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

/**
 *
 * @author Ivanl
 */
public class Contador {
    private int numHormigas;
    private int numCrias;
    private int numSoldados;
    private int numObreras;
    private Thread[] listaSoldados = new Thread[2000];
    private Thread[] listaCrias = new Thread[2000];

    public int getNumHormigas() {
        return numHormigas;
    }

    public int getNumCrias() {
        return numCrias;
    }

    public int getNumSoldados() {
        return numSoldados;
    }

    public int getNumObreras() {
        return numObreras;
    }

    public void setNumHormigas(int numHormigas) {
        this.numHormigas = numHormigas;
    }

    public void setNumCrias(int numCrias) {
        this.numCrias = numCrias;
    }

    public void setNumSoldados(int numSoldados) {
        this.numSoldados = numSoldados;
    }

    public void setNumObreras(int numObreras) {
        this.numObreras = numObreras;
    }

    public Thread[] getListaSoldados() {
        return listaSoldados;
    }

    public void setListaSoldados(Thread[] soldado) {
        this.listaSoldados = listaSoldados;
    }

    public Thread[] getListaCrias() {
        return listaCrias;
    }

    public void setListaCrias(Thread[] listaCrias) {
        this.listaCrias = listaCrias;
    }
}
