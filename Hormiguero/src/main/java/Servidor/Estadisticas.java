/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servidor;

import java.util.ArrayList;

/**
 *
 * @author Ivanl
 */
public class Estadisticas {
    private InterfazServidor interfazServidor;
    private Boolean interrumpirInsecto = false;
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
    private final Object bloqueoBuscandoComida = new Object();
    private final Object bloqueoDefendiendo = new Object();
    private final Object bloqueoAlmacen = new Object();
    private final Object bloqueoLlevandoComida = new Object();
    private final Object bloqueoInstruyendo = new Object();
    private final Object bloqueoDescansando = new Object();
    private final Object bloqueoZonaComer = new Object();
    private final Object bloqueoRefugio = new Object();
    private final Object bloqueoComidaAlmacen = new Object();
    private final Object bloqueoComidaZonaComer = new Object();
    private int comidaAlmacen;
    private int comidaZonaComer;
    private int obrerasExterior;
    private int obrerasInterior;
    private int soldadosInstruccion;
    private int soldadosDefendiendo;
    private int criasZonaComer;
    private int criasRefugio;
    private final Object bloqueoObrerasExterior = new Object();
    private final Object bloqueoObrerasInterior = new Object();
    private final Object bloqueoSoldadosInstruccion = new Object();
    private final Object bloqueoSoldadosDefendiendo = new Object();
    private final Object bloqueoCriasZonaComer = new Object();
    private final Object bloqueoCriasRefugio = new Object();
    
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
        interfazServidor.setTextoBuscandoComida(texto);
    }
    
    public synchronized void actualizarDefendiendo(){
        String texto = "";
        int numHormigasDefendiendo = 0;
        if (!listaBuscandoComida.isEmpty()){
            for(char[] arreglo : listaDefendiendo){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    if (numHormigasDefendiendo%6 == 0){
                        texto = texto + ",\n" + new String(arreglo);
                    }
                    else{
                        texto = texto + ", " + new String(arreglo);
                    }
                }
                numHormigasDefendiendo++;
            }
        }
        interfazServidor.setTextoDefendiendo(texto);
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
        interfazServidor.setTextoAlmacen(texto);
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
        interfazServidor.setTextoLlevandoComida(texto);
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
        interfazServidor.setTextoInstruyendo(texto);
    }
    
    public synchronized void actualizarDescansando(){
        String texto = "";
        if (!listaDescansando.isEmpty()){
            for(char[] arreglo : listaDescansando){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfazServidor.setTextoDescansando(texto);
    }
    
    public synchronized void actualizarZonaComer(){
        String texto = "";
        if (!listaZonaComer.isEmpty()){
            for(char[] arreglo : listaZonaComer){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfazServidor.setTextoZonaComer(texto);
    }
    
    public synchronized void actualizarRefugio(){
        String texto = "";
        if (!listaRefugio.isEmpty()){
            for(char[] arreglo : listaRefugio){
                if (texto == ""){
                    texto = new String(arreglo);
                }
                else{
                    texto = texto + ", " + new String(arreglo);
                }
            }
        }
        interfazServidor.setTextoRefugiadas(texto);
    }
    
    public void activarBotonInsecto(){
        interfazServidor.getGenerarInsecto().setEnabled(true);
    }
    
    public void desactivarBotonInsecto(){
        interfazServidor.getGenerarInsecto().setEnabled(false);
    }
    
    public synchronized void actualizarComidaAlmacen(){
        interfazServidor.setTextoComidaAlmacen(String.valueOf(comidaAlmacen));
    }
    
    public synchronized void actualizarComidaZonaComer(){
        interfazServidor.setTextoComidaZonaComer(String.valueOf(comidaZonaComer));
    }

    public void setInterrumpirInsecto(Boolean interrumpirInsecto) {
        this.interrumpirInsecto = interrumpirInsecto;
    }

    public Boolean getInterrumpirInsecto() {
        return interrumpirInsecto;
    }
    
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

    public void setInterfazServidor(InterfazServidor interfazServidor) {
        this.interfazServidor = interfazServidor;
    }

    public InterfazServidor getInterfazServidor() {
        return interfazServidor;
    }

    public Object getBloqueoBuscandoComida() {
        return bloqueoBuscandoComida;
    }

    public Object getBloqueoDefendiendo() {
        return bloqueoDefendiendo;
    }

    public Object getBloqueoAlmacen() {
        return bloqueoAlmacen;
    }

    public Object getBloqueoLlevandoComida() {
        return bloqueoLlevandoComida;
    }

    public Object getBloqueoInstruyendo() {
        return bloqueoInstruyendo;
    }

    public Object getBloqueoDescansando() {
        return bloqueoDescansando;
    }

    public Object getBloqueoZonaComer() {
        return bloqueoZonaComer;
    }

    public Object getBloqueoRefugio() {
        return bloqueoRefugio;
    }

    public Object getBloqueoComidaAlmacen() {
        return bloqueoComidaAlmacen;
    }

    public Object getBloqueoComidaZonaComer() {
        return bloqueoComidaZonaComer;
    }

    public int getObrerasExterior() {
        return obrerasExterior;
    }

    public int getObrerasInterior() {
        return obrerasInterior;
    }

    public int getSoldadosInstruccion() {
        return soldadosInstruccion;
    }

    public int getSoldadosDefendiendo() {
        return soldadosDefendiendo;
    }

    public int getCriasZonaComer() {
        return criasZonaComer;
    }

    public int getCriasRefugio() {
        return criasRefugio;
    }

    public void setObrerasExterior(int obrerasExterior) {
        this.obrerasExterior = obrerasExterior;
    }

    public void setObrerasInterior(int obrerasInterior) {
        this.obrerasInterior = obrerasInterior;
    }

    public void setSoldadosInstruccion(int soldadosInstruccion) {
        this.soldadosInstruccion = soldadosInstruccion;
    }

    public void setSoldadosDefendiendo(int soldadosDefendiendo) {
        this.soldadosDefendiendo = soldadosDefendiendo;
    }

    public void setCriasZonaComer(int criasZonaComer) {
        this.criasZonaComer = criasZonaComer;
    }

    public void setCriasRefugio(int criasRefugio) {
        this.criasRefugio = criasRefugio;
    }

    public Object getBloqueoObrerasExterior() {
        return bloqueoObrerasExterior;
    }

    public Object getBloqueoObrerasInterior() {
        return bloqueoObrerasInterior;
    }

    public Object getBloqueoSoldadosInstruccion() {
        return bloqueoSoldadosInstruccion;
    }

    public Object getBloqueoSoldadosDefendiendo() {
        return bloqueoSoldadosDefendiendo;
    }

    public Object getBloqueoCriasZonaComer() {
        return bloqueoCriasZonaComer;
    }

    public Object getBloqueoCriasRefugio() {
        return bloqueoCriasRefugio;
    }
}
