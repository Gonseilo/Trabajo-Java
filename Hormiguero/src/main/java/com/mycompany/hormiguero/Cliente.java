/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.hormiguero;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivanl
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Socket cliente;
        DataInputStream entrada;
        DataOutputStream salida;
        String mensaje, respuesta;
        int n = 0;
        try {
            cliente = new Socket(InetAddress.getLocalHost(), 5000);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            while(n<=1){
                mensaje = "Iván";
                salida.writeUTF(mensaje);
                respuesta = entrada.readUTF();
                System.out.println("Mi mensaje: " + mensaje);
                System.out.println("Respuesta servidor: " + respuesta);
                respuesta = entrada.readUTF();
                System.out.println("Mi mensaje: " + mensaje);
                System.out.println("Respuesta servidor: " + respuesta);
            }
            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
