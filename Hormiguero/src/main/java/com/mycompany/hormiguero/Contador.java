/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hormiguero;

import java.util.ArrayList;

/**
 *
 * @author Ivanl
 */
public class Contador {
    private Interfaz interfaz;
    private Boolean play = true;
    private final Object bloqueoPausa = new Object();
    private int numHormigas;
    private int numCrias;
    private int numSoldados;
    private int numObreras;
    private Thread[] listaHormigas = new Thread[10000];
    private Thread[] listaCrias = new Thread[2000];
    private Thread[] listaSoldados = new Thread[2000];
    private ArrayList<char[]> listaBuscandoComida = new ArrayList<>();
    private ArrayList<char[]> listaDefendiendo = new ArrayList<>();
    private ArrayList<char[]> listaAlmacen = new ArrayList<>();
    private ArrayList<char[]> listaLlevandoComida = new ArrayList<>();
    private ArrayList<char[]> listaInstruyendo = new ArrayList<>();
    private ArrayList<char[]> listaDescansando = new ArrayList<>();
    private ArrayList<char[]> listaZonaComer = new ArrayList<>();
    private ArrayList<char[]> listaRefugio = new ArrayList<>();
    private int comidaAlmacen;
    private int comidaZonaComer;
    
    public synchronized void actualizarBuscandoComida(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaBuscandoComida){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoBuscandoComida(texto);
    }
    
    public synchronized void actualizarDefendiendo(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaDefendiendo){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoDefendiendo(texto);
    }
    
    public synchronized void actualizarAlmacen(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaAlmacen){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoAlmacen(texto);
    }
    
    public synchronized void actualizarLlevandoComida(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaLlevandoComida){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoLlevandoComida(texto);
    }
    
    public synchronized void actualizarInstruyendo(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaInstruyendo){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoInstruyendo(texto);
    }
    /*
    public synchronized void actualizarBuscandoComida(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaBuscandoComida){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoBuscandoComida(texto);
    }
    
    public synchronized void actualizarBuscandoComida(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaBuscandoComida){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoBuscandoComida(texto);
    }
    
    public synchronized void actualizarBuscandoComida(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaBuscandoComida){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoBuscandoComida(texto);
    }
    
    public synchronized void actualizarBuscandoComida(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaBuscandoComida){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoBuscandoComida(texto);
    }
    
    public synchronized void actualizarBuscandoComida(){
        String texto = "";
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaBuscandoComida){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfaz.setTextoBuscandoComida(texto);
    }
*/
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

    public Thread[] getListaHormigas() {
        return listaHormigas;
    }

    public void setListaHormigas(Thread[] listaHormigas) {
        this.listaHormigas = listaHormigas;
    }

    public Boolean getPlay() {
        return play;
    }

    public void setPlay(Boolean play) {
        this.play = play;
    }

    public Object getBloqueoPausa() {
        return bloqueoPausa;
    }

    public void setListaBuscandoComida(ArrayList<char[]> listaBuscandoComida) {
        this.listaBuscandoComida = listaBuscandoComida;
    }

    public void setListaDefendiendo(ArrayList<char[]> listaDefendiendo) {
        this.listaDefendiendo = listaDefendiendo;
    }

    public void setListaAlmacen(ArrayList<char[]> listaAlmacen) {
        this.listaAlmacen = listaAlmacen;
    }

    public void setListaLlevandoComida(ArrayList<char[]> listaLlevandoComida) {
        this.listaLlevandoComida = listaLlevandoComida;
    }

    public void setListaInstruyendo(ArrayList<char[]> listaInstruyendo) {
        this.listaInstruyendo = listaInstruyendo;
    }

    public void setListaDescansando(ArrayList<char[]> listaDescansando) {
        this.listaDescansando = listaDescansando;
    }

    public void setListaZonaComer(ArrayList<char[]> listaZonaComer) {
        this.listaZonaComer = listaZonaComer;
    }

    public void setListaRefugio(ArrayList<char[]> listaRefugio) {
        this.listaRefugio = listaRefugio;
    }

    public void setComidaAlmacen(int comidaAlmacen) {
        this.comidaAlmacen = comidaAlmacen;
    }

    public void setComidaZonaComer(int comidaZonaComer) {
        this.comidaZonaComer = comidaZonaComer;
    }

    public synchronized ArrayList<char[]> getListaBuscandoComida() {
        return listaBuscandoComida;
    }

    public synchronized ArrayList<char[]> getListaDefendiendo() {
        return listaDefendiendo;
    }

    public synchronized ArrayList<char[]> getListaAlmacen() {
        return listaAlmacen;
    }

    public synchronized ArrayList<char[]> getListaLlevandoComida() {
        return listaLlevandoComida;
    }

    public synchronized ArrayList<char[]> getListaInstruyendo() {
        return listaInstruyendo;
    }

    public synchronized ArrayList<char[]> getListaDescansando() {
        return listaDescansando;
    }

    public synchronized ArrayList<char[]> getListaZonaComer() {
        return listaZonaComer;
    }

    public synchronized ArrayList<char[]> getListaRefugio() {
        return listaRefugio;
    }

    public int getComidaAlmacen() {
        return comidaAlmacen;
    }

    public int getComidaZonaComer() {
        return comidaZonaComer;
    }

    public void setInterfaz(Interfaz interfaz) {
        this.interfaz = interfaz;
    }

    public Interfaz getInterfaz() {
        return interfaz;
    }
}
