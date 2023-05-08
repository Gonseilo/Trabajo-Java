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
        InterfazCliente interfazCliente = new InterfazCliente();
        interfazCliente.setVisible(true);
        int n = 0;
        try {
            cliente = new Socket(InetAddress.getLocalHost(), 5000);
            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());
            salida.writeUTF("Hola soy el cliente");
            while(n<=1){
                interfazCliente.setTextoObrerasExterior(entrada.readUTF());
                interfazCliente.setTextoObrerasInterior(entrada.readUTF());
                interfazCliente.setTextoSoldadosInstruccion(entrada.readUTF());
                interfazCliente.setTextoSoldadosDefendiendo(entrada.readUTF());
                interfazCliente.setTextoCriasZonaComer(entrada.readUTF());
                interfazCliente.setTextoCriasRefugio(entrada.readUTF());
                salida = new DataOutputStream(cliente.getOutputStream());
                salida.writeUTF(String.valueOf(interfazCliente.getInsecto()));
                interfazCliente.setInsecto(0);
                interfazCliente.getGenerarInsecto().setEnabled(!Boolean.parseBoolean(entrada.readUTF()));
            }
            entrada.close();
            salida.close();
            cliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
