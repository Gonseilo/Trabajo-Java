/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.hormiguero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class Servidor implements Runnable{
    private static Estadisticas estadisticas;
    
    public Servidor(Estadisticas estadisticas){
        this.estadisticas = estadisticas;
    }

    /**
     * @param args the command line arguments
     */
    

    @Override
    public void run() {
        
        ServerSocket servidor;
        Socket conexion;
        DataOutputStream salida;
        DataInputStream entrada;
        int numConexion = 0;
        int n = 0;
        try {
            servidor = new ServerSocket(5000);
            System.out.println("Servidor arrancando...");
            while(true){
                conexion = servidor.accept();
                numConexion++;
                System.out.println("Conexión n." + numConexion + " desde: " + conexion.getInetAddress().getHostName());
                entrada = new DataInputStream(conexion.getInputStream());
                salida = new DataOutputStream(conexion.getOutputStream());
                String mensaje = entrada.readUTF();
                System.out.println("Conexión n." + numConexion + "\nMensaje: " + mensaje);
                while(n<1){
                                        salida.writeUTF(String.valueOf(estadisticas.getObrerasExterior()));
                    salida.writeUTF(String.valueOf(estadisticas.getObrerasInterior()));
                    salida.writeUTF(String.valueOf(estadisticas.getSoldadosInstruccion()));
                    salida.writeUTF(String.valueOf(estadisticas.getSoldadosDefendiendo()));
                    salida.writeUTF(String.valueOf(estadisticas.getCriasZonaComer()));
                    salida.writeUTF(String.valueOf(estadisticas.getCriasRefugio()));
                }
                entrada.close();
                salida.close();
                conexion.close();
            }
            //servidor.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
